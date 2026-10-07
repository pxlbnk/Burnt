package net.pixelbank.burnt.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.procedures.SmokeFogProcedure;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.util.Mth;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Overrides fog colour and density when smoke is nearby
@Mixin(FogRenderer.class)
public class SmokeFogMixin {

	@Inject(method = "setupFog", at = @At("TAIL"))
	private static void burnt$smokeFog(Camera camera, FogRenderer.FogMode fogMode,
			float renderDistance, boolean useFog, float partialTick,
			CallbackInfo ci) {

		float intensity = SmokeFogProcedure.currentIntensity;
		// Below 0.05 the fog shift is sub-perceptible on a clear day, but still shows with shaders
		// SmokeFogProcedure snaps to exact 0 below this, so the early return fires reliably
		if (intensity < 0.05f) return;

		int configLevel = BurntBasicConfigConfiguration.SMOKE_FOG_INTENSITY.get();
		if (configLevel <= 0) return;

		// Rescaled so level 5 matches the old level 3 - x^1.523, so 5 is ~38 blocks and 10 is ~16
		float minFogBlocks = 200f / (float) Math.pow(configLevel * 0.6f, 1.523);

		// Lerp near and far so the gradient eases back to vanilla - locking near=0 made fog cling to the camera
		float vanillaNear = RenderSystem.getShaderFogStart();
		float fogNear = Mth.lerp(intensity, vanillaNear, 0f);
		float fogEnd  = Mth.lerp(intensity, renderDistance, minFogBlocks);
		RenderSystem.setShaderFogStart(fogNear);
		RenderSystem.setShaderFogEnd(fogEnd);
	}
}
