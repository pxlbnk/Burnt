package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

public class ThickFogParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleAmount = 0;
		double particleRadius = 0;
		double xSpeed = 0;
		double multiplyer = 0;
		double zSpeed = 0;
		if (world.isClientSide()) {
			if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
				if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
					if (BurntBasicConfigConfiguration.WIND.get() == true) {
						xSpeed = WindAngleXProcedure.execute(world);
						zSpeed = WindAngleZProcedure.execute(world);
						multiplyer = ParticleWindMultiplyerProcedure.execute(world, x, y, z);
						particleAmount = Math.min(Mth.nextDouble(world.getRandom(), 0, 1) + BurntModVariables.MapVariables.get(world).windSpeed / 10.0, 2);
						particleRadius = 0.6;
						for (int index0 = 0; index0 < (int) particleAmount; index0++) {
							world.addParticle((SimpleParticleType) (BurntModParticleTypes.THICK_FOG), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
									(y + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), ((xSpeed / 10) * multiplyer), 0, ((zSpeed / 10) * multiplyer));
						}
					} else {
						particleAmount = Mth.nextDouble(world.getRandom(), 0, 2);
						particleRadius = 0.6;
						for (int index1 = 0; index1 < (int) particleAmount; index1++) {
							world.addParticle((SimpleParticleType) (BurntModParticleTypes.THICK_FOG), (x + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius),
									(y + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius), (Mth.nextDouble(world.getRandom(), -0.2, 0.2)), 0,
									(Mth.nextDouble(world.getRandom(), -0.2, 0.2)));
						}
					}
				}
			}
		}
	}
}
