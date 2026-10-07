package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class LogStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean done = false;
		double rnd = 0;
		if (!world.isClientSide()) {
			done = false;
			rnd = Math.random();
			if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_OAK_LOGS)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_OAK_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp3
							? BurntModBlocks.SOOTY_OAK_LOG.defaultBlockState().setValue(_withbp3, true)
							: BurntModBlocks.SOOTY_OAK_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_SPRUCE_LOGS)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_SPRUCE_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp7
							? BurntModBlocks.SOOTY_SPRUCE_LOG.defaultBlockState().setValue(_withbp7, true)
							: BurntModBlocks.SOOTY_SPRUCE_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_BIRCH_LOGS)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_BIRCH_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp11
							? BurntModBlocks.SOOTY_BIRCH_LOG.defaultBlockState().setValue(_withbp11, true)
							: BurntModBlocks.SOOTY_BIRCH_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_JUNGLE_LOGS)) {
				if (Math.random() <= 0.5) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.SOOTY_JUNGLE_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp15
								? BurntModBlocks.SOOTY_JUNGLE_LOG.defaultBlockState().setValue(_withbp15, true)
								: BurntModBlocks.SOOTY_JUNGLE_LOG.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
				done = true;
			} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_ACACIA_LOGS)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_ACACIA_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp19
							? BurntModBlocks.SOOTY_ACACIA_LOG.defaultBlockState().setValue(_withbp19, true)
							: BurntModBlocks.SOOTY_ACACIA_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_DARK_OAK_LOGS)) {
				if (Math.random() <= 0.5) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.SOOTY_DARK_OAK_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp23
								? BurntModBlocks.SOOTY_DARK_OAK_LOG.defaultBlockState().setValue(_withbp23, true)
								: BurntModBlocks.SOOTY_DARK_OAK_LOG.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
				done = true;
			} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_MANGROVE_LOGS)) {
				if (Math.random() <= 0.5) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.SOOTY_MANGROVE_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp27
								? BurntModBlocks.SOOTY_MANGROVE_LOG.defaultBlockState().setValue(_withbp27, true)
								: BurntModBlocks.SOOTY_MANGROVE_LOG.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
				done = true;
			} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_CHERRY_LOGS)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_CHERRY_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp31
							? BurntModBlocks.SOOTY_CHERRY_LOG.defaultBlockState().setValue(_withbp31, true)
							: BurntModBlocks.SOOTY_CHERRY_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_PALE_OAK_LOGS)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.SOOTY_PALE_OAK_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbppo31
							? BurntModBlocks.SOOTY_PALE_OAK_LOG.defaultBlockState().setValue(_withbppo31, true)
							: BurntModBlocks.SOOTY_PALE_OAK_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_LOGS_THAT_BURN)
					&& !((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_ROOTS)
					&& !BurntTags.isCompatBurnable(world.getBlockState(BlockPos.containing(x, y, z)))) {
				// No flame placement here - it made modded logs burn a whole floor in one frame
				BurntMod.queueServerWork(Mth.nextInt(world.getRandom(), 1, 200), () -> {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bso = world.getBlockState(_bp);
					// Fix 1: bail out if another lambda already converted this block
					if (!_bso.is(BurntTags.BLOCK_MC_LOGS_THAT_BURN)) return;
					{
						BlockState _bs = (BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_SMOLDERING_LOGS).getRandomElement(world.getRandom())
								.orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value()).defaultBlockState();
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						// Fix 2: set stage AFTER property copy so it can't be overwritten by the source block's own stage property
						_bs = blockStateWithInt(_bs, "stage", 1);
						world.setBlock(_bp, _bs, 3);
					}
					if (Math.random() <= 0.3) {
						world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.SMOLDERING_LOG.defaultBlockState()));
					}
				});
				done = true;
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}