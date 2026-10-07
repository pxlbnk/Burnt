package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BambooStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_PLANKS) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_PLANKS.defaultBlockState(), "stage", 1));
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_TRAPDOOR) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_BAMBOO_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_MOSAIC) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_MOSAIC_PLANKS.defaultBlockState(), "stage", 1));
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_BLOCK) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_BLOCK.defaultBlockState(), "stage", 1));
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.STRIPPED_BAMBOO_BLOCK) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_BLOCK.defaultBlockState(), "stage", 1));
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_STAIRS
					&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp23
							&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp23)) == false) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_STAIRS.defaultBlockState(), "stage", 1));
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_MOSAIC_STAIRS
					&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp29
							&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp29)) == false) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_MOSAIC_STAIRS.defaultBlockState(), "stage", 1));
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_FENCE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_FENCE.defaultBlockState(), "stage", 1));
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_FENCE_GATE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_FENCEGATE.defaultBlockState(), "stage", 1));
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_SLAB
					&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp43
							&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp43)) == false) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_SLAB.defaultBlockState(), "stage", 1));
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_MOSAIC_SLAB
					&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp49
							&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp49)) == false) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_MOSAIC_SLAB.defaultBlockState(), "stage", 1));
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}