package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModParticleTypes;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

public class BlastSmokeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (BurntModParticleTypes.FLAME_SMOKE), x, y, z, 5, 2, 1, 2, 0);
	}
}