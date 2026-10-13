package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;

// Volcano vents call this instead of SpawnSignalPlumeXYZProcedure
// Same plume and spread, but the long-range smoke carries no flames or embers
public class SpawnVolcanoPlumeXYZProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		SpawnSignalPlumeXYZProcedure.execute(world, x, y, z, true);
	}
}
