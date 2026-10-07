package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModParticleTypes;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.minecraft.resources.ResourceLocation;

public class FleshBubblesParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		if (world.isClientSide()) {
			if (Math.random() <= 0.5) {
				world.addParticle((SimpleParticleType) (BurntModParticleTypes.FLESH_BUBBLE), (x + Mth.nextDouble(world.getRandom(), 0, 1)), (y + 1), (z + Mth.nextDouble(world.getRandom(), 0, 1)), 0, 0.06, 0);
				if (Math.random() <= 0.05) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.bubble_column.bubble_pop")), SoundSource.AMBIENT, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.bubble_column.bubble_pop")), SoundSource.AMBIENT, 1, 1, false);
						}
					}
				}
			}
		}
	}
}