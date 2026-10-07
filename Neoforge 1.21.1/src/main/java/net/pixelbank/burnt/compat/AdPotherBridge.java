package net.pixelbank.burnt.compat;

import net.neoforged.fml.ModList;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Optional bridge to the AdPother mod (modid "adpother"), which tracks combustion pollution.
 *
 * Burnt sets block states directly, so AdPother's hook on onCaughtFire never fires for a Burnt
 * fire. This calls WorldEvents.onBlockBurned() explicitly instead - once at ignition with the
 * original vanilla state, and periodically from WillitburnProcedure for ongoing combustion.
 *
 * Safe without AdPother installed - the isActive() guard keeps its classes untouched.
 */
public class AdPotherBridge {

    private static final boolean MOD_PRESENT = ModList.get().isLoaded("adpother");

    static {
        if (MOD_PRESENT) {
            BurntMod.LOGGER.info("[Burnt] AdPother detected - fire pollution reporting enabled.");
        }
    }

    /** Returns true if AdPother is installed and available at runtime. */
    public static boolean isActive() {
        return MOD_PRESENT;
    }

    /**
     * Reports an ignition to AdPother. Call BEFORE replacing the block, passing the original
     * vanilla state so AdPother can find it in its Fuels registry.
     */
    public static void onBlockIgnited(LevelAccessor world, BlockPos pos, BlockState preIgnitionState) {
        try {
            if (world.isClientSide()) return;
            if (!(world instanceof Level level)) return;
            com.endertech.minecraft.mods.adpother.events.WorldEvents
                    .onBlockBurned(preIgnitionState, level, pos);
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] AdPotherBridge.onBlockIgnited() failed: {}", e.getMessage());
        }
    }

    /**
     * Reports ongoing smoldering to AdPother, gated by its own getRandomTickFactor() so the rate
     * matches its emitter system. A no-op for blocks absent from its Fuels registry.
     */
    public static void onSmolderingTick(LevelAccessor world, BlockPos pos, BlockState blockState) {
        try {
            if (!(world instanceof ServerLevel serverLevel)) return;
            // Match AdPother's own random tick emission rate - don't emit every single tick
            float tickFactor = com.endertech.minecraft.mods.adpother.events.WorldEvents
                    .getRandomTickFactor(serverLevel);
            if (serverLevel.getRandom().nextFloat() >= tickFactor) return;
            com.endertech.minecraft.mods.adpother.events.WorldEvents
                    .onBlockBurned(blockState, serverLevel, pos);
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] AdPotherBridge.onSmolderingTick() failed: {}", e.getMessage());
        }
    }
}
