package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;

public class WindAdjustedChanceProcedure {
	// baseChance, dx, dz passed directly - no longer written to MapVariables
	public static double execute(LevelAccessor world, double x, double y, double z, double baseChance, double dx, double dz) {
		double len = 0;
		double speed01 = 0;
		double influence01 = 0;
		double rad = 0;
		double wx = 0;
		double wz = 0;
		double nx = 0;
		double nz = 0;
		double dot = 0;
		double windMulti = 0;
		double effect = 0;
		double out = 0;
		double mult = 0;
		double fromX = 0;
		double fromZ = 0;
		double dotFrom = 0;
		double dotTo = 0;
		double upwind = 0;
		double downwind = 0;
		double expo = 0;
		double penalty = 0;
		double boost = 0;
		double t = 0;
		double t2 = 0;
		double t4 = 0;
		double t8 = 0;
		if (baseChance <= 0) {
			return 0;
		}
		if ((double) BurntBasicConfigConfiguration.WIND_INFLUENCE.get() <= 0) {
			return baseChance;
		}
		len = Math.sqrt(dx * dx + dz * dz);
		if (len <= 0.000001) {
			return baseChance;
		}
		nx = dx / len;
		nz = dz / len;
		rad = BurntModVariables.MapVariables.get(world).windAngle * 0.0174532925;
		fromX = Math.sin(rad);
		fromZ = 0 - Math.cos(rad);
		dotFrom = nx * fromX + nz * fromZ;
		dotTo = 0 - dotFrom;
		speed01 = BurntModVariables.MapVariables.get(world).windSpeed / 100;
		speed01 = Math.max(0, Math.min(1, speed01));
		influence01 = (double) BurntBasicConfigConfiguration.WIND_INFLUENCE.get() / 10;
		influence01 = Math.max(0, Math.min(1, influence01));
		windMulti = WindMultiplyerProcedure.execute(world, x, y, z);
		windMulti = Math.max(0, Math.min(2, windMulti));
		effect = speed01 * influence01 * windMulti;
		effect = Math.max(0, Math.min(1, effect));
		t = (dotTo + 1) / 2;
		t = Math.max(0, Math.min(1, t));
		t2 = t * t;
		t4 = t2 * t2;
		t8 = t4 * t4;
		penalty = t2 * (1 - effect) + t8 * effect;
		downwind = Math.max(0, dotTo);
		boost = 1 + 0.35 * effect * downwind;
		out = baseChance * penalty * boost;
		out = Math.max(0, Math.min(1, out));
		return out;
	}
}
