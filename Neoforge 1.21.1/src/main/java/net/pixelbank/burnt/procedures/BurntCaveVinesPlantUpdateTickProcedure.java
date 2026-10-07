package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurntCaveVinesPlantUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (!(world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_MC_CAVE_VINES)
					|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_AIR)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_CAVE_VINES.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.CAVE_VINES) {
				{
					BlockPos _bp = BlockPos.containing(x, y + 1, z);
					BlockState _bs = Blocks.CAVE_VINES_PLANT.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if (((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.CAVE_VINES_PLANT || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.CAVE_VINES)
					&& (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.BURNT_CAVE_VINES_PLANT) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_CAVE_VINES.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.BURNT_CAVE_VINES) {
				{
					BlockPos _bp = BlockPos.containing(x, y + 1, z);
					BlockState _bs = BurntModBlocks.BURNT_CAVE_VINES_PLANT.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}