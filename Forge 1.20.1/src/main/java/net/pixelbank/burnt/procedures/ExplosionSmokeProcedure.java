package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

public class ExplosionSmokeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
			particleAmount = Mth.nextDouble(world.getRandom(), 10, 20);
			particleRadius = 0;
			for (int index0 = 0; index0 < (int) particleAmount; index0++) {
				world.addParticle((SimpleParticleType) (BurntModParticleTypes.FLAME_SMOKE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -0.5, 0.5) * particleRadius),
						(y + 0.5 + Mth.nextDouble(world.getRandom(), -0.5, 0.5) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -0.5, 0.5) * particleRadius), 0, (Mth.nextDouble(world.getRandom(), 0.02, 0.1)), 0);
			}
		}
	}
}