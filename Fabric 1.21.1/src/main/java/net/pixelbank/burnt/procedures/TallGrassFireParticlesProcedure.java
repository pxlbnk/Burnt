package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;

public class TallGrassFireParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) {
			LightSmokeParticlesProcedure.execute(world, x, y + 1, z);
		}
	}
}