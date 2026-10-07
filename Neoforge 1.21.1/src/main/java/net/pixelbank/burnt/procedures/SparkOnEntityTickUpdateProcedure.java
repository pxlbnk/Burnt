package net.pixelbank.burnt.procedures;

import net.minecraft.world.entity.Entity;

public class SparkOnEntityTickUpdateProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!entity.level().isClientSide())
			entity.discard();
	}
}