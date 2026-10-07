package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class CompatStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double sx = 0;
		double sy = 0;
		double sz = 0;
		boolean found = false;
		boolean done = false;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.OAK_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SOOTY_OAK_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SPRUCE_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SOOTY_SPRUCE_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BIRCH_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SOOTY_BIRCH_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.JUNGLE_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SOOTY_JUNGLE_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.ACACIA_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SOOTY_ACACIA_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DARK_OAK_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SOOTY_DARK_OAK_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SOOTY_MANGROVE_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CHERRY_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SOOTY_CHERRY_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "pale_oak_log")) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SOOTY_PALE_OAK_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_LOGS_THAT_BURN) && !((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_ROOTS)) {
				// No flame placement here - it made modded wood flash over in seconds
				final net.minecraft.world.level.block.Block _origBlock = world.getBlockState(BlockPos.containing(x, y, z)).getBlock();
				BurntMod.queueServerWork(Mth.nextInt(world.getRandom(), 1, 200), () -> {
					// Bail if something already converted this block - the delay lets duplicates queue up
					if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != _origBlock)
						return;
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (blockStateWithInt((BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_SMOLDERING_LOGS).getRandomElement(world.getRandom())
								.orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value()).defaultBlockState(), "stage", 1));
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					if (Math.random() <= 0.3) {
						world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.SMOLDERING_LOG.defaultBlockState()));
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
