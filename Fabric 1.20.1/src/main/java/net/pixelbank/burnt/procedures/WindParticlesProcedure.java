package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

import javax.annotation.Nullable;

public class WindParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Object event, LevelAccessor world, double x, double y, double z) {
		double xSpeed = 0;
		double zSpeed = 0;
		double dx = 0;
		double dz = 0;
		double baseX = 0;
		double baseY = 0;
		double segments = 0;
		double spacing = 0;
		double i = 0;
		double px = 0;
		double pz = 0;
		double baseZ = 0;
		double multiplyer = 0;
		double speed = 0;
		double particleRadius = 0;
		double xDir = 0;
		double rad = 0;
		double turb = 0;
		double zDir = 0;
		double tx = 0;
		double tz = 0;
		double faceX = 0;
		double faceY = 0;
		double faceZ = 0;
		if (world.isClientSide()) {
			if (BurntBasicConfigConfiguration.WIND.get() == true) {
				speed = BurntModVariables.MapVariables.get(world).windSpeed / 5;
				if (speed >= 5) {
					xSpeed = WindAngleXProcedure.execute(world);
					zSpeed = WindAngleZProcedure.execute(world);
					multiplyer = ParticleWindMultiplyerProcedure.execute(world, x, y, z);
					if (Math.random() <= speed / 100) {
						baseX = x + Mth.nextDouble(world.getRandom(), -1, 1) * 10;
						baseY = y + Mth.nextDouble(world.getRandom(), 8, 16);
						baseZ = z + Mth.nextDouble(world.getRandom(), -1, 1) * 10;
						segments = 10 * speed * multiplyer;
						spacing = 0.0001 * speed;
						i = 0;
						for (int index0 = 0; index0 < (int) segments; index0++) {
							px = baseX - xSpeed * i * spacing;
							pz = baseZ - zSpeed * i * spacing;
							world.addParticle((SimpleParticleType) (BurntModParticleTypes.AIR_PARTICLE), px, baseY, pz, (Math.round(Math.pow(10, 1) * (xSpeed / 10)) / Math.pow(10, 1) * multiplyer), 0,
									(Math.round(Math.pow(10, 1) * (zSpeed / 10)) / Math.pow(10, 1) * multiplyer));
							i = i + 1;
						}
					}
				}
			}
		}
	}
}