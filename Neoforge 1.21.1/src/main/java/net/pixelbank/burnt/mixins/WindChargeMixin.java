package net.pixelbank.burnt.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import net.pixelbank.burnt.procedures.ExtinguishBlockProcedure;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// A wind charge puts out any fire where it hits
@Mixin(AbstractWindCharge.class)
public class WindChargeMixin {

    @Inject(method = "onHitBlock", at = @At("HEAD"))
    private void burnt$extinguishOnWindChargeHit(BlockHitResult hitResult, CallbackInfo ci) {
        AbstractWindCharge self = (AbstractWindCharge) (Object) this;
        Level level = self.level();
        if (level.isClientSide()) return;

        BlockPos pos = hitResult.getBlockPos();
        ExtinguishBlockProcedure.execute(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, false);
    }
}
