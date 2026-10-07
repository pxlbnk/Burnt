package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModParticleTypes;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

public class LeavesExplodeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		particleAmount = Mth.nextDouble(world.getRandom(), 2, 4);
		particleRadius = 1;
		for (int index0 = 0; index0 < (int) particleAmount; index0++) {
			world.addParticle((SimpleParticleType) (BurntModParticleTypes.BURNT_LEAVES_PARTICLE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
					(y + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (Mth.nextDouble(world.getRandom(), -0.1, 0.1)),
					(Mth.nextDouble(world.getRandom(), 0, 0.1)), (Mth.nextDouble(world.getRandom(), -0.1, -0.1)));
		}
		for (int index1 = 0; index1 < (int) particleAmount; index1++) {
			world.addParticle((SimpleParticleType) (BurntModParticleTypes.BURNT_LEAVES_PARTICLE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
					(y + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (Mth.nextDouble(world.getRandom(), -0.1, 0.1)),
					(Mth.nextDouble(world.getRandom(), 0, 0.1)), (Mth.nextDouble(world.getRandom(), -0.1, -0.1)));
		}
		for (int index2 = 0; index2 < (int) particleAmount; index2++) {
			world.addParticle((SimpleParticleType) (BurntModParticleTypes.SMOLDER_LEAVES_PARTICLE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
					(y + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (Mth.nextDouble(world.getRandom(), -0.1, 0.1)),
					(Mth.nextDouble(world.getRandom(), 0, 0.1)), (Mth.nextDouble(world.getRandom(), -0.1, -0.1)));
		}
		for (int index3 = 0; index3 < (int) particleAmount; index3++) {
			world.addParticle((SimpleParticleType) (BurntModParticleTypes.SMOLDER_LEAVES_PARTICLE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
					(y + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (Mth.nextDouble(world.getRandom(), -0.1, 0.1)),
					(Mth.nextDouble(world.getRandom(), 0, 0.1)), (Mth.nextDouble(world.getRandom(), -0.1, -0.1)));
		}
	}
}