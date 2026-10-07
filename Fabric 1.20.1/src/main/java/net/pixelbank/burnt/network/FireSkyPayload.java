package net.pixelbank.burnt.network;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

/** Sent to each player every 25 ticks to update the fire sky effect strength. */
public class FireSkyPayload {

	public static final ResourceLocation ID =
		new ResourceLocation(BurntMod.MODID, "fire_sky");

	public final float intensity;

	public FireSkyPayload(float intensity) {
		this.intensity = intensity;
	}

	public static void write(FriendlyByteBuf buf, FireSkyPayload msg) {
		buf.writeFloat(msg.intensity);
	}

	public static FireSkyPayload read(FriendlyByteBuf buf) {
		return new FireSkyPayload(buf.readFloat());
	}
}
