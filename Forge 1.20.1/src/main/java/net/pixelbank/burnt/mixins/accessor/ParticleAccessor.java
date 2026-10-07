package net.pixelbank.burnt.mixins.accessor;

import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Particle.class)
public interface ParticleAccessor {
    @Accessor("xd") double burnt$getXd();
    @Accessor("yd") double burnt$getYd();
    @Accessor("zd") double burnt$getZd();

    @Accessor("xd") void burnt$setXd(double v);
    @Accessor("yd") void burnt$setYd(double v);
    @Accessor("zd") void burnt$setZd(double v);
}
