package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

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
		if (biome.is(new ResourceLocation("dark_forest"))) {
			chance = chance - 25;
		}
		return Math.max(0, Math.min(100, chance));
	}
}