package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BrokenWaterloggedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.FOREST_RECOVERY.get() == true) {
				if ((blockstate.getBlock().getStateDefinition().getProperty("fragile") instanceof BooleanProperty _getbp28 && blockstate.getValue(_getbp28)) == true) {
					if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_AIR)
							|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
						if (Math.random() <= 0.001) {
							world.destroyBlock(BlockPos.containing(x, y, z), false);
						}
					}
				}
			}
		}
	}
}
