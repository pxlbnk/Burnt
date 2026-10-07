package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class SprayProjectileHitsPlayerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (!world.isClientSide()) {
			if (!(entity == sourceentity)) {
				if (Math.random() <= 0.3) {
					world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.SPLASH.defaultBlockState()));
				}
			}
		}
	}
}