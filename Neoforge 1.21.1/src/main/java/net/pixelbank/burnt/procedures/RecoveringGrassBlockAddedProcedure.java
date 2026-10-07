package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class RecoveringGrassBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.TALL_GRASS_BURNT) {
			if (Math.random() <= 0.01) {
				world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.MOSS_CARPET.defaultBlockState(), 3);
			} else if (Math.random() <= 0.05) {
				world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.SHORT_GRASS.defaultBlockState(), 3);
			}
		}
	}
}