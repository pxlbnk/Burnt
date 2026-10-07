package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;

public class StartBurnAtProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			BurnBabyProcedure.execute(world, x, y, z);
		}
	}
}