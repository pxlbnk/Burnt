package net.pixelbank.burnt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;

// Stops fire appearing near lava when lava burn is off
// Fire a player lights near lava deliberately is left alone for a short window
public final class BurntLavaFireBlocker {

    private static final Map<String, Long> ALLOW_FIRE_UNTIL = new ConcurrentHashMap<>();

    private BurntLavaFireBlocker() {}

    public static String key(Level level, BlockPos pos) {
        return level.dimension().location().toString() + ":" + pos.asLong();
    }

    /** Marks a spot as player-lit so the fire there isn't removed. */
    public static void markEntityPlaced(Level level, BlockPos pos) {
        ALLOW_FIRE_UNTIL.put(key(level, pos), level.getGameTime() + 60L);
    }

    /** Returns true if a player lit fire here recently. */
    public static boolean isEntityPlaced(Level level, BlockPos pos) {
        Long until = ALLOW_FIRE_UNTIL.get(key(level, pos));
        return until != null && until >= level.getGameTime();
    }

    /** Returns true if there's lava nearby. */
    public static boolean isNearLava(Level level, BlockPos pos) {
        int radius = 2;
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) continue;
                    if (level.getFluidState(pos.offset(dx, dy, dz)).is(FluidTags.LAVA)) return true;
                }
            }
        }
        return false;
    }

    /** Removes old entries. Call this every so often to keep the list clean. */
    public static void cleanupStale(long now) {
        ALLOW_FIRE_UNTIL.entrySet().removeIf(e -> e.getValue() < now);
    }
}
