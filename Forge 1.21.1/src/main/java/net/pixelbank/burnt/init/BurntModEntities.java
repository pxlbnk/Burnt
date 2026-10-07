package net.pixelbank.burnt.init;

import net.pixelbank.burnt.entity.*;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.RegisterEvent;

public class BurntModEntities {
	public static EntityType<SprayEntity> SPRAY;
	public static EntityType<VisualSprayEntity> VISUAL_SPRAY;
	public static EntityType<FireballEntity> FIREBALL;
	public static EntityType<FallingSlabEntity> FALLING_SLAB;
	public static EntityType<SparkEntity> SPARK;
	public static EntityType<FlagEntity> FLAG;
	public static EntityType<FlagBlackEntity> FLAG_BLACK;
	public static EntityType<EmberProjectileEntity> EMBER_PROJECTILE;

	public static void register(IEventBus bus) {
		bus.addListener((RegisterEvent event) -> {
			if (!event.getRegistryKey().equals(Registries.ENTITY_TYPE)) return;

			SPRAY = Registry.register(BuiltInRegistries.ENTITY_TYPE,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "spray"),
					EntityType.Builder.<SprayEntity>of(SprayEntity::new, MobCategory.MISC)
							.sized(0.5f, 0.5f)
							.clientTrackingRange(64)
							.updateInterval(1)
							.build("spray"));

			VISUAL_SPRAY = Registry.register(BuiltInRegistries.ENTITY_TYPE,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "visual_spray"),
					EntityType.Builder.<VisualSprayEntity>of(VisualSprayEntity::new, MobCategory.MISC)
							.sized(0.5f, 0.5f)
							.clientTrackingRange(64)
							.updateInterval(1)
							.build("visual_spray"));

			FIREBALL = Registry.register(BuiltInRegistries.ENTITY_TYPE,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "fireball"),
					EntityType.Builder.<FireballEntity>of(FireballEntity::new, MobCategory.MISC)
							.sized(0.5f, 0.5f)
							.clientTrackingRange(64)
							.updateInterval(1)
							.build("fireball"));

			FALLING_SLAB = Registry.register(BuiltInRegistries.ENTITY_TYPE,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "falling_slab"),
					EntityType.Builder.<FallingSlabEntity>of(FallingSlabEntity::new, MobCategory.CREATURE)
							.sized(0.6f, 1.8f)
							.clientTrackingRange(64)
							.updateInterval(3)
							.build("falling_slab"));

			SPARK = Registry.register(BuiltInRegistries.ENTITY_TYPE,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "spark"),
					EntityType.Builder.<SparkEntity>of(SparkEntity::new, MobCategory.MONSTER)
							.sized(0.6f, 1.8f)
							.clientTrackingRange(64)
							.updateInterval(3)
							.build("spark"));

			FLAG = Registry.register(BuiltInRegistries.ENTITY_TYPE,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "flag"),
					EntityType.Builder.<FlagEntity>of(FlagEntity::new, MobCategory.MONSTER)
							.sized(0.4f, 0.6f)
							.clientTrackingRange(256)
							.updateInterval(3)
							.build("flag"));

			FLAG_BLACK = Registry.register(BuiltInRegistries.ENTITY_TYPE,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "flag_black"),
					EntityType.Builder.<FlagBlackEntity>of(FlagBlackEntity::new, MobCategory.MONSTER)
							.sized(0.1f, 0.6f)
							.clientTrackingRange(256)
							.updateInterval(3)
							.build("flag_black"));

			EMBER_PROJECTILE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_projectile"),
					EntityType.Builder.<EmberProjectileEntity>of(EmberProjectileEntity::new, MobCategory.MISC)
							.sized(0.1f, 0.1f)
							.clientTrackingRange(64)
							.updateInterval(1)
							.build("ember_projectile"));
		});

		// Register default attributes for mob entities (projectile/misc entities don't need these)
		bus.addListener((EntityAttributeCreationEvent event) -> {
			event.put(FALLING_SLAB, FallingSlabEntity.createAttributes().build());
			event.put(SPARK, SparkEntity.createAttributes().build());
			event.put(FLAG, FlagEntity.createAttributes().build());
			event.put(FLAG_BLACK, FlagBlackEntity.createAttributes().build());
		});
	}
}
