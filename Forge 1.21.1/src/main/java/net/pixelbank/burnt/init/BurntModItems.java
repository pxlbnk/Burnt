package net.pixelbank.burnt.init;

import net.pixelbank.burnt.item.*;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;

public class BurntModItems {

	public static Item BURNT_LEAVES;
	public static Item SMOLDERING_LEAVES;
	public static Item BURNT_LOG;
	public static Item SMOLDERING_LOG;
	public static Item SMOLDERING_HOLLOW_LOG;
	public static Item EMBER_HOLLOW_LOG;
	public static Item BLAZING_HOLLOW_LOG;
	public static Item BURNT_HOLLOW_LOG;
	public static Item SMALL_SMOLDERING_LOG;
	public static Item BURNT_GRASS;
	public static Item BURNT_DIRT;
	public static Item SMALL_BURNT_LOG;
	public static Item GRASS_FLAMES;
	public static Item SMOLDERING_GRASS;
	public static Item TALL_GRASS_FIRE;
	public static Item SMOLDERING_FERN;
	public static Item BROKEN_BURNT_LOG;
	public static Item BURNT_VINE;
	public static Item BURNT_MANGROVE_ROOTS;
	public static Item TALL_GRASS_BURNT;
	public static Item BURNT_FERN;
	public static Item BURNT_PATH;
	public static Item STRIPPED_BURNT_LOG;
	public static Item SMOLDERING_MANGROVE_ROOTS;
	public static Item STRIPPED_BROKEN_BURNT_LOG;
	public static Item FIRE_BARREL;
	public static Item BURNT_FARMLAND;
	public static Item BURNT_FARMLAND_MOIST;
	public static Item BURNT_PLANKS;
	public static Item BURNT_DOOR;
	public static Item BURNT_TRAPDOOR;
	public static Item SMOLDERING_PLANKS;
	public static Item SMOLDERING_STRIPPED_LOG;
	public static Item SMOLDERING_STAIRS;
	public static Item BURNT_STAIRS;
	public static Item SOOTY_OAK_STAIRS;
	public static Item SOOTY_SPRUCE_STAIRS;
	public static Item SOOTY_BIRCH_STAIRS;
	public static Item SOOTY_JUNGLE_STAIRS;
	public static Item SOOTY_MANGROVE_STAIRS;
	public static Item SOOTY_DARK_OAK_STAIRS;
	public static Item SOOTY_CHERRY_STAIRS;
	public static Item SOOTY_PALE_OAK_STAIRS;
	public static Item SOOTY_ACACIA_STAIRS;
	public static Item EMBER_STAIRS;
	public static Item BLAZING_STAIRS;
	public static Item BURNT_SLAB;
	public static Item SMOLDERING_SLAB;
	public static Item SMOLDERING_WALL;
	public static Item EMBER_WALL;
	public static Item BURNT_WALL;
	public static Item SMOLDERING_VERTICAL_SLAB;
	public static Item EMBER_VERTICAL_SLAB;
	public static Item BURNT_VERTICAL_SLAB;
	public static Item BURNT_FENCE;
	public static Item SMOLDERING_FENCE;
	public static Item SOOTY_OAK_SLAB;
	public static Item SOOTY_SPRUCE_SLAB;
	public static Item SOOTY_BIRCH_SLAB;
	public static Item SOOTY_JUNGLE_SLAB;
	public static Item SOOTY_MANGROVE_SLAB;
	public static Item SOOTY_DARK_OAK_SLAB;
	public static Item SOOTY_CHERRY_SLAB;
	public static Item SOOTY_PALE_OAK_SLAB;
	public static Item SOOTY_ACACIA_SLAB;
	public static Item EMBER_SLAB;
	public static Item BLAZING_SLAB;
	public static Item BURNT_FENCEGATE;
	public static Item SMOLDERING_FENCEGATE;
	public static Item WOOD_FIRE;
	public static Item WOOD_FIRE_2;
	public static Item WOOD_FIRE_3;
	public static Item COPPER_FIRE;
	public static Item COPPER_FIRE_2;
	public static Item COPPER_FIRE_3;
	public static Item COPPER_FIRE_4;
	public static Item COPPER_FIRE_5;
	public static Item COPPER_FIRE_6;
	public static Item COPPER_FIRE_7;
	public static Item STAIRS_FIRE;
	public static Item STAIRS_FIRE_2;
	public static Item STAIRS_FIRE_3;
	public static Item BLAZING_LOG;
	public static Item WOOD_FIRE_4;
	public static Item WOOD_FIRE_5;
	public static Item BURNT_OAK_TRAPDOOR;
	public static Item BURNT_SPRUCE_TRAPDOOR;
	public static Item BURNT_BIRCH_TRAPDOOR;
	public static Item BURNT_JUNGLE_TRAPDOOR;
	public static Item BURNT_DARK_OAK_TRAPDOOR;
	public static Item BURNT_MANGROVE_TRAPDOOR;
	public static Item BURNT_CHERRY_TRAPDOOR;
	public static Item BURNT_PALE_OAK_TRAPDOOR;
	public static Item BURNT_ACACIA_TRAPDOOR;
	public static Item BURNT_BAMBOO_TRAPDOOR;
	public static Item BLAZING_FENCE;
	public static Item EMBER_FENCE;
	public static Item SOOTY_ACACIA_FENCE;
	public static Item SOOTY_BIRCH_FENCE;
	public static Item SOOTY_CHERRY_FENCE;
	public static Item SOOTY_PALE_OAK_FENCE;
	public static Item SOOTY_DARK_OAK_FENCE;
	public static Item SOOTY_JUNGLE_FENCE;
	public static Item SOOTY_MANGROVE_FENCE;
	public static Item SOOTY_OAK_FENCE;
	public static Item SOOTY_SPRUCE_FENCE;
	public static Item BLAZING_FENCE_GATE;
	public static Item EMBER_FENCE_GATE;
	public static Item SOOTY_ACACIA_FENCE_GATE;
	public static Item SOOTY_BIRCH_FENCE_GATE;
	public static Item SOOTY_CHERRY_FENCE_GATE;
	public static Item SOOTY_PALE_OAK_FENCE_GATE;
	public static Item SOOTY_DARK_OAK_FENCE_GATE;
	public static Item SOOTY_JUNGLE_FENCE_GATE;
	public static Item SOOTY_OAK_FENCE_GATE;
	public static Item SOOTY_SPRUCE_FENCE_GATE;
	public static Item BURNT_BUTTON;
	public static Item BURNT_PRESSURE_PLATE;
	public static Item SMOLDERING_WOOL;
	public static Item SMOLDERING_ENVELOPE;
	public static Item SMOLDERING_SAIL;
	public static Item SMOLDERING_SAIL_FRAME;
	public static Item SMOLDERING_CARPET;
	public static Item SOOTY_STRIPPED_OAK_LOG;
	public static Item SOOTY_STRIPPED_SPRUCE_LOG;
	public static Item SOOTY_STRIPPED_BIRCH_LOG;
	public static Item SOOTY_STRIPPED_ACACIA_LOG;
	public static Item SOOTY_STRIPPED_JUNGLE_LOG;
	public static Item SOOTY_STRIPPED_DARK_OAK_LOG;
	public static Item SOOTY_STRIPPED_MANGROVE_LOG;
	public static Item SOOTY_STRIPPED_CHERRY_LOG;
	public static Item SOOTY_STRIPPED_PALE_OAK_LOG;
	public static Item WOOD_FIRE_6;
	public static Item WOOD_FIRE_7;
	public static Item WOOD_FIRE_8;
	public static Item WOOD_FIRE_9;
	public static Item BURNT_BAMBOO_STAIRS;
	public static Item BURNT_BAMBOO_MOSAIC_STAIRS;
	public static Item BURNT_BAMBOO_FENCE;
	public static Item BURNT_BAMBOO_FENCEGATE;
	public static Item BURNT_BAMBOO_PLANKS;
	public static Item BURNT_BAMBOO_MOSAIC_PLANKS;
	public static Item BURNT_BAMBOO_BLOCK;
	public static Item BURNT_BAMBOO_STALK;
	public static Item BURNT_BAMBOO_SLAB;
	public static Item BURNT_BAMBOO_MOSAIC_SLAB;
	public static Item SMOLDERING_HAY;
	public static Item BURNT_HAY;
	public static Item EMBER_PLANKS;
	public static Item EMBER_LOG;
	public static Item SOOTY_OAK_PLANKS;
	public static Item SOOTY_DARK_OAK_PLANKS;
	public static Item SOOTY_SPRUCE_PLANKS;
	public static Item SOOTY_BIRCH_PLANKS;
	public static Item SOOTY_ACACIA_PLANKS;
	public static Item SOOTY_MANGROVE_PLANKS;
	public static Item SOOTY_CHERRY_PLANKS;
	public static Item SOOTY_PALE_OAK_PLANKS;
	public static Item SOOTY_JUNGLE_PLANKS;
	public static Item BLAZING_PLANKS;
	public static Item SOOTY_OAK_LOG;
	public static Item SOOTY_OAK_WOOD;
	public static Item BURNT_WOOD;
	public static Item SMOLDERING_WOOD;
	public static Item BLAZING_WOOD;
	public static Item SOOTY_DARK_OAK_LOG;
	public static Item SOOTY_SPRUCE_LOG;
	public static Item SOOTY_JUNGLE_LOG;
	public static Item SOOTY_BIRCH_LOG;
	public static Item SOOTY_MANGROVE_LOG;
	public static Item SOOTY_ACACIA_LOG;
	public static Item SOOTY_CHERRY_LOG;
	public static Item SOOTY_PALE_OAK_LOG;
	public static Item BROKEN_BLAZING_LOG;
	public static Item BROKEN_BLAZING_LOG_2;
	public static Item EMBER_WOOD;
	public static Item SOOTY_SPRUCE_WOOD;
	public static Item SOOTY_DARK_OAK_WOOD;
	public static Item SOOTY_JUNGLE_WOOD;
	public static Item SOOTY_BIRCH_WOOD;
	public static Item SOOTY_MANGROVE_WOOD;
	public static Item SOOTY_CHERRY_WOOD;
	public static Item SOOTY_PALE_OAK_WOOD;
	public static Item SOOTY_ACACIA_WOOD;
	public static Item SMOLDERING_STRIPPED_WOOD;
	public static Item STRIPPED_BURNT_WOOD;
	public static Item STRIPPED_BROKEN_BURNT_LOG_2;
	public static Item EXTINGUISHER;
	public static Item EXTINGUISHER_SPRAY;
	public static Item RECOVERING_GRASS;
	public static Item FIRE_BARREL_ACTIVE;
	public static Item SMOLDERING_GRASS_START;
	public static Item SMOLDERING_OAK_LEAVES;
	public static Item SMOLDERING_DARK_OAK_LEAVES;
	public static Item SMOLDERING_SPRUCE_LEAVES;
	public static Item SMOLDERING_BIRCH_LEAVES;
	public static Item SMOLDERING_JUNGLE_LEAVES;
	public static Item SMOLDERING_MANGROVE_LEAVES;
	public static Item SMOLDERING_CHERRY_LEAVES;
	public static Item SMOLDERING_PALE_OAK_LEAVES;
	public static Item SMOLDERING_ACACIA_LEAVES;
	public static Item SMOLDERING_BAMBOO_BLOCK;
	public static Item SMOLDERING_BAMBOO_FENCE;
	public static Item SMOLDERING_BAMBOO_FENCEGATE;
	public static Item SMOLDERING_BAMBOO_MOSAIC_PLANKS;
	public static Item SMOLDERING_BAMBOO_MOSAIC_SLAB;
	public static Item SMOLDERING_BAMBOO_MOSAIC_STAIRS;
	public static Item SMOLDERING_BAMBOO_PLANKS;
	public static Item SMOLDERING_BAMBOO_SLAB;
	public static Item SMOLDERING_BAMBOO_STAIRS;
	public static Item SMOLDERING_BAMBOO_STALK;
	public static Item BURNT_LADDER;
	public static Item BURNT_SCAFFOLDING;
	public static Item SOOTY_ACACIA_STRIPPED_WOOD;
	public static Item SMOLDERING_COAL;
	public static Item SOOTY_OAK_STRIPPED_WOOD;
	public static Item SOOTY_DARK_OAK_STRIPPED_WOOD;
	public static Item SOOTY_JUNGLE_STRIPPED_WOOD;
	public static Item SOOTY_BIRCH_STRIPPED_WOOD;
	public static Item SOOTY_MANGROVE_STRIPPED_WOOD;
	public static Item SOOTY_CHERRY_STRIPPED_WOOD;
	public static Item SOOTY_PALE_OAK_STRIPPED_WOOD;
	public static Item SOOTY_SPRUCE_STRIPPED_WOOD;
	public static Item SOOTY_BOOKSHELF;
	public static Item BURNT_BOOKSHELF;
	public static Item SPLASH;
	public static Item SMOLDERING_LOG_2;
	public static Item SMOLDERING_LOG_3;
	public static Item SMOLDERING_DARK_OAK_LOG;
	public static Item SMOLDERING_DARK_OAK_WOOD;
	public static Item BURNT_MOSS;
	public static Item BURNT_WOOL;
	public static Item BURNT_ENVELOPE;
	public static Item BURNT_SAIL;
	public static Item BURNT_CARPET;
	public static Item BURNT_CAVE_VINES;
	public static Item BURNT_CAVE_VINES_PLANT;
	public static Item SMOLDERING_CAVE_VINES;
	public static Item SMOLDERING_CAVE_VINES_PLANT;
	public static Item BAKED_APPLE;
	public static Item SOOTY_SMALL_LOG;
	public static Item BURNT_BAMBOO_LOG;
	public static Item BURNT_FOREST_BLOCK;
	public static Item BURNT_FOREST;
	public static Item CREEKING_BURNT_LOG;
	public static Item SMOLDERING_CACTUS;
	public static Item BURNT_CACTUS;
	public static Item SMOLDERING_AZALEA;
	public static Item BURNT_AZALEA;
	public static Item BURNT_MOSS_CARPET;
	public static Item BURNING_CAVE_VINES_PLANT;
	public static Item BLAST_LEAVES;
	public static Item BURNT_CAMPFIRE;
	public static Item EMBER_CAMPFIRE;
	public static Item SMOLDERING_CAMPFIRE;
	public static Item BURNT_PUMPKIN;
	public static Item BURNT_CARVED_PUMPKIN;
	public static Item BURNT_JACK_O_LANTERN;
	public static Item SEMI_BURNT_DIRT;
	public static Item BURNT_MELON;
	public static Item BURNT_SUGAR_CANE;
	public static Item FRAGILE_MAGMA;
	public static Item BURNT_BAMBOO_BUTTON;
	public static Item BURNT_BAMBOO_PRESSURE_PLATE;
	public static Item EMBER_BERRIES;
	public static Item FLINT_SPARK_ITEM;
	public static Item BURNT_CROPS;
	public static Item SMOLDERING_CROPS;
	public static Item STRIPPED_EMBER_LOG;
	public static Item FALLING_BURNT_SLAB;
	public static Item FIRE_BARREL_BLAST_BLOCK;
	public static Item FIRE_SET_BLOCK;
	public static Item BURNT_GRASSY_EARTHEN_CLAY;
	public static Item BURNT_GRASSY_SANDY_D_IRT;
	public static Item BURNT_IVY;
	public static Item TALL_FLAMES;
	public static Item BURNT_BARREL;
	public static Item SMOLDERING_PLANKS_2;
	public static Item SMOLDERING_PLANKS_3;
	public static Item SMOLDERING_PLANKS_4;
	public static Item SOOT_BLOCK;
	public static Item STAIRS_FIRE_4;
	public static Item STAIRS_FIRE_5;
	public static Item SMOLDERING_WHEAT;
	public static Item SMOLDERING_CARROTS;
	public static Item SMOLDERING_BEETROOT;
	public static Item SMOLDERING_POTATOES;
	public static Item FIRESTARTER;
	public static Item SMOLDERING_OAK_DOOR;
	public static Item SMOLDERING_ACACIA_DOOR;
	public static Item SMOLDERING_SPRUCE_DOOR;
	public static Item SMOLDERING_JUNGLE_DOOR;
	public static Item SMOLDERING_BIRCH_DOOR;
	public static Item SMOLDERING_DARK_OAK_DOOR;
	public static Item SMOLDERING_CHERRY_DOOR;
	public static Item SMOLDERING_PALE_OAK_DOOR;
	public static Item SMOLDERING_MANGROVE_DOOR;
	public static Item SMOLDERING_BAMBOO_DOOR;
	public static Item SMOLDERING_DOOR;
	public static Item BURNT_ACACIA_DOOR;
	public static Item BURNT_BAMBOO_DOOR;
	public static Item BURNT_BIRCH_DOOR;
	public static Item BURNT_CHERRY_DOOR;
	public static Item BURNT_PALE_OAK_DOOR;
	public static Item BURNT_DARK_OAK_DOOR;
	public static Item BURNT_JUNGLE_DOOR;
	public static Item BURNT_MANGROVE_DOOR;
	public static Item BURNT_OAK_DOOR;
	public static Item BURNT_SPRUCE_DOOR;
	public static Item SMOLDERING_TRAPDOOR;
	public static Item SMOLDERING_OAK_TRAPDOOR;
	public static Item SMOLDERING_DARK_OAK_TRAPDOOR;
	public static Item SMOLDERING_SPRUCE_TRAPDOOR;
	public static Item SMOLDERING_BIRCH_TRAPDOOR;
	public static Item SMOLDERING_JUNGLE_TRAPDOOR;
	public static Item SMOLDERING_MANGROVE_TRAPDOOR;
	public static Item SMOLDERING_CHERRY_TRAPDOOR;
	public static Item SMOLDERING_PALE_OAK_TRAPDOOR;
	public static Item SMOLDERING_ACACIA_TRAPDOOR;
	public static Item SMOLDERING_BAMBOO_TRAPDOOR;
	public static Item SMOLDERING_MOSS;
	public static Item SMOLDERING_MOSS_CARPET;
	public static Item SOOTY_COBBLESTONE;
	public static Item SOOTY_MOSSY_COBBLESTONE;
	public static Item SOOTY_STONE_BRICKS;
	public static Item SOOTY_MOSSY_STONE_BRICKS;
	public static Item SOOTY_STONE;
	public static Item SOOTY_BRICKS;
	public static Item SOOTY_COBBLESTONE_WALL;
	public static Item SOOTY_BRICK_WALL;
	public static Item SOOTY_STONE_BRICK_WALL;
	public static Item SOOTY_MOSSY_COBBLESTONE_WALL;
	public static Item SOOTY_MOSSY_STONE_BRICK_WALL;
	public static Item SOOTY_COBBLESTONE_STAIRS;
	public static Item SOOTY_MOSSY_COBBLESTONE_STAIRS;
	public static Item SOOTY_STONE_BRICK_STAIRS;
	public static Item SOOTY_MOSSY_STONE_BRICK_STAIRS;
	public static Item SOOTY_BRICK_STAIRS;
	public static Item SOOTY_COBBLESTONE_SLAB;
	public static Item SOOTY_MOSSY_COBBLESTONE_SLAB;
	public static Item SOOTY_STONE_BRICK_SLAB;
	public static Item SOOTY_MOSSY_STONE_BRICK_SLAB;
	public static Item SOOTY_BRICK_SLAB;
	public static Item SOOT;
	public static Item DOUBLE_TALL_GRASS_FIRE;
	public static Item SOOTY_DIORITE;
	public static Item SOOTY_ANDESITE;
	public static Item SOOTY_GRANITE;
	public static Item SOOTY_CHISELED_STONE_BRICKS;
	public static Item FIREBALL_ITEM;
	public static Item SMOLDERING_LEAVES_2;
	public static Item SMOLDERING_LEAVES_3;
	public static Item SMOLDERING_LEAVES_4;
	public static Item SMOLDERING_LEAVES_5;
	public static Item COOKED_RED_MUSHROOM_BLOCK;
	public static Item COOKED_BROWN_MUSHROOM_BLOCK;
	public static Item COOKED_MUSHROOM_STEM;
	public static Item SOOTY_STONE_SLAB;
	public static Item SOOTY_STONE_STAIRS;
	public static Item SOOTY_DIORITE_SLAB;
	public static Item SOOTY_DIORITE_STAIRS;
	public static Item SOOTY_DIORITE_WALL;
	public static Item SOOTY_ANDESITE_SLAB;
	public static Item SOOTY_ANDESITE_STAIRS;
	public static Item SOOTY_ANDESITE_WALL;
	public static Item SOOTY_GRANITE_SLAB;
	public static Item SOOTY_GRANITE_STAIRS;
	public static Item SOOTY_GRANITE_WALL;
	public static Item FALLEN_BURNT_SLAB;
	public static Item SMOLDERING_HIGH_GRASS;
	public static Item BURNT_HIGH_GRASS;
	public static Item DOUBLE_FALLEN_BURNT_SLAB;
	public static Item SOOTY_MANGROVE_FENCE_GATE;
	public static Item SMOKE_SPRITE;
	public static Item FAR_SMOKE_SPRITE;
	public static Item NEAR_SMOKE_SPRITE;
	public static Item TALL_SMOKE_SPRITE;
	public static Item SMOLDERING_COBWEB;
	public static Item DISTANT_SMOKE_SPRITE;
	public static Item BURNT_FLESH_BLOCK;
	public static Item CHARRED_MYCELIUM;
	public static Item CRACKED_TERRACOTTA;
	public static Item CRACKED_WHITE_TERRACOTTA;
	public static Item CRACKED_GREEN_TERRACOTTA;
	public static Item CRACKED_LIGHT_BLUE_TERRACOTTA;
	public static Item CRACKED_LIGHT_GREY_TERRACOTTA;
	public static Item CRACKED_LIME_TERRACOTTA;
	public static Item CRACKED_MAGENTA_TERRACOTTA;
	public static Item CRACKED_ORANGE_TERRACOTTA;
	public static Item CRACKED_PINK_TERRACOTTA;
	public static Item CRACKED_PURPLE_TERRACOTTA;
	public static Item CRACKED_RED_TERRACOTTA;
	public static Item CRACKED_YELLOW_TERRACOTTA;
	public static Item CRACKED_CYAN_TERRACOTTA;
	public static Item CRACKED_BLUE_TERRACOTTA;
	public static Item CRACKED_GREY_TERRACOTTA;
	public static Item CRACKED_BLACK_TERRACOTTA;
	public static Item CRACKED_BROWN_TERRACOTTA;
	public static Item SOOTY_MUD_BRICKS;
	public static Item SOOTY_MUD_BRICK_STAIRS;
	public static Item SOOTY_MUD_BRICK_SLAB;
	public static Item SOOTY_MUD_BRICK_WALL;
	public static Item NEAR_SMOKE_SPRITE_2;
	public static Item NEAR_SMOKE_SPRITE_3;
	public static Item TALL_SMOKE_SPRITE_2;
	public static Item WHITE_FLAG;
	public static Item BLACK_FLAG;
	public static Item YELLOW_FLAG;
	public static Item PINK_FLAG;
	public static Item BLUE_FLAG;
	public static Item LIGHT_BLUE_FLAG;
	public static Item CYAN_FLAG;
	public static Item GREEN_FLAG;
	public static Item LIME_FLAG;
	public static Item PURPLE_FLAG;
	public static Item MAGENTA_FLAG;
	public static Item ORANGE_FLAG;
	public static Item BROWN_FLAG;
	public static Item GREY_FLAG;
	public static Item LIGHT_GREY_FLAG;
	public static Item RED_FLAG;
	public static Item WIND_FLAG;
	public static Item RAINBOW_FLAG;
	public static Item PIRATE_FLAG;
	public static Item EMBER_PROJECTILE_ITEM;
	// Terrain Slabs soil chain - see compat/TerrainSlabsBridge.
	public static Item SMOLDERING_GRASS_SLAB;
	public static Item BURNT_GRASS_SLAB;
	public static Item RECOVERING_GRASS_SLAB;
	public static Item SMOLDERING_MOSS_SLAB;
	public static Item BURNT_MOSS_SLAB;
	public static Item BURNT_DIRT_SLAB;
	public static Item SMOKE_DETECTOR;

