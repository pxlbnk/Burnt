package net.pixelbank.burnt.init;

import net.pixelbank.burnt.entity.*;
import net.pixelbank.burnt.BurntMod;

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
			event.register(Registries.ENTITY_TYPE, helper -> {

				helper.register(new ResourceLocation(BurntMod.MODID, "spray"),
					SPRAY = EntityType.Builder.<SprayEntity>of(SprayEntity::new, MobCategory.MISC)
							.sized(0.5f, 0.5f)
							.clientTrackingRange(64)
							.updateInterval(1)
							.build("spray"));

				helper.register(new ResourceLocation(BurntMod.MODID, "visual_spray"),
					VISUAL_SPRAY = EntityType.Builder.<VisualSprayEntity>of(VisualSprayEntity::new, MobCategory.MISC)
							.sized(0.5f, 0.5f)
							.clientTrackingRange(64)
							.updateInterval(1)
							.build("visual_spray"));

				helper.register(new ResourceLocation(BurntMod.MODID, "fireball"),
					FIREBALL = EntityType.Builder.<FireballEntity>of(FireballEntity::new, MobCategory.MISC)
							.sized(0.5f, 0.5f)
							.clientTrackingRange(64)
							.updateInterval(1)
							.build("fireball"));

				helper.register(new ResourceLocation(BurntMod.MODID, "falling_slab"),
					FALLING_SLAB = EntityType.Builder.<FallingSlabEntity>of(FallingSlabEntity::new, MobCategory.CREATURE)
							.sized(0.6f, 1.8f)
							.clientTrackingRange(64)
							.updateInterval(3)
							.build("falling_slab"));

				helper.register(new ResourceLocation(BurntMod.MODID, "spark"),
					SPARK = EntityType.Builder.<SparkEntity>of(SparkEntity::new, MobCategory.MONSTER)
							.sized(0.6f, 1.8f)
							.clientTrackingRange(64)
							.updateInterval(3)
							.build("spark"));

				helper.register(new ResourceLocation(BurntMod.MODID, "flag"),
					FLAG = EntityType.Builder.<FlagEntity>of(FlagEntity::new, MobCategory.MONSTER)
							.sized(0.4f, 0.6f)
							.clientTrackingRange(256)
							.updateInterval(3)
							.build("flag"));

				helper.register(new ResourceLocation(BurntMod.MODID, "flag_black"),
					FLAG_BLACK = EntityType.Builder.<FlagBlackEntity>of(FlagBlackEntity::new, MobCategory.MONSTER)
							.sized(0.1f, 0.6f)
							.clientTrackingRange(256)
							.updateInterval(3)
							.build("flag_black"));

				helper.register(new ResourceLocation(BurntMod.MODID, "ember_projectile"),
					EMBER_PROJECTILE = EntityType.Builder.<EmberProjectileEntity>of(EmberProjectileEntity::new, MobCategory.MISC)
							.sized(0.1f, 0.1f)
							.clientTrackingRange(64)
							.updateInterval(1)
							.build("ember_projectile"));
			});		// closes event.register(Registries.ENTITY_TYPE, ...)
		});			// closes bus.addListener(RegisterEvent ...)

		// Register default attributes for mob entities (projectile/misc entities don't need these)
		bus.addListener((EntityAttributeCreationEvent event) -> {
			event.put(FALLING_SLAB, FallingSlabEntity.createAttributes().build());
			event.put(SPARK, SparkEntity.createAttributes().build());
			event.put(FLAG, FlagEntity.createAttributes().build());
			event.put(FLAG_BLACK, FlagBlackEntity.createAttributes().build());
		});
	}
}
