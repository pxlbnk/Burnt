package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;

public class CoalParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) {
			WoodParticlesProcedure.execute(world, x, y, z);
			if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
				LightSmokeParticlesProcedure.execute(world, x, y + 1, z);
			}
		}
	}
}