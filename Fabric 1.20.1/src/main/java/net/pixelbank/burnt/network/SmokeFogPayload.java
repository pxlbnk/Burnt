package net.pixelbank.burnt.network;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

/** Sent to each player every 25 ticks to update their smoke fog strength. */
public class SmokeFogPayload {

	public static final ResourceLocation ID =
		new ResourceLocation(BurntMod.MODID, "smoke_fog");

	public final float intensity;

	public SmokeFogPayload(float intensity) {
		this.intensity = intensity;
	}

	public static void write(FriendlyByteBuf buf, SmokeFogPayload msg) {
		buf.writeFloat(msg.intensity);
	}

	public static SmokeFogPayload read(FriendlyByteBuf buf) {
		return new SmokeFogPayload(buf.readFloat());
	}
}
