package net.pixelbank.burnt.init;

import net.pixelbank.burnt.BurntMod;

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
			event.register(Registries.SOUND_EVENT, helper -> {
				BURNT_AMBIENT = reg(helper, "burnt_ambient");
				WISP_TREASURE = reg(helper, "wisp_treasure");
				WISP_QUICK    = reg(helper, "wisp_quick");
				WISP_SLOW     = reg(helper, "wisp_slow");
				BURNT_EERIE   = reg(helper, "burnt_eerie");
				BURNT         = reg(helper, "burnt");
				THERMAL       = reg(helper, "thermal");
				THERMAL_TICK  = reg(helper, "thermal_tick");
			});
		});
	}

	private static SoundEvent reg(RegisterEvent.RegisterHelper<SoundEvent> helper, String name) {
		ResourceLocation id = new ResourceLocation(BurntMod.MODID, name);
		SoundEvent se = SoundEvent.createVariableRangeEvent(id);
		helper.register(id, se);
		return se;
	}
}
