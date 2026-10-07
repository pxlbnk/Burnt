package net.pixelbank.burnt.init;

import net.pixelbank.burnt.block.entity.BurntBarrelBlockEntity;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BurntModBlockEntities {
	public static BlockEntityType<BurntBarrelBlockEntity> BURNT_BARREL;

	public static void register() {
		BURNT_BARREL = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_barrel"),
				BlockEntityType.Builder.of(BurntBarrelBlockEntity::new, BurntModBlocks.BURNT_BARREL).build(null));
	}
}
