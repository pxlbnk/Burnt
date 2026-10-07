package net.pixelbank.burnt.mixins;

import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import net.pixelbank.burnt.procedures.ExtinguishBlockProcedure;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// A thrown water bottle puts out fires in a small radius
// Targets applyWater(), which vanilla only calls for an actual water bottle
@Mixin(ThrownPotion.class)
public class SplashPotionMixin {

    @Inject(method = "applyWater", at = @At("HEAD"))
    private void burnt$extinguishOnWaterSplash(CallbackInfo ci) {
        ThrownPotion self = (ThrownPotion) (Object) this;
        Level level = self.level();
        if (level.isClientSide()) return;
        Vec3 pos = self.position();
        ExtinguishBlockProcedure.execute(level, pos.x, pos.y, pos.z, false);
    }
}
