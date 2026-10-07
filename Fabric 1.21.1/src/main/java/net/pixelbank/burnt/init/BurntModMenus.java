package net.pixelbank.burnt.init;

import net.pixelbank.burnt.world.inventory.BurntBarrelGUIMenu;
import net.pixelbank.burnt.network.MenuStateUpdateMessage;
import net.pixelbank.burnt.BurntMod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;

import java.util.Map;

public class BurntModMenus {

	public static MenuType<BurntBarrelGUIMenu> BURNT_BARREL_GUI;

	public static void register() {
		// Client-side factory uses the no-extra-data constructor (server passes items via Container sync)
		BURNT_BARREL_GUI = Registry.register(BuiltInRegistries.MENU,
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "burnt_barrel_gui"),
				new MenuType<>((id, inv) -> new BurntBarrelGUIMenu(id, inv), FeatureFlags.DEFAULT_FLAGS));
	}

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (needClientUpdate && player instanceof ServerPlayer sp) {
				ServerPlayNetworking.send(sp, new MenuStateUpdateMessage(elementType, name, elementState));
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
