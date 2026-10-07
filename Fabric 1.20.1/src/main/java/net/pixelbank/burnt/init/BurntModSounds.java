package net.pixelbank.burnt.init;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class BurntModSounds {
	public static SoundEvent BURNT_AMBIENT;
	public static SoundEvent WISP_TREASURE;
	public static SoundEvent WISP_QUICK;
	public static SoundEvent WISP_SLOW;
	public static SoundEvent BURNT_EERIE;
	public static SoundEvent BURNT;
	public static SoundEvent THERMAL;
	public static SoundEvent THERMAL_TICK;

	public static void register() {
		BURNT_AMBIENT = register("burnt_ambient");
		WISP_TREASURE = register("wisp_treasure");
		WISP_QUICK    = register("wisp_quick");
		WISP_SLOW     = register("wisp_slow");
		BURNT_EERIE   = register("burnt_eerie");
		BURNT         = register("burnt");
		THERMAL       = register("thermal");
		THERMAL_TICK  = register("thermal_tick");
	}

	private static SoundEvent register(String name) {
		ResourceLocation id = new ResourceLocation(BurntMod.MODID, name);
		return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
	}
}
