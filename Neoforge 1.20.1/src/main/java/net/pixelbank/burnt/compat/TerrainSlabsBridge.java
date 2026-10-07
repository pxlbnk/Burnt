package net.pixelbank.burnt.compat;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;

// Terrain Slabs (modid "terrain_slabs") - half-height soil blocks
// The soil slab chain ends by growing back into that mod's grass slab, so it has to come from here
// Returns null without the mod, which is why Burnt's own slabs stay out of the creative tab
// Resolved lazily - registries aren't populated when this class first loads

public final class TerrainSlabsBridge {

	private static final String MOD_ID = "terrain_slabs";

	private static boolean resolved = false;
	private static Block grassSlab;
	private static Block dirtSlab;
	private static Block mossSlab;

	private TerrainSlabsBridge() {}

	/** True once Terrain Slabs' blocks are actually in the registry. */
	public static boolean isActive() {
		resolve();
		return grassSlab != null;
	}

	/** The living grass slab a recovering slab grows back into, or null without the mod. */
	@Nullable
	public static Block grassSlab() {
		resolve();
		return grassSlab;
	}

	/** The plain dirt slab, or null without the mod. */
	@Nullable
	public static Block dirtSlab() {
		resolve();
		return dirtSlab;
	}

	/** The living moss slab, or null without the mod. */
	@Nullable
	public static Block mossSlab() {
		resolve();
		return mossSlab;
	}

	private static synchronized void resolve() {
		if (resolved) return;
		Block g = lookup("grass_slab");
		// Registries are empty very early; only latch once something is actually there.
		if (g == null) return;
		grassSlab = g;
		dirtSlab = lookup("dirt_slab");
		mossSlab = lookup("moss_slab");
		resolved = true;
	}

	@Nullable
	private static Block lookup(String path) {
		try {
			Block b = BuiltInRegistries.BLOCK.get(new ResourceLocation(MOD_ID, path));
			return (b == null || b == Blocks.AIR) ? null : b;
		} catch (Throwable ignored) {
			return null;
		}
	}
}
