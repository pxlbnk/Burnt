package net.pixelbank.burnt.init;

import net.pixelbank.burnt.block.entity.BurntBarrelBlockEntity;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;

public class BurntModBlockEntities {
	public static BlockEntityType<BurntBarrelBlockEntity> BURNT_BARREL;

	public static void register(IEventBus bus) {
		bus.addListener((RegisterEvent event) -> {
			event.register(Registries.BLOCK_ENTITY_TYPE, helper -> {


				helper.register(new ResourceLocation(BurntMod.MODID, "burnt_barrel"),
					BURNT_BARREL = BlockEntityType.Builder.of(BurntBarrelBlockEntity::new, BurntModBlocks.BURNT_BARREL).build(null));
			});
		});
	}
}
