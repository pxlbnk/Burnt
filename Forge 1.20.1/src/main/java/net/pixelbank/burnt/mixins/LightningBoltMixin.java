package net.pixelbank.burnt.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LightningBolt;
import net.pixelbank.burnt.BurntLightningRodGuard;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.procedures.LightningStrikeProcedure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Wires LightningStrikeProcedure to lightning bolts
// Over a lightning rod the procedure is suppressed, and a guard makes the fire mixin clear the bolt's vanilla fire too
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

        // Vanilla spawns the bolt at rod.above(), so the rod sits one block below.
        BlockPos boltPos = self.blockPosition();
        boolean atRod = self.level().getBlockState(boltPos).getBlock() instanceof net.minecraft.world.level.block.LightningRodBlock;
        boolean belowRod = self.level().getBlockState(boltPos.below()).getBlock() instanceof net.minecraft.world.level.block.LightningRodBlock;

        if (atRod || belowRod) {
            BlockPos rodPos = atRod ? boltPos : boltPos.below();
            BurntLightningRodGuard.markRodStruck(rodPos, self.level().getGameTime());
            return;
        }

        // Ignition off: record the strike so the bolt's vanilla fire is left alone rather than swapped
        if (!BurntBasicConfigConfiguration.LIGHTNING_IGNITES.get()) {
            BurntLightningRodGuard.markStrikeNoBurn(boltPos, self.level().getGameTime());
            return;
        }

        LightningStrikeProcedure.execute(self.level(), self.getX(), self.getY(), self.getZ(), self);
    }
}
