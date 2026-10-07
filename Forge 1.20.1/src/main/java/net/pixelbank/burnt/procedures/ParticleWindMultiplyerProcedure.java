package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;


import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class ParticleWindMultiplyerProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		// Sheltered spots get no wind so particles don't drift underground
		// Uses the sky check, not the cave biome tag - a dug tunnel keeps its surface biome
		if (!world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
			return 0;
		}
		double biomeFactor = 0;
		double h = 0;
		double heightFactor = 0;
		double multi = 0;
		double yCoord = 0;
		double yMultiplyer = 0;
		biomeFactor = 1;
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_WINDY)) {
			biomeFactor = 2;
		} else if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_CALM)) {
			biomeFactor = 0.5;
		} else {
			if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_MC_IS_CAVE)) {
				biomeFactor = 0;
			}
		}
		h = y;
		if (h >= 64) {
			heightFactor = h / 100;
		} else {
			heightFactor = 0.5;
		}
		multi = biomeFactor * heightFactor;
		return Math.round(Math.pow(10, 2) * multi) / Math.pow(10, 2);
	}
}