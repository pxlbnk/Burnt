package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurntGrassyBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_TALL_GRASS)) {
				{
					BlockPos _bp = BlockPos.containing(x, y + 1, z);
					BlockState _bs = BurntModBlocks.TALL_GRASS_FIRE.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_MC_REPLACEABLE)
					|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_MC_FLOWERS)) {
				world.destroyBlock(BlockPos.containing(x, y + 1, z), false);
			}
			if (Math.random() <= 0.25) {
				BurntMod.queueServerWork(50, () -> {
					WillitburnProcedure.execute(world, x, y, z);
				});
			}
		}
	}
}