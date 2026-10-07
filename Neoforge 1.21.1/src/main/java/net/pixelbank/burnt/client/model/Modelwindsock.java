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

public class Modelwindsock<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("burnt", "modelwindsock"), "main");
	public final ModelPart head;
	public final ModelPart Sock;
	public final ModelPart Sock2;
	public final ModelPart Sock3;
	public final ModelPart Sock4;
	public final ModelPart Sock5;
	public final ModelPart body;

	public Modelwindsock(ModelPart root) {
		this.head = root.getChild("head");
		this.Sock = this.head.getChild("Sock");
		this.Sock2 = this.Sock.getChild("Sock2");
		this.Sock3 = this.Sock2.getChild("Sock3");
		this.Sock4 = this.Sock3.getChild("Sock4");
		this.Sock5 = this.Sock4.getChild("Sock5");
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 8.25F, 0.25F));
		PartDefinition Sock = head.addOrReplaceChild("Sock", CubeListBuilder.create().texOffs(2, 2).addBox(-6.0F, -6.0F, -8.0F, 12.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Sock2 = Sock.addOrReplaceChild("Sock2", CubeListBuilder.create().texOffs(0, 18).addBox(-5.0F, -5.0F, -4.0F, 10.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -7.0F));
		PartDefinition Sock3 = Sock2.addOrReplaceChild("Sock3", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));
		PartDefinition Sock4 = Sock3.addOrReplaceChild("Sock4", CubeListBuilder.create().texOffs(36, 0).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));
		PartDefinition Sock5 = Sock4.addOrReplaceChild("Sock5", CubeListBuilder.create().texOffs(40, 10).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 18).addBox(-2.0F, -16.0F, -2.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}