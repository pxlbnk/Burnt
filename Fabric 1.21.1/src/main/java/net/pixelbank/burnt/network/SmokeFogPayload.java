package net.pixelbank.burnt.network;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Sent to each player every 25 ticks to update their smoke fog strength. */
public record SmokeFogPayload(float intensity) implements CustomPacketPayload {

	public static final Type<SmokeFogPayload> TYPE =
		new Type<>(ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "smoke_fog"));

	public static final StreamCodec<RegistryFriendlyByteBuf, SmokeFogPayload> STREAM_CODEC =
		StreamCodec.of(SmokeFogPayload::write, SmokeFogPayload::read);

	public static void write(FriendlyByteBuf buf, SmokeFogPayload msg) {
		buf.writeFloat(msg.intensity());
	}

	public static SmokeFogPayload read(FriendlyByteBuf buf) {
		return new SmokeFogPayload(buf.readFloat());
	}

	@Override
	public Type<SmokeFogPayload> type() {
		return TYPE;
	}
}
