package net.pixelbank.burnt.compat;

import net.minecraftforge.fml.ModList;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

/**
 * Optional bridge to Simple Clouds (modid "simpleclouds").
 * TODO restore the precipitation and wind bridge once a 1.20.1 API build is in libs/.
 * The full version is in the 1.21.1 source.
 */
public class SimpleCloudsBridge {

    private static final boolean MOD_PRESENT = ModList.get().isLoaded("simpleclouds");

    static {
        if (MOD_PRESENT) {
            BurntMod.LOGGER.info("[Burnt] Simple Clouds detected - precipitation/wind bridge pending 1.20.1 port.");
        }
    }

    public static boolean isActive() {
        return MOD_PRESENT;
    }

    public static boolean isPrecipitatingAt(LevelAccessor world, BlockPos pos) {
        // TODO Phase 7: implement using ScAPICloudManager
        return false;
    }

    public static boolean isAnyPrecipitationActive(LevelAccessor world) {
        // TODO Phase 7: implement using ScAPICloudManager
        return false;
    }

    public static double getBurntSpeed(LevelAccessor world, double x, double z) {
        // TODO Phase 7: implement using ScAPICloudManager
        return 0.0;
    }

    public static double getBurntAngle(LevelAccessor world) {
        // TODO Phase 7: implement using ScAPICloudManager
        return 0.0;
    }
}
