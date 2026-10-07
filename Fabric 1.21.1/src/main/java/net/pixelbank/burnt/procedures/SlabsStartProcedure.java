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

public class SlabsStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double sx = 0;
		double sy = 0;
		double sz = 0;
		boolean found = false;
		boolean done = false;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.OAK_SLAB) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.SOOTY_OAK_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp2
						? BurntModBlocks.SOOTY_OAK_SLAB.defaultBlockState().setValue(_withbp2, true)
						: BurntModBlocks.SOOTY_OAK_SLAB.defaultBlockState());
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
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SPRUCE_SLAB) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.SOOTY_SPRUCE_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp7
						? BurntModBlocks.SOOTY_SPRUCE_SLAB.defaultBlockState().setValue(_withbp7, true)
						: BurntModBlocks.SOOTY_SPRUCE_SLAB.defaultBlockState());
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
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BIRCH_SLAB) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.SOOTY_BIRCH_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp12
						? BurntModBlocks.SOOTY_BIRCH_SLAB.defaultBlockState().setValue(_withbp12, true)
						: BurntModBlocks.SOOTY_BIRCH_SLAB.defaultBlockState());
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
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.JUNGLE_SLAB) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.SOOTY_JUNGLE_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp17
						? BurntModBlocks.SOOTY_JUNGLE_SLAB.defaultBlockState().setValue(_withbp17, true)
						: BurntModBlocks.SOOTY_JUNGLE_SLAB.defaultBlockState());
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
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.ACACIA_SLAB) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.SOOTY_ACACIA_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp22
						? BurntModBlocks.SOOTY_ACACIA_SLAB.defaultBlockState().setValue(_withbp22, true)
						: BurntModBlocks.SOOTY_ACACIA_SLAB.defaultBlockState());
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
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DARK_OAK_SLAB) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.SOOTY_DARK_OAK_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp27
						? BurntModBlocks.SOOTY_DARK_OAK_SLAB.defaultBlockState().setValue(_withbp27, true)
						: BurntModBlocks.SOOTY_DARK_OAK_SLAB.defaultBlockState());
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
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_SLAB) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.SOOTY_MANGROVE_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp32
						? BurntModBlocks.SOOTY_MANGROVE_SLAB.defaultBlockState().setValue(_withbp32, true)
						: BurntModBlocks.SOOTY_MANGROVE_SLAB.defaultBlockState());
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
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CHERRY_SLAB) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.SOOTY_CHERRY_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp37
						? BurntModBlocks.SOOTY_CHERRY_SLAB.defaultBlockState().setValue(_withbp37, true)
						: BurntModBlocks.SOOTY_CHERRY_SLAB.defaultBlockState());
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
		} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "pale_oak_slab")) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.SOOTY_PALE_OAK_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbppo37
						? BurntModBlocks.SOOTY_PALE_OAK_SLAB.defaultBlockState().setValue(_withbppo37, true)
						: BurntModBlocks.SOOTY_PALE_OAK_SLAB.defaultBlockState());
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
		} else if (((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_WOODEN_SLABS) || (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_SLABS))
				&& !(world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FIRE_RESISTANT)) {
			// No flame placement here - it made modded wood flash over in seconds
			final net.minecraft.world.level.block.Block _origBlock = world.getBlockState(BlockPos.containing(x, y, z)).getBlock();
			BurntMod.queueServerWork(Mth.nextInt(world.getRandom(), 1, 200), () -> {
				// Bail if something already converted this block - the delay lets duplicates queue up
				if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != _origBlock)
					return;
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_SLAB.defaultBlockState(), "stage", 1));
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

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}
