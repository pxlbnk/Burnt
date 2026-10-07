package net.pixelbank.burnt.network;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.function.Consumer;

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

	// Delegate set by BurntModClient so the server never loads client-only classes
	// No-op by default, so the server can load this class safely
	public static Consumer<Float> clientHandler = i -> {};

	/** Registered with PayloadRegistrar; delegate ensures dist-safety. */
	public static void handle(FireSkyPayload payload, IPayloadContext ctx) {
		ctx.enqueueWork(() -> clientHandler.accept(payload.intensity()));
	}

	@Override
	public Type<FireSkyPayload> type() {
		return TYPE;
	}
}
