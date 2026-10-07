package net.pixelbank.burnt.network;

import net.pixelbank.burnt.BurntMod;

import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class BurntNetwork {

	public static final SimpleChannel CHANNEL = ChannelBuilder
			.named(ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "main"))
			.networkProtocolVersion(1)
			.simpleChannel();

	public static void register() {
		int id = 0;
		CHANNEL.messageBuilder(MapVarsSyncPayload.class,     id++)
				.encoder(MapVarsSyncPayload::encode)
				.decoder(MapVarsSyncPayload::read)
				.consumerMainThread((msg, ctx) -> msg.handle())
				.add();
		CHANNEL.messageBuilder(SmokeFogPayload.class,        id++)
				.encoder(SmokeFogPayload::encode)
				.decoder(SmokeFogPayload::read)
				.consumerMainThread((msg, ctx) -> msg.handle())
				.add();
		CHANNEL.messageBuilder(FireSkyPayload.class,         id++)
				.encoder(FireSkyPayload::encode)
				.decoder(FireSkyPayload::read)
				.consumerMainThread((msg, ctx) -> msg.handle())
				.add();
		CHANNEL.messageBuilder(MenuStateUpdateMessage.class, id++)
				.encoder(MenuStateUpdateMessage::encode)
				.decoder(MenuStateUpdateMessage::read)
				.consumerMainThread((msg, ctx) -> msg.handle())
				.add();
	}

	/** Send a registered message to a specific player. */
	public static <T> void sendToPlayer(ServerPlayer player, T msg) {
		CHANNEL.send(msg, PacketDistributor.PLAYER.with(player));
	}
}
