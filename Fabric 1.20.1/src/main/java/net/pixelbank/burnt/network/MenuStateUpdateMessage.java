package net.pixelbank.burnt.network;

import net.pixelbank.burnt.init.BurntModScreens;
import net.pixelbank.burnt.init.BurntModMenus;
import net.pixelbank.burnt.BurntMod;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;

public class MenuStateUpdateMessage {

	public static final ResourceLocation ID =
		new ResourceLocation(BurntMod.MODID, "guistate_update");

	public final int elementType;
	public final String name;
	public final Object elementState;

	public MenuStateUpdateMessage(int elementType, String name, Object elementState) {
		this.elementType  = elementType;
		this.name         = name;
		this.elementState = elementState;
	}

	public static void write(FriendlyByteBuf buffer, MenuStateUpdateMessage message) {
		buffer.writeInt(message.elementType);
		buffer.writeUtf(message.name);
		if (message.elementType == 0) {
			buffer.writeUtf((String) message.elementState);
		} else if (message.elementType == 1) {
			buffer.writeBoolean((boolean) message.elementState);
		} else if (message.elementType == 2 && message.elementState instanceof Number n) {
			buffer.writeDouble(n.doubleValue());
		}
	}

	public static MenuStateUpdateMessage read(FriendlyByteBuf buffer) {
		int elementType = buffer.readInt();
		String name = buffer.readUtf();
		Object elementState = null;
		if (elementType == 0) {
			elementState = buffer.readUtf();
		} else if (elementType == 1) {
			elementState = buffer.readBoolean();
		} else if (elementType == 2) {
			elementState = buffer.readDouble();
		}
		return new MenuStateUpdateMessage(elementType, name, elementState);
	}

	/** Client-side packet handler - registered in BurntModClient. */
	public static void handleMenuState(MenuStateUpdateMessage message, Minecraft client) {
		if (message.name.length() > 256 || message.elementState instanceof String string && string.length() > 8192)
			return;
		client.execute(() -> {
			if (client.player != null && client.player.containerMenu instanceof BurntModMenus.MenuAccessor menu) {
				menu.getMenuState().put(message.elementType + ":" + message.name, message.elementState);
				if (client.screen instanceof BurntModScreens.ScreenAccessor accessor) {
					accessor.updateMenuState(message.elementType, message.name, message.elementState);
				}
			}
		});
	}
}
