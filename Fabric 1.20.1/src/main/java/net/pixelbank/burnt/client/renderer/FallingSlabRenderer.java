package net.pixelbank.burnt.client.renderer;

import net.pixelbank.burnt.entity.FallingSlabEntity;
import net.pixelbank.burnt.client.model.ModelCustomModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.mojang.blaze3d.vertex.PoseStack;

public class FallingSlabRenderer extends MobRenderer<FallingSlabEntity, ModelCustomModel<FallingSlabEntity>> {
	public FallingSlabRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelCustomModel<FallingSlabEntity>(context.bakeLayer(ModelCustomModel.LAYER_LOCATION)), 0f);
	}

	@Override
	protected void scale(FallingSlabEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(0.8f, 0.8f, 0.8f);
	}

	@Override
	public ResourceLocation getTextureLocation(FallingSlabEntity entity) {
		return new ResourceLocation("burnt:textures/entities/" + entity.getTexture() + ".png");
	}
}