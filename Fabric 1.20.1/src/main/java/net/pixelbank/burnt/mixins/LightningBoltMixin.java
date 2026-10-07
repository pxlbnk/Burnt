package net.pixelbank.burnt.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LightningBolt;
import net.pixelbank.burnt.BurntLightningRodGuard;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Records where a bolt landed while lightning ignition is off, so the fire mixin leaves its vanilla fire alone
// Deliberately does not run LightningStrikeProcedure - it only closes the hole, leaving lightning as vanilla
@Mixin(LightningBolt.class)
public class LightningBoltMixin {

    @Unique
    private boolean burnt$struck = false;

    @Inject(method = "tick", at = @At("HEAD"))
    private void burnt$onFirstTick(CallbackInfo ci) {
        LightningBolt self = (LightningBolt) (Object) this;
        if (self.level().isClientSide() || burnt$struck) return;
        burnt$struck = true;

        if (!BurntBasicConfigConfiguration.BURNT_ON.get()) return;
        if (BurntBasicConfigConfiguration.LIGHTNING_IGNITES.get()) return;

        BlockPos boltPos = self.blockPosition();
        BurntLightningRodGuard.markStrikeNoBurn(boltPos, self.level().getGameTime());
    }
}
