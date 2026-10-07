package net.pixelbank.burnt.network;

import net.pixelbank.burnt.init.BurntModScreens;
import net.pixelbank.burnt.init.BurntModMenus;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MenuStateUpdateMessage {

	public final int elementType;
	public final String name;
	public final Object elementState;

	public MenuStateUpdateMessage(int elementType, String name, Object elementState) {
		this.elementType  = elementType;
		this.name         = name;
		this.elementState = elementState;
	}

	public static void encode(MenuStateUpdateMessage message, FriendlyByteBuf buffer) {
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

	public static MenuStateUpdateMessage decode(FriendlyByteBuf buffer) {
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

	public static void handle(MenuStateUpdateMessage message, Supplier<NetworkEvent.Context> ctx) {
		if (message.name.length() > 256 || message.elementState instanceof String string && string.length() > 8192)
			return;
		ctx.get().enqueueWork(() -> {
			Minecraft mc = Minecraft.getInstance();
			if (mc.player != null && mc.player.containerMenu instanceof BurntModMenus.MenuAccessor menu) {
				menu.getMenuState().put(message.elementType + ":" + message.name, message.elementState);
				if (mc.screen instanceof BurntModScreens.ScreenAccessor accessor) {
					accessor.updateMenuState(message.elementType, message.name, message.elementState);
				}
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
