package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BlastLeavesTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (Math.random() <= 0.1) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					if (_bs.hasProperty(BlockStateProperties.WATERLOGGED))
						_bs = _bs.setValue(BlockStateProperties.WATERLOGGED, false);
					world.setBlock(_bp, _bs, 3);
				}
				WillitburnProcedure.execute(world, x, y, z);
			} else if (Math.random() <= 0.3) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					if (_bs.hasProperty(BlockStateProperties.WATERLOGGED))
						_bs = _bs.setValue(BlockStateProperties.WATERLOGGED, false);
					world.setBlock(_bp, _bs, 3);
				}
			} else {
				world.destroyBlock(BlockPos.containing(x, y, z), false);
			}
		}
	}
}