package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.particles.SimpleParticleType;

public class SteamParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) {
			if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
				if (Math.random() <= 0.05) {
					world.addParticle((SimpleParticleType) (BurntModParticleTypes.MEDIUM_STEAM_PARTICLE), (x + 0.5), (y + 1.5), (z + 0.5), 0.8, 0.2, 0.5);
				} else if (Math.random() <= 0.02) {
					world.addParticle((SimpleParticleType) (BurntModParticleTypes.LARGE_STEAM_PARTICLE), (x + 0.3), (y + 1.5), (z + 0.3), 0.8, 0.2, 0.5);
				}
			}
		}
	}
}