package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SmolderingSlabParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (world.isClientSide()) {
			if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
				if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
					PlankSmokeParticlesProcedure.execute(world, x, y, z, blockstate);
				}
			} else {
				WoodParticlesProcedure.execute(world, x, y, z);
			}
		}
	}
}