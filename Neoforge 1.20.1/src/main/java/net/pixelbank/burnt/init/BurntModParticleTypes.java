package net.pixelbank.burnt.init;

import net.pixelbank.burnt.BurntMod;

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
	public static SimpleParticleType VOLCANO_SMOKE;
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
			event.register(Registries.PARTICLE_TYPE, helper -> {
				helper.register(new ResourceLocation(BurntMod.MODID, "fire_particle"),
						FIRE_PARTICLE           = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "flames"),
						FLAMES                  = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "flame_smoke"),
						FLAME_SMOKE             = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "light_flame_smoke"),
						LIGHT_FLAME_SMOKE       = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "volcano_smoke"),
						VOLCANO_SMOKE           = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "big_explosion"),
						BIG_EXPLOSION           = new SimpleParticleType(true) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "large_steam_particle"),
						LARGE_STEAM_PARTICLE    = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "medium_steam_particle"),
						MEDIUM_STEAM_PARTICLE   = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "large_flame_smoke"),
						LARGE_FLAME_SMOKE       = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "fog"),
						FOG                     = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "tall_fog"),
						TALL_FOG                = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "thick_fog"),
						THICK_FOG               = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "test_particle"),
						TEST_PARTICLE           = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "burnt_leaves_particle"),
						BURNT_LEAVES_PARTICLE   = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "smolder_leaves_particle"),
						SMOLDER_LEAVES_PARTICLE = new SimpleParticleType(true) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "soot_particle"),
						SOOT_PARTICLE           = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "moss_fire_particle"),
						MOSS_FIRE_PARTICLE      = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "blue_flame_particle"),
						BLUE_FLAME_PARTICLE     = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "flesh_bubble"),
						FLESH_BUBBLE            = new SimpleParticleType(true) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "air_particle"),
						AIR_PARTICLE            = new SimpleParticleType(false) {});
				helper.register(new ResourceLocation(BurntMod.MODID, "torch_fire_particle"),
						TORCH_FIRE_PARTICLE     = new SimpleParticleType(false) {});
			});
		});
	}
}
