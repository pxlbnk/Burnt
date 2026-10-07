package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.compat.SimpleCloudsBridge;
import net.pixelbank.burnt.compat.Weather2Bridge;
import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.LevelAccessor;

public class IsRainingProcedure {

	// Fast global check - like isRaining(), but also true for a Weather2 storm or Simple Clouds rain
	// Use it anywhere vanilla would gate on isRaining() before a per-position check
	public static boolean isGloballyRaining(LevelAccessor world) {
		if (world.getLevelData().isRaining()) return true;
		if (Weather2Bridge.isActive() && Weather2Bridge.isAnyPrecipitationActive(world)) return true;
		return SimpleCloudsBridge.isActive() && SimpleCloudsBridge.isAnyPrecipitationActive(world);
	}

	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		// extinguishAll - treat everywhere as raining for 15 seconds
		if (BurntMod.forceRain) return true;

		BlockPos pos = BlockPos.containing(x, y, z);
		Holder<Biome> biome = world.getBiome(pos);

		// Must be globally raining, unless Weather2 or Simple Clouds is precipitating right here
		boolean vanillaRaining = world.getLevelData().isRaining();
		boolean weather2Raining = Weather2Bridge.isActive() && Weather2Bridge.isPrecipitatingAt(world, pos);
		// Gate the per-position Simple Clouds check behind the global one
		// Ambient cloud types report a tiny rain level in clear weather and would extinguish fire
		boolean scRaining = SimpleCloudsBridge.isActive()
				&& SimpleCloudsBridge.isAnyPrecipitationActive(world)
				&& SimpleCloudsBridge.isPrecipitatingAt(world, pos);
		boolean tfcRaining = net.pixelbank.burnt.compat.TFCBridge.isActive() && net.pixelbank.burnt.compat.TFCBridge.isRainingAt(world, pos);
		// TFC owns the weather model when loaded, so its climate is authoritative here
		// The vanilla flag is only TFC's global switch, and its BiomeMixin reports RAIN regardless of
		// local climate - trusting it made Burnt think it was raining everywhere with sky access
		if (net.pixelbank.burnt.compat.TFCBridge.isActive() && !tfcRaining && !weather2Raining && !scRaining)
			return false;
		if (!vanillaRaining && !weather2Raining && !scRaining && !tfcRaining) return false;

		if (!biome.is(BurntTags.BIOME_MC_IS_OVERWORLD)) return false;
		if (biome.is(BurntTags.BIOME_MC_IS_CAVE)) return false;

		// Only rain counts, not snow or dry biomes - unless Weather2 or Simple Clouds is driving it
		// In a try-catch: season mods can throw here for biomes they don't recognise
		if (!weather2Raining && !scRaining && !tfcRaining) {
			try {
				if (biome.value().getPrecipitationAt(pos) != Biome.Precipitation.RAIN) return false;
			} catch (Exception e) {
				return false;
			}
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
