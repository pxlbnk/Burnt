package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurntSlabParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) {
			if (Math.random() <= 0.5) {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.EMBER_SLAB) {
					WoodParticlesProcedure.execute(world, x, y, z);
				} else {
					if (BurntBasicConfigConfiguration.ASH.get() == true) {
						AshParticlesProcedure.execute(world, x, y, z);
					}
				}
			}
		}
	}
}