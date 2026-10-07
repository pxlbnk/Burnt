package net.pixelbank.burnt.client.renderer;

import net.pixelbank.burnt.procedures.*;
import net.pixelbank.burnt.entity.FlagEntity;
import net.pixelbank.burnt.client.model.animations.flagAnimationStrong;
import net.pixelbank.burnt.client.model.animations.flagAnimationMedium;
import net.pixelbank.burnt.client.model.animations.flagAnimationLow;
import net.pixelbank.burnt.client.model.animations.flagAnimationHigh;
import net.pixelbank.burnt.client.model.animations.flagAnimationFull;
import net.pixelbank.burnt.client.model.Modelflag;

import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class FlagRenderer extends MobRenderer<FlagEntity, Modelflag<FlagEntity>> {
	public FlagRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelflag.LAYER_LOCATION)), 0f);
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_white.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (WhiteFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_black.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BlackFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_grey.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (GreyFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_light_grey.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (LightGreyFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_red.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (RedFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_cyan.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (CyanFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_yellow.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (YellowFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_magenta.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (MagentaFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_lime.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (LimeFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_orange.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (OrangeFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_brown.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BrownFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_purple.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (PurpleFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_pink.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (PinkFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_blue.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (BlueFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_green.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (GreenFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_light_blue.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (LightBlueFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_wind.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (WindFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_rainbow.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (RainbowFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
		this.addLayer(new RenderLayer<FlagEntity, Modelflag<FlagEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("burnt:textures/entities/flag_pirate.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, FlagEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				Level world = entity.level();
				double x = entity.getX();
				double y = entity.getY();
				double z = entity.getZ();
				if (PirateFlagDisplayConditionProcedure.execute(entity)) {
					VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
					this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1f, 1f, 1f, 1f);
				}
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(FlagEntity entity) {
		return new ResourceLocation("burnt:textures/entities/" + entity.getTexture() + ".png");
	}

	private static final class AnimatedModel extends Modelflag<FlagEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<FlagEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(FlagEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
		public void setupAnim(FlagEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}