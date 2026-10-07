package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SootBlockOnBlockHitByProjectileProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			world.destroyBlock(BlockPos.containing(x, y, z), false);
		}
	}
}