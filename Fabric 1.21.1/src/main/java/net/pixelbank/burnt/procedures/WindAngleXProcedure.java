package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;

public class WindAngleXProcedure {
	public static double execute(LevelAccessor world) {
		double xSpeed = 0;
		double rad = 0;
		double xDir = 0;
		double turb = 0;
		rad = BurntModVariables.MapVariables.get(world).windAngle * 0.0174532925;
		xDir = Math.sin(rad * (-1));
		xSpeed = xDir * (BurntModVariables.MapVariables.get(world).windSpeed / 5);
		turb = 0.1 * (BurntModVariables.MapVariables.get(world).windSpeed / 5);
		xSpeed = xSpeed + turb * Mth.nextDouble(world.getRandom(), -1, 1);
		return xSpeed;
	}
}