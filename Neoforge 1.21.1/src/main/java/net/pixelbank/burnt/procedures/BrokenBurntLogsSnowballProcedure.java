package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class BrokenBurntLogsSnowballProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (entity.getType().is(BurntTags.ENTITY_SNOWBALL)) {
				ExtinguishBlockProcedure.execute(world, x, y, z);
			}
		}
	}
}