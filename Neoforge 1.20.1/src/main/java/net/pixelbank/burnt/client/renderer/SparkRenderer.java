package net.pixelbank.burnt.client.renderer;

import net.pixelbank.burnt.entity.SparkEntity;
import net.pixelbank.burnt.client.model.ModelSmoke;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class SparkRenderer extends MobRenderer<SparkEntity, ModelSmoke<SparkEntity>> {
	public SparkRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelSmoke<SparkEntity>(context.bakeLayer(ModelSmoke.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(SparkEntity entity) {
		return new ResourceLocation("burnt:textures/entities/" + entity.getTexture() + ".png");
	}
}