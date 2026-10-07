package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.compat.Weather2Bridge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

public class LightSmokeParticlesProcedure {
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
		double yCoord = 0;
		double yMultiplyer = 0;
		if (world.isClientSide()) {
			if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
				if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
					if (BurntBasicConfigConfiguration.WIND.get() == true) {
						xSpeed = WindAngleXProcedure.execute(world);
						zSpeed = WindAngleZProcedure.execute(world);
						multiplyer = ParticleWindMultiplyerProcedure.execute(world, x, y, z);
						particleAmount = Mth.nextDouble(world.getRandom(), 0, 2) + BurntModVariables.MapVariables.get(world).windSpeed / 10;
						particleAmount *= net.pixelbank.burnt.util.BurntSmokeBudget.densityFactor();
						particleRadius = 0.6;
						for (int index0 = 0; index0 < (int) particleAmount; index0++) {
							if (!net.pixelbank.burnt.util.BurntSmokeBudget.allowSpawn(world.getRandom())) break;
							world.addParticle((SimpleParticleType) (BurntModParticleTypes.LIGHT_FLAME_SMOKE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
									(y + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), ((xSpeed / 10) * multiplyer),
									(Mth.nextDouble(world.getRandom(), -0.001, 0.001)), ((zSpeed / 10) * multiplyer));
						}
					} else {
						particleAmount = Mth.nextDouble(world.getRandom(), 0, 4);
						particleAmount *= net.pixelbank.burnt.util.BurntSmokeBudget.densityFactor();
						particleRadius = 0.6;
						for (int index1 = 0; index1 < (int) particleAmount; index1++) {
							if (!net.pixelbank.burnt.util.BurntSmokeBudget.allowSpawn(world.getRandom())) break;
							world.addParticle((SimpleParticleType) (BurntModParticleTypes.LIGHT_FLAME_SMOKE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius),
									(y + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius), 0, (Mth.nextDouble(world.getRandom(), 0.02, 0.1)),
									0);
						}
					}
				}
			}
		}
	}
}