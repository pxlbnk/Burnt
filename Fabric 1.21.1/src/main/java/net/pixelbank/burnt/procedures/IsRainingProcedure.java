package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.LevelAccessor;

public class IsRainingProcedure {

	// Fast global check, the same as isRaining()
	// Mirrors the Forge signature so cross-tree callers can share source; this tree has no
	// Weather2 or Simple Clouds bridges, so it just delegates to vanilla
	public static boolean isGloballyRaining(LevelAccessor world) {
		return world.getLevelData().isRaining();
	}

	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		// extinguishAll - treat everywhere as raining for 15 seconds
		if (BurntMod.forceRain) return true;

		BlockPos pos = BlockPos.containing(x, y, z);
		Holder<Biome> biome = world.getBiome(pos);

		// Must be globally raining (covers clear weather and thunder-only storms)
		if (!world.getLevelData().isRaining()) return false;

		if (!biome.is(BurntTags.BIOME_MC_IS_OVERWORLD)) return false;
		if (biome.is(BurntTags.BIOME_MC_IS_CAVE)) return false;

		// Only rain counts, not snow or dry biomes
		// In a try-catch - season mods can throw here for biomes they don't recognise
		try {
			if (biome.value().getPrecipitationAt(pos) != Biome.Precipitation.RAIN) return false;
		} catch (Exception e) {
			return false;
		}

		// Fire on top of leaves gets no shelter, fire inside a canopy checks two blocks up
		if (world.getBlockState(pos.below()).is(BurntTags.BLOCK_MC_LEAVES)) {
			// Fire on top of leaves - fully exposed, same as open ground.
			return world.canSeeSkyFromBelowWater(pos.above());
		} else if (world.getBlockState(pos).is(BurntTags.BLOCK_MC_LEAVES)) {
			// Fire at leaf level (e.g. inside canopy) - slight shelter, check two up.
			return world.canSeeSkyFromBelowWater(pos.above().above());
		} else {
			return world.canSeeSkyFromBelowWater(pos.above());
		}
	}
}
