package net.pixelbank.burnt.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.pixelbank.burnt.BurntMod;

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
				MapVarsSyncPayload::encode, MapVarsSyncPayload::decode, MapVarsSyncPayload::handle);
		CHANNEL.registerMessage(id++, SmokeFogPayload.class,
				SmokeFogPayload::encode, SmokeFogPayload::decode, SmokeFogPayload::handle);
		CHANNEL.registerMessage(id++, FireSkyPayload.class,
				FireSkyPayload::encode, FireSkyPayload::decode, FireSkyPayload::handle);
		CHANNEL.registerMessage(id++, MenuStateUpdateMessage.class,
				MenuStateUpdateMessage::encode, MenuStateUpdateMessage::decode, MenuStateUpdateMessage::handle);
	}

	public static void sendToPlayer(ServerPlayer player, Object msg) {
		CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);
	}
}
