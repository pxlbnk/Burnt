package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SmolderingPlanksParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) >= 1
					|| (blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp4 && blockstate.getValue(_getbp4)) == true) {
				if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR) && !world.canSeeSkyFromBelowWater(BlockPos.containing(x, y + 1, z))) {
					if (BurntBasicConfigConfiguration.GROUND_SMOKE.get() == true) {
						ThickFogParticlesProcedure.execute(world, x, y, z);
					}
				} else if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
					PlankSmokeParticlesProcedure.execute(world, x, y, z, blockstate);
				} else {
					WoodParticlesProcedure.execute(world, x, y, z);
				}
			} else {
				WoodParticlesProcedure.execute(world, x, y, z);
			}
		}
	}
}