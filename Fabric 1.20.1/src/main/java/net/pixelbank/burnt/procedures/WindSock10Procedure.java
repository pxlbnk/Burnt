package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.level.LevelAccessor;

public class WindSock10Procedure {
	public static boolean execute(LevelAccessor world) {
		if (BurntModVariables.MapVariables.get(world).windSpeed >= 4) {
			return true;
		}
		return false;
	}
}