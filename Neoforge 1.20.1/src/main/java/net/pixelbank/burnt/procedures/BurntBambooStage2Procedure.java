package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;

public class BurntBambooStage2Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (Math.random() <= 0.1) {
				BambooExtinguishProcedure.execute(world, x, y, z);
			}
		}
	}
}