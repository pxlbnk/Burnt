package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

public class WoodParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		double xDir = 0;
		double rad = 0;
		double xSpeed = 0;
		double turb = 0;
		double zSpeed = 0;
		double zDir = 0;
		double multiplyer = 0;
		if (world.isClientSide()) {
			if (Math.random() <= 0.3) {
				if (BurntBasicConfigConfiguration.WIND.get() == true) {
					multiplyer = ParticleWindMultiplyerProcedure.execute(world, x, y, z);
					xSpeed = WindAngleXProcedure.execute(world) * multiplyer;
					zSpeed = WindAngleZProcedure.execute(world) * multiplyer;
					particleAmount = 2;
					particleRadius = 0.7;
					for (int index0 = 0; index0 < (int) particleAmount; index0++) {
						world.addParticle((SimpleParticleType) (BurntModParticleTypes.FIRE_PARTICLE), (x + 0 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
								(y + 0 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (z + 0 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (xSpeed / 10), (Mth.nextDouble(world.getRandom(), -0.001, 0.001)),
								(zSpeed / 10));
					}
				} else {
					particleAmount = 2;
					particleRadius = 0.7;
					for (int index1 = 0; index1 < (int) particleAmount; index1++) {
						world.addParticle((SimpleParticleType) (BurntModParticleTypes.FIRE_PARTICLE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
								(y + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (Mth.nextDouble(world.getRandom(), -0.001, 0.01)),
								(Mth.nextDouble(world.getRandom(), -0.001, 0.05)), (Mth.nextDouble(world.getRandom(), -0.001, 0.01)));
					}
				}
			}
		}
	}
}