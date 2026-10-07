package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurntSugarCaneBlockValidPlacementConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_BURNT_SUGAR_CANE_PLACES_ON)) {
			return true;
		}
		return false;
	}
}