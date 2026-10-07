package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;

public class FenceFlameParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp2 && blockstate.getValue(_getbp2)) == true) {
				if (BurntBasicConfigConfiguration.FLAMES.get() == true) {
					particleAmount = Mth.nextDouble(world.getRandom(), 1, 2);
					particleRadius = 1;
					for (int index0 = 0; index0 < (int) particleAmount; index0++) {
						world.addParticle(ParticleTypes.FLAME, (x + 0.5 + Mth.nextDouble(world.getRandom(), -0.5, 0.5) * particleRadius), y, (z + 0.5 + Mth.nextDouble(world.getRandom(), -0.5, 0.5) * particleRadius), 0, 0, 0);
					}
					if (Math.random() <= 0.1) {
						world.addParticle((SimpleParticleType) (BurntModParticleTypes.FLAMES), (x + 0.5 + Mth.nextDouble(world.getRandom(), -0.5, 0.5) * particleRadius), y,
								(z + 0.5 + Mth.nextDouble(world.getRandom(), -0.5, 0.5) * particleRadius), 0, (Mth.nextDouble(world.getRandom(), 0, 0.1)), 0);
					}
				}
			}
		}
	}
}