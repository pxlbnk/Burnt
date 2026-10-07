package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SootyStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_OAK_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_OAK_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp3
							? BurntModBlocks.SOOTY_OAK_LOG.defaultBlockState().setValue(_withbp3, true)
							: BurntModBlocks.SOOTY_OAK_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_SPRUCE_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_SPRUCE_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp7
							? BurntModBlocks.SOOTY_SPRUCE_LOG.defaultBlockState().setValue(_withbp7, true)
							: BurntModBlocks.SOOTY_SPRUCE_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_BIRCH_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_BIRCH_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp11
							? BurntModBlocks.SOOTY_BIRCH_LOG.defaultBlockState().setValue(_withbp11, true)
							: BurntModBlocks.SOOTY_BIRCH_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_JUNGLE_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp15
							? BurntModBlocks.SOOTY_JUNGLE_LOG.defaultBlockState().setValue(_withbp15, true)
							: BurntModBlocks.SOOTY_JUNGLE_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_ACACIA_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_ACACIA_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp19
							? BurntModBlocks.SOOTY_ACACIA_LOG.defaultBlockState().setValue(_withbp19, true)
							: BurntModBlocks.SOOTY_ACACIA_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_DARK_OAK_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp23
							? BurntModBlocks.SOOTY_DARK_OAK_LOG.defaultBlockState().setValue(_withbp23, true)
							: BurntModBlocks.SOOTY_DARK_OAK_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_MANGROVE_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_MANGROVE_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp27
							? BurntModBlocks.SOOTY_MANGROVE_LOG.defaultBlockState().setValue(_withbp27, true)
							: BurntModBlocks.SOOTY_MANGROVE_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_CHERRY_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_CHERRY_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp31
							? BurntModBlocks.SOOTY_CHERRY_LOG.defaultBlockState().setValue(_withbp31, true)
							: BurntModBlocks.SOOTY_CHERRY_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}