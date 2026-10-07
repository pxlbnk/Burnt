package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class MushroomStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MUSHROOM_STEM) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.COOKED_MUSHROOM_STEM.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp3
							? BurntModBlocks.COOKED_MUSHROOM_STEM.defaultBlockState().setValue(_withbp3, true)
							: BurntModBlocks.COOKED_MUSHROOM_STEM.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.RED_MUSHROOM_BLOCK) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.COOKED_RED_MUSHROOM_BLOCK.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp7
							? BurntModBlocks.COOKED_RED_MUSHROOM_BLOCK.defaultBlockState().setValue(_withbp7, true)
							: BurntModBlocks.COOKED_RED_MUSHROOM_BLOCK.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BROWN_MUSHROOM_BLOCK) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.COOKED_BROWN_MUSHROOM_BLOCK.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp11
							? BurntModBlocks.COOKED_BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(_withbp11, true)
							: BurntModBlocks.COOKED_BROWN_MUSHROOM_BLOCK.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.JUNGLE_WOOD) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_JUNGLE_WOOD.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp15
							? BurntModBlocks.SOOTY_JUNGLE_WOOD.defaultBlockState().setValue(_withbp15, true)
							: BurntModBlocks.SOOTY_JUNGLE_WOOD.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}