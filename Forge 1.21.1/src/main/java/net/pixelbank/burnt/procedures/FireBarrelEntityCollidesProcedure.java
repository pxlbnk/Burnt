package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class FireBarrelEntityCollidesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (entity.isOnFire()) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.FIRE_BARREL_ACTIVE.defaultBlockState(), 3);
			}
		}
	}
}