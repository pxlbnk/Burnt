package net.pixelbank.burnt.util;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.util.RandomSource;

/**
 * Keeps a big fire from drowning in smoke particles.
 * FlameSmokeParticle lives 150-400 ticks against vanilla smoke's ~30, so the on-screen count
 * scales straight off the number of burning blocks in view. Two separate levers:
 * densityFactor() is the player's own smoke_density setting, and allowSpawn() tapers new spawns
 * off automatically as the live count climbs.
 */
public final class BurntSmokeBudget {

	/** Below this, never interfere - a normal fire should look untouched. */
	private static final int SOFT_CAP = 700;

	/** At this point stop spawning entirely; between the two the odds fall off linearly. */
	private static final int HARD_CAP = 1500;

	private static int alive = 0;

	/** The level the count belongs to, held as Object so this class stays loader/side neutral. */
	private static Object lastLevel = null;

	/** Called from a smoke particle constructor. Resets the count when the world changes. */
	public static void acquire(Object levelRef) {
		if (levelRef != lastLevel) {
			lastLevel = levelRef;
			alive = 0;
		}
		alive++;
	}

	/** Called once from a smoke particle when it dies. */
	public static void release() {
		if (alive > 0) alive--;
	}

	/** Player facing density, 0-100, as a plain multiplier. */
	public static double densityFactor() {
		double pct = BurntBasicConfigConfiguration.SMOKE_DENSITY.get();
		if (pct <= 0) return 0.0;
		if (pct >= 100) return 1.0;
		return pct / 100.0;
	}

	/** False when the scene already has enough smoke in it. */
	public static boolean allowSpawn(RandomSource random) {
		int n = alive;
		if (n < SOFT_CAP) return true;
		if (n >= HARD_CAP) return false;
		// Taper rather than cut off hard, so the smoke thins out instead of visibly stopping.
		return random.nextFloat() < (float) (HARD_CAP - n) / (float) (HARD_CAP - SOFT_CAP);
	}

	/** For debugging / future readouts. */
	public static int liveCount() {
		return alive;
	}

	private BurntSmokeBudget() {}
}
