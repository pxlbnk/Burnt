package net.pixelbank.burnt.compat;

import net.neoforged.fml.ModList;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;

/**
 * Optional bridge to Weather2 (modid "weather2") - its global wind speed and angle replace
 * Burnt's own simulation, keeping fire spread in sync with its weather.
 *
 * Safe without Weather2 - the isActive() guard keeps its classes untouched.
 */
public class Weather2Bridge {

    private static final boolean MOD_PRESENT =
            ModList.get().isLoaded("weather2");

    static {
        if (MOD_PRESENT) {
            BurntMod.LOGGER.info("[Burnt] Weather2 detected - wind system bridged.");
        }
    }

    /** Returns true if Weather2 is installed and available. */
    public static boolean isActive() {
        return MOD_PRESENT;
    }

    /**
     * Weather2's global wind speed on Burnt's 0-100 scale.
     * Read from windSpeedGlobal directly, not ServerWeatherProxy.getWindSpeed(), which returns -1
     * without Love Tropics installed - a negative speed would invert every particle velocity.
     */
    public static double getBurntSpeed(LevelAccessor world) {
        try {
            if (!(world instanceof ServerLevel serverLevel)) return 0.0;
            weather2.weathersystem.WeatherManager manager =
                    weather2.util.WindReader.getWeatherManagerFor(serverLevel);
            if (manager == null) return 0.0;
            float raw = manager.getWindManager().windSpeedGlobal;
            float max = (float) weather2.config.ConfigWind.windSpeedMax;
            if (max <= 0) max = 1f; // safety against divide-by-zero
            return Math.min(100.0, (raw / max) * 100.0);
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] Weather2Bridge.getBurntSpeed() failed: {}", e.getMessage());
            return 0.0;
        }
    }

    /** Weather2's global wind angle. It already uses the same 0-360 convention as Burnt. */
    public static double getBurntAngle(LevelAccessor world) {
        try {
            if (!(world instanceof ServerLevel serverLevel)) return 0.0;
            weather2.weathersystem.WeatherManager manager =
                    weather2.util.WindReader.getWeatherManagerFor(serverLevel);
            if (manager == null) return 0.0;
            return manager.getWindManager().windAngleGlobal;
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] Weather2Bridge.getBurntAngle() failed: {}", e.getMessage());
            return 0.0;
        }
    }

    /**
     * True when any Weather2 storm is precipitating anywhere in this world. The fast global gate,
     * so a clear world skips the per-position checks.
     */
    public static boolean isAnyPrecipitationActive(LevelAccessor world) {
        try {
            if (!(world instanceof ServerLevel serverLevel)) return false;
            weather2.weathersystem.WeatherManager manager =
                    weather2.util.WindReader.getWeatherManagerFor(serverLevel);
            if (manager == null) return false;
            for (weather2.weathersystem.storm.WeatherObject obj : manager.getStormObjects()) {
                if (obj.isDead) continue;
                if (obj instanceof weather2.weathersystem.storm.StormObject storm
                        && storm.attrib_precipitation) return true;
                if (obj instanceof weather2.weathersystem.storm.WeatherObjectParticleStorm ps
                        && ps.getType() == weather2.weathersystem.storm.WeatherObjectParticleStorm.StormType.SNOWSTORM)
                    return true;
            }
            return false;
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] Weather2Bridge.isAnyPrecipitationActive() failed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * True when a Weather2 firenado is near this position, which makes FireBlockOnPlaceMixin skip
     * the FIRESTARTER swap.
     *
     * Weather2 protects Blocks.FIRE from its tornado grab by object identity, so FIRESTARTER is not
     * protected and gets grabbed, leaving no visible fire. Letting vanilla fire survive in the zone
     * is the safe fix. Checks within 3x the storm's size to cover the fire radius with a margin.
     */
    public static boolean isFirenadoNear(LevelAccessor world, BlockPos pos) {
        try {
            if (!(world instanceof ServerLevel serverLevel)) return false;
            weather2.weathersystem.WeatherManager manager =
                    weather2.util.WindReader.getWeatherManagerFor(serverLevel);
            if (manager == null) return false;
            net.minecraft.world.phys.Vec3 vec =
                    new net.minecraft.world.phys.Vec3(pos.getX(), pos.getY(), pos.getZ());
            for (weather2.weathersystem.storm.WeatherObject obj : manager.getStormObjects()) {
                if (obj.isDead) continue;
                if (obj instanceof weather2.weathersystem.storm.StormObject storm
                        && storm.isFirenado
                        && vec.distanceTo(storm.pos) < storm.size * 3) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] Weather2Bridge.isFirenadoNear() failed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * True when Weather2 is precipitating at this position - heavy rain, lightning, hail or snowstorm.
     *
     * Deliberately avoids WeatherManager.isPrecipitatingAt(), whose lambda hard-casts every
     * WeatherObject to StormObject and throws whenever a snowstorm or old sandstorm is in the list,
     * silently returning false for every storm type. Also avoids the Love Tropics proxy, which
     * returns false in any standard setup. Falls back to false on error.
     */
    public static boolean isPrecipitatingAt(LevelAccessor world, BlockPos pos) {
        try {
            if (!(world instanceof ServerLevel serverLevel)) return false;
            weather2.weathersystem.WeatherManager manager =
                    weather2.util.WindReader.getWeatherManagerFor(serverLevel);
            if (manager == null) return false;

            net.minecraft.world.phys.Vec3 vec =
                    new net.minecraft.world.phys.Vec3(pos.getX(), pos.getY(), pos.getZ());

            for (weather2.weathersystem.storm.WeatherObject obj : manager.getStormObjects()) {
                if (obj.isDead) continue;

                if (obj instanceof weather2.weathersystem.storm.StormObject storm) {
                    // Heavy rain, lightning storms and hail all set attrib_precipitation while active
                    if (storm.attrib_precipitation && vec.distanceTo(storm.pos) < storm.size) {
                        return true;
                    }
                } else if (obj instanceof weather2.weathersystem.storm.WeatherObjectParticleStorm particleStorm) {
                    // Snowstorms are particle storms rather than StormObjects. Sandstorms are too, but aren't precipitation
                    var type = particleStorm.getType();
                    if (type == weather2.weathersystem.storm.WeatherObjectParticleStorm.StormType.SNOWSTORM
                            && vec.distanceTo(particleStorm.pos) < particleStorm.getSize()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] Weather2Bridge.isPrecipitatingAt() failed: {}", e.getMessage());
            return false;
        }
    }
}
