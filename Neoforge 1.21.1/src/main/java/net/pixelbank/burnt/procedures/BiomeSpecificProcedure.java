package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.compat.SereneSeasonsBridge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BiomeSpecificProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		double newSpread = 0;
		newSpread = (double) BurntBasicConfigConfiguration.BURN_SPREAD.get();
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_INCREASED_FIRE_BURNOUT)) {
			newSpread = newSpread - 10;
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_COLD)) {
			newSpread = newSpread - 10;
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_DENSE)) {
			newSpread = newSpread + 10;
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_DRY)) {
			newSpread = newSpread + 20;
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_HOT)) {
			newSpread = newSpread + 20;
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_SPARSE)) {
			newSpread = newSpread - 10;
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_WET)) {
			newSpread = newSpread - 20;
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_SWAMP)) {
			newSpread = newSpread - 20;
		}
		// Additive seasonal offset from Serene Seasons, no effect when it isn't installed
		if (SereneSeasonsBridge.isActive()) {
			newSpread = newSpread + SereneSeasonsBridge.getSpreadModifier(world, x, y, z);
		}
		if (newSpread <= 100 && newSpread >= 0) {
			return newSpread;
		} else if (newSpread > 100) {
			return 100;
		} else if (newSpread < 0) {
			return 0;
		}
		return 50;
	}
}