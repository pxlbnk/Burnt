package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.client.RenderUtils;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;

@OnlyIn(Dist.CLIENT)
public class SkyProcedure {

	// Set when a fire sky packet arrives
	public static volatile float targetIntensity = 0f;

	// Follows targetIntensity smoothly each frame
	private static float currentIntensity = 0f;

	// How fast the sky effect fades in and out
	private static final float LERP_FACTOR = 0.08f;

	// Sky tint colour #cc6033
	private static final int SKY_COLOR = 0xcc6033;

	public static void execute(LevelAccessor world, RenderLevelStageEvent event) {
		if (event == null) return;

		if (!BurntBasicConfigConfiguration.HEAT_HAZE.get()) {
			currentIntensity = 0f;
			return;
		}

		// Proportional lerp - fast response, natural ease at both ends
		currentIntensity += (targetIntensity - currentIntensity) * LERP_FACTOR;

		if (currentIntensity > 0f) {
			int configLevel = BurntBasicConfigConfiguration.HEAT_HAZE_INTENSITY.get();
			if (configLevel <= 0) return;
			// Rescaled so level 5 looks like old level 3 (sweet spot): level 5 -> 30%, level 10 -> 60%
			float scaledIntensity = currentIntensity * (configLevel * 0.6f / 10f);
			// Forge 52.x: getPoseStack() now returns Matrix4f; wrap it in a PoseStack
			PoseStack poseStack = new PoseStack();
			poseStack.mulPose(event.getPoseStack());
			RenderUtils.renderCustomSkybox(
					poseStack,
					event.getCamera(),
					ResourceLocation.parse("burnt:textures/screens/red_sky.png"),
					SKY_COLOR, scaledIntensity);
		}
	}
}
