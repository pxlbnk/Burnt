package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.pixelbank.burnt.util.BurntBlockUtil;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;

public class BurntGrassTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) >= 2) {
				if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
					if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
						world.setBlock(BlockPos.containing(x, y, z),
								(BurntModBlocks.BURNT_GRASS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp22
										? BurntModBlocks.BURNT_GRASS.defaultBlockState().setValue(_withbp22, true)
										: BurntModBlocks.BURNT_GRASS.defaultBlockState()),
								3);
					} else {
						if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip25 ? blockstate.getValue(_getip25) : -1) == 2) {
							// Grass under a solid block can't spread, so skip stage 2 and char in place
							if (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude()) {
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _skipProp
										&& _skipProp.getPossibleValues().contains(3))
									world.setBlock(_pos, _bs.setValue(_skipProp, 3), 3);
							} else {
								BurntGrassStage2Procedure.execute(world, x, y, z);
							}
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip27 ? blockstate.getValue(_getip27) : -1) == 3) {
							BurntGrassStage3Procedure.execute(world, x, y, z);
						}
					}
				} else {
					world.setBlock(BlockPos.containing(x, y, z),
							(BurntModBlocks.BURNT_GRASS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp28
									? BurntModBlocks.BURNT_GRASS.defaultBlockState().setValue(_withbp28, true)
									: BurntModBlocks.BURNT_GRASS.defaultBlockState()),
							3);
				}
			}
		}
	}
}