package net.pixelbank.burnt.util;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Central "may fire touch this block?" gate, and the extension point other mods use to keep
 * Burnt out of an area.
 *
 * <p>Burnt drives its burn chain from its own procedures, calling {@code setBlock} directly - no
 * event and no player context, so there is nothing for a claim mod to cancel. Every path that
 * starts a block burning funnels through {@code BurnBabyProcedure.execute}, so one check there
 * covers spread, flint, lightning, fireballs, lava and the compat entry points alike.
 *
 * <p>Blockers get both the position the fire comes from and the one it is trying to reach, since
 * claim mods treat fire spreading within an area differently from fire crossing into it.
 *
 * <p>Register one during common setup:
 * <pre>{@code
 * BurntProtection.register((level, from, to) -> myClaimManager.isProtected(level, to));
 * }</pre>
 * Consulted on a hot path, so keep them cheap. A blocker that throws is dropped and logged.
 */
public final class BurntProtection {

	/** A single "keep fire out of here" rule. */
	@FunctionalInterface
	public interface BurnBlocker {
		/**
		 * @param from where the fire is spreading from; equal to {@code to} when there is no source
		 * @param to   the position Burnt wants to ignite or convert
		 * @return true to stop Burnt touching {@code to}
		 */
		boolean isProtected(LevelAccessor level, BlockPos from, BlockPos to);
	}

	private static final List<BurnBlocker> BLOCKERS = new CopyOnWriteArrayList<>();

	/** Add a rule that stops Burnt touching blocks where it returns true. */
	public static void register(BurnBlocker blocker) {
		if (blocker == null) return;
		BLOCKERS.add(blocker);
		BurntMod.LOGGER.info("[Burnt] Burn blocker registered ({} active).", BLOCKERS.size());
	}

	/** True when something has claimed this position and Burnt should leave it alone. */
	public static boolean isProtected(LevelAccessor level, BlockPos from, BlockPos to) {
		// Overwhelmingly the common case - no protection mods installed at all.
		if (BLOCKERS.isEmpty()) return false;
		// Indexed loop - CopyOnWriteArrayList allocates an iterator per traversal, and this is a hot path
		for (int i = 0; i < BLOCKERS.size(); i++) {
			BurnBlocker blocker = BLOCKERS.get(i);
			try {
				if (blocker.isProtected(level, from, to)) return true;
			} catch (Throwable t) {
				// Drop a blocker after its first failure - it must not halt the chain or spam the log every tick
				BLOCKERS.remove(blocker);
				BurntMod.LOGGER.error("[Burnt] Burn blocker threw and was removed.", t);
			}
		}
		return false;
	}

	/** No meaningful source: lightning, flint, a fireball landing. */
	public static boolean isProtected(LevelAccessor level, BlockPos pos) {
		return isProtected(level, pos, pos);
	}

	/** Convenience for procedure code, which works in raw doubles. */
	public static boolean isProtected(LevelAccessor level, double x, double y, double z) {
		if (BLOCKERS.isEmpty()) return false;
		BlockPos pos = BlockPos.containing(x, y, z);
		return isProtected(level, pos, pos);
	}

	/** Spread form: fire moving from one block to the next. */
	public static boolean isProtected(LevelAccessor level, double fx, double fy, double fz,
	                                  double tx, double ty, double tz) {
		if (BLOCKERS.isEmpty()) return false;
		return isProtected(level, BlockPos.containing(fx, fy, fz), BlockPos.containing(tx, ty, tz));
	}

	/** True when at least one protection rule is installed. */
	public static boolean hasBlockers() {
		return !BLOCKERS.isEmpty();
	}

	/**
	 * True when a block that is already burning should stop and settle to its burnt state.
	 * The ignition gates keep fire out of claimed land, but cannot help a block that was already
	 * alight when the claim appeared, so this rides the same check the procedures make for killFire.
	 */
	public static boolean stopBurning(LevelAccessor level, double x, double y, double z) {
		return net.pixelbank.burnt.BurntMod.killFire || isProtected(level, x, y, z);
	}

	private BurntProtection() {}
}
