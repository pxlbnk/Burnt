package net.pixelbank.burnt.network;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Sent to each player every 25 ticks to update the fire sky effect strength. */
public record FireSkyPayload(float intensity) implements CustomPacketPayload {

	public static final Type<FireSkyPayload> TYPE =
		new Type<>(ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "fire_sky"));

	public static final StreamCodec<RegistryFriendlyByteBuf, FireSkyPayload> STREAM_CODEC =
		StreamCodec.of(FireSkyPayload::write, FireSkyPayload::read);

	public static void write(FriendlyByteBuf buf, FireSkyPayload msg) {
		buf.writeFloat(msg.intensity());
	}

	public static FireSkyPayload read(FriendlyByteBuf buf) {
		return new FireSkyPayload(buf.readFloat());
	}

	@Override
	public Type<FireSkyPayload> type() {
		return TYPE;
	}
}
