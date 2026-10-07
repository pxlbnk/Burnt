package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.pixelbank.burnt.util.BurntBlockUtil;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;

public class SmallLogsTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.SMALL_BURNT_LOG.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				} else {
					if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip21 ? blockstate.getValue(_getip21) : -1) == 1) {
						SmallLogsStage1Procedure.execute(world, x, y, z);
					} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip23 ? blockstate.getValue(_getip23) : -1) == 2) {
						SmallLogsStage2Procedure.execute(world, x, y, z);
					} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip25 ? blockstate.getValue(_getip25) : -1) == 3) {
						SmallLogsStage3Procedure.execute(world, x, y, z);
					}
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMALL_BURNT_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}