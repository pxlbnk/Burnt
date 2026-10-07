package net.pixelbank.burnt.init;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;

public class BurntModParticleTypes {
	public static SimpleParticleType FIRE_PARTICLE;
	public static SimpleParticleType FLAMES;
	public static SimpleParticleType FLAME_SMOKE;
	public static SimpleParticleType LIGHT_FLAME_SMOKE;
	public static SimpleParticleType BIG_EXPLOSION;
	public static SimpleParticleType LARGE_STEAM_PARTICLE;
	public static SimpleParticleType MEDIUM_STEAM_PARTICLE;
	public static SimpleParticleType LARGE_FLAME_SMOKE;
	public static SimpleParticleType FOG;
	public static SimpleParticleType TALL_FOG;
	public static SimpleParticleType THICK_FOG;
	public static SimpleParticleType TEST_PARTICLE;
	public static SimpleParticleType BURNT_LEAVES_PARTICLE;
	public static SimpleParticleType SMOLDER_LEAVES_PARTICLE;
	public static SimpleParticleType SOOT_PARTICLE;
	public static SimpleParticleType MOSS_FIRE_PARTICLE;
	public static SimpleParticleType BLUE_FLAME_PARTICLE;
	public static SimpleParticleType FLESH_BUBBLE;
	public static SimpleParticleType AIR_PARTICLE;
	public static SimpleParticleType TORCH_FIRE_PARTICLE;

	public static void register(IEventBus bus) {
		bus.addListener((RegisterEvent event) -> {
			if (!event.getRegistryKey().equals(Registries.PARTICLE_TYPE)) return;
			FIRE_PARTICLE           = reg(event, "fire_particle",           false);
			FLAMES                  = reg(event, "flames",                  false);
			FLAME_SMOKE             = reg(event, "flame_smoke",             false);
			LIGHT_FLAME_SMOKE       = reg(event, "light_flame_smoke",       false);
			BIG_EXPLOSION           = reg(event, "big_explosion",           true);
			LARGE_STEAM_PARTICLE    = reg(event, "large_steam_particle",    false);
			MEDIUM_STEAM_PARTICLE   = reg(event, "medium_steam_particle",   false);
			LARGE_FLAME_SMOKE       = reg(event, "large_flame_smoke",       false);
			FOG                     = reg(event, "fog",                     false);
			TALL_FOG                = reg(event, "tall_fog",                false);
			THICK_FOG               = reg(event, "thick_fog",               false);
			TEST_PARTICLE           = reg(event, "test_particle",           false);
			BURNT_LEAVES_PARTICLE   = reg(event, "burnt_leaves_particle",   false);
			SMOLDER_LEAVES_PARTICLE = reg(event, "smolder_leaves_particle", true);
			SOOT_PARTICLE           = reg(event, "soot_particle",           false);
			MOSS_FIRE_PARTICLE      = reg(event, "moss_fire_particle",      false);
			BLUE_FLAME_PARTICLE     = reg(event, "blue_flame_particle",     false);
			FLESH_BUBBLE            = reg(event, "flesh_bubble",            true);
			AIR_PARTICLE            = reg(event, "air_particle",            false);
			TORCH_FIRE_PARTICLE     = reg(event, "torch_fire_particle",     false);
		});
	}

	private static SimpleParticleType reg(RegisterEvent event, String name, boolean overrideLimiter) {
		ResourceLocation id = new ResourceLocation(BurntMod.MODID, name);
		SimpleParticleType pt = new SimpleParticleType(overrideLimiter) {};
		event.register(Registries.PARTICLE_TYPE, id, () -> pt);
		return pt;
	}
}
