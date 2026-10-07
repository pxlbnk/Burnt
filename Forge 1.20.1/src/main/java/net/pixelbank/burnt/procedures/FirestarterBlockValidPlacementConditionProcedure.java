package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class FirestarterBlockValidPlacementConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		// Only refuse when there is no block at all below - leaves and logs are fine
		return !world.getBlockState(BlockPos.containing(x, y - 1, z)).isAir();
	}
}