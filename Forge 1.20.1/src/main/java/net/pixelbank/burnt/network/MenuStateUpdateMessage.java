package net.pixelbank.burnt.network;

import net.pixelbank.burnt.init.BurntModScreens;
import net.pixelbank.burnt.init.BurntModMenus;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;

public record MenuStateUpdateMessage(int elementType, String name, Object elementState) {

	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(elementType);
		buf.writeUtf(name);
		if (elementType == 0) {
			buf.writeUtf((String) elementState);
		} else if (elementType == 1) {
			buf.writeBoolean((boolean) elementState);
		} else if (elementType == 2 && elementState instanceof Number n) {
			buf.writeDouble(n.doubleValue());
		}
	}

	public static MenuStateUpdateMessage read(FriendlyByteBuf buf) {
		int elementType = buf.readInt();
		String name = buf.readUtf();
		Object elementState = null;
		if (elementType == 0) {
			elementState = buf.readUtf();
		} else if (elementType == 1) {
			elementState = buf.readBoolean();
		} else if (elementType == 2) {
			elementState = buf.readDouble();
		}
		return new MenuStateUpdateMessage(elementType, name, elementState);
	}

	/** Client-side handler - runs on main thread via consumerMainThread. */
	public void handle() {
		if (name.length() > 256 || elementState instanceof String s && s.length() > 8192)
			return;
		Minecraft mc = Minecraft.getInstance();
		if (mc.player != null && mc.player.containerMenu instanceof BurntModMenus.MenuAccessor menu) {
			menu.getMenuState().put(elementType + ":" + name, elementState);
			if (mc.screen instanceof BurntModScreens.ScreenAccessor accessor) {
				accessor.updateMenuState(elementType, name, elementState);
			}
		}
	}
}
