package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class SparkOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double bx = 0;
		double by = 0;
		double bz = 0;
		boolean done = false;
		if (!world.isClientSide()) {
			ArrowBurnProcedure.execute(world, x, y, z);
			ArrowBurnProcedure.execute(world, x + 1, y, z);
			ArrowBurnProcedure.execute(world, x - 1, y, z);
			ArrowBurnProcedure.execute(world, x, y, z + 1);
			ArrowBurnProcedure.execute(world, x, y, z - 1);
			ArrowBurnProcedure.execute(world, x, y + 1, z);
			ArrowBurnProcedure.execute(world, x, y - 1, z);
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}