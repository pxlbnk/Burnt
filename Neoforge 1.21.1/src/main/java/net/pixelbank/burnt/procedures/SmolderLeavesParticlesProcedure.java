package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SmolderLeavesParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (world.isClientSide()) {
			// Pass on the stage gate, or on having a radius property - that way smoldering DT branches get
			// the same smoke and wood particles as our own smoldering logs
			int stage = (blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2
					? blockstate.getValue(_getip2) : -1);
			boolean hasRadius = blockstate.getBlock().getStateDefinition().getProperty("radius") instanceof IntegerProperty;
			if (stage >= 1 || hasRadius) {
				if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
					if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
						SmokeParticlesProcedure.execute(world, x, y + 1, z);
					}
				} else {
					WoodParticlesProcedure.execute(world, x, y, z);
				}
			}
		}
	}
}