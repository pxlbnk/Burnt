package net.pixelbank.burnt.compat;

import net.minecraftforge.fml.ModList;
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
        if (!MOD_PRESENT) return;
        try {
            if (world.isClientSide()) return;
            if (!(world instanceof Level level)) return;
            Class<?> cls = Class.forName("com.endertech.minecraft.mods.adpother.events.WorldEvents");
            cls.getMethod("onBlockBurned", BlockState.class, Level.class, BlockPos.class)
               .invoke(null, preIgnitionState, level, pos);
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] AdPotherBridge.onBlockIgnited() failed: {}", e.getMessage());
        }
    }

    /**
     * Reports ongoing smoldering to AdPother, gated by its own getRandomTickFactor() so the rate
     * matches its emitter system. A no-op for blocks absent from its Fuels registry.
     */
    public static void onSmolderingTick(LevelAccessor world, BlockPos pos, BlockState blockState) {
        if (!MOD_PRESENT) return;
        try {
            if (!(world instanceof ServerLevel serverLevel)) return;
            Class<?> cls = Class.forName("com.endertech.minecraft.mods.adpother.events.WorldEvents");
            float tickFactor = (float) cls.getMethod("getRandomTickFactor", ServerLevel.class)
                    .invoke(null, serverLevel);
            if (serverLevel.getRandom().nextFloat() >= tickFactor) return;
            cls.getMethod("onBlockBurned", BlockState.class, Level.class, BlockPos.class)
               .invoke(null, blockState, serverLevel, pos);
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] AdPotherBridge.onSmolderingTick() failed: {}", e.getMessage());
        }
    }
}
