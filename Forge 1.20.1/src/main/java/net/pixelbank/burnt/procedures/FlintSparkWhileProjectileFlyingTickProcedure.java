package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class FlintSparkWhileProjectileFlyingTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		if (!world.isClientSide()) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z, 1, 0.2, 0.2, 0.2, 0.2);
			BurntMod.queueServerWork(3, () -> {
				if (!immediatesourceentity.level().isClientSide())
					immediatesourceentity.discard();
			});
		}
	}
}