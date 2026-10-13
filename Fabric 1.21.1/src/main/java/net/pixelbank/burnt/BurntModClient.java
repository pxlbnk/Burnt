package net.pixelbank.burnt;

import net.pixelbank.burnt.entity.SprayEntity;
import net.pixelbank.burnt.entity.FireballEntity;
import net.pixelbank.burnt.entity.EmberProjectileEntity;
import net.pixelbank.burnt.init.BurntModEntities;
import net.pixelbank.burnt.init.BurntModBlockEntities;
import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.init.BurntModItems;
import net.pixelbank.burnt.init.BurntModMenus;
import net.pixelbank.burnt.client.gui.BurntBarrelGUIScreen;
import net.pixelbank.burnt.network.FireSkyPayload;
import net.pixelbank.burnt.network.SmokeFogPayload;
import net.pixelbank.burnt.network.MapVarsSyncPayload;
import net.pixelbank.burnt.network.MenuStateUpdateMessage;
import net.pixelbank.burnt.network.BurntModVariables.MapVariables;
import net.pixelbank.burnt.client.renderer.EmberProjectileRenderer;
import net.pixelbank.burnt.client.renderer.FallingSlabRenderer;
import net.pixelbank.burnt.client.renderer.SparkRenderer;
import net.pixelbank.burnt.client.renderer.FlagBlockEntityRenderer;
import net.pixelbank.burnt.client.model.ModelCustomModel;
import net.pixelbank.burnt.client.model.ModelSmoke;
import net.pixelbank.burnt.client.model.Modelflag;
import net.pixelbank.burnt.client.particle.*;
import net.pixelbank.burnt.procedures.FireExtinguisherPropertyValueProcedure;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.pixelbank.burnt.procedures.SkyProcedure;
import net.pixelbank.burnt.procedures.SmokeFogProcedure;
import net.minecraft.client.gui.screens.MenuScreens;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GrassColor;
import net.minecraft.client.renderer.BiomeColors;

