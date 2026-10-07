package net.pixelbank.burnt.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.pixelbank.burnt.BurntMod;

import java.lang.reflect.Field;

/**
 * Connects to Immersive Winds if it is installed.
 * When active, Burnt uses its wind speed and direction instead of simulating its own.
 */
public class ImmersiveWindsBridge {

    private static final boolean MOD_PRESENT =
            FabricLoader.getInstance().isModLoaded("immersivewind");

    private static Field strengthField;
    private static Field directionField;
    private static boolean active = false;

    static {
        if (MOD_PRESENT) {
            try {
                Class<?> windManager = Class.forName("net.vibzz.immersivewind.wind.WindManager");
                strengthField  = windManager.getField("currentWindStrength");
                directionField = windManager.getField("currentWindDirection");
                active = true;
                BurntMod.LOGGER.info("[Burnt] Immersive Winds detected - wind system bridged.");
            } catch (Exception e) {
                BurntMod.LOGGER.warn("[Burnt] Immersive Winds found but bridge failed: {}", e.getMessage());
            }
        }
    }

    /** Returns true if Immersive Winds is installed and connected. */
    public static boolean isActive() {
        return active;
    }

    /** Returns Immersive Winds' wind speed converted to Burnt's 0-100 scale. */
    public static double getBurntSpeed() {
        try {
            int iwStrength = (int) strengthField.get(null);
            return Math.min(100.0, iwStrength * (100.0 / 30.0));
        } catch (Exception e) {
            return 0.0;
        }
    }

    /** Returns Immersive Winds' wind direction converted to match Burnt's angle system. */
    public static double getBurntAngle() {
        try {
            int iwDirection = (int) directionField.get(null);
            return (iwDirection - 90 + 360) % 360;
        } catch (Exception e) {
            return 0.0;
        }
    }
}
