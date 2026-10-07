package net.pixelbank.burnt.client.renderer;

import net.pixelbank.burnt.entity.FlagBlackEntity;
import net.pixelbank.burnt.client.model.animations.flagAnimationStrong;
import net.pixelbank.burnt.client.model.animations.flagAnimationMedium;
import net.pixelbank.burnt.client.model.animations.flagAnimationLow;
import net.pixelbank.burnt.client.model.animations.flagAnimationHigh;
import net.pixelbank.burnt.client.model.animations.flagAnimationFull;
import net.pixelbank.burnt.client.model.Modelflag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

public class FlagBlackRenderer extends MobRenderer<FlagBlackEntity, Modelflag<FlagBlackEntity>> {
	public FlagBlackRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelflag.LAYER_LOCATION)), 0f);
	}

	@Override
	public ResourceLocation getTextureLocation(FlagBlackEntity entity) {
		return new ResourceLocation("burnt:textures/entities/" + entity.getTexture() + ".png");
	}

	private static final class AnimatedModel extends Modelflag<FlagBlackEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<FlagBlackEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(FlagBlackEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, flagAnimationLow.LOW, ageInTicks, 0.5f);
				this.animate(entity.animationState1, flagAnimationLow.LOW, ageInTicks, 1f);
				this.animate(entity.animationState2, flagAnimationMedium.MEDIUM, ageInTicks, 1f);
				this.animate(entity.animationState3, flagAnimationHigh.HIGH, ageInTicks, 1f);
				this.animate(entity.animationState4, flagAnimationStrong.STRONG, ageInTicks, 1f);
				this.animate(entity.animationState5, flagAnimationFull.FULL, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(FlagBlackEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}