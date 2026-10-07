package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.compat.SereneSeasonsBridge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.minecraft.resources.ResourceLocation;

public class BiomeChanceProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		double chance = 100;
		var biome = world.getBiome(BlockPos.containing(x, y, z));
		if (biome.is(BurntTags.BIOME_INCREASED_FIRE_BURNOUT)) {
			chance = chance - 10;
		}
		if (biome.is(BurntTags.BIOME_IS_COLD)) {
			chance = chance - 10;
		}
		if (biome.is(BurntTags.BIOME_IS_WET)) {
			chance = chance - 20;
		}
		if (biome.is(BurntTags.BIOME_IS_SWAMP)) {
			chance = chance - 25;
		}
		if (biome.is(ResourceLocation.parse("dark_forest"))) {
			chance = chance - 25;
		}
		// Additive seasonal offset from Serene Seasons, no effect when it isn't installed
		if (SereneSeasonsBridge.isActive()) {
			chance = chance + SereneSeasonsBridge.getSpreadModifier(world, x, y, z);
		}
		return Math.max(0, Math.min(100, chance));
	}
}