package net.pixelbank.burnt.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.BlockHitResult;
import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.procedures.ArrowBurnProcedure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// A flaming arrow spreads fire from where it hit, after a short delay so it looks like it smoulders first
@Mixin(AbstractArrow.class)
public class FlameArrowMixin {

    @Inject(method = "onHitBlock", at = @At("HEAD"))
    private void burnt$onFireArrowHitBlock(BlockHitResult result, CallbackInfo ci) {
        AbstractArrow self = (AbstractArrow) (Object) this;
        if (!self.level().isClientSide()
                && self.isOnFire()
                && BurntBasicConfigConfiguration.BURNT_ON.get()
                && BurntBasicConfigConfiguration.FLAME_BOW.get()) {
            BlockPos pos = result.getBlockPos();
            double x = pos.getX(), y = pos.getY(), z = pos.getZ();
            // Capture the level for use inside the lambda (self may move/despawn)
            LevelAccessor level = self.level();
            // Random delay 20-60 ticks so the arrow sits embedded before fire spreads
            int delay = 20 + self.level().getRandom().nextInt(41);
            // Mirror SparkOnInitialEntitySpawnProcedure: burn hit block + all 6 cardinal neighbours
            BurntMod.queueServerWork(delay, () -> {
                ArrowBurnProcedure.execute(level, x,     y,     z    );
                ArrowBurnProcedure.execute(level, x + 1, y,     z    );
                ArrowBurnProcedure.execute(level, x - 1, y,     z    );
                ArrowBurnProcedure.execute(level, x,     y,     z + 1);
                ArrowBurnProcedure.execute(level, x,     y,     z - 1);
                ArrowBurnProcedure.execute(level, x,     y + 1, z    );
                ArrowBurnProcedure.execute(level, x,     y - 1, z    );
            });
        }
    }
}
