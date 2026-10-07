package net.pixelbank.burnt.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.init.BurntModBlocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.projectile.SmallFireball;

// When a fireball would place vanilla fire, swap in our fire block
@Mixin(SmallFireball.class)
public class SmallFireballMixin {

    @Inject(method = "onHitBlock", at = @At("HEAD"), cancellable = true)
    private void burnt$interceptSmallFireball(BlockHitResult hitResult, CallbackInfo ci) {
        SmallFireball self = (SmallFireball) (Object) this;
        Level level = self.level();
        if (level.isClientSide()) return;
        if (!BurntBasicConfigConfiguration.VANILLA_BURN.get()) return;

        BlockPos placePos = hitResult.getBlockPos().relative(hitResult.getDirection());
        if (!level.getBlockState(placePos).isAir()) return;

        BlockState wouldBe = BaseFireBlock.getState(level, placePos);
        if (!wouldBe.is(Blocks.FIRE)) return; // soul fire context - leave vanilla alone

        if (level.getBlockState(placePos.below()).is(Blocks.NETHERRACK)) return;

        for (Direction d : Direction.values()) {
            if (level.getBlockState(placePos.relative(d)).is(Blocks.OBSIDIAN)) return;
        }

        ci.cancel();
        level.setBlock(placePos, BurntModBlocks.FIRESTARTER.defaultBlockState(), 11);
        self.discard();
    }
}
