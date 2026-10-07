package net.pixelbank.burnt.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.procedures.WillitburnProcedure;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.level.material.LavaFluid;

// When lava places vanilla fire, also run the burn procedure there
@Mixin(LavaFluid.class)
public class LavaFireIgnitionMixin {

    @Redirect(
        method = "randomTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"
        )
    )
    private boolean burnt$onLavaFirePlaced(Level level, BlockPos firePos, BlockState newState) {
        // If lavaCatch is disabled, cancel lava-originated fire placement entirely
        if (!level.isClientSide()
                && newState.is(Blocks.FIRE)
                && !BurntBasicConfigConfiguration.LAVA_BURN.get()) {
            return false;
        }

        boolean placed = level.setBlockAndUpdate(firePos, newState);
        if (placed
                && !level.isClientSide()
                && newState.is(Blocks.FIRE)
                && BurntBasicConfigConfiguration.VANILLA_BURN.get()) {
            WillitburnProcedure.execute(level, firePos.getX(), firePos.getY(), firePos.getZ());
        }
        return placed;
    }
}
