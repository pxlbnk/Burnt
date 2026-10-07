package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;

public class WindAngleZProcedure {
	public static double execute(LevelAccessor world) {
		double rad = 0;
		double zSpeed = 0;
		double turb = 0;
		double zDir = 0;
		rad = BurntModVariables.MapVariables.get(world).windAngle * 0.0174532925;
		zDir = Math.cos(rad);
		zSpeed = zDir * (BurntModVariables.MapVariables.get(world).windSpeed / 5);
		turb = 0.1 * (BurntModVariables.MapVariables.get(world).windSpeed / 5);
		zSpeed = zSpeed + turb * Mth.nextDouble(world.getRandom(), -1, 1);
		return zSpeed;
	}
}