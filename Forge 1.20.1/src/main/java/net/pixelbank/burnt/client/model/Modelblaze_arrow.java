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

public class Modelblaze_arrow<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("burnt", "modelblaze_arrow"), "main");
	public final ModelPart bone;
	public final ModelPart cross_1;
	public final ModelPart cross_2;
	public final ModelPart back;

	public Modelblaze_arrow(ModelPart root) {
		this.bone = root.getChild("bone");
		this.cross_1 = this.bone.getChild("cross_1");
		this.cross_2 = this.bone.getChild("cross_2");
		this.back = this.bone.getChild("back");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 8.0F, -3.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cross_1 = bone.addOrReplaceChild("cross_1",
				CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -2.5F, 0.0F, 16.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(20, 12).addBox(1.0F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.5F, -4.0F, -2.3562F, 1.5708F, 0.0F));
		PartDefinition cross_2 = bone.addOrReplaceChild("cross_2", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -2.5F, 0.0F, 16.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.5F, -4.0F, -0.7854F, 1.5708F, 0.0F));
		PartDefinition back = bone.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 7.0F, -2.3562F, 1.5708F, 0.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float r, float g, float b, float a) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, r, g, b, a);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}