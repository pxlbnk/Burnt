package net.pixelbank.burnt.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.pixelbank.burnt.entity.EmberProjectileEntity;

/** Renders the ember projectile as a small glowing item sprite. */
public class EmberProjectileRenderer extends ThrownItemRenderer<EmberProjectileEntity> {
    public EmberProjectileRenderer(EntityRendererProvider.Context context) {
        super(context, 0.5f, true);
    }
}
