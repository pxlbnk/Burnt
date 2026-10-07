package net.pixelbank.burnt;

import net.minecraft.core.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Tracks lightning rods that just caught a bolt, so FireBlockOnPlaceMixin can leave fire near them alone
public final class BurntLightningRodGuard {

    // packed rod BlockPos -> expiry game tick
    private static final Map<Long, Long> ROD_STRUCK = new ConcurrentHashMap<>();

    private BurntLightningRodGuard() {}

    /** Called by LightningBoltMixin when a bolt spawned over a rod. */
    public static void markRodStruck(BlockPos rodPos, long gameTime) {
        ROD_STRUCK.put(rodPos.asLong(), gameTime + 20L);
    }

    /** Returns true if firePos is within 4 blocks of a recently-struck rod. */
    public static boolean isNearStruckRod(long gameTime, BlockPos firePos) {
        for (Map.Entry<Long, Long> e : ROD_STRUCK.entrySet()) {
            if (e.getValue() < gameTime) continue;
            if (firePos.closerThan(BlockPos.of(e.getKey()), 4.0)) return true;
        }
        return false;
    }


    // Packed strike position to expiry tick, for bolts that landed with lightning ignition off
    private static final Map<Long, Long> STRIKE_NO_BURN = new ConcurrentHashMap<>();

    // Called by LightningBoltMixin when a bolt lands with lightning ignition off
    // A bolt starts two fires - ours, and the vanilla one the mixin would convert
    // Marking the strike leaves that vanilla fire alone, so a bolt behaves as if Burnt weren't installed
    public static void markStrikeNoBurn(BlockPos strikePos, long gameTime) {
        STRIKE_NO_BURN.put(strikePos.asLong(), gameTime + 20L);
    }

    /** Returns true if firePos is within 4 blocks of a recent strike that must not burn. */
    public static boolean isNearNoBurnStrike(long gameTime, BlockPos firePos) {
        for (Map.Entry<Long, Long> e : STRIKE_NO_BURN.entrySet()) {
            if (e.getValue() < gameTime) continue;
            if (firePos.closerThan(BlockPos.of(e.getKey()), 4.0)) return true;
        }
        return false;
    }

    /** Removes expired entries - call once per server tick. */
    public static void cleanupStale(long gameTime) {
        ROD_STRUCK.entrySet().removeIf(e -> e.getValue() < gameTime);
        STRIKE_NO_BURN.entrySet().removeIf(e -> e.getValue() < gameTime);
    }
}
