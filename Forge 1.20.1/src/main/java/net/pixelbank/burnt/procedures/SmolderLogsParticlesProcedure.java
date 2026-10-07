package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SmolderLogsParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
				if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip4 ? blockstate.getValue(_getip4) : -1) >= 1) {
					if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
						PlankSmokeParticlesProcedure.execute(world, x, y, z, blockstate);
					}
				} else {
					WoodParticlesProcedure.execute(world, x, y, z);
				}
			} else {
				WoodParticlesProcedure.execute(world, x, y, z);
			}
		}
	}
}