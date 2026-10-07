package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BlazingPlanksParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR) && !world.canSeeSkyFromBelowWater(BlockPos.containing(x, y + 1, z))) {
				if (BurntBasicConfigConfiguration.GROUND_SMOKE.get() == true) {
					ThickFogParticlesProcedure.execute(world, x, y, z);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
				if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
					PlankSmokeParticlesProcedure.execute(world, x, y, z, blockstate);
				}
			} else {
				WoodParticlesProcedure.execute(world, x, y, z);
			}
		}
	}
}