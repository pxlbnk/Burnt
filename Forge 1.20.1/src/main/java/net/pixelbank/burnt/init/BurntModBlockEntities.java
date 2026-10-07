package net.pixelbank.burnt.init;

import net.pixelbank.burnt.block.entity.BurntBarrelBlockEntity;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;

public class BurntModBlockEntities {
	public static BlockEntityType<BurntBarrelBlockEntity> BURNT_BARREL;

	public static void register(IEventBus bus) {
		bus.addListener((RegisterEvent event) -> {
			if (!event.getRegistryKey().equals(Registries.BLOCK_ENTITY_TYPE)) return;


			BURNT_BARREL = reg(event, "burnt_barrel", BlockEntityType.Builder.of(BurntBarrelBlockEntity::new, BurntModBlocks.BURNT_BARREL).build(null));
		});
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private static <T extends BlockEntityType<?>> T reg(RegisterEvent event, String name, T type) {
		event.register(Registries.BLOCK_ENTITY_TYPE, new ResourceLocation(BurntMod.MODID, name), () -> (BlockEntityType) type);
		return type;
	}
}
