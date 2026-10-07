package net.pixelbank.burnt.mixins.compat;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.procedures.TheBurnProcedure;
import net.pixelbank.burnt.util.BurntTags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Two-part Weather2 tornado compatibility, on TornadoHelper.
 *
 * 1. canGrab - Weather2 excludes Blocks.FIRE by object identity, which doesn't cover FIRESTARTER.
 *    This extends the same protection to every block in burnt:active_fire.
 * 2. Burn scan - after each tick, finds fire near the funnel's ground contact and drives the burn
 *    from there, rather than placing blocks proactively.
 *
 * @Pseudo so it is silently skipped when Weather2 is absent.
 */
@Pseudo
@Mixin(targets = "weather2.weathersystem.storm.TornadoHelper", remap = false)
public class Weather2TornadoHelperMixin {

    /** The storm this TornadoHelper belongs to. */
    @Shadow
    public Object storm;

    // 1. Block grab protection

    @Inject(
        method = "canGrab(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)Z",
        at = @At("HEAD"),
        cancellable = true,
        require = 0
    )
    private void burnt$protectActiveFire(
            Level level,
            BlockState state,
            BlockPos pos,
            CallbackInfoReturnable<Boolean> cir) {
        // Cannot grab any Burnt fire block, mirroring the Blocks.FIRE exclusion already in canGrab
        if (state.is(BurntTags.BLOCK_ACTIVE_FIRE)) {
            cir.setReturnValue(false);
            return;
        }
        // Logs stay on the ground and burn in place rather than being launched as physics entities
        if (state.is(BurntTags.BLOCK_TORNADO_GRAB_EXEMPT_LOGS)) {
            cir.setReturnValue(false);
        }
    }

    // 2. Firenado burn scan

    /**
     * After TornadoHelper.tick(), finds fire blocks near the funnel's ground contact and calls
     * WillitburnProcedure at each. Weather2 places the fire and our canGrab keeps it alive, so by
     * the time this runs the blocks are already there.
     *
     * Centred on the funnel's bottom layer rather than storm.pos, which is the cloud-level anchor.
     * Self-gating - no fire present means nothing is found.
     */
    @Inject(
        method = "tick(Lnet/minecraft/world/level/Level;)V",
        at = @At("RETURN"),
        require = 0
    )
    private void burnt$firenadoBurnScan(Level level, CallbackInfo ci) {
        if (level.isClientSide()) return;
        if (storm == null) return;
        // Access weather2 fields via reflection to avoid compile-time dependency
        try {
            if ((boolean) storm.getClass().getField("isDead").get(storm)) return;
            if (!(boolean) storm.getClass().getField("isFirenado").get(storm)) return;
        } catch (Exception e) { return; }
        if (!BurntBasicConfigConfiguration.BURNT_ON.get()) return;
        if (!level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) return;
        if (level.getGameTime() % 20 != 0) return;

        final int SCAN_RADIUS = 8;
        final int SCAN_Y_FINE = 1;

        int cx, cz;
        String contactSource;
        try {
            // Try funnel bottom-layer contact point via reflection (no weather2 type refs)
            Object funnel = storm.getClass().getMethod("getTornadoFunnelSimple").invoke(storm);
            java.util.List<?> layers = (java.util.List<?>) funnel.getClass().getField("listLayers").get(funnel);
            Object layer0 = layers.get(0);
            net.minecraft.world.phys.Vec3 contact = (net.minecraft.world.phys.Vec3)
                    layer0.getClass().getMethod("getPos").invoke(layer0);
            cx = (int) contact.x;
            cz = (int) contact.z;
            contactSource = "funnel";
        } catch (Exception e) {
            // Funnel not yet built or layers empty - fall back to storm.pos
            try {
                net.minecraft.world.phys.Vec3 pos = (net.minecraft.world.phys.Vec3)
                        storm.getClass().getField("pos").get(storm);
                cx = (int) pos.x;
                cz = (int) pos.z;
                contactSource = "storm.pos(fallback)";
            } catch (Exception e2) { return; }
        }
        int heightmapY = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                new BlockPos(cx, 0, cz)).getY();
        int cy = heightmapY + SCAN_Y_FINE;

        int r = SCAN_RADIUS;
        int r2 = r * r;
        BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
        int fireCount = 0;

        for (int dx = -r; dx <= r; dx++) {
            for (int dy = -r; dy <= r; dy++) {
                for (int dz = -r; dz <= r; dz++) {
                    if (dx * dx + dy * dy + dz * dz > r2) continue;
                    mpos.set(cx + dx, cy + dy, cz + dz);
                    BlockState state = level.getBlockState(mpos);
                    if (state.is(Blocks.FIRE) || state.is(BurntTags.BLOCK_ACTIVE_FIRE)) {
                        fireCount++;
                        TheBurnProcedure.execute(level, cx + dx, cy + dy, cz + dz);
                    }
                }
            }
        }

    }
}
