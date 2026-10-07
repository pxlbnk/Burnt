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

public class FenceStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double sx = 0;
		double sy = 0;
		double sz = 0;
		boolean found = false;
		boolean done = false;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.OAK_FENCE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_OAK_FENCE.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp3
							? BurntModBlocks.SOOTY_OAK_FENCE.defaultBlockState().setValue(_withbp3, true)
							: BurntModBlocks.SOOTY_OAK_FENCE.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SPRUCE_FENCE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_SPRUCE_FENCE.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp8
							? BurntModBlocks.SOOTY_SPRUCE_FENCE.defaultBlockState().setValue(_withbp8, true)
							: BurntModBlocks.SOOTY_SPRUCE_FENCE.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BIRCH_FENCE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_BIRCH_FENCE.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp13
							? BurntModBlocks.SOOTY_BIRCH_FENCE.defaultBlockState().setValue(_withbp13, true)
							: BurntModBlocks.SOOTY_BIRCH_FENCE.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.JUNGLE_FENCE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_JUNGLE_FENCE.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp18
							? BurntModBlocks.SOOTY_JUNGLE_FENCE.defaultBlockState().setValue(_withbp18, true)
							: BurntModBlocks.SOOTY_JUNGLE_FENCE.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.ACACIA_FENCE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_ACACIA_FENCE.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp23
							? BurntModBlocks.SOOTY_ACACIA_FENCE.defaultBlockState().setValue(_withbp23, true)
							: BurntModBlocks.SOOTY_ACACIA_FENCE.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DARK_OAK_FENCE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_DARK_OAK_FENCE.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp28
							? BurntModBlocks.SOOTY_DARK_OAK_FENCE.defaultBlockState().setValue(_withbp28, true)
							: BurntModBlocks.SOOTY_DARK_OAK_FENCE.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_FENCE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_MANGROVE_FENCE.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp33
							? BurntModBlocks.SOOTY_MANGROVE_FENCE.defaultBlockState().setValue(_withbp33, true)
							: BurntModBlocks.SOOTY_MANGROVE_FENCE.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CHERRY_FENCE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_CHERRY_FENCE.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp38
							? BurntModBlocks.SOOTY_CHERRY_FENCE.defaultBlockState().setValue(_withbp38, true)
							: BurntModBlocks.SOOTY_CHERRY_FENCE.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FORGE_FENCES_WOODEN)) {
				// No flame placement here - it made modded wood flash over in seconds
				final net.minecraft.world.level.block.Block _origBlock = world.getBlockState(BlockPos.containing(x, y, z)).getBlock();
				BurntMod.queueServerWork(Mth.nextInt(world.getRandom(), 1, 200), () -> {
					// Bail if something already converted this block - the delay lets duplicates queue up
					if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != _origBlock)
						return;
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_FENCE.defaultBlockState(), "stage", 1));
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
					}
				});
			} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "pale_oak_fence")) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_PALE_OAK_FENCE.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbppo38
							? BurntModBlocks.SOOTY_PALE_OAK_FENCE.defaultBlockState().setValue(_withbppo38, true)
							: BurntModBlocks.SOOTY_PALE_OAK_FENCE.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FORGE_FENCES_WOODEN)) {
				// No flame placement here - it made modded wood flash over in seconds
				final net.minecraft.world.level.block.Block _origBlock = world.getBlockState(BlockPos.containing(x, y, z)).getBlock();
				BurntMod.queueServerWork(Mth.nextInt(world.getRandom(), 1, 200), () -> {
					// Bail if something already converted this block - the delay lets duplicates queue up
					if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != _origBlock)
						return;
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_FENCE.defaultBlockState(), "stage", 1));
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
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
