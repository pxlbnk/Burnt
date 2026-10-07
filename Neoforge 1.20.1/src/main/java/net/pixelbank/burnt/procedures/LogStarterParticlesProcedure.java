package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;

public class LogStarterParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) {
			if (BurntBasicConfigConfiguration.FLAMES.get() == true) {
				LogFlameParticlesProcedure.execute(world, x, y, z);
			}
		}
	}
}