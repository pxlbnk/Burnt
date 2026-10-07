package net.pixelbank.burnt.mixins.compat;

import net.pixelbank.burnt.client.particle.BlueFlameParticleParticle;
import net.pixelbank.burnt.client.particle.FireParticleParticle;
import net.pixelbank.burnt.client.particle.FlameSmokeParticle;
import net.pixelbank.burnt.client.particle.FlamesParticle;
import net.pixelbank.burnt.client.particle.LargeFlameSmokeParticle;
import net.pixelbank.burnt.client.particle.LightFlameSmokeParticle;
import net.pixelbank.burnt.client.particle.MossFireParticleParticle;
import net.pixelbank.burnt.client.particle.TorchFireParticleParticle;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Exempts Burnt's flame and smoke particles from Weather2's global wind.
 * Burnt's particles already drift on their own; Weather2's full wind force on top blasts them
 * sideways, so fire flies horizontally instead of rising. Cancelled at HEAD for our particles only.
 *
 * @Pseudo, and the injector uses require = 0, so it is skipped when Weather2 is absent.
 */
@Pseudo
@Mixin(value = weather2.weathersystem.wind.WindManager.class, remap = false)
public class Weather2WindManagerMixin {

    @Inject(
        method = "applyWindForceNew(Ljava/lang/Object;FFZ)V",
        at = @At("HEAD"),
        cancellable = true,
        require = 0
    )
    private void burnt$exemptFlameAndSmokeParticles(
            Object target, float strengthMul, float speedCap, boolean usePos,
            CallbackInfo ci) {
        if (target instanceof FireParticleParticle
                || target instanceof TorchFireParticleParticle
                || target instanceof FlamesParticle
                || target instanceof BlueFlameParticleParticle
                || target instanceof MossFireParticleParticle
                || target instanceof FlameSmokeParticle
                || target instanceof LargeFlameSmokeParticle
                || target instanceof LightFlameSmokeParticle) {
            ci.cancel();
        }
    }
}
