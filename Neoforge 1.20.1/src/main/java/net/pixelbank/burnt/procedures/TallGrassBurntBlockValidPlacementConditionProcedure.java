package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class TallGrassBurntBlockValidPlacementConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.BURNT_GRASS || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.SMOLDERING_GRASS
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.RECOVERING_GRASS) {
			return true;
		}
		return false;
	}
}