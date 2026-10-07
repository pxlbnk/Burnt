package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurntCactusBlockValidPlacementConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_MC_SAND) || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.CACTUS
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.SMOLDERING_CACTUS || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.BURNT_CACTUS)
				&& !world.getBlockState(BlockPos.containing(x + 1, y, z)).canOcclude() && !world.getBlockState(BlockPos.containing(x - 1, y, z)).canOcclude() && !world.getBlockState(BlockPos.containing(x, y, z + 1)).canOcclude()
				&& !world.getBlockState(BlockPos.containing(x, y, z - 1)).canOcclude()) {
			return true;
		}
		return false;
	}
}