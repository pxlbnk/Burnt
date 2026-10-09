package net.pixelbank.burnt.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.world.item.CreativeModeTabs;

public class BurntModTabs {

	public static void register() {
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
			if (net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration.ENABLE_FIRE_EXTINGUISHER.get())
				entries.accept(BurntModItems.EXTINGUISHER);
		});

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COLORED_BLOCKS).register(entries -> {
			entries.accept(BurntModBlocks.BURNT_WOOL);
			if (net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration.ENABLE_FLAGS.get()) {
				entries.accept(BurntModBlocks.WHITE_FLAG);
				entries.accept(BurntModBlocks.BLACK_FLAG);
				entries.accept(BurntModBlocks.YELLOW_FLAG);
				entries.accept(BurntModBlocks.PINK_FLAG);
				entries.accept(BurntModBlocks.BLUE_FLAG);
				entries.accept(BurntModBlocks.LIGHT_BLUE_FLAG);
				entries.accept(BurntModBlocks.CYAN_FLAG);
				entries.accept(BurntModBlocks.GREEN_FLAG);
				entries.accept(BurntModBlocks.LIME_FLAG);
				entries.accept(BurntModBlocks.PURPLE_FLAG);
				entries.accept(BurntModBlocks.MAGENTA_FLAG);
				entries.accept(BurntModBlocks.ORANGE_FLAG);
				entries.accept(BurntModBlocks.BROWN_FLAG);
				entries.accept(BurntModBlocks.GREY_FLAG);
				entries.accept(BurntModBlocks.LIGHT_GREY_FLAG);
				entries.accept(BurntModBlocks.RED_FLAG);
				entries.accept(BurntModBlocks.WIND_FLAG);
				entries.accept(BurntModBlocks.RAINBOW_FLAG);
				entries.accept(BurntModBlocks.PIRATE_FLAG);
				entries.accept(BurntModBlocks.CREEPER_FLAG);
				entries.accept(BurntModBlocks.WILDERNESS_FLAG);
				entries.accept(BurntModBlocks.FIRE_FLAG);
			}
		});

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
			entries.accept(BurntModItems.BAKED_APPLE);
			entries.accept(BurntModItems.EMBER_BERRIES);
		});

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
			entries.accept(BurntModItems.SOOT);
		});

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
			entries.accept(BurntModBlocks.BURNT_LEAVES);
			entries.accept(BurntModBlocks.BURNT_LOG);
			entries.accept(BurntModBlocks.BURNT_HOLLOW_LOG);
			entries.accept(BurntModBlocks.BROKEN_BURNT_LOG);
			entries.accept(BurntModBlocks.BURNT_GRASS);
			entries.accept(BurntModBlocks.BURNT_DIRT);
			entries.accept(BurntModBlocks.BURNT_PATH);
			entries.accept(BurntModBlocks.BURNT_MANGROVE_ROOTS);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_STALK);
			entries.accept(BurntModBlocks.BURNT_HAY);
			entries.accept(BurntModBlocks.BURNT_MOSS);
			entries.accept(BurntModBlocks.BURNT_CACTUS);
			entries.accept(BurntModBlocks.BURNT_AZALEA);
			entries.accept(BurntModBlocks.BURNT_MOSS_CARPET);
			// Terrain Slabs soil chain. These are always registered but only reachable in-world
			// when Terrain Slabs is installed, so keep them out of the tab otherwise. Only the
			// burnt stages are offered - smoldering and recovering are transient states.
			if (net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("terrain_slabs")) {
				entries.accept(BurntModBlocks.BURNT_GRASS_SLAB);
				entries.accept(BurntModBlocks.BURNT_MOSS_SLAB);
				entries.accept(BurntModBlocks.BURNT_DIRT_SLAB);
			}
			entries.accept(BurntModBlocks.BURNT_PUMPKIN);
			entries.accept(BurntModBlocks.BURNT_CARVED_PUMPKIN);
			entries.accept(BurntModBlocks.BURNT_JACK_O_LANTERN);
			entries.accept(BurntModBlocks.BURNT_MELON);
			entries.accept(BurntModBlocks.COOKED_RED_MUSHROOM_BLOCK);
			entries.accept(BurntModBlocks.COOKED_BROWN_MUSHROOM_BLOCK);
			entries.accept(BurntModBlocks.COOKED_MUSHROOM_STEM);
			entries.accept(BurntModBlocks.CHARRED_MYCELIUM);
		});

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
			entries.accept(BurntModBlocks.BURNT_LOG);
			entries.accept(BurntModBlocks.BURNT_HOLLOW_LOG);
			entries.accept(BurntModBlocks.BROKEN_BURNT_LOG);
			entries.accept(BurntModBlocks.STRIPPED_BURNT_LOG);
			entries.accept(BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG);
			entries.accept(BurntModBlocks.BURNT_WOOD);
			entries.accept(BurntModBlocks.STRIPPED_BURNT_WOOD);
			entries.accept(BurntModBlocks.BURNT_PLANKS);
			entries.accept(BurntModBlocks.BURNT_STAIRS);
			entries.accept(BurntModBlocks.BURNT_SLAB);
			entries.accept(BurntModBlocks.BURNT_WALL);
			entries.accept(BurntModBlocks.BURNT_VERTICAL_SLAB);
			entries.accept(BurntModBlocks.BURNT_FENCE);
			entries.accept(BurntModBlocks.BURNT_FENCEGATE);
			entries.accept(BurntModBlocks.BURNT_BUTTON);
			entries.accept(BurntModBlocks.BURNT_PRESSURE_PLATE);
			entries.accept(BurntModBlocks.BURNT_DOOR);
			entries.accept(BurntModBlocks.BURNT_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_OAK_DOOR);
			entries.accept(BurntModBlocks.BURNT_OAK_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_SPRUCE_DOOR);
			entries.accept(BurntModBlocks.BURNT_SPRUCE_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_BIRCH_DOOR);
			entries.accept(BurntModBlocks.BURNT_BIRCH_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_ACACIA_DOOR);
			entries.accept(BurntModBlocks.BURNT_ACACIA_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_DARK_OAK_DOOR);
			entries.accept(BurntModBlocks.BURNT_DARK_OAK_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_JUNGLE_DOOR);
			entries.accept(BurntModBlocks.BURNT_JUNGLE_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_MANGROVE_DOOR);
			entries.accept(BurntModBlocks.BURNT_MANGROVE_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_CHERRY_DOOR);
			entries.accept(BurntModBlocks.BURNT_PALE_OAK_DOOR);
			entries.accept(BurntModBlocks.BURNT_CHERRY_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_PALE_OAK_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_DOOR);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_TRAPDOOR);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_BLOCK);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_PLANKS);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_MOSAIC_PLANKS);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_STAIRS);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_MOSAIC_STAIRS);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_SLAB);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_MOSAIC_SLAB);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_FENCE);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_FENCEGATE);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_BUTTON);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_PRESSURE_PLATE);
			entries.accept(BurntModBlocks.BURNT_BOOKSHELF);
			entries.accept(BurntModBlocks.BURNT_LADDER);
			entries.accept(BurntModBlocks.BURNT_SCAFFOLDING);
			entries.accept(BurntModBlocks.FALLEN_BURNT_SLAB);
			entries.accept(BurntModBlocks.BURNT_CARPET);
		});

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(entries -> {
			entries.accept(BurntModBlocks.BURNT_BUTTON);
			entries.accept(BurntModBlocks.BURNT_PRESSURE_PLATE);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_BUTTON);
			entries.accept(BurntModBlocks.BURNT_BAMBOO_PRESSURE_PLATE);
			entries.accept(BurntModBlocks.FIRE_BARREL);
			entries.accept(BurntModBlocks.SMOKE_DETECTOR);
		});

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
			entries.accept(BurntModBlocks.BURNT_BARREL);
			entries.accept(BurntModBlocks.BURNT_CAMPFIRE);
			if (net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration.ENABLE_FLAGS.get()) {
				entries.accept(BurntModBlocks.WHITE_FLAG);
				entries.accept(BurntModBlocks.BLACK_FLAG);
				entries.accept(BurntModBlocks.YELLOW_FLAG);
				entries.accept(BurntModBlocks.PINK_FLAG);
				entries.accept(BurntModBlocks.BLUE_FLAG);
				entries.accept(BurntModBlocks.LIGHT_BLUE_FLAG);
				entries.accept(BurntModBlocks.CYAN_FLAG);
				entries.accept(BurntModBlocks.GREEN_FLAG);
				entries.accept(BurntModBlocks.LIME_FLAG);
				entries.accept(BurntModBlocks.PURPLE_FLAG);
				entries.accept(BurntModBlocks.MAGENTA_FLAG);
				entries.accept(BurntModBlocks.ORANGE_FLAG);
				entries.accept(BurntModBlocks.BROWN_FLAG);
				entries.accept(BurntModBlocks.GREY_FLAG);
				entries.accept(BurntModBlocks.LIGHT_GREY_FLAG);
				entries.accept(BurntModBlocks.RED_FLAG);
				entries.accept(BurntModBlocks.WIND_FLAG);
				entries.accept(BurntModBlocks.RAINBOW_FLAG);
				entries.accept(BurntModBlocks.PIRATE_FLAG);
				entries.accept(BurntModBlocks.CREEPER_FLAG);
				entries.accept(BurntModBlocks.WILDERNESS_FLAG);
				entries.accept(BurntModBlocks.FIRE_FLAG);
			}
		});
	}
}
