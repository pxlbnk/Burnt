package net.pixelbank.burnt.mixins;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Makes mobs treat Burnt fire as dangerous when pathfinding, so they walk around it
@Mixin(WalkNodeEvaluator.class)
public class FirePathTypeMixin {

	@Inject(
		method = "getBlockPathTypeStatic(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos$MutableBlockPos;)Lnet/minecraft/world/level/pathfinder/BlockPathTypes;",
		at = @At("RETURN"),
		cancellable = true
	)
	private static void burnt$markFireBlocksDangerous(
			BlockGetter level, BlockPos.MutableBlockPos pos,
			CallbackInfoReturnable<BlockPathTypes> cir) {
		BlockPathTypes current = cir.getReturnValue();
		if (current == BlockPathTypes.OPEN || current == BlockPathTypes.WALKABLE) {
			if (level.getBlockState(pos).is(BurntTags.BLOCK_ON_FIRE)) {
				cir.setReturnValue(BlockPathTypes.DAMAGE_FIRE);
			}
		}
	}
}
