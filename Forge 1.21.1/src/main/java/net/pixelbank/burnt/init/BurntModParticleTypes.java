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
			FIRE_PARTICLE           = reg("fire_particle",           false);
			FLAMES                  = reg("flames",                  false);
			FLAME_SMOKE             = reg("flame_smoke",             false);
			LIGHT_FLAME_SMOKE       = reg("light_flame_smoke",       false);
			BIG_EXPLOSION           = reg("big_explosion",           true);
			LARGE_STEAM_PARTICLE    = reg("large_steam_particle",    false);
			MEDIUM_STEAM_PARTICLE   = reg("medium_steam_particle",   false);
			LARGE_FLAME_SMOKE       = reg("large_flame_smoke",       false);
			FOG                     = reg("fog",                     false);
			TALL_FOG                = reg("tall_fog",                false);
			THICK_FOG               = reg("thick_fog",               false);
			TEST_PARTICLE           = reg("test_particle",           false);
			BURNT_LEAVES_PARTICLE   = reg("burnt_leaves_particle",   false);
			SMOLDER_LEAVES_PARTICLE = reg("smolder_leaves_particle", true);
			SOOT_PARTICLE           = reg("soot_particle",           false);
			MOSS_FIRE_PARTICLE      = reg("moss_fire_particle",      false);
			BLUE_FLAME_PARTICLE     = reg("blue_flame_particle",     false);
			FLESH_BUBBLE            = reg("flesh_bubble",            true);
			AIR_PARTICLE            = reg("air_particle",            false);
			TORCH_FIRE_PARTICLE     = reg("torch_fire_particle",     false);
		});
	}

	private static SimpleParticleType reg(String name, boolean overrideLimiter) {
		return Registry.register(BuiltInRegistries.PARTICLE_TYPE,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, name),
				new SimpleParticleType(overrideLimiter) {});
	}
}
