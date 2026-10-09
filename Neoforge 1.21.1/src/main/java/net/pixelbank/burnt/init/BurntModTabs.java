package net.pixelbank.burnt.init;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;

public class BurntModTabs {

	public static void register(IEventBus bus) {
		bus.addListener(BurntModTabs::addCreative);
	}

	private static void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			if (net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration.ENABLE_FIRE_EXTINGUISHER.get())
				event.accept(BurntModItems.EXTINGUISHER);
		}

		if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
			event.accept(BurntModBlocks.BURNT_WOOL);
			if (net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration.ENABLE_FLAGS.get()) {
				event.accept(BurntModBlocks.WHITE_FLAG);
				event.accept(BurntModBlocks.BLACK_FLAG);
				event.accept(BurntModBlocks.YELLOW_FLAG);
				event.accept(BurntModBlocks.PINK_FLAG);
				event.accept(BurntModBlocks.BLUE_FLAG);
				event.accept(BurntModBlocks.LIGHT_BLUE_FLAG);
				event.accept(BurntModBlocks.CYAN_FLAG);
				event.accept(BurntModBlocks.GREEN_FLAG);
				event.accept(BurntModBlocks.LIME_FLAG);
				event.accept(BurntModBlocks.PURPLE_FLAG);
				event.accept(BurntModBlocks.MAGENTA_FLAG);
				event.accept(BurntModBlocks.ORANGE_FLAG);
				event.accept(BurntModBlocks.BROWN_FLAG);
				event.accept(BurntModBlocks.GREY_FLAG);
				event.accept(BurntModBlocks.LIGHT_GREY_FLAG);
				event.accept(BurntModBlocks.RED_FLAG);
				event.accept(BurntModBlocks.WIND_FLAG);
				event.accept(BurntModBlocks.RAINBOW_FLAG);
				event.accept(BurntModBlocks.PIRATE_FLAG);
				event.accept(BurntModBlocks.CREEPER_FLAG);
				event.accept(BurntModBlocks.WILDERNESS_FLAG);
				event.accept(BurntModBlocks.FIRE_FLAG);
			}
		}

		if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			event.accept(BurntModItems.BAKED_APPLE);
			event.accept(BurntModItems.EMBER_BERRIES);
		}

		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			event.accept(BurntModItems.SOOT);
		}

		if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			event.accept(BurntModBlocks.BURNT_LEAVES);
			event.accept(BurntModBlocks.BURNT_LOG);
			event.accept(BurntModBlocks.BURNT_HOLLOW_LOG);
			event.accept(BurntModBlocks.BROKEN_BURNT_LOG);
			event.accept(BurntModBlocks.BURNT_GRASS);
			event.accept(BurntModBlocks.BURNT_DIRT);
			event.accept(BurntModBlocks.BURNT_PATH);
			event.accept(BurntModBlocks.BURNT_MANGROVE_ROOTS);
			event.accept(BurntModBlocks.BURNT_BAMBOO_STALK);
			event.accept(BurntModBlocks.BURNT_HAY);
			event.accept(BurntModBlocks.BURNT_MOSS);
			event.accept(BurntModBlocks.BURNT_CACTUS);
			event.accept(BurntModBlocks.BURNT_AZALEA);
			event.accept(BurntModBlocks.BURNT_MOSS_CARPET);
			// Terrain Slabs soil chain. These are always registered but only reachable in-world
			// when Terrain Slabs is installed, so keep them out of the tab otherwise. Only the
			// burnt stages are offered - smoldering and recovering are transient states.
			if (net.neoforged.fml.ModList.get().isLoaded("terrain_slabs")) {
				event.accept(BurntModBlocks.BURNT_GRASS_SLAB);
				event.accept(BurntModBlocks.BURNT_MOSS_SLAB);
				event.accept(BurntModBlocks.BURNT_DIRT_SLAB);
			}
			event.accept(BurntModBlocks.BURNT_PUMPKIN);
			event.accept(BurntModBlocks.BURNT_CARVED_PUMPKIN);
			event.accept(BurntModBlocks.BURNT_JACK_O_LANTERN);
			event.accept(BurntModBlocks.BURNT_MELON);
			event.accept(BurntModBlocks.COOKED_RED_MUSHROOM_BLOCK);
			event.accept(BurntModBlocks.COOKED_BROWN_MUSHROOM_BLOCK);
			event.accept(BurntModBlocks.COOKED_MUSHROOM_STEM);
			event.accept(BurntModBlocks.CHARRED_MYCELIUM);
		}

		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			event.accept(BurntModBlocks.BURNT_LOG);
			event.accept(BurntModBlocks.BURNT_HOLLOW_LOG);
			event.accept(BurntModBlocks.BROKEN_BURNT_LOG);
			event.accept(BurntModBlocks.STRIPPED_BURNT_LOG);
			event.accept(BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG);
			event.accept(BurntModBlocks.BURNT_WOOD);
			event.accept(BurntModBlocks.STRIPPED_BURNT_WOOD);
			event.accept(BurntModBlocks.BURNT_PLANKS);
			event.accept(BurntModBlocks.BURNT_STAIRS);
			event.accept(BurntModBlocks.BURNT_SLAB);
			event.accept(BurntModBlocks.BURNT_WALL);
			event.accept(BurntModBlocks.BURNT_VERTICAL_SLAB);
			event.accept(BurntModBlocks.BURNT_FENCE);
			event.accept(BurntModBlocks.BURNT_FENCEGATE);
			event.accept(BurntModBlocks.BURNT_BUTTON);
			event.accept(BurntModBlocks.BURNT_PRESSURE_PLATE);
			event.accept(BurntModBlocks.BURNT_DOOR);
			event.accept(BurntModBlocks.BURNT_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_OAK_DOOR);
			event.accept(BurntModBlocks.BURNT_OAK_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_SPRUCE_DOOR);
			event.accept(BurntModBlocks.BURNT_SPRUCE_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_BIRCH_DOOR);
			event.accept(BurntModBlocks.BURNT_BIRCH_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_ACACIA_DOOR);
			event.accept(BurntModBlocks.BURNT_ACACIA_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_DARK_OAK_DOOR);
			event.accept(BurntModBlocks.BURNT_DARK_OAK_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_JUNGLE_DOOR);
			event.accept(BurntModBlocks.BURNT_JUNGLE_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_MANGROVE_DOOR);
			event.accept(BurntModBlocks.BURNT_MANGROVE_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_CHERRY_DOOR);
			event.accept(BurntModBlocks.BURNT_PALE_OAK_DOOR);
			event.accept(BurntModBlocks.BURNT_CHERRY_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_PALE_OAK_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_BAMBOO_DOOR);
			event.accept(BurntModBlocks.BURNT_BAMBOO_TRAPDOOR);
			event.accept(BurntModBlocks.BURNT_BAMBOO_BLOCK);
			event.accept(BurntModBlocks.BURNT_BAMBOO_PLANKS);
			event.accept(BurntModBlocks.BURNT_BAMBOO_MOSAIC_PLANKS);
			event.accept(BurntModBlocks.BURNT_BAMBOO_STAIRS);
			event.accept(BurntModBlocks.BURNT_BAMBOO_MOSAIC_STAIRS);
			event.accept(BurntModBlocks.BURNT_BAMBOO_SLAB);
			event.accept(BurntModBlocks.BURNT_BAMBOO_MOSAIC_SLAB);
			event.accept(BurntModBlocks.BURNT_BAMBOO_FENCE);
			event.accept(BurntModBlocks.BURNT_BAMBOO_FENCEGATE);
			event.accept(BurntModBlocks.BURNT_BAMBOO_BUTTON);
			event.accept(BurntModBlocks.BURNT_BAMBOO_PRESSURE_PLATE);
			event.accept(BurntModBlocks.BURNT_BOOKSHELF);
			event.accept(BurntModBlocks.BURNT_LADDER);
			event.accept(BurntModBlocks.BURNT_SCAFFOLDING);
			event.accept(BurntModBlocks.FALLEN_BURNT_SLAB);
			event.accept(BurntModBlocks.BURNT_CARPET);
		}

		if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
			event.accept(BurntModBlocks.BURNT_BUTTON);
			event.accept(BurntModBlocks.BURNT_PRESSURE_PLATE);
			event.accept(BurntModBlocks.BURNT_BAMBOO_BUTTON);
			event.accept(BurntModBlocks.BURNT_BAMBOO_PRESSURE_PLATE);
			event.accept(BurntModBlocks.FIRE_BARREL);
			event.accept(BurntModBlocks.SMOKE_DETECTOR);
		}

		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			event.accept(BurntModBlocks.BURNT_BARREL);
			event.accept(BurntModBlocks.BURNT_CAMPFIRE);
			if (net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration.ENABLE_FLAGS.get()) {
				event.accept(BurntModBlocks.WHITE_FLAG);
				event.accept(BurntModBlocks.BLACK_FLAG);
				event.accept(BurntModBlocks.YELLOW_FLAG);
				event.accept(BurntModBlocks.PINK_FLAG);
				event.accept(BurntModBlocks.BLUE_FLAG);
				event.accept(BurntModBlocks.LIGHT_BLUE_FLAG);
				event.accept(BurntModBlocks.CYAN_FLAG);
				event.accept(BurntModBlocks.GREEN_FLAG);
				event.accept(BurntModBlocks.LIME_FLAG);
				event.accept(BurntModBlocks.PURPLE_FLAG);
				event.accept(BurntModBlocks.MAGENTA_FLAG);
				event.accept(BurntModBlocks.ORANGE_FLAG);
				event.accept(BurntModBlocks.BROWN_FLAG);
				event.accept(BurntModBlocks.GREY_FLAG);
				event.accept(BurntModBlocks.LIGHT_GREY_FLAG);
				event.accept(BurntModBlocks.RED_FLAG);
				event.accept(BurntModBlocks.WIND_FLAG);
				event.accept(BurntModBlocks.RAINBOW_FLAG);
				event.accept(BurntModBlocks.PIRATE_FLAG);
				event.accept(BurntModBlocks.CREEPER_FLAG);
				event.accept(BurntModBlocks.WILDERNESS_FLAG);
				event.accept(BurntModBlocks.FIRE_FLAG);
			}
		}
	}
}
