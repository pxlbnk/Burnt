package net.pixelbank.burnt.init;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;

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

	public static void register() {
		FIRE_PARTICLE           = register("fire_particle",           false);
		FLAMES                  = register("flames",                  false);
		FLAME_SMOKE             = register("flame_smoke",             false);
		LIGHT_FLAME_SMOKE       = register("light_flame_smoke",       false);
		BIG_EXPLOSION           = register("big_explosion",           true);
		LARGE_STEAM_PARTICLE    = register("large_steam_particle",    false);
		MEDIUM_STEAM_PARTICLE   = register("medium_steam_particle",   false);
		LARGE_FLAME_SMOKE       = register("large_flame_smoke",       false);
		FOG                     = register("fog",                     false);
		TALL_FOG                = register("tall_fog",                false);
		THICK_FOG               = register("thick_fog",               false);
		TEST_PARTICLE           = register("test_particle",           false);
		BURNT_LEAVES_PARTICLE   = register("burnt_leaves_particle",   false);
		SMOLDER_LEAVES_PARTICLE = register("smolder_leaves_particle", true);
		SOOT_PARTICLE           = register("soot_particle",           false);
		MOSS_FIRE_PARTICLE      = register("moss_fire_particle",      false);
		BLUE_FLAME_PARTICLE     = register("blue_flame_particle",     false);
		FLESH_BUBBLE            = register("flesh_bubble",            true);
		AIR_PARTICLE            = register("air_particle",            false);
		TORCH_FIRE_PARTICLE     = register("torch_fire_particle",     false);
	}

	private static SimpleParticleType register(String name, boolean overrideLimiter) {
		return Registry.register(BuiltInRegistries.PARTICLE_TYPE,
				new ResourceLocation(BurntMod.MODID, name),
				new SimpleParticleType(overrideLimiter) {});
	}
}
