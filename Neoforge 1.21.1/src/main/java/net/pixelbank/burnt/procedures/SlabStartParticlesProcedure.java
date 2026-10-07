package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;

public class SlabStartParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) {
			WoodParticlesProcedure.execute(world, x, y, z);
		}
	}
}