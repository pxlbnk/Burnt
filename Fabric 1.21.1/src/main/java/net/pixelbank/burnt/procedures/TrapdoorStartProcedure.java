package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class TrapdoorStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.OAK_TRAPDOOR) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_OAK_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SPRUCE_TRAPDOOR) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_SPRUCE_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BIRCH_TRAPDOOR) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_BIRCH_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.JUNGLE_TRAPDOOR) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_JUNGLE_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.ACACIA_TRAPDOOR) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_ACACIA_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DARK_OAK_TRAPDOOR) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_DARK_OAK_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_TRAPDOOR) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_MANGROVE_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CHERRY_TRAPDOOR) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_CHERRY_TRAPDOOR.defaultBlockState();
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
			} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "pale_oak_trapdoor")) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_PALE_OAK_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "bamboo_trapdoor")) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_BAMBOO_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			} else if (((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_WOODEN_TRAPDOORS) || (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_TRAPDOOR))
					&& !(world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FIRE_RESISTANT)) {
				world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.BURNT_TRAPDOOR.defaultBlockState()));
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_TRAPDOOR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}