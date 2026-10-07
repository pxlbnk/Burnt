package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.ParticleTypes;

public class LogFlameParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (world.isClientSide()) {
			if (BurntBasicConfigConfiguration.FLAMES.get() == true) {
				if (Math.random() <= 0.3) {
					particleAmount = 3;
					particleRadius = 0.6;
					for (int index0 = 0; index0 < (int) particleAmount; index0++) {
						world.addParticle(ParticleTypes.FLAME, (x + 0.5 + Mth.nextDouble(world.getRandom(), -1.2, 1.2) * particleRadius), (y + 0.6 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
								(z + 0.5 + Mth.nextDouble(world.getRandom(), -1.2, 1.2) * particleRadius), (Mth.nextDouble(world.getRandom(), 0, 0)), (Mth.nextDouble(world.getRandom(), 0, 0.1)),
								(Mth.nextDouble(world.getRandom(), 0, 0)));
					}
				}
			}
		}
	}
}