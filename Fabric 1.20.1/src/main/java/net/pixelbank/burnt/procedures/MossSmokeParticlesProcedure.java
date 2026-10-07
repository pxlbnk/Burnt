package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

public class MossSmokeParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) >= 1) {
				if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
					if (BurntBasicConfigConfiguration.GROUND_SMOKE.get() == true) {
						if (Math.random() <= 0.5) {
							ThickFogParticlesProcedure.execute(world, x, y, z);
						}
					}
				}
			} else {
				if (Math.random() <= 0.3) {
					particleAmount = 3;
					particleRadius = 0.7;
					for (int index0 = 0; index0 < (int) particleAmount; index0++) {
						world.addParticle((SimpleParticleType) (BurntModParticleTypes.FIRE_PARTICLE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius),
								(y + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -1, 1) * particleRadius), (Mth.nextDouble(world.getRandom(), -0.1, 0.1)),
								(Mth.nextDouble(world.getRandom(), -0.1, 0.1)), (Mth.nextDouble(world.getRandom(), -0.1, 0.1)));
					}
				}
			}
		}
	}
}