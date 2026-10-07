package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;

public class FireBarrelParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) {
			LightSmokeParticlesProcedure.execute(world, x, y, z);
		}
	}
}