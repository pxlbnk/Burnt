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

public class BurntGrassSlabTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) >= 1) {
				if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
					if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
						world.setBlock(BlockPos.containing(x, y, z),
								(BurntModBlocks.BURNT_GRASS_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp22
										? net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()).setValue(_withbp22, true)
										: net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState())),
								3);
					} else {
						if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getipSlab1 ? blockstate.getValue(_getipSlab1) : -1) == 1) {
							// No slab version of SMOLDERING_GRASS_START, so stage 1 does that work here
							BurntGrassSlabStage1Procedure.execute(world, x, y, z);
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip25 ? blockstate.getValue(_getip25) : -1) == 2) {
							// Grass under a solid block can't spread, so skip stage 2 and char in place
							if (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude()) {
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _skipProp
										&& _skipProp.getPossibleValues().contains(3))
									world.setBlock(_pos, _bs.setValue(_skipProp, 3), 3);
							} else {
								BurntGrassSlabStage2Procedure.execute(world, x, y, z);
							}
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip27 ? blockstate.getValue(_getip27) : -1) == 3) {
							BurntGrassSlabStage3Procedure.execute(world, x, y, z);
						}
					}
				} else {
					world.setBlock(BlockPos.containing(x, y, z),
							(BurntModBlocks.BURNT_GRASS_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp28
									? net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()).setValue(_withbp28, true)
									: net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState())),
							3);
				}
			}
		}
	}
}