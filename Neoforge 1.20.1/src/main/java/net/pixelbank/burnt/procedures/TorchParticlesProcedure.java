package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModParticleTypes;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;

public class TorchParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double particleAmount = 0;
		double particleRadius = 0;
		double tx = 0;
		double tz = 0;
		if (world.isClientSide()) {
			if (Math.random() <= 0.3) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.TORCH) {
					// Torch flames skip wind - the position is already offset by look direction, so wind on top looks wrong
					particleAmount = 2;
					particleRadius = 0.7;
					tx = entity.getLookAngle().x;
					tz = entity.getLookAngle().z;
					for (int index0 = 0; index0 < Mth.nextInt(world.getRandom(), 1, 3); index0++) {
						world.addParticle((SimpleParticleType) (BurntModParticleTypes.TORCH_FIRE_PARTICLE), (x - ((tz / Math.max(Math.sqrt(tx * tx + tz * tz), 0.001)) * 0.7 + (tx / Math.max(Math.sqrt(tx * tx + tz * tz), 0.001)) * 0.25)),
								(y + 1), (z + (tz / Math.max(Math.sqrt(tx * tx + tz * tz), 0.001)) * 0.7 + (tx / Math.max(Math.sqrt(tx * tx + tz * tz), 0.001)) * 0.25), 0, (Mth.nextDouble(world.getRandom(), 0.02, 0.06)), 0);
					}
				}
			}
		}
	}
}