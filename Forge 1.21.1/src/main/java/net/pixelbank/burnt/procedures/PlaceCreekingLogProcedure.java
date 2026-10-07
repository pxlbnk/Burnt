package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class PlaceCreekingLogProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = BurntModBlocks.CREEKING_BURNT_LOG.defaultBlockState();
				BlockState _bso = world.getBlockState(_bp);
				_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
				world.setBlock(_bp, _bs, 3);
			}
		}
	}
}