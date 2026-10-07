package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;

public class FenceStartParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (world.isClientSide()) {
			if (BurntBasicConfigConfiguration.FLAMES.get() == true) {
				FenceFlameParticlesProcedure.execute(world, x, y, z, blockstate);
			}
		}
	}
}