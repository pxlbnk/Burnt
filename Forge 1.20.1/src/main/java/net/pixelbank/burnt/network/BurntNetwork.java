package net.pixelbank.burnt.network;

import net.pixelbank.burnt.BurntMod;

import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class BurntNetwork {

	private static final String PROTOCOL = "1";

	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(BurntMod.MODID, "main"),
			() -> PROTOCOL,
			PROTOCOL::equals,
			PROTOCOL::equals);

	public static void register() {
		int id = 0;
		CHANNEL.registerMessage(id++, MapVarsSyncPayload.class,
				MapVarsSyncPayload::encode,
				MapVarsSyncPayload::read,
				(msg, ctx) -> {
					ctx.get().enqueueWork(msg::handle);
					ctx.get().setPacketHandled(true);
				});
		CHANNEL.registerMessage(id++, SmokeFogPayload.class,
				SmokeFogPayload::encode,
				SmokeFogPayload::read,
				(msg, ctx) -> {
					ctx.get().enqueueWork(msg::handle);
					ctx.get().setPacketHandled(true);
				});
		CHANNEL.registerMessage(id++, FireSkyPayload.class,
				FireSkyPayload::encode,
				FireSkyPayload::read,
				(msg, ctx) -> {
					ctx.get().enqueueWork(msg::handle);
					ctx.get().setPacketHandled(true);
				});
		CHANNEL.registerMessage(id++, MenuStateUpdateMessage.class,
				MenuStateUpdateMessage::encode,
				MenuStateUpdateMessage::read,
				(msg, ctx) -> {
					ctx.get().enqueueWork(msg::handle);
					ctx.get().setPacketHandled(true);
				});
	}

	/** Send a registered message to a specific player. */
	public static <T> void sendToPlayer(ServerPlayer player, T msg) {
		CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);
	}
}
