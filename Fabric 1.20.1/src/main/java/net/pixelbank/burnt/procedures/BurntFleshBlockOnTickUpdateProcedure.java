package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurntFleshBlockOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (Math.random() <= 0.25) {
				WillitburnProcedure.execute(world, x, y, z);
			}
			if (Math.random() <= 0.25) {
				world.destroyBlock(BlockPos.containing(x, y, z), false);
			}
		}
	}
}