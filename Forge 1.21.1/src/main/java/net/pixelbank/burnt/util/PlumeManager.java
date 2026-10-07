package net.pixelbank.burnt.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Tracks how long each smoke plume has been alive, so they can be removed on time
public final class PlumeManager {

    // [0] = age, [1] = life
    private static final Map<UUID, int[]> COUNTERS = new HashMap<>();

    /** Returns how long this plume has been alive. */
    public static int getAge(UUID uuid) {
        int[] c = COUNTERS.get(uuid);
        return c == null ? 0 : c[0];
    }

    /** Resets a plume's life counter so it sticks around longer - used when nearby fire tops it up. */
    public static void resetLife(UUID uuid) {
        int[] c = COUNTERS.computeIfAbsent(uuid, id -> new int[2]);
        c[1] = 0;
    }

    // Updates life counters and returns the plumes that are due for removal
    public static List<UUID> tick(Collection<UUID> livingPlumeUUIDs, int maxLife) {
        // Drop entries for entities that are no longer alive
        COUNTERS.keySet().retainAll(livingPlumeUUIDs);

        List<UUID> toKill = new ArrayList<>();
        for (UUID uuid : livingPlumeUUIDs) {
            int[] c = COUNTERS.computeIfAbsent(uuid, id -> new int[2]);
            c[0]++; // age  - total lifetime; NEVER reset
            c[1]++; // life - reset to 0 by nearby active fire via resetLife()
            // Cap on total age, not life - resetLife() on the nearest plume meant one beside a long fire never expired
            // It then rose into the sky forever, since the rise tick has no height cap of its own
            if (c[0] >= maxLife || c[1] >= maxLife) {
                toKill.add(uuid);
            }
        }
        return toKill;
    }

    /** Removes a plume from tracking after it's been discarded. */
    public static void remove(UUID uuid) {
        COUNTERS.remove(uuid);
    }

    /** Clears all tracking data. Call this when the server stops. */
    public static void clear() {
        COUNTERS.clear();
    }

    private PlumeManager() {}
}
