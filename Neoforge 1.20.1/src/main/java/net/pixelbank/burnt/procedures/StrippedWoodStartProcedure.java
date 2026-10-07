package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

public class StrippedWoodStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double sx = 0;
		double sy = 0;
		double sz = 0;
		boolean found = false;
		boolean done = false;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.STRIPPED_OAK_WOOD) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_OAK_STRIPPED_WOOD.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp3
							? BurntModBlocks.SOOTY_OAK_STRIPPED_WOOD.defaultBlockState().setValue(_withbp3, true)
							: BurntModBlocks.SOOTY_OAK_STRIPPED_WOOD.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.STRIPPED_SPRUCE_WOOD) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_SPRUCE_STRIPPED_WOOD.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp7
							? BurntModBlocks.SOOTY_SPRUCE_STRIPPED_WOOD.defaultBlockState().setValue(_withbp7, true)
							: BurntModBlocks.SOOTY_SPRUCE_STRIPPED_WOOD.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.STRIPPED_BIRCH_WOOD) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_BIRCH_STRIPPED_WOOD.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp11
							? BurntModBlocks.SOOTY_BIRCH_STRIPPED_WOOD.defaultBlockState().setValue(_withbp11, true)
							: BurntModBlocks.SOOTY_BIRCH_STRIPPED_WOOD.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.STRIPPED_JUNGLE_WOOD) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_JUNGLE_STRIPPED_WOOD.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp15
							? BurntModBlocks.SOOTY_JUNGLE_STRIPPED_WOOD.defaultBlockState().setValue(_withbp15, true)
							: BurntModBlocks.SOOTY_JUNGLE_STRIPPED_WOOD.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.STRIPPED_ACACIA_WOOD) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_ACACIA_STRIPPED_WOOD.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp19
							? BurntModBlocks.SOOTY_ACACIA_STRIPPED_WOOD.defaultBlockState().setValue(_withbp19, true)
							: BurntModBlocks.SOOTY_ACACIA_STRIPPED_WOOD.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.STRIPPED_DARK_OAK_WOOD) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_DARK_OAK_STRIPPED_WOOD.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp23
							? BurntModBlocks.SOOTY_DARK_OAK_STRIPPED_WOOD.defaultBlockState().setValue(_withbp23, true)
							: BurntModBlocks.SOOTY_DARK_OAK_STRIPPED_WOOD.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.STRIPPED_MANGROVE_WOOD) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_MANGROVE_STRIPPED_WOOD.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp27
							? BurntModBlocks.SOOTY_MANGROVE_STRIPPED_WOOD.defaultBlockState().setValue(_withbp27, true)
							: BurntModBlocks.SOOTY_MANGROVE_STRIPPED_WOOD.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.STRIPPED_CHERRY_WOOD) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_CHERRY_STRIPPED_WOOD.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp31
							? BurntModBlocks.SOOTY_CHERRY_STRIPPED_WOOD.defaultBlockState().setValue(_withbp31, true)
							: BurntModBlocks.SOOTY_CHERRY_STRIPPED_WOOD.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "stripped_pale_oak_wood")) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_PALE_OAK_STRIPPED_WOOD.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbppo31
							? BurntModBlocks.SOOTY_PALE_OAK_STRIPPED_WOOD.defaultBlockState().setValue(_withbppo31, true)
							: BurntModBlocks.SOOTY_PALE_OAK_STRIPPED_WOOD.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if (((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_LOGS_THAT_BURN) || (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_STRIPPED_WOOD)) && !((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_ROOTS)) {
				// No flame placement here - it made modded wood flash over in seconds
				final net.minecraft.world.level.block.Block _origBlock = world.getBlockState(BlockPos.containing(x, y, z)).getBlock();
				BurntMod.queueServerWork(Mth.nextInt(world.getRandom(), 1, 200), () -> {
					// Bail if something already converted this block - the delay lets duplicates queue up
					if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != _origBlock)
						return;
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_STRIPPED_WOOD.defaultBlockState(), "stage", 1));
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				});
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}
