package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurntLeavesTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (world.isEmptyBlock(BlockPos.containing(x + 1, y, z)) && world.isEmptyBlock(BlockPos.containing(x - 1, y, z)) && world.isEmptyBlock(BlockPos.containing(x, y + 1, z)) && world.isEmptyBlock(BlockPos.containing(x, y - 1, z))
					&& world.isEmptyBlock(BlockPos.containing(x, y, z + 1)) && world.isEmptyBlock(BlockPos.containing(x, y, z - 1))) {
				if (Math.random() <= 0.01) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				}
			}
		}
	}
}