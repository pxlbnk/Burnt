package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;

public class TallGrassFireBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (Math.random() <= 0.5) {
				WillitburnProcedure.execute(world, x, y + 1, z);
			}
		}
	}
}