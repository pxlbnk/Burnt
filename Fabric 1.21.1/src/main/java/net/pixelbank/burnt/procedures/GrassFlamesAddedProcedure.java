package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class GrassFlamesAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == BurntModBlocks.FIRE_BARREL) {
				{
					BlockPos _bp = BlockPos.containing(x + 1, y, z);
					BlockState _bs = BurntModBlocks.FIRE_BARREL_ACTIVE.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == BurntModBlocks.FIRE_BARREL) {
				{
					BlockPos _bp = BlockPos.containing(x - 1, y, z);
					BlockState _bs = BurntModBlocks.FIRE_BARREL_ACTIVE.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == BurntModBlocks.FIRE_BARREL) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z - 1);
					BlockState _bs = BurntModBlocks.FIRE_BARREL_ACTIVE.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == BurntModBlocks.FIRE_BARREL) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z + 1);
					BlockState _bs = BurntModBlocks.FIRE_BARREL_ACTIVE.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}