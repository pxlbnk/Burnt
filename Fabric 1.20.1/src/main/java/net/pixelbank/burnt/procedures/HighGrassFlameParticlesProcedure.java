package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

public class HighGrassFlameParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (world.isClientSide()) {
			if (BurntBasicConfigConfiguration.FLAMES.get() == true) {
				if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(16 / 2d), e -> true).isEmpty()) {
					particleAmount = 1;
					particleRadius = 0.5;
					for (int index0 = 0; index0 < (int) particleAmount; index0++) {
						world.addParticle((SimpleParticleType) (BurntModParticleTypes.FLAMES), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (y + Mth.nextDouble(world.getRandom(), 0, 1.5) * particleRadius),
								(z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (Mth.nextDouble(world.getRandom(), 0, 0)), (Mth.nextDouble(world.getRandom(), 0, 0.2)), (Mth.nextDouble(world.getRandom(), 0, 0)));
						world.addParticle((SimpleParticleType) (BurntModParticleTypes.FIRE_PARTICLE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
								(y + Mth.nextDouble(world.getRandom(), 0, 1.5) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (Mth.nextDouble(world.getRandom(), -0.1, 0.1)),
								(Mth.nextDouble(world.getRandom(), 0.1, 0.5)), (Mth.nextDouble(world.getRandom(), -0.1, 0.1)));
					}
				}
			}
		}
	}
}