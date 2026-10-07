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

public class MossParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (world.isClientSide()) {
			if (Math.random() <= 0.4) {
				particleAmount = 3;
				particleRadius = 0.7;
				for (int index0 = 0; index0 < (int) particleAmount; index0++) {
					world.addParticle((SimpleParticleType) (BurntModParticleTypes.MOSS_FIRE_PARTICLE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -1.5, 1.5) * particleRadius),
							(y + 0.5 + Mth.nextDouble(world.getRandom(), -1.5, 1.5) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -1.5, 1.5) * particleRadius), (Mth.nextDouble(world.getRandom(), -0.05, 0.05)),
							(Mth.nextDouble(world.getRandom(), -0.05, 0.05)), (Mth.nextDouble(world.getRandom(), -0.05, 0.05)));
				}
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip9 ? blockstate.getValue(_getip9) : -1) >= 1) {
				if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
					if (BurntBasicConfigConfiguration.GROUND_SMOKE.get() == true) {
						if (Math.random() <= 0.3) {
							world.addParticle((SimpleParticleType) (BurntModParticleTypes.THICK_FOG), (x + 0.5), (y + Mth.nextDouble(world.getRandom(), 1, 1.5)), (z + 0.5), (Mth.nextDouble(world.getRandom(), -0.3, 0.3)), 0,
									(Mth.nextDouble(world.getRandom(), -0.3, 0.3)));
						}
					}
				}
				if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_AIR)) {
					if (BurntBasicConfigConfiguration.GROUND_SMOKE.get() == true) {
						if (Math.random() <= 0.3) {
							world.addParticle((SimpleParticleType) (BurntModParticleTypes.THICK_FOG), (x + 0.5), (y + Mth.nextDouble(world.getRandom(), -2, 0)), (z + 0.5), (Mth.nextDouble(world.getRandom(), -0.3, 0.3)), 0,
									(Mth.nextDouble(world.getRandom(), -0.3, 0.3)));
						}
					}
				}
			}
		}
	}
}