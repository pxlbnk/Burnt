package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;

public class BrokenBurntLogsParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) {
			if (BurntBasicConfigConfiguration.ASH.get() == true) {
				AshParticlesProcedure.execute(world, x, y, z);
			}
		}
	}
}