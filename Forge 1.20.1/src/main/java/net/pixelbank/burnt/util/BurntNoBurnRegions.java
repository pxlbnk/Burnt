package net.pixelbank.burnt.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.storage.LevelResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Server-owned no-burn boxes, saved with the world.
 * The fallback that works with any protection mod, or none: an admin draws a box and Burnt
 * refuses to ignite, convert or spread inside it. Stored as plain JSON rather than vanilla
 * SavedData, whose API changed shape between 1.20.1 and 1.21. Loaded lazily on first check.
 */
public final class BurntNoBurnRegions {

	/** An inclusive box in one dimension. */
	private record Region(String dimension, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
		boolean contains(String dim, int x, int y, int z) {
			return dimension.equals(dim)
					&& x >= minX && x <= maxX
					&& y >= minY && y <= maxY
					&& z >= minZ && z <= maxZ;
		}
	}

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final List<Region> REGIONS = new ArrayList<>();
	private static volatile boolean loaded = false;

	/** Registered once at startup; consulted on the burn hot path. */
	public static boolean blocks(LevelAccessor level, BlockPos from, BlockPos to) {
		if (!(level instanceof ServerLevel server)) return false;
		ensureLoaded(server.getServer());
		if (REGIONS.isEmpty()) return false;
		String dim = server.dimension().location().toString();
		for (Region r : REGIONS) {
			if (r.contains(dim, to.getX(), to.getY(), to.getZ())) return true;
		}
		return false;
	}

	// ---- commands -------------------------------------------------------------------

	/** @return the number of regions now defined. */
	public static int add(ServerLevel level, BlockPos a, BlockPos b) {
		ensureLoaded(level.getServer());
		REGIONS.add(new Region(level.dimension().location().toString(),
				Math.min(a.getX(), b.getX()), Math.min(a.getY(), b.getY()), Math.min(a.getZ(), b.getZ()),
				Math.max(a.getX(), b.getX()), Math.max(a.getY(), b.getY()), Math.max(a.getZ(), b.getZ())));
		save(level.getServer());
		return REGIONS.size();
	}

	/** Human-readable listing, one entry per line, numbered from 1. */
	public static List<String> list(MinecraftServer server) {
		ensureLoaded(server);
		List<String> out = new ArrayList<>();
		for (int i = 0; i < REGIONS.size(); i++) {
			Region r = REGIONS.get(i);
			out.add(String.format("%d: %s  (%d, %d, %d) -> (%d, %d, %d)",
					i + 1, r.dimension(), r.minX(), r.minY(), r.minZ(), r.maxX(), r.maxY(), r.maxZ()));
		}
		return out;
	}

	/** @return true if the 1-based index existed and was removed. */
	public static boolean remove(MinecraftServer server, int oneBasedIndex) {
		ensureLoaded(server);
		if (oneBasedIndex < 1 || oneBasedIndex > REGIONS.size()) return false;
		REGIONS.remove(oneBasedIndex - 1);
		save(server);
		return true;
	}

	/** @return how many were cleared. */
	public static int clear(MinecraftServer server) {
		ensureLoaded(server);
		int n = REGIONS.size();
		REGIONS.clear();
		save(server);
		return n;
	}

	public static int count(MinecraftServer server) {
		ensureLoaded(server);
		return REGIONS.size();
	}

	// ---- persistence ----------------------------------------------------------------

	private static Path file(MinecraftServer server) {
		return server.getWorldPath(LevelResource.ROOT).resolve("data").resolve("burnt_no_burn_regions.json");
	}

	private static void ensureLoaded(MinecraftServer server) {
		// Lock-free fast path - stopBurning() reaches this every burning block tick, only the load locks
		if (loaded || server == null)
			return;
		loadOnce(server);
	}

	private static synchronized void loadOnce(MinecraftServer server) {
		if (loaded) return;
		loaded = true;
		Path path = file(server);
		if (!Files.exists(path)) return;
		try {
			JsonArray arr = JsonParser.parseString(Files.readString(path)).getAsJsonArray();
			for (int i = 0; i < arr.size(); i++) {
				JsonObject o = arr.get(i).getAsJsonObject();
				REGIONS.add(new Region(o.get("dimension").getAsString(),
						o.get("minX").getAsInt(), o.get("minY").getAsInt(), o.get("minZ").getAsInt(),
						o.get("maxX").getAsInt(), o.get("maxY").getAsInt(), o.get("maxZ").getAsInt()));
			}
			BurntMod.LOGGER.info("[Burnt] Loaded {} no-burn region(s).", REGIONS.size());
		} catch (Exception e) {
			// A corrupt file must not stop the server or silently disable protection without a word.
			BurntMod.LOGGER.error("[Burnt] Could not read {} - no-burn regions are NOT active.", path, e);
		}
	}

	private static synchronized void save(MinecraftServer server) {
		if (server == null) return;
		Path path = file(server);
		try {
			Files.createDirectories(path.getParent());
			JsonArray arr = new JsonArray();
			for (Region r : REGIONS) {
				JsonObject o = new JsonObject();
				o.addProperty("dimension", r.dimension());
				o.addProperty("minX", r.minX()); o.addProperty("minY", r.minY()); o.addProperty("minZ", r.minZ());
				o.addProperty("maxX", r.maxX()); o.addProperty("maxY", r.maxY()); o.addProperty("maxZ", r.maxZ());
				arr.add(o);
			}
			Files.writeString(path, GSON.toJson(arr));
		} catch (Exception e) {
			BurntMod.LOGGER.error("[Burnt] Could not save no-burn regions to {}.", path, e);
		}
	}

	/** Called when a world unloads so the next world starts from its own file. */
	public static synchronized void reset() {
		REGIONS.clear();
		loaded = false;
	}

	private BurntNoBurnRegions() {}
}
