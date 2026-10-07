package net.pixelbank.burnt.network;

import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Consumer;

/** Sent to each player to update their smoke fog strength. */
public record SmokeFogPayload(float intensity) {

	public void encode(FriendlyByteBuf buf) {
		buf.writeFloat(intensity());
	}

	public static SmokeFogPayload read(FriendlyByteBuf buf) {
		return new SmokeFogPayload(buf.readFloat());
	}

	// Delegate set by BurntModClient so the server never loads client-only classes
	// No-op by default, so the server can load this class safely
	public static Consumer<Float> clientHandler = i -> {};

	/** Client-side handler - runs on main thread via consumerMainThread. */
	public void handle() {
		clientHandler.accept(intensity());
	}
}
