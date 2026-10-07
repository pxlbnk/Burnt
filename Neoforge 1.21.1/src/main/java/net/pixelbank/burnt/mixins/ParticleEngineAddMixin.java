package net.pixelbank.burnt.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.world.level.Level;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.mixins.accessor.ParticleAccessor;
import net.pixelbank.burnt.procedures.WindMultiplyerProcedure;
import net.pixelbank.burnt.compat.Weather2Bridge;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleEngine.class)
public class ParticleEngineAddMixin {

    @Inject(method = "add(Lnet/minecraft/client/particle/Particle;)V", at = @At("HEAD"))
    private void burnt$windifyParticles(Particle p, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;

        // particles
        if (!(p instanceof CampfireSmokeParticle)) return;

        // With wind off, leave vanilla particles on their default vertical drift
        if (!BurntBasicConfigConfiguration.WIND.get()) return;
        // Weather2 already moves all particles with its own wind system - don't double-apply
        if (Weather2Bridge.isActive()) return;

        var vars = BurntModVariables.MapVariables.get(level);

        double windSpeed = vars.windSpeed;     // 0..10
        double windAngleDeg = vars.windAngle;  // 0 = FROM north

        double speed01 = Math.max(0.0, Math.min(1.0, windSpeed / 10.0));
        if (speed01 <= 0.0) return;

        double rad = windAngleDeg * (Math.PI / 180.0);
        double toX = -Math.sin(rad);
        double toZ =  Math.cos(rad);

        // biome/altitude multiplier (your procedure)
        double windMulti = WindMultiplyerProcedure.execute(level, 0, 0, 0);
        windMulti = Math.max(0.0, windMulti);

        // strength knob * multiplier
        double k = 0.08 * windMulti;

        double pushX = toX * speed01 * k;
        double pushZ = toZ * speed01 * k;

        ParticleAccessor a = (ParticleAccessor) p;
        a.burnt$setXd(a.burnt$getXd() + pushX);
        a.burnt$setZd(a.burnt$getZd() + pushZ);
    }
}
