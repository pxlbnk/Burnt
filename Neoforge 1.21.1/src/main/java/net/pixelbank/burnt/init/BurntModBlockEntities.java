package net.pixelbank.burnt.init;

import net.pixelbank.burnt.block.entity.BurntBarrelBlockEntity;
import net.pixelbank.burnt.block.entity.FlagBlockEntity;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;

public class BurntModBlockEntities {
	public static BlockEntityType<BurntBarrelBlockEntity> BURNT_BARREL;
	public static BlockEntityType<FlagBlockEntity> FLAG;

	public static void register(IEventBus bus) {
		bus.addListener((RegisterEvent event) -> {
			if (!event.getRegistryKey().equals(Registries.BLOCK_ENTITY_TYPE)) return;

			BURNT_BARREL = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_barrel"),
					BlockEntityType.Builder.of(BurntBarrelBlockEntity::new, BurntModBlocks.BURNT_BARREL).build(null));

			// Flag block entity, attached to every coloured flag block
			FLAG = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
					ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "flag"),
					BlockEntityType.Builder.of(FlagBlockEntity::new,
							BurntModBlocks.BLACK_FLAG,
							BurntModBlocks.BLUE_FLAG,
							BurntModBlocks.BROWN_FLAG,
							BurntModBlocks.CYAN_FLAG,
							BurntModBlocks.GREEN_FLAG,
							BurntModBlocks.GREY_FLAG,
							BurntModBlocks.LIGHT_BLUE_FLAG,
							BurntModBlocks.LIGHT_GREY_FLAG,
							BurntModBlocks.LIME_FLAG,
							BurntModBlocks.MAGENTA_FLAG,
							BurntModBlocks.ORANGE_FLAG,
							BurntModBlocks.PINK_FLAG,
							BurntModBlocks.PIRATE_FLAG,
							BurntModBlocks.PURPLE_FLAG,
							BurntModBlocks.RAINBOW_FLAG,
							BurntModBlocks.RED_FLAG,
							BurntModBlocks.WHITE_FLAG,
							BurntModBlocks.WIND_FLAG,
							BurntModBlocks.YELLOW_FLAG
					).build(null));
		});
	}
}
