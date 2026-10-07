package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class LeavesStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double rnd = 0;
		boolean done = false;
		if (!world.isClientSide()) {
			if (IsRainingProcedure.isGloballyRaining(world)) {
				if (IsRainingProcedure.execute(world, x, y, z) == false) {
					done = false;
					if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp3
							&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp3)) == false) {
						if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DARK_OAK_LEAVES) {
							if (Math.random() <= 0.2) {
								{
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockState _bs = BurntModBlocks.SMOLDERING_DARK_OAK_LEAVES.defaultBlockState();
									BlockState _bso = world.getBlockState(_bp);
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
									world.setBlock(_bp, _bs, 3);
								}
							}
							done = true;
						} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.OAK_LEAVES) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_OAK_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SPRUCE_LEAVES) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_SPRUCE_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BIRCH_LEAVES) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_BIRCH_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.JUNGLE_LEAVES) {
							if (Math.random() <= 0.5) {
								{
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockState _bs = BurntModBlocks.SMOLDERING_JUNGLE_LEAVES.defaultBlockState();
									BlockState _bso = world.getBlockState(_bp);
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
									world.setBlock(_bp, _bs, 3);
								}
							}
							done = true;
						} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.ACACIA_LEAVES) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_ACACIA_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_LEAVES) {
							if (Math.random() <= 0.2) {
								{
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockState _bs = BurntModBlocks.SMOLDERING_MANGROVE_LEAVES.defaultBlockState();
									BlockState _bso = world.getBlockState(_bp);
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
									world.setBlock(_bp, _bs, 3);
								}
							}
							done = true;
						} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CHERRY_LEAVES) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						} else if (done == false && net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "pale_oak_leaves")) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_PALE_OAK_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.AZALEA_LEAVES) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.FLOWERING_AZALEA_LEAVES) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_LEAVES)
								&& !(world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FIRE_RESISTANT)) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_SPRUCE_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						} else if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_foliage")) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						} else if (done == false) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						}
					}
				}
			} else {
				done = false;
				if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp44
						&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp44)) == false) {
					if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.OAK_LEAVES) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.SMOLDERING_OAK_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SPRUCE_LEAVES) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.SMOLDERING_SPRUCE_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BIRCH_LEAVES) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.SMOLDERING_BIRCH_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.JUNGLE_LEAVES) {
						if (Math.random() <= 0.3) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_JUNGLE_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						done = true;
					} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.ACACIA_LEAVES) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.SMOLDERING_ACACIA_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DARK_OAK_LEAVES) {
						if (Math.random() <= 0.2) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_DARK_OAK_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						done = true;
					} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_LEAVES) {
						if (Math.random() <= 0.2) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_MANGROVE_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						done = true;
					} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CHERRY_LEAVES) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					} else if (done == false && net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "pale_oak_leaves")) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.SMOLDERING_PALE_OAK_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.AZALEA_LEAVES) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.FLOWERING_AZALEA_LEAVES) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					} else if (done == false && (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_LEAVES)
							&& !(world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FIRE_RESISTANT)) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.SMOLDERING_SPRUCE_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					} else if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_foliage")) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					} else if (done == false) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					}
				}
			}
		}
	}
}