package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class BurntForestSoundProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double idx = 0;
		double foundIndex = 0;
		double size = 0;
		double ex = 0;
		double ey = 0;
		double ez = 0;
		com.google.gson.JsonObject elem = new com.google.gson.JsonObject();
		com.google.gson.JsonObject pt = new com.google.gson.JsonObject();
		String ptText = "";
		com.google.gson.JsonArray arr = new com.google.gson.JsonArray();
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("burnt:burnt_ambient")), SoundSource.AMBIENT, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("burnt:burnt_ambient")), SoundSource.AMBIENT, 1, 1, false);
			}
		}
	}
}