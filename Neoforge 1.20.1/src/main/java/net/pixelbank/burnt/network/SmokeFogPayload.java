package net.pixelbank.burnt.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Consumer;
import java.util.function.Supplier;

/** Sent to each player every 25 ticks to update their smoke fog strength. */
public class SmokeFogPayload {

	public final float intensity;

	public SmokeFogPayload(float intensity) {
		this.intensity = intensity;
	}

	public static void encode(SmokeFogPayload msg, FriendlyByteBuf buf) {
		buf.writeFloat(msg.intensity);
	}

	public static SmokeFogPayload decode(FriendlyByteBuf buf) {
		return new SmokeFogPayload(buf.readFloat());
	}

	// Delegate set by BurntModClient so the server never loads client-only classes
	// No-op by default, so the server can load this class safely
	public static Consumer<Float> clientHandler = i -> {};

	public static void handle(SmokeFogPayload payload, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> clientHandler.accept(payload.intensity));
		ctx.get().setPacketHandled(true);
	}
}
