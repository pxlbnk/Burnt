package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.minecraft.resources.ResourceLocation;

public class BlastParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (!world.isClientSide()) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (BurntModParticleTypes.BIG_EXPLOSION), x, y, z, 3, 0.5, 1, 0.5, 0);
			if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (BurntModParticleTypes.FLAME_SMOKE), x, y, z, (int) Mth.nextDouble(world.getRandom(), 15, 30), 1, 2, 1, 0);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.dragon_fireball.explode")), SoundSource.BLOCKS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.dragon_fireball.explode")), SoundSource.BLOCKS, 1, 1, false);
				}
			}
		}
	}
}