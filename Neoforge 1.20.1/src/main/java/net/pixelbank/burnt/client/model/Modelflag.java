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

public class Modelflag<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("burnt", "modelflag"), "main");
	public final ModelPart FLAG;
	public final ModelPart HEAD;
	public final ModelPart flag1;
	public final ModelPart flag2;
	public final ModelPart flag3;
	public final ModelPart flag4;
	public final ModelPart flag5;
	public final ModelPart MAST;

	public Modelflag(ModelPart root) {
		this.FLAG = root.getChild("FLAG");
		this.HEAD = this.FLAG.getChild("HEAD");
		this.flag1 = this.HEAD.getChild("flag1");
		this.flag2 = this.flag1.getChild("flag2");
		this.flag3 = this.flag2.getChild("flag3");
		this.flag4 = this.flag3.getChild("flag4");
		this.flag5 = this.flag4.getChild("flag5");
		this.MAST = this.FLAG.getChild("MAST");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition FLAG = partdefinition.addOrReplaceChild("FLAG", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition HEAD = FLAG.addOrReplaceChild("HEAD", CubeListBuilder.create().texOffs(2, -2).addBox(0.0F, -8.0F, -6.0F, 0.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 1.0F));
		PartDefinition flag1 = HEAD.addOrReplaceChild("flag1", CubeListBuilder.create().texOffs(10, -2).addBox(0.0F, -8.0F, -4.0F, 0.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));
		PartDefinition flag2 = flag1.addOrReplaceChild("flag2", CubeListBuilder.create().texOffs(18, -2).addBox(0.0F, -8.0F, -4.0F, 0.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));
		PartDefinition flag3 = flag2.addOrReplaceChild("flag3", CubeListBuilder.create().texOffs(26, -2).addBox(0.0F, -8.0F, -4.0F, 0.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));
		PartDefinition flag4 = flag3.addOrReplaceChild("flag4", CubeListBuilder.create().texOffs(34, -2).addBox(0.0F, -8.0F, -4.0F, 0.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));
		PartDefinition flag5 = flag4.addOrReplaceChild("flag5", CubeListBuilder.create().texOffs(42, -2).addBox(0.0F, -8.0F, -4.0F, 0.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));
		PartDefinition MAST = FLAG.addOrReplaceChild("MAST", CubeListBuilder.create().texOffs(54, 2).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		FLAG.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}