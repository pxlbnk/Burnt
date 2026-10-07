package net.pixelbank.burnt.compat;

import net.neoforged.fml.ModList;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

/**
 * Optional bridge to Simple Clouds (modid "simpleclouds") for per-position precipitation and
 * wind direction. Sits third in WindWakerProcedure's priority, below Immersive Winds and Weather2.
 *
 * Safe without Simple Clouds installed - the isActive() guard keeps its classes untouched. The
 * API jar is compileOnly and is not shipped with Burnt.
 */
public class SimpleCloudsBridge {

    private static final boolean MOD_PRESENT = ModList.get().isLoaded("simpleclouds");

    static {
        if (MOD_PRESENT) {
            BurntMod.LOGGER.info("[Burnt] Simple Clouds detected - precipitation and wind bridged.");
        }
    }

    /** Returns true if Simple Clouds is installed and available at runtime. */
    public static boolean isActive() {
        return MOD_PRESENT;
    }

    /** The Simple Clouds cloud manager for this world, or null if unavailable. */
    private static dev.nonamecrackers2.simpleclouds.api.common.world.ScAPICloudManager getManager(LevelAccessor world) {
        if (!(world instanceof Level level)) return null;
        dev.nonamecrackers2.simpleclouds.api.SimpleCloudsAPI api =
                dev.nonamecrackers2.simpleclouds.api.SimpleCloudsAPI.getApi();
        if (api == null) return null;
        return api.getCloudManager(level);
    }

    /**
     * True when Simple Clouds reports precipitation at this position. getRainLevel() returns a 0-1
     * intensity accounting for vertical fade, so anything above zero means rain is falling there.
     */
    public static boolean isPrecipitatingAt(LevelAccessor world, BlockPos pos) {
        try {
            dev.nonamecrackers2.simpleclouds.api.common.world.ScAPICloudManager manager = getManager(world);
            if (manager == null) return false;
            return manager.getRainLevel((float) pos.getX(), (float) pos.getY(), (float) pos.getZ()) > 0.0f;
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] SimpleCloudsBridge.isPrecipitatingAt() failed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * True when any cloud region has a rain-bearing weather type. Used as the fast global gate, the
     * same role isRaining() plays for vanilla weather, so clear conditions skip the per-position checks.
     */
    public static boolean isAnyPrecipitationActive(LevelAccessor world) {
        try {
            dev.nonamecrackers2.simpleclouds.api.common.world.ScAPICloudManager manager = getManager(world);
            if (manager == null) return false;
            for (dev.nonamecrackers2.simpleclouds.api.common.cloud.region.ScAPICloudRegion region
                    : manager.getClouds()) {
                if (region.isDead()) continue;
                dev.nonamecrackers2.simpleclouds.api.common.cloud.ScAPICloudType type =
                        manager.getCloudTypeForId(region.getCloudTypeId());
                if (type != null && type.weatherType().includesRain()) return true;
            }
            return false;
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] SimpleCloudsBridge.isAnyPrecipitationActive() failed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Simple Clouds' storminess at (x, z) mapped onto Burnt's 0-100 wind scale, with a base of 10
     * so even calm cloud cover gives a gentle wind rather than dead calm.
     */
    public static double getBurntSpeed(LevelAccessor world, double x, double z) {
        try {
            dev.nonamecrackers2.simpleclouds.api.common.world.ScAPICloudManager manager = getManager(world);
            if (manager == null) return 0.0;
            // getCloudTypeAtPosition returns org.apache.commons.lang3.tuple.Pair - use getLeft()
            org.apache.commons.lang3.tuple.Pair<
                    ? extends dev.nonamecrackers2.simpleclouds.api.common.cloud.ScAPICloudType,
                    Float> pair = manager.getCloudTypeAtPosition((float) x, (float) z);
            if (pair == null) return 0.0;
            dev.nonamecrackers2.simpleclouds.api.common.cloud.ScAPICloudType cloudType = pair.getLeft();
            if (cloudType == null) return 0.0;
            // storminess 0-1 -> Burnt speed 10-100 (base 10 keeps a light wind even in calm clouds)
            return Math.min(100.0, 10.0 + cloudType.storminess() * 90.0);
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] SimpleCloudsBridge.getBurntSpeed() failed: {}", e.getMessage());
            return 0.0;
        }
    }

    /**
     * Cloud drift direction as a bearing in Burnt's convention (0 = south, 90 = west, 180 = north,
     * 270 = east), converted from Simple Clouds' normalized (x, z) vector with atan2.
     */
    public static double getBurntAngle(LevelAccessor world) {
        try {
            dev.nonamecrackers2.simpleclouds.api.common.world.ScAPICloudManager manager = getManager(world);
            if (manager == null) return 0.0;
            org.joml.Vector2f dir = manager.calculateWindDirection();
            if (dir == null) return 0.0;
            // dir.x is world-X and dir.y is world-Z; atan2(-x, z) gives 0 for south, 90 for west
            double angleDeg = Math.toDegrees(Math.atan2(-dir.x, dir.y));
            return (angleDeg + 360.0) % 360.0;
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] SimpleCloudsBridge.getBurntAngle() failed: {}", e.getMessage());
            return 0.0;
        }
    }
}
