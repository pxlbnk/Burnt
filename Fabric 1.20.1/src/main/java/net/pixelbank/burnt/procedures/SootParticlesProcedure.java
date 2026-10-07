package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModParticleTypes;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

public class SootParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (world.isClientSide()) {
			particleAmount = 10;
			particleRadius = 1;
			for (int index0 = 0; index0 < (int) particleAmount; index0++) {
				world.addParticle((SimpleParticleType) (BurntModParticleTypes.SOOT_PARTICLE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (y + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
						(z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (Mth.nextDouble(world.getRandom(), -0.01, 0.01)), (Mth.nextDouble(world.getRandom(), -0.05, 0.01)),
						(Mth.nextDouble(world.getRandom(), -0.01, 0.01)));
			}
		}
	}
}