package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.minecraft.resources.ResourceLocation;

public class BiomeSpecificGroundProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		double newSpread = 0;
		newSpread = (double) BurntBasicConfigConfiguration.GROUND_SPREAD.get();
		if (!(world instanceof Level _lvl1 && _lvl1.isDay())) {
			newSpread = newSpread - 10;
		}
		if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.SHORT_GRASS || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.TALL_GRASS) {
			newSpread = newSpread + 10;
		}
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
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_WET)) {
			newSpread = newSpread - 20;
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_IS_SWAMP)) {
			newSpread = newSpread - 20;
		}
		if (world.getBiome(BlockPos.containing(x, y, z)).is(ResourceLocation.parse("dark_forest"))) {
			newSpread = newSpread - 20;
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