	public static void register(IEventBus bus) {
		bus.addListener((RegisterEvent event) -> {
			if (!event.getRegistryKey().equals(Registries.ITEM)) return;
			BURNT_LEAVES = Registry.register(BuiltInRegistries.ITEM,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_leaves"),
					new BlockItem(BurntModBlocks.BURNT_LEAVES, new Item.Properties()));
		SMOLDERING_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_leaves"),
				new BlockItem(BurntModBlocks.SMOLDERING_LEAVES, new Item.Properties()));
		BURNT_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_log"),
				new BlockItem(BurntModBlocks.BURNT_LOG, new Item.Properties()));
		SMOLDERING_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_log"),
				new BlockItem(BurntModBlocks.SMOLDERING_LOG, new Item.Properties()));
		SMOLDERING_HOLLOW_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_hollow_log"),
				new BlockItem(BurntModBlocks.SMOLDERING_HOLLOW_LOG, new Item.Properties()));
		EMBER_HOLLOW_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_hollow_log"),
				new BlockItem(BurntModBlocks.EMBER_HOLLOW_LOG, new Item.Properties()));
		BLAZING_HOLLOW_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "blazing_hollow_log"),
				new BlockItem(BurntModBlocks.BLAZING_HOLLOW_LOG, new Item.Properties()));
		BURNT_HOLLOW_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_hollow_log"),
				new BlockItem(BurntModBlocks.BURNT_HOLLOW_LOG, new Item.Properties()));
		SMALL_SMOLDERING_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "small_smoldering_log"),
				new BlockItem(BurntModBlocks.SMALL_SMOLDERING_LOG, new Item.Properties()));
		BURNT_GRASS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_grass"),
				new BlockItem(BurntModBlocks.BURNT_GRASS, new Item.Properties()));
		BURNT_DIRT = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_dirt"),
				new BlockItem(BurntModBlocks.BURNT_DIRT, new Item.Properties()));
		SMALL_BURNT_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "small_burnt_log"),
				new BlockItem(BurntModBlocks.SMALL_BURNT_LOG, new Item.Properties()));
		GRASS_FLAMES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "grass_flames"),
				new BlockItem(BurntModBlocks.GRASS_FLAMES, new Item.Properties()));
		SMOLDERING_GRASS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_grass"),
				new BlockItem(BurntModBlocks.SMOLDERING_GRASS, new Item.Properties()));
		TALL_GRASS_FIRE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "tall_grass_fire"),
				new BlockItem(BurntModBlocks.TALL_GRASS_FIRE, new Item.Properties()));
		SMOLDERING_FERN = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_fern"),
				new BlockItem(BurntModBlocks.SMOLDERING_FERN, new Item.Properties()));
		BROKEN_BURNT_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "broken_burnt_log"),
				new BlockItem(BurntModBlocks.BROKEN_BURNT_LOG, new Item.Properties()));
		BURNT_VINE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_vine"),
				new BlockItem(BurntModBlocks.BURNT_VINE, new Item.Properties()));
		BURNT_MANGROVE_ROOTS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_mangrove_roots"),
				new BlockItem(BurntModBlocks.BURNT_MANGROVE_ROOTS, new Item.Properties()));
		TALL_GRASS_BURNT = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "tall_grass_burnt"),
				new BlockItem(BurntModBlocks.TALL_GRASS_BURNT, new Item.Properties()));
		BURNT_FERN = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_fern"),
				new BlockItem(BurntModBlocks.BURNT_FERN, new Item.Properties()));
		BURNT_PATH = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_path"),
				new BlockItem(BurntModBlocks.BURNT_PATH, new Item.Properties()));
		STRIPPED_BURNT_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "stripped_burnt_log"),
				new BlockItem(BurntModBlocks.STRIPPED_BURNT_LOG, new Item.Properties()));
		SMOLDERING_MANGROVE_ROOTS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_mangrove_roots"),
				new BlockItem(BurntModBlocks.SMOLDERING_MANGROVE_ROOTS, new Item.Properties()));
		STRIPPED_BROKEN_BURNT_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "stripped_broken_burnt_log"),
				new BlockItem(BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG, new Item.Properties()));
		FIRE_BARREL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "fire_barrel"),
				new BlockItem(BurntModBlocks.FIRE_BARREL, new Item.Properties()));
		BURNT_FARMLAND = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_farmland"),
				new BlockItem(BurntModBlocks.BURNT_FARMLAND, new Item.Properties()));
		BURNT_FARMLAND_MOIST = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_farmland_moist"),
				new BlockItem(BurntModBlocks.BURNT_FARMLAND_MOIST, new Item.Properties()));
		BURNT_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_planks"),
				new BlockItem(BurntModBlocks.BURNT_PLANKS, new Item.Properties()));
		BURNT_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_DOOR, new Item.Properties()));
		BURNT_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_TRAPDOOR, new Item.Properties()));
		SMOLDERING_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_planks"),
				new BlockItem(BurntModBlocks.SMOLDERING_PLANKS, new Item.Properties()));
		SMOLDERING_STRIPPED_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_stripped_log"),
				new BlockItem(BurntModBlocks.SMOLDERING_STRIPPED_LOG, new Item.Properties()));
		SMOLDERING_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_stairs"),
				new BlockItem(BurntModBlocks.SMOLDERING_STAIRS, new Item.Properties()));
		BURNT_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_stairs"),
				new BlockItem(BurntModBlocks.BURNT_STAIRS, new Item.Properties()));
		SOOTY_OAK_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_oak_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_OAK_STAIRS, new Item.Properties()));
		SOOTY_SPRUCE_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_spruce_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_SPRUCE_STAIRS, new Item.Properties()));
		SOOTY_BIRCH_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_birch_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_BIRCH_STAIRS, new Item.Properties()));
		SOOTY_JUNGLE_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_jungle_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_JUNGLE_STAIRS, new Item.Properties()));
		SOOTY_MANGROVE_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mangrove_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_MANGROVE_STAIRS, new Item.Properties()));
		SOOTY_DARK_OAK_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_dark_oak_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_DARK_OAK_STAIRS, new Item.Properties()));
		SOOTY_CHERRY_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cherry_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_CHERRY_STAIRS, new Item.Properties()));
		SOOTY_PALE_OAK_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_pale_oak_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_PALE_OAK_STAIRS, new Item.Properties()));
		SOOTY_ACACIA_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_acacia_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_ACACIA_STAIRS, new Item.Properties()));
		EMBER_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_stairs"),
				new BlockItem(BurntModBlocks.EMBER_STAIRS, new Item.Properties()));
		BLAZING_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "blazing_stairs"),
				new BlockItem(BurntModBlocks.BLAZING_STAIRS, new Item.Properties()));
		BURNT_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_slab"),
				new BlockItem(BurntModBlocks.BURNT_SLAB, new Item.Properties()));
		SMOLDERING_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_slab"),
				new BlockItem(BurntModBlocks.SMOLDERING_SLAB, new Item.Properties()));
		SMOLDERING_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_wall"),
				new BlockItem(BurntModBlocks.SMOLDERING_WALL, new Item.Properties()));
		EMBER_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_wall"),
				new BlockItem(BurntModBlocks.EMBER_WALL, new Item.Properties()));
		BURNT_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_wall"),
				new BlockItem(BurntModBlocks.BURNT_WALL, new Item.Properties()));
		SMOLDERING_VERTICAL_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_vertical_slab"),
				new BlockItem(BurntModBlocks.SMOLDERING_VERTICAL_SLAB, new Item.Properties()));
		EMBER_VERTICAL_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_vertical_slab"),
				new BlockItem(BurntModBlocks.EMBER_VERTICAL_SLAB, new Item.Properties()));
		BURNT_VERTICAL_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_vertical_slab"),
				new BlockItem(BurntModBlocks.BURNT_VERTICAL_SLAB, new Item.Properties()));
		BURNT_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_fence"),
				new BlockItem(BurntModBlocks.BURNT_FENCE, new Item.Properties()));
		SMOLDERING_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_fence"),
				new BlockItem(BurntModBlocks.SMOLDERING_FENCE, new Item.Properties()));
		SOOTY_OAK_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_oak_slab"),
				new BlockItem(BurntModBlocks.SOOTY_OAK_SLAB, new Item.Properties()));
		SOOTY_SPRUCE_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_spruce_slab"),
				new BlockItem(BurntModBlocks.SOOTY_SPRUCE_SLAB, new Item.Properties()));
		SOOTY_BIRCH_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_birch_slab"),
				new BlockItem(BurntModBlocks.SOOTY_BIRCH_SLAB, new Item.Properties()));
		SOOTY_JUNGLE_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_jungle_slab"),
				new BlockItem(BurntModBlocks.SOOTY_JUNGLE_SLAB, new Item.Properties()));
		SOOTY_MANGROVE_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mangrove_slab"),
				new BlockItem(BurntModBlocks.SOOTY_MANGROVE_SLAB, new Item.Properties()));
		SOOTY_DARK_OAK_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_dark_oak_slab"),
				new BlockItem(BurntModBlocks.SOOTY_DARK_OAK_SLAB, new Item.Properties()));
		SOOTY_CHERRY_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cherry_slab"),
				new BlockItem(BurntModBlocks.SOOTY_CHERRY_SLAB, new Item.Properties()));
		SOOTY_PALE_OAK_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_pale_oak_slab"),
				new BlockItem(BurntModBlocks.SOOTY_PALE_OAK_SLAB, new Item.Properties()));
		SOOTY_ACACIA_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_acacia_slab"),
				new BlockItem(BurntModBlocks.SOOTY_ACACIA_SLAB, new Item.Properties()));
		EMBER_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_slab"),
				new BlockItem(BurntModBlocks.EMBER_SLAB, new Item.Properties()));
		BLAZING_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "blazing_slab"),
				new BlockItem(BurntModBlocks.BLAZING_SLAB, new Item.Properties()));
		BURNT_FENCEGATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_fencegate"),
				new BlockItem(BurntModBlocks.BURNT_FENCEGATE, new Item.Properties()));
		SMOLDERING_FENCEGATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_fencegate"),
				new BlockItem(BurntModBlocks.SMOLDERING_FENCEGATE, new Item.Properties()));
		WOOD_FIRE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "wood_fire"),
				new BlockItem(BurntModBlocks.WOOD_FIRE, new Item.Properties()));
		WOOD_FIRE_2 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "wood_fire_2"),
				new BlockItem(BurntModBlocks.WOOD_FIRE_2, new Item.Properties()));
		WOOD_FIRE_3 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "wood_fire_3"),
				new BlockItem(BurntModBlocks.WOOD_FIRE_3, new Item.Properties()));
		STAIRS_FIRE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "stairs_fire"),
				new BlockItem(BurntModBlocks.STAIRS_FIRE, new Item.Properties()));
		STAIRS_FIRE_2 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "stairs_fire_2"),
				new BlockItem(BurntModBlocks.STAIRS_FIRE_2, new Item.Properties()));
		STAIRS_FIRE_3 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "stairs_fire_3"),
				new BlockItem(BurntModBlocks.STAIRS_FIRE_3, new Item.Properties()));
		BLAZING_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "blazing_log"),
				new BlockItem(BurntModBlocks.BLAZING_LOG, new Item.Properties()));
		WOOD_FIRE_4 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "wood_fire_4"),
				new BlockItem(BurntModBlocks.WOOD_FIRE_4, new Item.Properties()));
		WOOD_FIRE_5 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "wood_fire_5"),
				new BlockItem(BurntModBlocks.WOOD_FIRE_5, new Item.Properties()));
		BURNT_OAK_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_oak_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_OAK_TRAPDOOR, new Item.Properties()));
		BURNT_SPRUCE_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_spruce_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_SPRUCE_TRAPDOOR, new Item.Properties()));
		BURNT_BIRCH_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_birch_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_BIRCH_TRAPDOOR, new Item.Properties()));
		BURNT_JUNGLE_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_jungle_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_JUNGLE_TRAPDOOR, new Item.Properties()));
		BURNT_DARK_OAK_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_dark_oak_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_DARK_OAK_TRAPDOOR, new Item.Properties()));
		BURNT_MANGROVE_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_mangrove_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_MANGROVE_TRAPDOOR, new Item.Properties()));
		BURNT_CHERRY_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_cherry_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_CHERRY_TRAPDOOR, new Item.Properties()));
		BURNT_PALE_OAK_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_pale_oak_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_PALE_OAK_TRAPDOOR, new Item.Properties()));
		BURNT_ACACIA_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_acacia_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_ACACIA_TRAPDOOR, new Item.Properties()));
		BURNT_BAMBOO_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_trapdoor"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_TRAPDOOR, new Item.Properties()));
		BLAZING_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "blazing_fence"),
				new BlockItem(BurntModBlocks.BLAZING_FENCE, new Item.Properties()));
		EMBER_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_fence"),
				new BlockItem(BurntModBlocks.EMBER_FENCE, new Item.Properties()));
		SOOTY_ACACIA_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_acacia_fence"),
				new BlockItem(BurntModBlocks.SOOTY_ACACIA_FENCE, new Item.Properties()));
		SOOTY_BIRCH_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_birch_fence"),
				new BlockItem(BurntModBlocks.SOOTY_BIRCH_FENCE, new Item.Properties()));
		SOOTY_CHERRY_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cherry_fence"),
				new BlockItem(BurntModBlocks.SOOTY_CHERRY_FENCE, new Item.Properties()));
		SOOTY_PALE_OAK_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_pale_oak_fence"),
				new BlockItem(BurntModBlocks.SOOTY_PALE_OAK_FENCE, new Item.Properties()));
		SOOTY_DARK_OAK_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_dark_oak_fence"),
				new BlockItem(BurntModBlocks.SOOTY_DARK_OAK_FENCE, new Item.Properties()));
		SOOTY_JUNGLE_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_jungle_fence"),
				new BlockItem(BurntModBlocks.SOOTY_JUNGLE_FENCE, new Item.Properties()));
		SOOTY_MANGROVE_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mangrove_fence"),
				new BlockItem(BurntModBlocks.SOOTY_MANGROVE_FENCE, new Item.Properties()));
		SOOTY_OAK_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_oak_fence"),
				new BlockItem(BurntModBlocks.SOOTY_OAK_FENCE, new Item.Properties()));
		SOOTY_SPRUCE_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_spruce_fence"),
				new BlockItem(BurntModBlocks.SOOTY_SPRUCE_FENCE, new Item.Properties()));
		BLAZING_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "blazing_fence_gate"),
				new BlockItem(BurntModBlocks.BLAZING_FENCE_GATE, new Item.Properties()));
		EMBER_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_fence_gate"),
				new BlockItem(BurntModBlocks.EMBER_FENCE_GATE, new Item.Properties()));
		SOOTY_ACACIA_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_acacia_fence_gate"),
				new BlockItem(BurntModBlocks.SOOTY_ACACIA_FENCE_GATE, new Item.Properties()));
		SOOTY_BIRCH_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_birch_fence_gate"),
				new BlockItem(BurntModBlocks.SOOTY_BIRCH_FENCE_GATE, new Item.Properties()));
		SOOTY_CHERRY_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cherry_fence_gate"),
				new BlockItem(BurntModBlocks.SOOTY_CHERRY_FENCE_GATE, new Item.Properties()));
		SOOTY_PALE_OAK_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_pale_oak_fence_gate"),
				new BlockItem(BurntModBlocks.SOOTY_PALE_OAK_FENCE_GATE, new Item.Properties()));
		SOOTY_DARK_OAK_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_dark_oak_fence_gate"),
				new BlockItem(BurntModBlocks.SOOTY_DARK_OAK_FENCE_GATE, new Item.Properties()));
		SOOTY_JUNGLE_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_jungle_fence_gate"),
				new BlockItem(BurntModBlocks.SOOTY_JUNGLE_FENCE_GATE, new Item.Properties()));
		SOOTY_OAK_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_oak_fence_gate"),
				new BlockItem(BurntModBlocks.SOOTY_OAK_FENCE_GATE, new Item.Properties()));
		SOOTY_SPRUCE_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_spruce_fence_gate"),
				new BlockItem(BurntModBlocks.SOOTY_SPRUCE_FENCE_GATE, new Item.Properties()));
		BURNT_BUTTON = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_button"),
				new BlockItem(BurntModBlocks.BURNT_BUTTON, new Item.Properties()));
		BURNT_PRESSURE_PLATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_pressure_plate"),
				new BlockItem(BurntModBlocks.BURNT_PRESSURE_PLATE, new Item.Properties()));
		SMOLDERING_WOOL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_wool"),
				new BlockItem(BurntModBlocks.SMOLDERING_WOOL, new Item.Properties()));
		SMOLDERING_ENVELOPE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_envelope"),
				new BlockItem(BurntModBlocks.SMOLDERING_ENVELOPE, new Item.Properties()));
		SMOLDERING_SAIL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_sail"),
				new BlockItem(BurntModBlocks.SMOLDERING_SAIL, new Item.Properties()));
		SMOLDERING_SAIL_FRAME = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_sail_frame"),
				new BlockItem(BurntModBlocks.SMOLDERING_SAIL_FRAME, new Item.Properties()));
		SMOLDERING_CARPET = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_carpet"),
				new BlockItem(BurntModBlocks.SMOLDERING_CARPET, new Item.Properties()));
		SOOTY_STRIPPED_OAK_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stripped_oak_log"),
				new BlockItem(BurntModBlocks.SOOTY_STRIPPED_OAK_LOG, new Item.Properties()));
		SOOTY_STRIPPED_SPRUCE_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stripped_spruce_log"),
				new BlockItem(BurntModBlocks.SOOTY_STRIPPED_SPRUCE_LOG, new Item.Properties()));
		SOOTY_STRIPPED_BIRCH_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stripped_birch_log"),
				new BlockItem(BurntModBlocks.SOOTY_STRIPPED_BIRCH_LOG, new Item.Properties()));
		SOOTY_STRIPPED_ACACIA_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stripped_acacia_log"),
				new BlockItem(BurntModBlocks.SOOTY_STRIPPED_ACACIA_LOG, new Item.Properties()));
		SOOTY_STRIPPED_JUNGLE_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stripped_jungle_log"),
				new BlockItem(BurntModBlocks.SOOTY_STRIPPED_JUNGLE_LOG, new Item.Properties()));
		SOOTY_STRIPPED_DARK_OAK_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stripped_dark_oak_log"),
				new BlockItem(BurntModBlocks.SOOTY_STRIPPED_DARK_OAK_LOG, new Item.Properties()));
		SOOTY_STRIPPED_MANGROVE_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stripped_mangrove_log"),
				new BlockItem(BurntModBlocks.SOOTY_STRIPPED_MANGROVE_LOG, new Item.Properties()));
		SOOTY_STRIPPED_CHERRY_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stripped_cherry_log"),
				new BlockItem(BurntModBlocks.SOOTY_STRIPPED_CHERRY_LOG, new Item.Properties()));
		SOOTY_STRIPPED_PALE_OAK_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stripped_pale_oak_log"),
				new BlockItem(BurntModBlocks.SOOTY_STRIPPED_PALE_OAK_LOG, new Item.Properties()));
		WOOD_FIRE_6 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "wood_fire_6"),
				new BlockItem(BurntModBlocks.WOOD_FIRE_6, new Item.Properties()));
		WOOD_FIRE_7 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "wood_fire_7"),
				new BlockItem(BurntModBlocks.WOOD_FIRE_7, new Item.Properties()));
		WOOD_FIRE_8 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "wood_fire_8"),
				new BlockItem(BurntModBlocks.WOOD_FIRE_8, new Item.Properties()));
		WOOD_FIRE_9 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "wood_fire_9"),
				new BlockItem(BurntModBlocks.WOOD_FIRE_9, new Item.Properties()));
		COPPER_FIRE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "copper_fire"),
				new BlockItem(BurntModBlocks.COPPER_FIRE, new Item.Properties()));
		COPPER_FIRE_2 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "copper_fire_2"),
				new BlockItem(BurntModBlocks.COPPER_FIRE_2, new Item.Properties()));
		COPPER_FIRE_3 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "copper_fire_3"),
				new BlockItem(BurntModBlocks.COPPER_FIRE_3, new Item.Properties()));
		COPPER_FIRE_4 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "copper_fire_4"),
				new BlockItem(BurntModBlocks.COPPER_FIRE_4, new Item.Properties()));
		COPPER_FIRE_5 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "copper_fire_5"),
				new BlockItem(BurntModBlocks.COPPER_FIRE_5, new Item.Properties()));
		COPPER_FIRE_6 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "copper_fire_6"),
				new BlockItem(BurntModBlocks.COPPER_FIRE_6, new Item.Properties()));
		COPPER_FIRE_7 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "copper_fire_7"),
				new BlockItem(BurntModBlocks.COPPER_FIRE_7, new Item.Properties()));
		BURNT_BAMBOO_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_stairs"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_STAIRS, new Item.Properties()));
		BURNT_BAMBOO_MOSAIC_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_mosaic_stairs"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_MOSAIC_STAIRS, new Item.Properties()));
		BURNT_BAMBOO_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_fence"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_FENCE, new Item.Properties()));
		BURNT_BAMBOO_FENCEGATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_fencegate"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_FENCEGATE, new Item.Properties()));
		BURNT_BAMBOO_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_planks"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_PLANKS, new Item.Properties()));
		BURNT_BAMBOO_MOSAIC_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_mosaic_planks"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_MOSAIC_PLANKS, new Item.Properties()));
		BURNT_BAMBOO_BLOCK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_block"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_BLOCK, new Item.Properties()));
		BURNT_BAMBOO_STALK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_stalk"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_STALK, new Item.Properties()));
		BURNT_BAMBOO_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_slab"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_SLAB, new Item.Properties()));
		BURNT_BAMBOO_MOSAIC_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_mosaic_slab"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_MOSAIC_SLAB, new Item.Properties()));
		SMOLDERING_HAY = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_hay"),
				new BlockItem(BurntModBlocks.SMOLDERING_HAY, new Item.Properties()));
		BURNT_HAY = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_hay"),
				new BlockItem(BurntModBlocks.BURNT_HAY, new Item.Properties()));
		EMBER_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_planks"),
				new BlockItem(BurntModBlocks.EMBER_PLANKS, new Item.Properties()));
		EMBER_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_log"),
				new BlockItem(BurntModBlocks.EMBER_LOG, new Item.Properties()));
		SOOTY_OAK_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_oak_planks"),
				new BlockItem(BurntModBlocks.SOOTY_OAK_PLANKS, new Item.Properties()));
		SOOTY_DARK_OAK_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_dark_oak_planks"),
				new BlockItem(BurntModBlocks.SOOTY_DARK_OAK_PLANKS, new Item.Properties()));
		SOOTY_SPRUCE_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_spruce_planks"),
				new BlockItem(BurntModBlocks.SOOTY_SPRUCE_PLANKS, new Item.Properties()));
		SOOTY_BIRCH_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_birch_planks"),
				new BlockItem(BurntModBlocks.SOOTY_BIRCH_PLANKS, new Item.Properties()));
		SOOTY_ACACIA_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_acacia_planks"),
				new BlockItem(BurntModBlocks.SOOTY_ACACIA_PLANKS, new Item.Properties()));
		SOOTY_MANGROVE_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mangrove_planks"),
				new BlockItem(BurntModBlocks.SOOTY_MANGROVE_PLANKS, new Item.Properties()));
		SOOTY_CHERRY_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cherry_planks"),
				new BlockItem(BurntModBlocks.SOOTY_CHERRY_PLANKS, new Item.Properties()));
		SOOTY_PALE_OAK_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_pale_oak_planks"),
				new BlockItem(BurntModBlocks.SOOTY_PALE_OAK_PLANKS, new Item.Properties()));
		SOOTY_JUNGLE_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_jungle_planks"),
				new BlockItem(BurntModBlocks.SOOTY_JUNGLE_PLANKS, new Item.Properties()));
		BLAZING_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "blazing_planks"),
				new BlockItem(BurntModBlocks.BLAZING_PLANKS, new Item.Properties()));
		SOOTY_OAK_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_oak_log"),
				new BlockItem(BurntModBlocks.SOOTY_OAK_LOG, new Item.Properties()));
		SOOTY_OAK_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_oak_wood"),
				new BlockItem(BurntModBlocks.SOOTY_OAK_WOOD, new Item.Properties()));
		BURNT_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_wood"),
				new BlockItem(BurntModBlocks.BURNT_WOOD, new Item.Properties()));
		SMOLDERING_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_wood"),
				new BlockItem(BurntModBlocks.SMOLDERING_WOOD, new Item.Properties()));
		BLAZING_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "blazing_wood"),
				new BlockItem(BurntModBlocks.BLAZING_WOOD, new Item.Properties()));
		SOOTY_DARK_OAK_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_dark_oak_log"),
				new BlockItem(BurntModBlocks.SOOTY_DARK_OAK_LOG, new Item.Properties()));
		SOOTY_SPRUCE_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_spruce_log"),
				new BlockItem(BurntModBlocks.SOOTY_SPRUCE_LOG, new Item.Properties()));
		SOOTY_JUNGLE_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_jungle_log"),
				new BlockItem(BurntModBlocks.SOOTY_JUNGLE_LOG, new Item.Properties()));
		SOOTY_BIRCH_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_birch_log"),
				new BlockItem(BurntModBlocks.SOOTY_BIRCH_LOG, new Item.Properties()));
		SOOTY_MANGROVE_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mangrove_log"),
				new BlockItem(BurntModBlocks.SOOTY_MANGROVE_LOG, new Item.Properties()));
		SOOTY_ACACIA_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_acacia_log"),
				new BlockItem(BurntModBlocks.SOOTY_ACACIA_LOG, new Item.Properties()));
		SOOTY_CHERRY_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cherry_log"),
				new BlockItem(BurntModBlocks.SOOTY_CHERRY_LOG, new Item.Properties()));
		SOOTY_PALE_OAK_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_pale_oak_log"),
				new BlockItem(BurntModBlocks.SOOTY_PALE_OAK_LOG, new Item.Properties()));
		BROKEN_BLAZING_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "broken_blazing_log"),
				new BlockItem(BurntModBlocks.BROKEN_BLAZING_LOG, new Item.Properties()));
		BROKEN_BLAZING_LOG_2 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "broken_blazing_log_2"),
				new BlockItem(BurntModBlocks.BROKEN_BLAZING_LOG_2, new Item.Properties()));
		EMBER_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_wood"),
				new BlockItem(BurntModBlocks.EMBER_WOOD, new Item.Properties()));
		SOOTY_SPRUCE_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_spruce_wood"),
				new BlockItem(BurntModBlocks.SOOTY_SPRUCE_WOOD, new Item.Properties()));
		SOOTY_DARK_OAK_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_dark_oak_wood"),
				new BlockItem(BurntModBlocks.SOOTY_DARK_OAK_WOOD, new Item.Properties()));
		SOOTY_JUNGLE_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_jungle_wood"),
				new BlockItem(BurntModBlocks.SOOTY_JUNGLE_WOOD, new Item.Properties()));
		SOOTY_BIRCH_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_birch_wood"),
				new BlockItem(BurntModBlocks.SOOTY_BIRCH_WOOD, new Item.Properties()));
		SOOTY_MANGROVE_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mangrove_wood"),
				new BlockItem(BurntModBlocks.SOOTY_MANGROVE_WOOD, new Item.Properties()));
		SOOTY_CHERRY_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cherry_wood"),
				new BlockItem(BurntModBlocks.SOOTY_CHERRY_WOOD, new Item.Properties()));
		SOOTY_PALE_OAK_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_pale_oak_wood"),
				new BlockItem(BurntModBlocks.SOOTY_PALE_OAK_WOOD, new Item.Properties()));
		SOOTY_ACACIA_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_acacia_wood"),
				new BlockItem(BurntModBlocks.SOOTY_ACACIA_WOOD, new Item.Properties()));
		SMOLDERING_STRIPPED_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_stripped_wood"),
				new BlockItem(BurntModBlocks.SMOLDERING_STRIPPED_WOOD, new Item.Properties()));
		STRIPPED_BURNT_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "stripped_burnt_wood"),
				new BlockItem(BurntModBlocks.STRIPPED_BURNT_WOOD, new Item.Properties()));
		STRIPPED_BROKEN_BURNT_LOG_2 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "stripped_broken_burnt_log_2"),
				new BlockItem(BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG_2, new Item.Properties()));
		EXTINGUISHER = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "extinguisher"),
				new ExtinguisherItem());
		EXTINGUISHER_SPRAY = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "extinguisher_spray"),
				new ExtinguisherSprayItem());
		RECOVERING_GRASS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "recovering_grass"),
				new BlockItem(BurntModBlocks.RECOVERING_GRASS, new Item.Properties()));
		FIRE_BARREL_ACTIVE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "fire_barrel_active"),
				new BlockItem(BurntModBlocks.FIRE_BARREL_ACTIVE, new Item.Properties()));
		SMOLDERING_GRASS_START = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_grass_start"),
				new BlockItem(BurntModBlocks.SMOLDERING_GRASS_START, new Item.Properties()));
		SMOLDERING_OAK_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_oak_leaves"),
				new BlockItem(BurntModBlocks.SMOLDERING_OAK_LEAVES, new Item.Properties()));
		SMOLDERING_DARK_OAK_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_dark_oak_leaves"),
				new BlockItem(BurntModBlocks.SMOLDERING_DARK_OAK_LEAVES, new Item.Properties()));
		SMOLDERING_SPRUCE_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_spruce_leaves"),
				new BlockItem(BurntModBlocks.SMOLDERING_SPRUCE_LEAVES, new Item.Properties()));
		SMOLDERING_BIRCH_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_birch_leaves"),
				new BlockItem(BurntModBlocks.SMOLDERING_BIRCH_LEAVES, new Item.Properties()));
		SMOLDERING_JUNGLE_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_jungle_leaves"),
				new BlockItem(BurntModBlocks.SMOLDERING_JUNGLE_LEAVES, new Item.Properties()));
		SMOLDERING_MANGROVE_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_mangrove_leaves"),
				new BlockItem(BurntModBlocks.SMOLDERING_MANGROVE_LEAVES, new Item.Properties()));
		SMOLDERING_CHERRY_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_cherry_leaves"),
				new BlockItem(BurntModBlocks.SMOLDERING_CHERRY_LEAVES, new Item.Properties()));
		SMOLDERING_PALE_OAK_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_pale_oak_leaves"),
				new BlockItem(BurntModBlocks.SMOLDERING_PALE_OAK_LEAVES, new Item.Properties()));
		SMOLDERING_ACACIA_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_acacia_leaves"),
				new BlockItem(BurntModBlocks.SMOLDERING_ACACIA_LEAVES, new Item.Properties()));
		SMOLDERING_BAMBOO_BLOCK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_block"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_BLOCK, new Item.Properties()));
		SMOLDERING_BAMBOO_FENCE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_fence"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_FENCE, new Item.Properties()));
		SMOLDERING_BAMBOO_FENCEGATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_fencegate"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_FENCEGATE, new Item.Properties()));
		SMOLDERING_BAMBOO_MOSAIC_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_mosaic_planks"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_MOSAIC_PLANKS, new Item.Properties()));
		SMOLDERING_BAMBOO_MOSAIC_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_mosaic_slab"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_MOSAIC_SLAB, new Item.Properties()));
		SMOLDERING_BAMBOO_MOSAIC_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_mosaic_stairs"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_MOSAIC_STAIRS, new Item.Properties()));
		SMOLDERING_BAMBOO_PLANKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_planks"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_PLANKS, new Item.Properties()));
		SMOLDERING_BAMBOO_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_slab"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_SLAB, new Item.Properties()));
		SMOLDERING_BAMBOO_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_stairs"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_STAIRS, new Item.Properties()));
		SMOLDERING_BAMBOO_STALK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_stalk"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_STALK, new Item.Properties()));
		BURNT_LADDER = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_ladder"),
				new BlockItem(BurntModBlocks.BURNT_LADDER, new Item.Properties()));
		BURNT_SCAFFOLDING = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_scaffolding"),
				new BlockItem(BurntModBlocks.BURNT_SCAFFOLDING, new Item.Properties()));
		SOOTY_ACACIA_STRIPPED_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_acacia_stripped_wood"),
				new BlockItem(BurntModBlocks.SOOTY_ACACIA_STRIPPED_WOOD, new Item.Properties()));
		SMOLDERING_COAL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_coal"),
				new BlockItem(BurntModBlocks.SMOLDERING_COAL, new Item.Properties()));
		SOOTY_OAK_STRIPPED_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_oak_stripped_wood"),
				new BlockItem(BurntModBlocks.SOOTY_OAK_STRIPPED_WOOD, new Item.Properties()));
		SOOTY_DARK_OAK_STRIPPED_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_dark_oak_stripped_wood"),
				new BlockItem(BurntModBlocks.SOOTY_DARK_OAK_STRIPPED_WOOD, new Item.Properties()));
		SOOTY_JUNGLE_STRIPPED_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_jungle_stripped_wood"),
				new BlockItem(BurntModBlocks.SOOTY_JUNGLE_STRIPPED_WOOD, new Item.Properties()));
		SOOTY_BIRCH_STRIPPED_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_birch_stripped_wood"),
				new BlockItem(BurntModBlocks.SOOTY_BIRCH_STRIPPED_WOOD, new Item.Properties()));
		SOOTY_MANGROVE_STRIPPED_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mangrove_stripped_wood"),
				new BlockItem(BurntModBlocks.SOOTY_MANGROVE_STRIPPED_WOOD, new Item.Properties()));
		SOOTY_CHERRY_STRIPPED_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cherry_stripped_wood"),
				new BlockItem(BurntModBlocks.SOOTY_CHERRY_STRIPPED_WOOD, new Item.Properties()));
		SOOTY_PALE_OAK_STRIPPED_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_pale_oak_stripped_wood"),
				new BlockItem(BurntModBlocks.SOOTY_PALE_OAK_STRIPPED_WOOD, new Item.Properties()));
		SOOTY_SPRUCE_STRIPPED_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_spruce_stripped_wood"),
				new BlockItem(BurntModBlocks.SOOTY_SPRUCE_STRIPPED_WOOD, new Item.Properties()));
		SOOTY_BOOKSHELF = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_bookshelf"),
				new BlockItem(BurntModBlocks.SOOTY_BOOKSHELF, new Item.Properties()));
		BURNT_BOOKSHELF = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bookshelf"),
				new BlockItem(BurntModBlocks.BURNT_BOOKSHELF, new Item.Properties()));
		SPLASH = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "splash"),
				new BlockItem(BurntModBlocks.SPLASH, new Item.Properties()));
		SMOLDERING_LOG_2 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_log_2"),
				new BlockItem(BurntModBlocks.SMOLDERING_LOG_2, new Item.Properties()));
		SMOLDERING_LOG_3 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_log_3"),
				new BlockItem(BurntModBlocks.SMOLDERING_LOG_3, new Item.Properties()));
		SMOLDERING_DARK_OAK_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_dark_oak_log"),
				new BlockItem(BurntModBlocks.SMOLDERING_DARK_OAK_LOG, new Item.Properties()));
		SMOLDERING_DARK_OAK_WOOD = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_dark_oak_wood"),
				new BlockItem(BurntModBlocks.SMOLDERING_DARK_OAK_WOOD, new Item.Properties()));
		BURNT_MOSS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_moss"),
				new BlockItem(BurntModBlocks.BURNT_MOSS, new Item.Properties()));
		BURNT_WOOL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_wool"),
				new BlockItem(BurntModBlocks.BURNT_WOOL, new Item.Properties()));
		BURNT_ENVELOPE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_envelope"),
				new BlockItem(BurntModBlocks.BURNT_ENVELOPE, new Item.Properties()));
		BURNT_SAIL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_sail"),
				new BlockItem(BurntModBlocks.BURNT_SAIL, new Item.Properties()));
		BURNT_CARPET = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_carpet"),
				new BlockItem(BurntModBlocks.BURNT_CARPET, new Item.Properties()));
		BURNT_CAVE_VINES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_cave_vines"),
				new BlockItem(BurntModBlocks.BURNT_CAVE_VINES, new Item.Properties()));
		BURNT_CAVE_VINES_PLANT = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_cave_vines_plant"),
				new BlockItem(BurntModBlocks.BURNT_CAVE_VINES_PLANT, new Item.Properties()));
		SMOLDERING_CAVE_VINES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_cave_vines"),
				new BlockItem(BurntModBlocks.SMOLDERING_CAVE_VINES, new Item.Properties()));
		SMOLDERING_CAVE_VINES_PLANT = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_cave_vines_plant"),
				new BlockItem(BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT, new Item.Properties()));
		BAKED_APPLE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "baked_apple"),
				new BakedAppleItem());
		SOOTY_SMALL_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_small_log"),
				new BlockItem(BurntModBlocks.SOOTY_SMALL_LOG, new Item.Properties()));
		BURNT_BAMBOO_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_log"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_LOG, new Item.Properties()));
		BURNT_FOREST_BLOCK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_forest_block"),
				new BlockItem(BurntModBlocks.BURNT_FOREST_BLOCK, new Item.Properties()));
		BURNT_FOREST = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_forest"),
				new BlockItem(BurntModBlocks.BURNT_FOREST, new Item.Properties()));
		CREEKING_BURNT_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "creeking_burnt_log"),
				new BlockItem(BurntModBlocks.CREEKING_BURNT_LOG, new Item.Properties()));
		SMOLDERING_CACTUS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_cactus"),
				new BlockItem(BurntModBlocks.SMOLDERING_CACTUS, new Item.Properties()));
		BURNT_CACTUS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_cactus"),
				new BlockItem(BurntModBlocks.BURNT_CACTUS, new Item.Properties()));
		SMOLDERING_AZALEA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_azalea"),
				new BlockItem(BurntModBlocks.SMOLDERING_AZALEA, new Item.Properties()));
		BURNT_AZALEA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_azalea"),
				new BlockItem(BurntModBlocks.BURNT_AZALEA, new Item.Properties()));
		BURNT_MOSS_CARPET = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_moss_carpet"),
				new BlockItem(BurntModBlocks.BURNT_MOSS_CARPET, new Item.Properties()));
		BURNING_CAVE_VINES_PLANT = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burning_cave_vines_plant"),
				new BlockItem(BurntModBlocks.BURNING_CAVE_VINES_PLANT, new Item.Properties()));
		BLAST_LEAVES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "blast_leaves"),
				new BlockItem(BurntModBlocks.BLAST_LEAVES, new Item.Properties()));
		BURNT_CAMPFIRE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_campfire"),
				new BlockItem(BurntModBlocks.BURNT_CAMPFIRE, new Item.Properties()));
		EMBER_CAMPFIRE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_campfire"),
				new BlockItem(BurntModBlocks.EMBER_CAMPFIRE, new Item.Properties()));
		SMOLDERING_CAMPFIRE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_campfire"),
				new BlockItem(BurntModBlocks.SMOLDERING_CAMPFIRE, new Item.Properties()));
		BURNT_PUMPKIN = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_pumpkin"),
				new BlockItem(BurntModBlocks.BURNT_PUMPKIN, new Item.Properties()));
		BURNT_CARVED_PUMPKIN = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_carved_pumpkin"),
				new BlockItem(BurntModBlocks.BURNT_CARVED_PUMPKIN, new Item.Properties()));
		BURNT_JACK_O_LANTERN = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_jack_o_lantern"),
				new BlockItem(BurntModBlocks.BURNT_JACK_O_LANTERN, new Item.Properties()));
		SEMI_BURNT_DIRT = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "semi_burnt_dirt"),
				new BlockItem(BurntModBlocks.SEMI_BURNT_DIRT, new Item.Properties()));
		BURNT_MELON = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_melon"),
				new BlockItem(BurntModBlocks.BURNT_MELON, new Item.Properties()));
		BURNT_SUGAR_CANE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_sugar_cane"),
				new BlockItem(BurntModBlocks.BURNT_SUGAR_CANE, new Item.Properties()));
		FRAGILE_MAGMA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "fragile_magma"),
				new BlockItem(BurntModBlocks.FRAGILE_MAGMA, new Item.Properties()));
		BURNT_BAMBOO_BUTTON = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_button"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_BUTTON, new Item.Properties()));
		BURNT_BAMBOO_PRESSURE_PLATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_pressure_plate"),
				new BlockItem(BurntModBlocks.BURNT_BAMBOO_PRESSURE_PLATE, new Item.Properties()));
		EMBER_BERRIES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_berries"),
				new EmberBerriesItem());
		FLINT_SPARK_ITEM = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "flint_spark_item"),
				new FlintSparkItemItem());
		BURNT_CROPS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_crops"),
				new BlockItem(BurntModBlocks.BURNT_CROPS, new Item.Properties()));
		SMOLDERING_CROPS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_crops"),
				new BlockItem(BurntModBlocks.SMOLDERING_CROPS, new Item.Properties()));
		STRIPPED_EMBER_LOG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "stripped_ember_log"),
				new BlockItem(BurntModBlocks.STRIPPED_EMBER_LOG, new Item.Properties()));
		FALLING_BURNT_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "falling_burnt_slab"),
				new BlockItem(BurntModBlocks.FALLING_BURNT_SLAB, new Item.Properties()));
		FIRE_BARREL_BLAST_BLOCK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "fire_barrel_blast_block"),
				new BlockItem(BurntModBlocks.FIRE_BARREL_BLAST_BLOCK, new Item.Properties()));
		FIRE_SET_BLOCK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "fire_set_block"),
				new BlockItem(BurntModBlocks.FIRE_SET_BLOCK, new Item.Properties()));
		BURNT_GRASSY_EARTHEN_CLAY = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_grassy_earthen_clay"),
				new BlockItem(BurntModBlocks.BURNT_GRASSY_EARTHEN_CLAY, new Item.Properties()));
		BURNT_GRASSY_SANDY_D_IRT = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_grassy_sandy_d_irt"),
				new BlockItem(BurntModBlocks.BURNT_GRASSY_SANDY_D_IRT, new Item.Properties()));
		BURNT_IVY = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_ivy"),
				new BlockItem(BurntModBlocks.BURNT_IVY, new Item.Properties()));
		TALL_FLAMES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "tall_flames"),
				new BlockItem(BurntModBlocks.TALL_FLAMES, new Item.Properties()));
		BURNT_BARREL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_barrel"),
				new BlockItem(BurntModBlocks.BURNT_BARREL, new Item.Properties()));
		SMOLDERING_PLANKS_2 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_planks_2"),
				new BlockItem(BurntModBlocks.SMOLDERING_PLANKS_2, new Item.Properties()));
		SMOLDERING_PLANKS_3 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_planks_3"),
				new BlockItem(BurntModBlocks.SMOLDERING_PLANKS_3, new Item.Properties()));
		SMOLDERING_PLANKS_4 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_planks_4"),
				new BlockItem(BurntModBlocks.SMOLDERING_PLANKS_4, new Item.Properties()));
		SOOT_BLOCK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "soot_block"),
				new BlockItem(BurntModBlocks.SOOT_BLOCK, new Item.Properties()));
		STAIRS_FIRE_4 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "stairs_fire_4"),
				new BlockItem(BurntModBlocks.STAIRS_FIRE_4, new Item.Properties()));
		STAIRS_FIRE_5 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "stairs_fire_5"),
				new BlockItem(BurntModBlocks.STAIRS_FIRE_5, new Item.Properties()));
		SMOLDERING_WHEAT = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_wheat"),
				new BlockItem(BurntModBlocks.SMOLDERING_WHEAT, new Item.Properties()));
		SMOLDERING_CARROTS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_carrots"),
				new BlockItem(BurntModBlocks.SMOLDERING_CARROTS, new Item.Properties()));
		SMOLDERING_BEETROOT = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_beetroot"),
				new BlockItem(BurntModBlocks.SMOLDERING_BEETROOT, new Item.Properties()));
		SMOLDERING_POTATOES = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_potatoes"),
				new BlockItem(BurntModBlocks.SMOLDERING_POTATOES, new Item.Properties()));
		FIRESTARTER = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "firestarter"),
				new BlockItem(BurntModBlocks.FIRESTARTER, new Item.Properties()));
		SMOLDERING_OAK_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_oak_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_OAK_DOOR, new Item.Properties()));
		SMOLDERING_ACACIA_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_acacia_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_ACACIA_DOOR, new Item.Properties()));
		SMOLDERING_SPRUCE_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_spruce_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_SPRUCE_DOOR, new Item.Properties()));
		SMOLDERING_JUNGLE_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_jungle_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_JUNGLE_DOOR, new Item.Properties()));
		SMOLDERING_BIRCH_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_birch_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_BIRCH_DOOR, new Item.Properties()));
		SMOLDERING_DARK_OAK_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_dark_oak_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_DARK_OAK_DOOR, new Item.Properties()));
		SMOLDERING_CHERRY_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_cherry_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_CHERRY_DOOR, new Item.Properties()));
		SMOLDERING_PALE_OAK_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_pale_oak_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_PALE_OAK_DOOR, new Item.Properties()));
		SMOLDERING_MANGROVE_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_mangrove_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_MANGROVE_DOOR, new Item.Properties()));
		SMOLDERING_BAMBOO_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_BAMBOO_DOOR, new Item.Properties()));
		SMOLDERING_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_door"),
				new DoubleHighBlockItem(BurntModBlocks.SMOLDERING_DOOR, new Item.Properties()));
		BURNT_ACACIA_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_acacia_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_ACACIA_DOOR, new Item.Properties()));
		BURNT_BAMBOO_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_bamboo_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_BAMBOO_DOOR, new Item.Properties()));
		BURNT_BIRCH_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_birch_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_BIRCH_DOOR, new Item.Properties()));
		BURNT_CHERRY_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_cherry_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_CHERRY_DOOR, new Item.Properties()));
		BURNT_PALE_OAK_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_pale_oak_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_PALE_OAK_DOOR, new Item.Properties()));
		BURNT_DARK_OAK_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_dark_oak_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_DARK_OAK_DOOR, new Item.Properties()));
		BURNT_JUNGLE_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_jungle_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_JUNGLE_DOOR, new Item.Properties()));
		BURNT_MANGROVE_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_mangrove_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_MANGROVE_DOOR, new Item.Properties()));
		BURNT_OAK_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_oak_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_OAK_DOOR, new Item.Properties()));
		BURNT_SPRUCE_DOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_spruce_door"),
				new DoubleHighBlockItem(BurntModBlocks.BURNT_SPRUCE_DOOR, new Item.Properties()));
		SMOLDERING_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_TRAPDOOR, new Item.Properties()));
		SMOLDERING_OAK_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_oak_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_OAK_TRAPDOOR, new Item.Properties()));
		SMOLDERING_DARK_OAK_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_dark_oak_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_DARK_OAK_TRAPDOOR, new Item.Properties()));
		SMOLDERING_SPRUCE_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_spruce_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_SPRUCE_TRAPDOOR, new Item.Properties()));
		SMOLDERING_BIRCH_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_birch_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_BIRCH_TRAPDOOR, new Item.Properties()));
		SMOLDERING_JUNGLE_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_jungle_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_JUNGLE_TRAPDOOR, new Item.Properties()));
		SMOLDERING_MANGROVE_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_mangrove_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_MANGROVE_TRAPDOOR, new Item.Properties()));
		SMOLDERING_CHERRY_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_cherry_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_CHERRY_TRAPDOOR, new Item.Properties()));
		SMOLDERING_PALE_OAK_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_pale_oak_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_PALE_OAK_TRAPDOOR, new Item.Properties()));
		SMOLDERING_ACACIA_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_acacia_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_ACACIA_TRAPDOOR, new Item.Properties()));
		SMOLDERING_BAMBOO_TRAPDOOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_bamboo_trapdoor"),
				new BlockItem(BurntModBlocks.SMOLDERING_BAMBOO_TRAPDOOR, new Item.Properties()));
		SMOLDERING_MOSS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_moss"),
				new BlockItem(BurntModBlocks.SMOLDERING_MOSS, new Item.Properties()));
		SMOLDERING_MOSS_CARPET = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_moss_carpet"),
				new BlockItem(BurntModBlocks.SMOLDERING_MOSS_CARPET, new Item.Properties()));
		SOOTY_COBBLESTONE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cobblestone"),
				new BlockItem(BurntModBlocks.SOOTY_COBBLESTONE, new Item.Properties()));
		SOOTY_MOSSY_COBBLESTONE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mossy_cobblestone"),
				new BlockItem(BurntModBlocks.SOOTY_MOSSY_COBBLESTONE, new Item.Properties()));
		SOOTY_STONE_BRICKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stone_bricks"),
				new BlockItem(BurntModBlocks.SOOTY_STONE_BRICKS, new Item.Properties()));
		SOOTY_MOSSY_STONE_BRICKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mossy_stone_bricks"),
				new BlockItem(BurntModBlocks.SOOTY_MOSSY_STONE_BRICKS, new Item.Properties()));
		SOOTY_STONE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stone"),
				new BlockItem(BurntModBlocks.SOOTY_STONE, new Item.Properties()));
		SOOTY_BRICKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_bricks"),
				new BlockItem(BurntModBlocks.SOOTY_BRICKS, new Item.Properties()));
		SOOTY_COBBLESTONE_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cobblestone_wall"),
				new BlockItem(BurntModBlocks.SOOTY_COBBLESTONE_WALL, new Item.Properties()));
		SOOTY_BRICK_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_brick_wall"),
				new BlockItem(BurntModBlocks.SOOTY_BRICK_WALL, new Item.Properties()));
		SOOTY_STONE_BRICK_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stone_brick_wall"),
				new BlockItem(BurntModBlocks.SOOTY_STONE_BRICK_WALL, new Item.Properties()));
		SOOTY_MOSSY_COBBLESTONE_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mossy_cobblestone_wall"),
				new BlockItem(BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_WALL, new Item.Properties()));
		SOOTY_MOSSY_STONE_BRICK_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mossy_stone_brick_wall"),
				new BlockItem(BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_WALL, new Item.Properties()));
		SOOTY_COBBLESTONE_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cobblestone_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_COBBLESTONE_STAIRS, new Item.Properties()));
		SOOTY_MOSSY_COBBLESTONE_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mossy_cobblestone_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_STAIRS, new Item.Properties()));
		SOOTY_STONE_BRICK_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stone_brick_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_STONE_BRICK_STAIRS, new Item.Properties()));
		SOOTY_MOSSY_STONE_BRICK_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mossy_stone_brick_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_STAIRS, new Item.Properties()));
		SOOTY_BRICK_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_brick_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_BRICK_STAIRS, new Item.Properties()));
		SOOTY_COBBLESTONE_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_cobblestone_slab"),
				new BlockItem(BurntModBlocks.SOOTY_COBBLESTONE_SLAB, new Item.Properties()));
		SOOTY_MOSSY_COBBLESTONE_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mossy_cobblestone_slab"),
				new BlockItem(BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_SLAB, new Item.Properties()));
		SOOTY_STONE_BRICK_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stone_brick_slab"),
				new BlockItem(BurntModBlocks.SOOTY_STONE_BRICK_SLAB, new Item.Properties()));
		SOOTY_MOSSY_STONE_BRICK_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mossy_stone_brick_slab"),
				new BlockItem(BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_SLAB, new Item.Properties()));
		SOOTY_BRICK_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_brick_slab"),
				new BlockItem(BurntModBlocks.SOOTY_BRICK_SLAB, new Item.Properties()));
		SOOT = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "soot"),
				new SootItem());
		DOUBLE_TALL_GRASS_FIRE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "double_tall_grass_fire"),
				new BlockItem(BurntModBlocks.DOUBLE_TALL_GRASS_FIRE, new Item.Properties()));
		SOOTY_DIORITE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_diorite"),
				new BlockItem(BurntModBlocks.SOOTY_DIORITE, new Item.Properties()));
		SOOTY_ANDESITE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_andesite"),
				new BlockItem(BurntModBlocks.SOOTY_ANDESITE, new Item.Properties()));
		SOOTY_GRANITE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_granite"),
				new BlockItem(BurntModBlocks.SOOTY_GRANITE, new Item.Properties()));
		SOOTY_CHISELED_STONE_BRICKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_chiseled_stone_bricks"),
				new BlockItem(BurntModBlocks.SOOTY_CHISELED_STONE_BRICKS, new Item.Properties()));
		FIREBALL_ITEM = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "fireball_item"),
				new FireballItemItem());
		SMOLDERING_LEAVES_2 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_leaves_2"),
				new BlockItem(BurntModBlocks.SMOLDERING_LEAVES_2, new Item.Properties()));
		SMOLDERING_LEAVES_3 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_leaves_3"),
				new BlockItem(BurntModBlocks.SMOLDERING_LEAVES_3, new Item.Properties()));
		SMOLDERING_LEAVES_4 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_leaves_4"),
				new BlockItem(BurntModBlocks.SMOLDERING_LEAVES_4, new Item.Properties()));
		SMOLDERING_LEAVES_5 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_leaves_5"),
				new BlockItem(BurntModBlocks.SMOLDERING_LEAVES_5, new Item.Properties()));
		COOKED_RED_MUSHROOM_BLOCK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cooked_red_mushroom_block"),
				new BlockItem(BurntModBlocks.COOKED_RED_MUSHROOM_BLOCK, new Item.Properties()));
		COOKED_BROWN_MUSHROOM_BLOCK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cooked_brown_mushroom_block"),
				new BlockItem(BurntModBlocks.COOKED_BROWN_MUSHROOM_BLOCK, new Item.Properties()));
		COOKED_MUSHROOM_STEM = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cooked_mushroom_stem"),
				new BlockItem(BurntModBlocks.COOKED_MUSHROOM_STEM, new Item.Properties()));
		SOOTY_STONE_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stone_slab"),
				new BlockItem(BurntModBlocks.SOOTY_STONE_SLAB, new Item.Properties()));
		SOOTY_STONE_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_stone_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_STONE_STAIRS, new Item.Properties()));
		SOOTY_DIORITE_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_diorite_slab"),
				new BlockItem(BurntModBlocks.SOOTY_DIORITE_SLAB, new Item.Properties()));
		SOOTY_DIORITE_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_diorite_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_DIORITE_STAIRS, new Item.Properties()));
		SOOTY_DIORITE_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_diorite_wall"),
				new BlockItem(BurntModBlocks.SOOTY_DIORITE_WALL, new Item.Properties()));
		SOOTY_ANDESITE_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_andesite_slab"),
				new BlockItem(BurntModBlocks.SOOTY_ANDESITE_SLAB, new Item.Properties()));
		SOOTY_ANDESITE_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_andesite_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_ANDESITE_STAIRS, new Item.Properties()));
		SOOTY_ANDESITE_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_andesite_wall"),
				new BlockItem(BurntModBlocks.SOOTY_ANDESITE_WALL, new Item.Properties()));
		SOOTY_GRANITE_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_granite_slab"),
				new BlockItem(BurntModBlocks.SOOTY_GRANITE_SLAB, new Item.Properties()));
		SOOTY_GRANITE_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_granite_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_GRANITE_STAIRS, new Item.Properties()));
		SOOTY_GRANITE_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_granite_wall"),
				new BlockItem(BurntModBlocks.SOOTY_GRANITE_WALL, new Item.Properties()));
		FALLEN_BURNT_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "fallen_burnt_slab"),
				new BlockItem(BurntModBlocks.FALLEN_BURNT_SLAB, new Item.Properties()));
		SMOLDERING_HIGH_GRASS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_high_grass"),
				new BlockItem(BurntModBlocks.SMOLDERING_HIGH_GRASS, new Item.Properties()));
		BURNT_HIGH_GRASS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_high_grass"),
				new BlockItem(BurntModBlocks.BURNT_HIGH_GRASS, new Item.Properties()));
		DOUBLE_FALLEN_BURNT_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "double_fallen_burnt_slab"),
				new BlockItem(BurntModBlocks.DOUBLE_FALLEN_BURNT_SLAB, new Item.Properties()));
		SOOTY_MANGROVE_FENCE_GATE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mangrove_fence_gate"),
				new BlockItem(BurntModBlocks.SOOTY_MANGROVE_FENCE_GATE, new Item.Properties()));
		SMOKE_SPRITE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoke_sprite"),
				new SmokeSpriteItem());
		FAR_SMOKE_SPRITE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "far_smoke_sprite"),
				new FarSmokeSpriteItem());
		NEAR_SMOKE_SPRITE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "near_smoke_sprite"),
				new NearSmokeSpriteItem());
		TALL_SMOKE_SPRITE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "tall_smoke_sprite"),
				new TallSmokeSpriteItem());
		SMOLDERING_COBWEB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_cobweb"),
				new BlockItem(BurntModBlocks.SMOLDERING_COBWEB, new Item.Properties()));
		DISTANT_SMOKE_SPRITE = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "distant_smoke_sprite"),
				new DistantSmokeSpriteItem());
		BURNT_FLESH_BLOCK = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_flesh_block"),
				new BlockItem(BurntModBlocks.BURNT_FLESH_BLOCK, new Item.Properties()));
		CHARRED_MYCELIUM = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "charred_mycelium"),
				new BlockItem(BurntModBlocks.CHARRED_MYCELIUM, new Item.Properties()));
		CRACKED_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_TERRACOTTA, new Item.Properties()));
		CRACKED_WHITE_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_white_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_WHITE_TERRACOTTA, new Item.Properties()));
		CRACKED_GREEN_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_green_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_GREEN_TERRACOTTA, new Item.Properties()));
		CRACKED_LIGHT_BLUE_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_light_blue_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_LIGHT_BLUE_TERRACOTTA, new Item.Properties()));
		CRACKED_LIGHT_GREY_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_light_grey_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_LIGHT_GREY_TERRACOTTA, new Item.Properties()));
		CRACKED_LIME_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_lime_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_LIME_TERRACOTTA, new Item.Properties()));
		CRACKED_MAGENTA_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_magenta_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_MAGENTA_TERRACOTTA, new Item.Properties()));
		CRACKED_ORANGE_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_orange_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_ORANGE_TERRACOTTA, new Item.Properties()));
		CRACKED_PINK_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_pink_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_PINK_TERRACOTTA, new Item.Properties()));
		CRACKED_PURPLE_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_purple_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_PURPLE_TERRACOTTA, new Item.Properties()));
		CRACKED_RED_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_red_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_RED_TERRACOTTA, new Item.Properties()));
		CRACKED_YELLOW_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_yellow_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_YELLOW_TERRACOTTA, new Item.Properties()));
		CRACKED_CYAN_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_cyan_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_CYAN_TERRACOTTA, new Item.Properties()));
		CRACKED_BLUE_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_blue_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_BLUE_TERRACOTTA, new Item.Properties()));
		CRACKED_GREY_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_grey_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_GREY_TERRACOTTA, new Item.Properties()));
		CRACKED_BLACK_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_black_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_BLACK_TERRACOTTA, new Item.Properties()));
		CRACKED_BROWN_TERRACOTTA = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cracked_brown_terracotta"),
				new BlockItem(BurntModBlocks.CRACKED_BROWN_TERRACOTTA, new Item.Properties()));
		SOOTY_MUD_BRICKS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mud_bricks"),
				new BlockItem(BurntModBlocks.SOOTY_MUD_BRICKS, new Item.Properties()));
		SOOTY_MUD_BRICK_STAIRS = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mud_brick_stairs"),
				new BlockItem(BurntModBlocks.SOOTY_MUD_BRICK_STAIRS, new Item.Properties()));
		SOOTY_MUD_BRICK_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mud_brick_slab"),
				new BlockItem(BurntModBlocks.SOOTY_MUD_BRICK_SLAB, new Item.Properties()));
		SOOTY_MUD_BRICK_WALL = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "sooty_mud_brick_wall"),
				new BlockItem(BurntModBlocks.SOOTY_MUD_BRICK_WALL, new Item.Properties()));
		NEAR_SMOKE_SPRITE_2 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "near_smoke_sprite_2"),
				new NearSmokeSprite2Item());
		NEAR_SMOKE_SPRITE_3 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "near_smoke_sprite_3"),
				new NearSmokeSprite3Item());
		TALL_SMOKE_SPRITE_2 = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "tall_smoke_sprite_2"),
				new TallSmokeSprite2Item());
		WHITE_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "white_flag"),
				new BlockItem(BurntModBlocks.WHITE_FLAG, new Item.Properties()));
		BLACK_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "black_flag"),
				new BlockItem(BurntModBlocks.BLACK_FLAG, new Item.Properties()));
		YELLOW_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "yellow_flag"),
				new BlockItem(BurntModBlocks.YELLOW_FLAG, new Item.Properties()));
		PINK_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "pink_flag"),
				new BlockItem(BurntModBlocks.PINK_FLAG, new Item.Properties()));
		BLUE_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "blue_flag"),
				new BlockItem(BurntModBlocks.BLUE_FLAG, new Item.Properties()));
		LIGHT_BLUE_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "light_blue_flag"),
				new BlockItem(BurntModBlocks.LIGHT_BLUE_FLAG, new Item.Properties()));
		CYAN_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "cyan_flag"),
				new BlockItem(BurntModBlocks.CYAN_FLAG, new Item.Properties()));
		GREEN_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "green_flag"),
				new BlockItem(BurntModBlocks.GREEN_FLAG, new Item.Properties()));
		LIME_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "lime_flag"),
				new BlockItem(BurntModBlocks.LIME_FLAG, new Item.Properties()));
		PURPLE_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "purple_flag"),
				new BlockItem(BurntModBlocks.PURPLE_FLAG, new Item.Properties()));
		MAGENTA_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "magenta_flag"),
				new BlockItem(BurntModBlocks.MAGENTA_FLAG, new Item.Properties()));
		ORANGE_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "orange_flag"),
				new BlockItem(BurntModBlocks.ORANGE_FLAG, new Item.Properties()));
		BROWN_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "brown_flag"),
				new BlockItem(BurntModBlocks.BROWN_FLAG, new Item.Properties()));
		GREY_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "grey_flag"),
				new BlockItem(BurntModBlocks.GREY_FLAG, new Item.Properties()));
		LIGHT_GREY_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "light_grey_flag"),
				new BlockItem(BurntModBlocks.LIGHT_GREY_FLAG, new Item.Properties()));
		RED_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "red_flag"),
				new BlockItem(BurntModBlocks.RED_FLAG, new Item.Properties()));
		WIND_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "wind_flag"),
				new BlockItem(BurntModBlocks.WIND_FLAG, new Item.Properties()));
		RAINBOW_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "rainbow_flag"),
				new BlockItem(BurntModBlocks.RAINBOW_FLAG, new Item.Properties()));
		PIRATE_FLAG = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "pirate_flag"),
				new BlockItem(BurntModBlocks.PIRATE_FLAG, new Item.Properties()));
		EMBER_PROJECTILE_ITEM = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "ember_projectile_item"),
				new EmberProjectileItemItem());
		SMOKE_DETECTOR = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoke_detector"),
				new BlockItem(BurntModBlocks.SMOKE_DETECTOR, new Item.Properties()));
		SMOLDERING_GRASS_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_grass_slab"),
				new BlockItem(BurntModBlocks.SMOLDERING_GRASS_SLAB, new Item.Properties()));
		BURNT_GRASS_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_grass_slab"),
				new BlockItem(BurntModBlocks.BURNT_GRASS_SLAB, new Item.Properties()));
		RECOVERING_GRASS_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "recovering_grass_slab"),
				new BlockItem(BurntModBlocks.RECOVERING_GRASS_SLAB, new Item.Properties()));
		SMOLDERING_MOSS_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoldering_moss_slab"),
				new BlockItem(BurntModBlocks.SMOLDERING_MOSS_SLAB, new Item.Properties()));
		BURNT_MOSS_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_moss_slab"),
				new BlockItem(BurntModBlocks.BURNT_MOSS_SLAB, new Item.Properties()));
		BURNT_DIRT_SLAB = Registry.register(BuiltInRegistries.ITEM,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_dirt_slab"),
				new BlockItem(BurntModBlocks.BURNT_DIRT_SLAB, new Item.Properties()));
		});
	}

}
