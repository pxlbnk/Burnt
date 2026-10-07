package net.pixelbank.burnt.init;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;

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
			BURNT_AMBIENT = reg(event, "burnt_ambient");
			WISP_TREASURE = reg(event, "wisp_treasure");
			WISP_QUICK    = reg(event, "wisp_quick");
			WISP_SLOW     = reg(event, "wisp_slow");
			BURNT_EERIE   = reg(event, "burnt_eerie");
			BURNT         = reg(event, "burnt");
			THERMAL       = reg(event, "thermal");
			THERMAL_TICK  = reg(event, "thermal_tick");
		});
	}

	private static SoundEvent reg(RegisterEvent event, String name) {
		ResourceLocation id = new ResourceLocation(BurntMod.MODID, name);
		SoundEvent se = SoundEvent.createVariableRangeEvent(id);
		event.register(Registries.SOUND_EVENT, id, () -> se);
		return se;
	}
}
