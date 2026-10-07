package net.pixelbank.burnt.init;

import net.pixelbank.burnt.world.inventory.BurntBarrelGUIMenu;
import net.pixelbank.burnt.network.MenuStateUpdateMessage;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.pixelbank.burnt.network.BurntNetwork;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Map;

public class BurntModMenus {

	public static MenuType<BurntBarrelGUIMenu> BURNT_BARREL_GUI;

	public static void register(IEventBus bus) {
		bus.addListener((RegisterEvent event) -> {
			if (!event.getRegistryKey().equals(Registries.MENU)) return;
			// Client-side factory uses the no-extra-data constructor (server passes items via Container sync)
			BURNT_BARREL_GUI = reg(event, "burnt_barrel_gui", new MenuType<>((id, inv) -> new BurntBarrelGUIMenu(id, inv), FeatureFlags.DEFAULT_FLAGS));
		});
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private static <T extends MenuType<?>> T reg(RegisterEvent event, String name, T type) {
		event.register(Registries.MENU, new ResourceLocation(BurntMod.MODID, name), () -> (MenuType) type);
		return type;
	}

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (needClientUpdate && player instanceof ServerPlayer sp) {
				BurntNetwork.sendToPlayer(sp, new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}
