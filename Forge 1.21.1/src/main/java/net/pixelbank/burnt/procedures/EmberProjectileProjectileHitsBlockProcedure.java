package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;

public class EmberProjectileProjectileHitsBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (Math.random() <= 0.2) {
				WillitburnProcedure.execute(world, x, y, z);
			}
		}
	}
}