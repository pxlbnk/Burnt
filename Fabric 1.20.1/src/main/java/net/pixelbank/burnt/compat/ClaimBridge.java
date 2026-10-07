package net.pixelbank.burnt.compat;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.util.BurntProtection;

import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Keeps Burnt fire out of land claimed by a chunk-claim mod.
 *
 * <p>Asks one question only: is this position inside a claim? Each mod's own fire-spread option
 * means something different, and FTB's allows fire within a claim - useless for Burnt, which
 * spreads block by block so almost every step stays inside one chunk.
 *
 * <p>Governed by {@code burn_in_claimed_chunks} (default off). Supports FTB Chunks and Open
 * Parties and Claims by reflection; a missing signature disables that bridge rather than
 * breaking the burn chain.
 */
public final class ClaimBridge {

	// FTB Chunks
	private static Object ftbDisableProtection;      // BooleanValue
	private static Method ftbBooleanValueGet;        // BooleanValue#get -> Object (Boolean)
	private static Method ftbApi;                    // FTBChunksAPI#api
	private static Method ftbGetManager;             // API#getManager
	private static Method ftbGetChunk;               // ClaimedChunkManager#getChunk(ChunkDimPos)
	private static Constructor<?> ftbChunkDimPos;    // ChunkDimPos(Level, BlockPos)

	// Open Parties and Claims
	private static Method opacGet;                   // OpenPACServerAPI#get(MinecraftServer)
	private static Method opacClaimsManager;         // OpenPACServerAPI#getServerClaimsManager
	private static Method opacClaimAt;               // IClaimsManagerAPI#get(ResourceLocation, BlockPos)

	/** Called once from common setup. */
	public static void init() {
		// First-party fallback: works regardless of which claim mod (if any) the server runs.
		BurntProtection.register(net.pixelbank.burnt.util.BurntNoBurnRegions::blocks);

		if (FabricLoader.getInstance().isModLoaded("ftbchunks")) {
			try {
				Class<?> worldConfig = Class.forName("dev.ftb.mods.ftbchunks.FTBChunksWorldConfig");
				ftbDisableProtection = worldConfig.getField("DISABLE_PROTECTION").get(null);
				ftbBooleanValueGet = ftbDisableProtection.getClass().getMethod("get");
				Class<?> chunkDimPos = Class.forName("dev.ftb.mods.ftblibrary.math.ChunkDimPos");
				ftbChunkDimPos = chunkDimPos.getConstructor(Level.class, BlockPos.class);
				ftbApi = Class.forName("dev.ftb.mods.ftbchunks.api.FTBChunksAPI").getMethod("api");
				ftbGetManager = Class.forName("dev.ftb.mods.ftbchunks.api.FTBChunksAPI$API").getMethod("getManager");
				ftbGetChunk = Class.forName("dev.ftb.mods.ftbchunks.api.ClaimedChunkManager")
						.getMethod("getChunk", chunkDimPos);
				BurntProtection.register(ClaimBridge::ftbBlocks);
				BurntMod.LOGGER.info("[Burnt] FTB Chunks detected - claimed chunks are closed to Burnt fire.");
			} catch (Throwable t) {
				ftbGetChunk = null;
				BurntMod.LOGGER.warn("[Burnt] FTB Chunks present but its claim API was not found; "
						+ "claimed chunks will NOT be protected from Burnt fire.", t);
			}
		}

		if (FabricLoader.getInstance().isModLoaded("openpartiesandclaims")) {
			try {
				Class<?> serverApi = Class.forName("xaero.pac.common.server.api.OpenPACServerAPI");
				opacGet = serverApi.getMethod("get", net.minecraft.server.MinecraftServer.class);
				opacClaimsManager = serverApi.getMethod("getServerClaimsManager");
				opacClaimAt = Class.forName("xaero.pac.common.claims.api.IClaimsManagerAPI")
						.getMethod("get", net.minecraft.resources.ResourceLocation.class, BlockPos.class);
				BurntProtection.register(ClaimBridge::opacBlocks);
				BurntMod.LOGGER.info("[Burnt] Open Parties and Claims detected - claimed chunks are closed to Burnt fire.");
			} catch (Throwable t) {
				opacClaimAt = null;
				BurntMod.LOGGER.warn("[Burnt] Open Parties and Claims present but its claim API was not found; "
						+ "claimed chunks will NOT be protected from Burnt fire.", t);
			}
		}
	}

	/** Server owners who want the old behaviour back. */
	private static boolean allowedByConfig() {
		return BurntBasicConfigConfiguration.BURN_IN_CLAIMED_CHUNKS.get();
	}

	private static boolean ftbBlocks(LevelAccessor level, BlockPos from, BlockPos to) {
		if (ftbGetChunk == null || allowedByConfig() || !(level instanceof Level)) return false;
		try {
			// An admin who switched FTB protection off entirely meant it - do not override that.
			if ((boolean) (Boolean) ftbBooleanValueGet.invoke(ftbDisableProtection)) return false;
			Object api = ftbApi.invoke(null);
			if (api == null) return false;
			Object manager = ftbGetManager.invoke(api);
			if (manager == null) return false;
			return ftbGetChunk.invoke(manager, ftbChunkDimPos.newInstance((Level) level, to)) != null;
		} catch (Throwable t) {
			ftbGetChunk = null;
			BurntMod.LOGGER.error("[Burnt] FTB Chunks claim lookup failed - bridge disabled.", t);
			return false;
		}
	}

	private static boolean opacBlocks(LevelAccessor level, BlockPos from, BlockPos to) {
		if (opacClaimAt == null || allowedByConfig() || !(level instanceof ServerLevel)) return false;
		ServerLevel server = (ServerLevel) level;
		try {
			Object api = opacGet.invoke(null, server.getServer());
			if (api == null) return false;
			Object manager = opacClaimsManager.invoke(api);
			if (manager == null) return false;
			return opacClaimAt.invoke(manager, server.dimension().location(), to) != null;
		} catch (Throwable t) {
			opacClaimAt = null;
			BurntMod.LOGGER.error("[Burnt] Open Parties and Claims claim lookup failed - bridge disabled.", t);
			return false;
		}
	}

	private ClaimBridge() {}
}
