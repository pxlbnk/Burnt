package net.pixelbank.burnt.mixins;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.pathfinder.PathfindingContext;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Makes mobs treat Burnt fire as dangerous when pathfinding, so they walk around it
@Mixin(WalkNodeEvaluator.class)
public class FirePathTypeMixin {

	// Targets the pathfinding check used during normal mob navigation
	@Inject(
		method = "getPathTypeStatic(Lnet/minecraft/world/level/pathfinder/PathfindingContext;Lnet/minecraft/core/BlockPos$MutableBlockPos;)Lnet/minecraft/world/level/pathfinder/PathType;",
		at = @At("RETURN"),
		cancellable = true
	)
	private static void burnt$markFireBlocksDangerous(
			PathfindingContext context, BlockPos.MutableBlockPos pos,
			CallbackInfoReturnable<PathType> cir) {
		PathType current = cir.getReturnValue();
		// Only upgrade walkable/open nodes - leave already-dangerous ones alone
		if (current == PathType.OPEN || current == PathType.WALKABLE) {
			if (context.getBlockState(pos).is(BurntTags.BLOCK_ON_FIRE)) {
				cir.setReturnValue(PathType.DAMAGE_FIRE);
			}
		}
	}
}
