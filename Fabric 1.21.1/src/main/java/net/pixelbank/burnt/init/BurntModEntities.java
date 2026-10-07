package net.pixelbank.burnt.init;

import net.pixelbank.burnt.entity.*;
import net.pixelbank.burnt.BurntMod;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class BurntModEntities {
	public static EntityType<SprayEntity> SPRAY;
	public static EntityType<VisualSprayEntity> VISUAL_SPRAY;
	public static EntityType<FireballEntity> FIREBALL;
	public static EntityType<FallingSlabEntity> FALLING_SLAB;
	public static EntityType<SparkEntity> SPARK;
	public static EntityType<FlagEntity> FLAG;
	public static EntityType<FlagBlackEntity> FLAG_BLACK;
	public static EntityType<EmberProjectileEntity> EMBER_PROJECTILE;

	public static void register() {
		SPRAY = Registry.register(BuiltInRegistries.ENTITY_TYPE,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "spray"),
				FabricEntityTypeBuilder.<SprayEntity>create(MobCategory.MISC, SprayEntity::new)
						.dimensions(EntityDimensions.scalable(0.5f, 0.5f))
						.trackRangeBlocks(64).trackedUpdateRate(1).forceTrackedVelocityUpdates(true)
						.build());

		VISUAL_SPRAY = Registry.register(BuiltInRegistries.ENTITY_TYPE,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "visual_spray"),
				FabricEntityTypeBuilder.<VisualSprayEntity>create(MobCategory.MISC, VisualSprayEntity::new)
						.dimensions(EntityDimensions.scalable(0.5f, 0.5f))
						.trackRangeBlocks(64).trackedUpdateRate(1).forceTrackedVelocityUpdates(true)
						.build());

		FIREBALL = Registry.register(BuiltInRegistries.ENTITY_TYPE,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "fireball"),
				FabricEntityTypeBuilder.<FireballEntity>create(MobCategory.MISC, FireballEntity::new)
						.dimensions(EntityDimensions.scalable(0.5f, 0.5f))
						.trackRangeBlocks(64).trackedUpdateRate(1).forceTrackedVelocityUpdates(true)
						.build());

		FALLING_SLAB = Registry.register(BuiltInRegistries.ENTITY_TYPE,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "falling_slab"),
				FabricEntityTypeBuilder.<FallingSlabEntity>create(MobCategory.CREATURE, FallingSlabEntity::new)
						.dimensions(EntityDimensions.scalable(0.6f, 1.8f))
						.trackRangeBlocks(64).trackedUpdateRate(3).forceTrackedVelocityUpdates(true)
						.build());

		SPARK = Registry.register(BuiltInRegistries.ENTITY_TYPE,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "spark"),
				FabricEntityTypeBuilder.<SparkEntity>create(MobCategory.MONSTER, SparkEntity::new)
						.dimensions(EntityDimensions.scalable(0.6f, 1.8f))
						.trackRangeBlocks(64).trackedUpdateRate(3).forceTrackedVelocityUpdates(true)
						.build());

		FLAG = Registry.register(BuiltInRegistries.ENTITY_TYPE,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "flag"),
				FabricEntityTypeBuilder.<FlagEntity>create(MobCategory.MONSTER, FlagEntity::new)
						.dimensions(EntityDimensions.scalable(0.4f, 0.6f))
						.trackRangeBlocks(256).trackedUpdateRate(3).forceTrackedVelocityUpdates(true)
						.build());

		FLAG_BLACK = Registry.register(BuiltInRegistries.ENTITY_TYPE,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "flag_black"),
				FabricEntityTypeBuilder.<FlagBlackEntity>create(MobCategory.MONSTER, FlagBlackEntity::new)
						.dimensions(EntityDimensions.scalable(0.1f, 0.6f))
						.trackRangeBlocks(256).trackedUpdateRate(3).forceTrackedVelocityUpdates(true)
						.build());

		EMBER_PROJECTILE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_projectile"),
				FabricEntityTypeBuilder.<EmberProjectileEntity>create(MobCategory.MISC, EmberProjectileEntity::new)
						.dimensions(EntityDimensions.scalable(0.1f, 0.1f))
						.trackRangeBlocks(64).trackedUpdateRate(1).forceTrackedVelocityUpdates(true)
						.build());

		// Register default attributes for mob entities (projectile entities don't need these)
		FabricDefaultAttributeRegistry.register(FALLING_SLAB, FallingSlabEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(SPARK, SparkEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(FLAG, FlagEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(FLAG_BLACK, FlagBlackEntity.createAttributes());
	}
}
