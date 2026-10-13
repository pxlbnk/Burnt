package net.pixelbank.burnt;

import net.pixelbank.burnt.entity.FireballEntity;
import net.pixelbank.burnt.init.BurntModEntities;
import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.init.BurntModItems;
import net.pixelbank.burnt.init.BurntModMenus;
import net.pixelbank.burnt.client.gui.BurntBarrelGUIScreen;
import net.pixelbank.burnt.network.FireSkyPayload;
import net.pixelbank.burnt.network.SmokeFogPayload;
import net.pixelbank.burnt.client.renderer.EmberProjectileRenderer;
import net.pixelbank.burnt.client.renderer.FallingSlabRenderer;
import net.pixelbank.burnt.client.renderer.SparkRenderer;
import net.pixelbank.burnt.client.renderer.FlagRenderer;
import net.pixelbank.burnt.client.renderer.FlagBlackRenderer;
import net.pixelbank.burnt.client.model.ModelCustomModel;
import net.pixelbank.burnt.client.model.ModelSmoke;
import net.pixelbank.burnt.client.model.Modelflag;
import net.pixelbank.burnt.client.particle.*;
import net.pixelbank.burnt.procedures.FireExtinguisherPropertyValueProcedure;
import net.pixelbank.burnt.procedures.SkyProcedure;
import net.pixelbank.burnt.procedures.SmokeFogProcedure;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraft.util.Mth;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GrassColor;
import net.minecraft.client.renderer.BiomeColors;

import me.shedaniel.autoconfig.AutoConfig;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

