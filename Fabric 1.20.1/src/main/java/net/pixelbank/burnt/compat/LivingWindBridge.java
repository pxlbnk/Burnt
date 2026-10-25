package net.pixelbank.burnt.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;

import java.lang.reflect.Method;

/**
 * Optional bridge to Living Wind (modid "livingwind") - its wind strength and direction
 * replace Burnt's own simulation, so fire spread and smoke follow its weather.
 *
 * Reached by reflection, because Living Wind only ships for some of the loaders Burnt targets
 * and a direct import would not compile on the rest. Safe without it - isActive() stays false.
 */
public class LivingWindBridge {

    private static final boolean MOD_PRESENT = FabricLoader.getInstance().isModLoaded("livingwind");

    /** Living Wind strength that corresponds to Burnt's maximum wind speed of 100. */
    private static final double FULL_STRENGTH = 1.0;

    /** Living Wind's own ceiling, used as full scale for the flag animation. */
    private static final double FLAG_FULL_STRENGTH = 2.5;

    private static Method windApiGet;      // WindApi.get(ServerLevel) -> WindSnapshot
    private static Method snapshotStrength; // WindSnapshot.strength() -> float
    private static Method snapshotDirection;// WindSnapshot.directionDegrees() -> float
    private static boolean active = false;

    static {
        if (MOD_PRESENT) {
            try {
                Class<?> windApi = Class.forName("com.macatmovie.livingwind.api.WindApi");
                windApiGet = windApi.getMethod("get", ServerLevel.class);
                Class<?> snapshot = Class.forName("com.macatmovie.livingwind.wind.WindSnapshot");
                snapshotStrength  = snapshot.getMethod("strength");
                snapshotDirection = snapshot.getMethod("directionDegrees");
                active = true;
                BurntMod.LOGGER.info("[Burnt] Living Wind detected - wind system bridged.");
            } catch (Throwable e) {
                BurntMod.LOGGER.warn("[Burnt] Living Wind found but bridge failed: {}", e.getMessage());
            }
        }
    }

    /** Returns true if Living Wind is installed and connected. */
    public static boolean isActive() {
        return active;
    }

    /** Living Wind's global wind strength on Burnt's 0-100 scale. */
    public static double getBurntSpeed(LevelAccessor world) {
        Object snapshot = snapshot(world);
        if (snapshot == null) return 0.0;
        try {
            double strength = ((Number) snapshotStrength.invoke(snapshot)).doubleValue();
            // Gusts and storms can push past FULL_STRENGTH, and a speed over 100 would
            // drive particle velocities Burnt never expects, so clamp rather than scale
            return Math.max(0.0, Math.min(100.0, strength * (100.0 / FULL_STRENGTH)));
        } catch (Throwable e) {
            return 0.0;
        }
    }

    /**
     * Living Wind's wind direction, already on Burnt's scale.
     * Both use degrees for the direction the wind blows FROM, resolved the same way
     * (x from -sin, z from cos), so no conversion is needed.
     */
    public static double getBurntAngle(LevelAccessor world) {
        Object snapshot = snapshot(world);
        if (snapshot == null) return 0.0;
        try {
            double degrees = ((Number) snapshotDirection.invoke(snapshot)).doubleValue();
            return ((degrees % 360) + 360) % 360;
        } catch (Throwable e) {
            return 0.0;
        }
    }

    /**
     * Wind speed for the flag animation only.
     *
     * <p>getBurntSpeed maps strength 1.0 to Burnt's 100 and clamps there, which suits fire
     * spread but flattens everything from 1.0 to Living Wind's 2.5 ceiling into one value -
     * so a flag sat at its top animation through any windy spell. Flags instead spread the
     * whole range, with 2.5 as full scale, keeping the hardest animations for real gales.
     *
     * <p>Fire spread still reads getBurntSpeed, so this changes nothing but the cloth.
     *
     * @param fallback speed to use when Living Wind is not driving, i.e. Burnt's own windSpeed
     */
    public static double flagSpeed(LevelAccessor world, double fallback) {
        Object snapshot = snapshot(world);
        if (snapshot == null) return fallback;
        try {
            double strength = ((Number) snapshotStrength.invoke(snapshot)).doubleValue();
            return Math.max(0.0, Math.min(100.0, strength * (100.0 / FLAG_FULL_STRENGTH)));
        } catch (Throwable e) {
            return fallback;
        }
    }

    /** Living Wind keeps its state server-side, so there is nothing to read on a client level. */
    private static Object snapshot(LevelAccessor world) {
        if (!active || !(world instanceof ServerLevel serverLevel)) return null;
        try {
            return windApiGet.invoke(null, serverLevel);
        } catch (Throwable e) {
            return null;
        }
    }
}
