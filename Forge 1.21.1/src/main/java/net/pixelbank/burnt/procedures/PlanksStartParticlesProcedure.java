package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class PlanksStartParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
				if (BurntBasicConfigConfiguration.FLAMES.get() == true) {
					LargeFlameParticlesProcedure.execute(world, x, y, z);
				}
			} else {
				WoodParticlesProcedure.execute(world, x, y, z);
			}
		}
	}
}