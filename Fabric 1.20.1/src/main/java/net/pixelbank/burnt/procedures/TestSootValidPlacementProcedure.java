package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class TestSootValidPlacementProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOT_BLOCK)
				&& (world.getBlockState(BlockPos.containing(x, y, z + 1)).canOcclude() || world.getBlockState(BlockPos.containing(x + 1, y, z)).canOcclude() || world.getBlockState(BlockPos.containing(x, y, z - 1)).canOcclude()
						|| world.getBlockState(BlockPos.containing(x - 1, y, z)).canOcclude())) {
			return true;
		}
		return false;
	}
}