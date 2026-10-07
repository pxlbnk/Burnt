package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class ExtinguishProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FIRE)) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount - 1;
				BurntModVariables.WorldVariables.get(world).markSyncDirty();
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_HAY) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_HAY.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_MOSS) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_MOSS.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_MOSS_CARPET) {
				world.destroyBlock(BlockPos.containing(x, y, z), false);
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_AZALEA) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_AZALEA.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_WOOL) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_WOOL.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_CARPET) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_CARPET.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_SOOTY)) {
				SootyExtinguishProcedure.execute(world, x, y, z);
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_GRASS)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_GRASS.defaultBlockState(), 3);
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_LEAVES)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_BAMBOO)) {
				BambooExtinguishProcedure.execute(world, x, y, z);
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_LOGS)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_HOLLOW_LOG || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.EMBER_HOLLOW_LOG || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.BLAZING_HOLLOW_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_HOLLOW_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.BROKEN_BLAZING_LOG) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BROKEN_BURNT_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_WOOD)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_WOOD.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_STRIPPED_LOGS)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.STRIPPED_BURNT_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_STRIPPED_WOOD)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.STRIPPED_BURNT_WOOD.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_MANGROVE_ROOTS) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_MANGROVE_ROOTS.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_PLANKS)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_PLANKS.defaultBlockState(), 3);
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_STAIRS)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_STAIRS.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_SLABS)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_SLAB.defaultBlockState(), 3);
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_FENCES)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_FENCE.defaultBlockState(), 3);
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNING_FENCE_GATES)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_FENCEGATE.defaultBlockState(), 3);
			}
		}
	}
}