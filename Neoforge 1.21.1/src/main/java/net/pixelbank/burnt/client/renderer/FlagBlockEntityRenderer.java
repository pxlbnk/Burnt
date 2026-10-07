package net.pixelbank.burnt.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;

import net.pixelbank.burnt.block.FlagBlock;
import net.pixelbank.burnt.block.entity.FlagBlockEntity;
import net.pixelbank.burnt.client.model.Modelflag;
import net.pixelbank.burnt.client.model.animations.flagAnimationFull;
import net.pixelbank.burnt.client.model.animations.flagAnimationHigh;
import net.pixelbank.burnt.client.model.animations.flagAnimationLow;
import net.pixelbank.burnt.client.model.animations.flagAnimationMedium;
import net.pixelbank.burnt.client.model.animations.flagAnimationStrong;

// Block-entity renderer for every coloured flag block
// Draws only the cloth (Modelflag's HEAD subtree) - the block model already draws the pole
// Animation runs off the BE's animation states, and the mesh is rotated by its windAngle
// Texture comes from the block's colour: burnt:textures/entities/flag_<colour>.png
public class FlagBlockEntityRenderer implements BlockEntityRenderer<FlagBlockEntity> {

	private final ModelPart root;
	private final Modelflag<Entity> mesh;
	private final Animator animator;

	public FlagBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
		this.root = context.bakeLayer(Modelflag.LAYER_LOCATION);
		this.mesh = new Modelflag<>(root);
		this.animator = new Animator(root);
	}

	@Override
	public void render(FlagBlockEntity be, float partialTicks, PoseStack pose,
	                   MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		if (be.getLevel() == null) return;
		Block block = be.getBlockState().getBlock();
		if (!(block instanceof FlagBlock flagBlock)) return;
		String colour = flagBlock.getColour();

		// Start / stop animation states matching the BE's current stage.
		int tickCount = (int) be.getLevel().getGameTime();
		be.updateAnimationStates(tickCount);
		float ageInTicks = tickCount + partialTicks;

		pose.pushPose();

		// The pole is 2 blocks tall and the model's FLAG part sits 24px up, which after the Y-flip
		// lands 1.5 blocks below the pose origin - so translate 2.5 to put the cloth on the upper block
		// (The legacy entity got 1.5 of that free from MobRenderer; we bake it in ourselves)
		pose.translate(0.5F, 2.5F, 0.5F);
		pose.scale(-1.0F, -1.0F, 1.0F);

		// Rotate the cloth downwind. It defaults to pointing -Z, and windAngle is the direction wind
		// comes FROM, so add 180 to flip it. The mirror cancels out because the offset is exactly 180
		pose.mulPose(Axis.YP.rotationDegrees(be.getWindAngle() + 180.0F));

		// Reset bones, then animate through the inner subclass - animate() is protected on the model
		root.getAllParts().forEach(ModelPart::resetPose);
		animator.applyFlagAnimation(be, ageInTicks);

		// Hide MAST on the cloth pass - the block model draws the pole, and both would z-fight
		boolean mastWasVisible = mesh.MAST.visible;
		mesh.MAST.visible = false;

		ResourceLocation tex = ResourceLocation.parse(
				"burnt:textures/entities/flag_" + colour + ".png");
		VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(tex));
		mesh.renderToBuffer(pose, consumer, packedLight, OverlayTexture.NO_OVERLAY);

		mesh.MAST.visible = mastWasVisible;

		pose.popPose();
	}

	// Exists only so the protected animate() helper can be called from inside a HierarchicalModel
	private static final class Animator extends HierarchicalModel<Entity> {
		private final ModelPart root;

		Animator(ModelPart root) { this.root = root; }

		@Override public ModelPart root() { return root; }
		@Override public void setupAnim(Entity e, float a, float b, float c, float d, float f) {}

		void applyFlagAnimation(FlagBlockEntity be, float ageInTicks) {
			// State 0 plays the LOW animation at half scale - a gentle idle rather than a frozen flag
			this.animate(be.animationState0, flagAnimationLow.LOW,       ageInTicks, 0.5F);
			this.animate(be.animationState1, flagAnimationLow.LOW,       ageInTicks, 1F);
			this.animate(be.animationState2, flagAnimationMedium.MEDIUM, ageInTicks, 1F);
			this.animate(be.animationState3, flagAnimationHigh.HIGH,     ageInTicks, 1F);
			this.animate(be.animationState4, flagAnimationStrong.STRONG, ageInTicks, 1F);
			this.animate(be.animationState5, flagAnimationFull.FULL,     ageInTicks, 1F);
		}
	}
}
