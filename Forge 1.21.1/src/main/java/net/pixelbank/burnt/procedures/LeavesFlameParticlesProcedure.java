package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

public class LeavesFlameParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (world.isClientSide()) {
			if (BurntBasicConfigConfiguration.FLAMES.get() == true) {
				if (Math.random() <= 0.1) {
					particleAmount = 2;
					particleRadius = 0.8;
					for (int index0 = 0; index0 < (int) particleAmount; index0++) {
						world.addParticle((SimpleParticleType) (BurntModParticleTypes.FLAMES), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (y + 0.6 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
								(z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (Mth.nextDouble(world.getRandom(), 0, 0)), (Mth.nextDouble(world.getRandom(), 0, 0.2)), (Mth.nextDouble(world.getRandom(), 0, 0)));
					}
				}
			}
		}
	}
}