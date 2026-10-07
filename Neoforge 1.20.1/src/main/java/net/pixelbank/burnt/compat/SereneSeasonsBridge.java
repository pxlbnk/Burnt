package net.pixelbank.burnt.compat;

import net.minecraftforge.fml.ModList;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.LevelAccessor;

/**
 * Optional bridge to Serene Seasons (modid "sereneseasons").
 * TODO restore the seasonal modifier once a 1.20.1 build is in libs/ as a compileOnly dep.
 * The full version is in the 1.21.1 source.
 */
public class SereneSeasonsBridge {

    private static final boolean MOD_PRESENT = ModList.get().isLoaded("sereneseasons");

    static {
        if (MOD_PRESENT) {
            BurntMod.LOGGER.info("[Burnt] Serene Seasons detected - seasonal fire spread pending 1.20.1 port.");
        }
    }

    public static boolean isActive() {
        return MOD_PRESENT;
    }

    public static double getSpreadModifier(LevelAccessor world, double x, double y, double z) {
        // TODO Phase 7: implement using sereneseasons.api.season.SeasonHelper
        return 0.0;
    }
}
