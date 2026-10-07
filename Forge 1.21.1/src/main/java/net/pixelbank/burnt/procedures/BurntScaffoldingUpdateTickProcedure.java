package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurntScaffoldingUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_AIR)) {
				world.destroyBlock(BlockPos.containing(x, y, z), false);
			}
		}
	}
}