package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

// Smoke fog on the client - the visible fog catches up to the server's target each frame
@OnlyIn(Dist.CLIENT)
public class SmokeFogProcedure {

	// Set by the SmokeFogPayload handler on the network thread
	public static volatile float targetIntensity = 0f;

	// Smoothed value read by SmokeFogMixin, lerps toward targetIntensity
	public static float currentIntensity = 0f;

	// Fog rolls in over about 4-5 seconds at 60fps
	private static final float LERP_IN  = 0.012f;
	// Smoke clears over about 8 seconds at 60fps
	private static final float LERP_OUT = 0.008f;

	// Called once per frame from BurntModClient to advance the lerp
	public static void tick() {
		if (!BurntBasicConfigConfiguration.SMOKE_FOG.get()) {
			currentIntensity = 0f;
			return;
		}
		// Asymmetric lerp: fog rolls in slowly, lingers even longer when clearing
		float factor = targetIntensity > currentIntensity ? LERP_IN : LERP_OUT;
		currentIntensity += (targetIntensity - currentIntensity) * factor;
		// Snap to zero when both values are under the mixin threshold, or the fog never fully clears
		if (targetIntensity < 0.01f && currentIntensity < 0.05f) {
			currentIntensity = 0f;
		}
	}
}