@Mod.EventBusSubscriber(modid = BurntMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BurntModClient {

	// Client setup - render layers, menu screens, item properties, payload delegates

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			// Block render layers - cutout (plant-like/thin blocks)
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BROKEN_BLAZING_LOG,       RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BROKEN_BLAZING_LOG_2,     RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BROKEN_BURNT_LOG,         RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_HOLLOW_LOG,       RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.EMBER_HOLLOW_LOG,       RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BLAZING_HOLLOW_LOG,       RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_HOLLOW_LOG,       RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNING_CAVE_VINES_PLANT, RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_AZALEA,             RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_BAMBOO_LOG,         RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_CACTUS,             RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_CAVE_VINES,         RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_CAVE_VINES_PLANT,   RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_HIGH_GRASS,         RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_LADDER,             RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_SCAFFOLDING,        RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_SUGAR_CANE,         RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_WOOD,               RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.DOUBLE_TALL_GRASS_FIRE,   RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMALL_BURNT_LOG,          RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMALL_SMOLDERING_LOG,     RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_AZALEA,        RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_CACTUS,        RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_CAVE_VINES,    RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT, RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_HIGH_GRASS,    RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_WOOD,          RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SOOTY_SMALL_LOG,          RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.TALL_GRASS_BURNT,         RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.TALL_GRASS_FIRE,          RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_FERN,          RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_FERN,               RenderType.cutout());
			// cutout_mipped - leaf blocks
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BLAST_LEAVES,               RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_LEAVES,               RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_LEAVES,          RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_LEAVES_2,        RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_LEAVES_3,        RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_LEAVES_4,        RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_LEAVES_5,        RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_OAK_LEAVES,      RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_BIRCH_LEAVES,    RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_SPRUCE_LEAVES,   RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_ACACIA_LEAVES,   RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_JUNGLE_LEAVES,   RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_DARK_OAK_LEAVES, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_CHERRY_LEAVES,   RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_PALE_OAK_LEAVES,   RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_MANGROVE_LEAVES, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_MOSS,            RenderType.cutoutMipped());
			// cutout_mipped - fire/flame blocks, doors, trapdoors, crops, vines
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_ACACIA_DOOR,          RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_ACACIA_TRAPDOOR,      RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_BAMBOO_DOOR,          RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_BAMBOO_TRAPDOOR,      RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_BIRCH_DOOR,           RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_BIRCH_TRAPDOOR,       RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_CHERRY_DOOR,          RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_PALE_OAK_DOOR,          RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_CHERRY_TRAPDOOR,      RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_PALE_OAK_TRAPDOOR,      RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_CROPS,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_DARK_OAK_DOOR,        RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_DARK_OAK_TRAPDOOR,    RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_DOOR,                 RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_IVY,                  RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_JUNGLE_DOOR,          RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_JUNGLE_TRAPDOOR,      RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_MANGROVE_DOOR,        RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_MANGROVE_ROOTS,       RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_MANGROVE_TRAPDOOR,    RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_MOSS_CARPET,          RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_OAK_DOOR,             RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_OAK_TRAPDOOR,         RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_PATH,                 RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_SPRUCE_DOOR,          RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_SPRUCE_TRAPDOOR,      RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_TRAPDOOR,             RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.BURNT_VINE,                 RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.FIRESTARTER,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.GRASS_FLAMES,               RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_ACACIA_DOOR,     RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_ACACIA_TRAPDOOR, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_BAMBOO_DOOR,     RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_BAMBOO_TRAPDOOR, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_BEETROOT,        RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_BIRCH_DOOR,      RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_BIRCH_TRAPDOOR,  RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_CARROTS,         RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_CHERRY_DOOR,     RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_PALE_OAK_DOOR,     RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_CHERRY_TRAPDOOR, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_PALE_OAK_TRAPDOOR, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_COBWEB,          RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_CROPS,           RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_DARK_OAK_DOOR,   RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_DARK_OAK_TRAPDOOR, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_DOOR,            RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_GRASS_START,     RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_JUNGLE_DOOR,     RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_JUNGLE_TRAPDOOR, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_MANGROVE_DOOR,   RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_MANGROVE_ROOTS,  RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_MANGROVE_TRAPDOOR, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_MOSS_CARPET,     RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_OAK_DOOR,        RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_OAK_TRAPDOOR,    RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_POTATOES,        RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_SPRUCE_DOOR,     RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_SPRUCE_TRAPDOOR, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_TRAPDOOR,        RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SMOLDERING_WHEAT,           RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.STAIRS_FIRE,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.STAIRS_FIRE_2,              RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.STAIRS_FIRE_3,              RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.STAIRS_FIRE_4,              RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.STAIRS_FIRE_5,              RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG,  RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG_2, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.TALL_FLAMES,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.WOOD_FIRE,                  RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.WOOD_FIRE_2,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.WOOD_FIRE_3,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.WOOD_FIRE_4,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.WOOD_FIRE_5,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.WOOD_FIRE_6,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.WOOD_FIRE_7,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.WOOD_FIRE_8,                RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.WOOD_FIRE_9,                RenderType.cutoutMipped());
			// cutout_mipped - copper fire flame panels
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.COPPER_FIRE,   RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.COPPER_FIRE_2, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.COPPER_FIRE_3, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.COPPER_FIRE_4, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.COPPER_FIRE_5, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.COPPER_FIRE_6, RenderType.cutoutMipped());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.COPPER_FIRE_7, RenderType.cutoutMipped());
			// translucent - semi-transparent overlay blocks
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.FIRE_SET_BLOCK, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(BurntModBlocks.SOOT_BLOCK,     RenderType.translucent());

			// Menu screens
			MenuScreens.register(BurntModMenus.BURNT_BARREL_GUI, BurntBarrelGUIScreen::new);

			// Item property predicates
			ItemProperties.register(BurntModItems.EXTINGUISHER,
					new ResourceLocation(BurntMod.MODID, "extinguisher_extinguishing"),
					(stack, world, entity, seed) ->
							entity != null ? (float) FireExtinguisherPropertyValueProcedure.execute(entity) : 0f);

			// Register config screen - makes the "Config" button appear in Forge's mod list
			ModList.get().getModContainerById(BurntMod.MODID).ifPresent(container ->
					container.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
							() -> new ConfigScreenHandler.ConfigScreenFactory(
									(c, screen) -> AutoConfig.getConfigScreen(BurntBasicConfigConfiguration.class, screen).get())));
		});

		// Wire the visual payload delegates - these lambdas only touch client-only fields
		FireSkyPayload.clientHandler  = intensity -> SkyProcedure.targetIntensity      = intensity;
		SmokeFogPayload.clientHandler = intensity -> SmokeFogProcedure.targetIntensity = intensity;

		// Register game-bus render event (sky overlay + smoke fog lerp)
		MinecraftForge.EVENT_BUS.addListener(BurntModClient::onRenderLevelStage);
		// Smoke fog has to use ViewportEvent.RenderFog on Forge
		// A mixin at setupFog TAIL gets clobbered by Forge's own fog event firing afterwards
		MinecraftForge.EVENT_BUS.addListener(BurntModClient::onRenderFog);

		BurntMod.LOGGER.info("Burnt Basic client initialized.");
	}

	// Sky overlay and the smoke fog lerp both run every AFTER_SKY frame

	private static void onRenderLevelStage(RenderLevelStageEvent event) {
		if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_SKY) return;
		var level = Minecraft.getInstance().level;
		if (level != null) {
			SkyProcedure.execute(level, event);
			SmokeFogProcedure.tick();
		}
	}

	// Overrides shader fog distance from SmokeFogProcedure.currentIntensity
	// The supported override point on Forge - a setupFog mixin gets clobbered by Forge's own fog event
	private static void onRenderFog(ViewportEvent.RenderFog event) {
		float intensity = SmokeFogProcedure.currentIntensity;
		// Below 0.05 the fog shift is sub-perceptible on a clear day, but still shows with shaders
		// SmokeFogProcedure snaps to exact 0 below this, so the early return fires reliably
		if (intensity < 0.05f) return;

		int configLevel = BurntBasicConfigConfiguration.SMOKE_FOG_INTENSITY.get();
		if (configLevel <= 0) return;

		// Rescaled so level 5 matches the old level 3 - x^1.523, so 5 is ~38 blocks and 10 is ~16
		float minFogBlocks = 200f / (float) Math.pow(configLevel * 0.6f, 1.523);

		float vanillaNear = event.getNearPlaneDistance();
		float vanillaFar  = event.getFarPlaneDistance();
		// Lerp near and far so the gradient eases back to vanilla - locking near=0 made fog cling to the camera
		float fogNear = Mth.lerp(intensity, vanillaNear, 0f);
		float fogFar  = Mth.lerp(intensity, vanillaFar,  minFogBlocks);
		event.setNearPlaneDistance(fogNear);
		event.setFarPlaneDistance(fogFar);
		event.setCanceled(true); // prevent later listeners from overriding our values
	}

	// EntityRenderersEvent.RegisterRenderers - mod bus

	@SubscribeEvent
	public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BurntModEntities.SPRAY, ThrownItemRenderer::new);
		event.registerEntityRenderer(BurntModEntities.VISUAL_SPRAY, ThrownItemRenderer::new);
		event.registerEntityRenderer(BurntModEntities.FIREBALL,
				ctx -> new ArrowRenderer<FireballEntity>(ctx) {
					@Override public ResourceLocation getTextureLocation(FireballEntity e) {
						return new ResourceLocation("textures/entity/projectiles/arrow.png");
					}
				});
		event.registerEntityRenderer(BurntModEntities.EMBER_PROJECTILE, EmberProjectileRenderer::new);
		event.registerEntityRenderer(BurntModEntities.FALLING_SLAB, FallingSlabRenderer::new);
		event.registerEntityRenderer(BurntModEntities.SPARK, SparkRenderer::new);
		event.registerEntityRenderer(BurntModEntities.FLAG, FlagRenderer::new);
		event.registerEntityRenderer(BurntModEntities.FLAG_BLACK, FlagBlackRenderer::new);
	}

	// EntityRenderersEvent.RegisterLayerDefinitions - mod bus

	@SubscribeEvent
	public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelCustomModel.LAYER_LOCATION, ModelCustomModel::createBodyLayer);
		event.registerLayerDefinition(ModelSmoke.LAYER_LOCATION, ModelSmoke::createBodyLayer);
		event.registerLayerDefinition(Modelflag.LAYER_LOCATION, Modelflag::createBodyLayer);
	}

	// RegisterParticleProvidersEvent - mod bus

	@SubscribeEvent
	public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(BurntModParticleTypes.FIRE_PARTICLE,        FireParticleParticle.FireParticleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.FLAMES,               FlamesParticle.FlamesParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.FLAME_SMOKE,          FlameSmokeParticle.FlameSmokeParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.LIGHT_FLAME_SMOKE,    LightFlameSmokeParticle.LightFlameSmokeParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.VOLCANO_SMOKE,        VolcanoSmokeParticle.VolcanoSmokeParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.BIG_EXPLOSION,        BigExplosionParticle.BigExplosionParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.LARGE_STEAM_PARTICLE, LargeSteamParticleParticle.LargeSteamParticleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.MEDIUM_STEAM_PARTICLE, MediumSteamParticleParticle.MediumSteamParticleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.LARGE_FLAME_SMOKE,    LargeFlameSmokeParticle.LargeFlameSmokeParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.FOG,                  FogParticle.FogParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.TALL_FOG,             TallFogParticle.TallFogParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.THICK_FOG,            ThickFogParticle.ThickFogParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.TEST_PARTICLE,        TestParticleParticle.TestParticleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.BURNT_LEAVES_PARTICLE, BurntLeavesParticleParticle.BurntLeavesParticleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.SMOLDER_LEAVES_PARTICLE, SmolderLeavesParticleParticle.SmolderLeavesParticleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.SOOT_PARTICLE,        SootParticleParticle.SootParticleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.MOSS_FIRE_PARTICLE,   MossFireParticleParticle.MossFireParticleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.BLUE_FLAME_PARTICLE,  BlueFlameParticleParticle.BlueFlameParticleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.FLESH_BUBBLE,         FleshBubbleParticle.FleshBubbleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.AIR_PARTICLE,         AirParticleParticle.AirParticleParticleProvider::new);
		event.registerSpriteSet(BurntModParticleTypes.TORCH_FIRE_PARTICLE,  TorchFireParticleParticle.TorchFireParticleParticleProvider::new);
	}

	// RegisterColorHandlersEvent - mod bus

	@SubscribeEvent
	public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register(
				(state, world, pos, tintIndex) ->
						world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D),
				BurntModBlocks.BURNT_GRASS,
				BurntModBlocks.SMOLDERING_GRASS_START,
				BurntModBlocks.RECOVERING_GRASS,
				BurntModBlocks.RECOVERING_GRASS_SLAB,
				BurntModBlocks.BURNT_GRASSY_SANDY_D_IRT,
				BurntModBlocks.BURNT_GRASSY_EARTHEN_CLAY);
	}

	@SubscribeEvent
	public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
		event.register(
				(stack, tintIndex) -> GrassColor.get(0.5D, 1.0D),
				BurntModBlocks.SMOLDERING_GRASS_START);
	}
}
