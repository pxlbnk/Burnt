package net.pixelbank.burnt.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.pixelbank.burnt.init.BurntModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Lets vanilla crops survive on Burnt farmland instead of popping off
@Mixin(CropBlock.class)
public class CropBlockMixin {

    @Inject(method = "mayPlaceOn", at = @At("HEAD"), cancellable = true)
    private void burnt$mayPlaceOnBurntFarmland(BlockState state, BlockGetter world, BlockPos pos,
            CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() == BurntModBlocks.BURNT_FARMLAND
                || state.getBlock() == BurntModBlocks.BURNT_FARMLAND_MOIST) {
            cir.setReturnValue(true);
        }
    }
}
