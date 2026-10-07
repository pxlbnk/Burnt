package net.pixelbank.burnt.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class Modelgas_mask<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("burnt", "modelgas_mask"), "main");
	public final ModelPart Head;
	public final ModelPart mask;
	public final ModelPart nose;
	public final ModelPart Eye;
	public final ModelPart Eye2;
	public final ModelPart straps;

	public Modelgas_mask(ModelPart root) {
		this.Head = root.getChild("Head");
		this.mask = this.Head.getChild("mask");
		this.nose = this.Head.getChild("nose");
		this.Eye = this.Head.getChild("Eye");
		this.Eye2 = this.Head.getChild("Eye2");
		this.straps = this.Head.getChild("straps");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition mask = Head.addOrReplaceChild("mask", CubeListBuilder.create().texOffs(9, 2).addBox(-6.25F, -4.0F, -1.0F, 9.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, -4.5F, -4.0F));
		PartDefinition nose = Head.addOrReplaceChild("nose", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -6.875F, 0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r1 = nose.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(20, 24).addBox(-1.5F, -1.5F, -2.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.25F, -1.625F, 0.0F, 0.0F, 0.7854F));
		PartDefinition cube_r2 = nose.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(9, 24).addBox(-0.5F, -0.5F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, -2.125F, 0.0F, 0.0F, 0.7854F));
		PartDefinition cube_r3 = nose.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 19).addBox(0.5F, 0.5F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.5F, -0.125F, 0.0F, 0.0F, 0.7854F));
		PartDefinition cube_r4 = nose.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(9, 14).addBox(-2.5F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.25F, 1.125F, 0.0F, 0.0F, 0.7854F));
		PartDefinition Eye = Head.addOrReplaceChild("Eye",
				CubeListBuilder.create().texOffs(1, 7).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(1, 1).addBox(-2.0F, -2.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(1, 1)
						.addBox(1.0F, -2.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 10).mirror().addBox(-1.0F, 1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 10)
						.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -5.5F, -5.5F, 0.0F, -0.2618F, 0.1745F));
		PartDefinition Eye2 = Head.addOrReplaceChild("Eye2",
				CubeListBuilder.create().texOffs(1, 7).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(1, 1).addBox(-2.0F, -2.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(1, 1)
						.addBox(1.0F, -2.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 10).mirror().addBox(-1.0F, 1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 10)
						.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -5.5F, -5.5F, 0.0F, 0.2618F, -0.1745F));
		PartDefinition straps = Head.addOrReplaceChild("straps",
				CubeListBuilder.create().texOffs(9, 30).addBox(-6.25F, -1.5F, -12.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(25, 25).addBox(-5.5F, -1.5F, -11.5F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(36, 21)
						.addBox(2.5F, -1.5F, -11.5F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(44, 33).addBox(-4.5F, -1.5F, -4.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 37)
						.addBox(-2.5F, -4.5F, -4.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.0F, -3.5F, 8.5F));
		PartDefinition topstrap_r1 = straps.addOrReplaceChild("topstrap_r1", CubeListBuilder.create().texOffs(13, 31).addBox(1.0F, -1.5F, -4.5F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -3.5F, -7.0F, 0.0F, 0.0F, -1.5708F));
		PartDefinition buckle_r1 = straps.addOrReplaceChild("buckle_r1", CubeListBuilder.create().texOffs(9, 30).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -5.5F, -11.0F, 0.0F, 0.0F, 1.5708F));
		PartDefinition buckle_r2 = straps.addOrReplaceChild("buckle_r2", CubeListBuilder.create().texOffs(9, 30).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.75F, 0.0F, -11.0F, 0.0F, 3.1416F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float r, float g, float b, float a) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, r, g, b, a);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Head.xRot = headPitch / (180F / (float) Math.PI);
	}
}