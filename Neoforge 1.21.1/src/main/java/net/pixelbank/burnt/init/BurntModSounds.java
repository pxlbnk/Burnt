package net.pixelbank.burnt.init;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;

public class BurntModSounds {
	public static SoundEvent BURNT_AMBIENT;
	public static SoundEvent WISP_TREASURE;
	public static SoundEvent WISP_QUICK;
	public static SoundEvent WISP_SLOW;
	public static SoundEvent BURNT_EERIE;
	public static SoundEvent BURNT;
	public static SoundEvent THERMAL;
	public static SoundEvent THERMAL_TICK;

	public static void register(IEventBus bus) {
		bus.addListener((RegisterEvent event) -> {
			if (!event.getRegistryKey().equals(Registries.SOUND_EVENT)) return;
			BURNT_AMBIENT = reg("burnt_ambient");
			WISP_TREASURE = reg("wisp_treasure");
			WISP_QUICK    = reg("wisp_quick");
			WISP_SLOW     = reg("wisp_slow");
			BURNT_EERIE   = reg("burnt_eerie");
			BURNT         = reg("burnt");
			THERMAL       = reg("thermal");
			THERMAL_TICK  = reg("thermal_tick");
		});
	}

	private static SoundEvent reg(String name) {
		ResourceLocation id = ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, name);
		return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
	}
}
