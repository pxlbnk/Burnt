package net.pixelbank.burnt.compat;

import net.minecraftforge.fml.ModList;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

/**
 * Optional bridge to Weather2 (modid "weather2").
 * TODO restore the wind, precipitation and firenado bridge once a 1.20.1 build is in libs/.
 * The full version is in the 1.21.1 source.
 */
public class Weather2Bridge {

    private static final boolean MOD_PRESENT = ModList.get().isLoaded("weather2");

    static {
        if (MOD_PRESENT) {
            BurntMod.LOGGER.info("[Burnt] Weather2 detected - wind/firenado bridge pending 1.20.1 port.");
        }
    }

    public static boolean isActive() {
        return MOD_PRESENT;
    }

    public static double getBurntSpeed(LevelAccessor world) {
        // TODO Phase 7: implement using Weather2 WindManager
        return 0.0;
    }

    public static double getBurntAngle(LevelAccessor world) {
        // TODO Phase 7: implement using Weather2 WindManager
        return 0.0;
    }

    public static boolean isAnyPrecipitationActive(LevelAccessor world) {
        // TODO Phase 7: implement using Weather2 WeatherManager
        return false;
    }

    public static boolean isFirenadoNear(LevelAccessor world, BlockPos pos) {
        // TODO Phase 7: implement using Weather2 WeatherManager
        return false;
    }

    public static boolean isPrecipitatingAt(LevelAccessor world, BlockPos pos) {
        // TODO Phase 7: implement using Weather2 WeatherManager
        return false;
    }
}