@Environment(EnvType.CLIENT)
public class BurntModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Block render layers - Fabric ignores render_type in model JSON
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BROKEN_BLAZING_LOG, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BROKEN_BLAZING_LOG_2, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BROKEN_BURNT_LOG, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_HOLLOW_LOG, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.EMBER_HOLLOW_LOG, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BLAZING_HOLLOW_LOG, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_HOLLOW_LOG, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNING_CAVE_VINES_PLANT, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_AZALEA, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_BAMBOO_LOG, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_CACTUS, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_CAVE_VINES, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_CAVE_VINES_PLANT, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_HIGH_GRASS, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_LADDER, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_SCAFFOLDING, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_SUGAR_CANE, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_WOOD, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.DOUBLE_TALL_GRASS_FIRE, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMALL_BURNT_LOG, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMALL_SMOLDERING_LOG, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_AZALEA, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_CACTUS, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_CAVE_VINES, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_HIGH_GRASS, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_WOOD, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SOOTY_SMALL_LOG, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.RECOVERING_GRASS, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.RECOVERING_GRASS_SLAB, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.TALL_GRASS_BURNT, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.TALL_GRASS_FIRE, RenderType.cutout());
		// cutout_mipped - leaf blocks (required for shader wave-effect detection)
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BLAST_LEAVES,               RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_LEAVES,               RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_LEAVES,          RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_LEAVES_2,        RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_LEAVES_3,        RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_LEAVES_4,        RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_LEAVES_5,        RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_OAK_LEAVES,      RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_BIRCH_LEAVES,    RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_SPRUCE_LEAVES,   RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_ACACIA_LEAVES,   RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_JUNGLE_LEAVES,   RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_DARK_OAK_LEAVES, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_CHERRY_LEAVES,   RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_PALE_OAK_LEAVES,   RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_MANGROVE_LEAVES, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_MOSS,            RenderType.cutoutMipped());
		// cutout_mipped - fire/flame blocks, doors, trapdoors, crops, vines
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_ACACIA_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_ACACIA_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_BAMBOO_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_BAMBOO_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_BIRCH_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_BIRCH_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_CHERRY_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_PALE_OAK_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_CHERRY_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_PALE_OAK_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_CROPS, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_DARK_OAK_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_DARK_OAK_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_IVY, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_JUNGLE_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_JUNGLE_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_MANGROVE_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_MANGROVE_ROOTS, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_MANGROVE_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_MOSS_CARPET, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_OAK_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_OAK_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_PATH, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_SPRUCE_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_SPRUCE_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_VINE, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.FIRESTARTER, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.GRASS_FLAMES, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_ACACIA_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_ACACIA_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_BAMBOO_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_BAMBOO_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_BEETROOT, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_BIRCH_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_BIRCH_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_CARROTS, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_CHERRY_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_PALE_OAK_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_CHERRY_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_PALE_OAK_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_COBWEB, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_CROPS, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_DARK_OAK_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_DARK_OAK_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_GRASS_START, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_JUNGLE_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_JUNGLE_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_MANGROVE_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_MANGROVE_ROOTS, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_MANGROVE_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_MOSS_CARPET, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_OAK_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_OAK_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_POTATOES, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_SPRUCE_DOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_SPRUCE_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_TRAPDOOR, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_WHEAT, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.STAIRS_FIRE, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.STAIRS_FIRE_2, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.STAIRS_FIRE_3, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.STAIRS_FIRE_4, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.STAIRS_FIRE_5, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG_2, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.TALL_FLAMES, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.WOOD_FIRE, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.WOOD_FIRE_2, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.WOOD_FIRE_3, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.WOOD_FIRE_4, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.WOOD_FIRE_5, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.WOOD_FIRE_6, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.WOOD_FIRE_7, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.WOOD_FIRE_8, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.WOOD_FIRE_9, RenderType.cutoutMipped());
		// cutout_mipped - copper fire flame panels
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.COPPER_FIRE,   RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.COPPER_FIRE_2, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.COPPER_FIRE_3, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.COPPER_FIRE_4, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.COPPER_FIRE_5, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.COPPER_FIRE_6, RenderType.cutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.COPPER_FIRE_7, RenderType.cutoutMipped());
		// cutout - cross-model plant blocks
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.TALL_GRASS_FIRE, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.TALL_GRASS_BURNT, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SMOLDERING_FERN, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.BURNT_FERN, RenderType.cutout());
		// translucent - semi-transparent overlay blocks
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.FIRE_SET_BLOCK, RenderType.translucent());
		BlockRenderLayerMap.INSTANCE.putBlock(BurntModBlocks.SOOT_BLOCK, RenderType.translucent());

		// Entity renderers
		EntityRendererRegistry.register(BurntModEntities.SPRAY, ThrownItemRenderer::new);
		EntityRendererRegistry.register(BurntModEntities.VISUAL_SPRAY, ThrownItemRenderer::new);
		EntityRendererRegistry.register(BurntModEntities.FIREBALL,
				ctx -> new ArrowRenderer<FireballEntity>(ctx) {
					@Override public ResourceLocation getTextureLocation(FireballEntity e) {
						return ResourceLocation.withDefaultNamespace("textures/entity/projectiles/arrow.png");
					}
				});
		EntityRendererRegistry.register(BurntModEntities.EMBER_PROJECTILE, EmberProjectileRenderer::new);
		EntityRendererRegistry.register(BurntModEntities.FALLING_SLAB, FallingSlabRenderer::new);
		EntityRendererRegistry.register(BurntModEntities.SPARK, SparkRenderer::new);
		// Flags draw their cloth from a block entity renderer, not an entity renderer
		BlockEntityRendererRegistry.register(BurntModBlockEntities.FLAG, FlagBlockEntityRenderer::new);

		// Entity model layers
		EntityModelLayerRegistry.registerModelLayer(ModelCustomModel.LAYER_LOCATION, ModelCustomModel::createBodyLayer);
		EntityModelLayerRegistry.registerModelLayer(ModelSmoke.LAYER_LOCATION, ModelSmoke::createBodyLayer);
		EntityModelLayerRegistry.registerModelLayer(Modelflag.LAYER_LOCATION, Modelflag::createBodyLayer);

		// Particle factories
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.FIRE_PARTICLE, FireParticleParticle.FireParticleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.FLAMES, FlamesParticle.FlamesParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.FLAME_SMOKE, FlameSmokeParticle.FlameSmokeParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.LIGHT_FLAME_SMOKE, LightFlameSmokeParticle.LightFlameSmokeParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.VOLCANO_SMOKE, VolcanoSmokeParticle.VolcanoSmokeParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.BIG_EXPLOSION, BigExplosionParticle.BigExplosionParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.LARGE_STEAM_PARTICLE, LargeSteamParticleParticle.LargeSteamParticleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.MEDIUM_STEAM_PARTICLE, MediumSteamParticleParticle.MediumSteamParticleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.LARGE_FLAME_SMOKE, LargeFlameSmokeParticle.LargeFlameSmokeParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.FOG, FogParticle.FogParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.TALL_FOG, TallFogParticle.TallFogParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.THICK_FOG, ThickFogParticle.ThickFogParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.TEST_PARTICLE, TestParticleParticle.TestParticleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.BURNT_LEAVES_PARTICLE, BurntLeavesParticleParticle.BurntLeavesParticleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.SMOLDER_LEAVES_PARTICLE, SmolderLeavesParticleParticle.SmolderLeavesParticleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.SOOT_PARTICLE, SootParticleParticle.SootParticleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.MOSS_FIRE_PARTICLE, MossFireParticleParticle.MossFireParticleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.BLUE_FLAME_PARTICLE, BlueFlameParticleParticle.BlueFlameParticleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.FLESH_BUBBLE, FleshBubbleParticle.FleshBubbleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.AIR_PARTICLE, AirParticleParticle.AirParticleParticleProvider::new);
		ParticleFactoryRegistry.getInstance().register(BurntModParticleTypes.TORCH_FIRE_PARTICLE, TorchFireParticleParticle.TorchFireParticleParticleProvider::new);

		// Block color handlers (biome-tinted grass)
		ColorProviderRegistry.BLOCK.register(
				(state, world, pos, tintIndex) ->
						world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D),
				BurntModBlocks.BURNT_GRASS,
				BurntModBlocks.SMOLDERING_GRASS_START,
				BurntModBlocks.RECOVERING_GRASS,
				BurntModBlocks.RECOVERING_GRASS_SLAB,
				BurntModBlocks.BURNT_GRASSY_SANDY_D_IRT,
				BurntModBlocks.BURNT_GRASSY_EARTHEN_CLAY);

		// Item color handlers
		ColorProviderRegistry.ITEM.register(
				(stack, tintIndex) -> GrassColor.get(0.5D, 1.0D),
				BurntModBlocks.SMOLDERING_GRASS_START);

		// Item property predicates
		ItemProperties.register(BurntModItems.EXTINGUISHER,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "extinguisher_extinguishing"),
				(stack, world, entity, seed) ->
						entity != null ? (float) FireExtinguisherPropertyValueProcedure.execute(entity) : 0f);

		// GUI screen registration
		MenuScreens.register(BurntModMenus.BURNT_BARREL_GUI, BurntBarrelGUIScreen::new);

		// S2C packet handlers
		ClientPlayNetworking.registerGlobalReceiver(MapVarsSyncPayload.TYPE,
				(payload, context) -> MapVariables.clientSide = payload.data());
		ClientPlayNetworking.registerGlobalReceiver(MenuStateUpdateMessage.TYPE,
				MenuStateUpdateMessage::handleMenuState);
		ClientPlayNetworking.registerGlobalReceiver(FireSkyPayload.TYPE,
				(payload, context) -> SkyProcedure.targetIntensity = payload.intensity());
		ClientPlayNetworking.registerGlobalReceiver(SmokeFogPayload.TYPE,
				(payload, context) -> SmokeFogProcedure.targetIntensity = payload.intensity());

		// Red sky overlay near fire, plus the smoke fog lerp so both effects stay in sync
		WorldRenderEvents.BEFORE_ENTITIES.register(context -> {
			if (context.world() != null) {
				SkyProcedure.execute(context.world(), context);
				SmokeFogProcedure.tick();
			}
		});

		BurntMod.LOGGER.info("Burnt Basic client initialized.");
	}
}
