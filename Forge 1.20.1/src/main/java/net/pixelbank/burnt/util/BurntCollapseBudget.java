package net.pixelbank.burnt.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;

/**
 * Server-wide cap on falling blocks per second.
 * Collapse is self-amplifying - each block that lands creates fresh air under its neighbours - so
 * one room collapses at its normal pace and a mansion just takes longer instead of stalling the server.
 */
public final class BurntCollapseBudget {

	/** Falling blocks allowed per second, server-wide. */
	private static final int MAX_PER_SECOND = 20;

	private static long window = -1L;
	private static int spawned = 0;

	private static void roll(ServerLevel level) {
		long now = level.getGameTime() / 20L;
		if (now != window) {
			window = now;
			spawned = 0;
		}
	}

	/** Cheap pre-check: is it worth scanning for collapsible blocks at all right now? */
	public static boolean hasHeadroom(LevelAccessor world) {
		if (!(world instanceof ServerLevel level)) return true;
		roll(level);
		return spawned < MAX_PER_SECOND;
	}

	/** Called for every block that actually starts falling. */
	public static void noteSpawn(LevelAccessor world) {
		if (!(world instanceof ServerLevel level)) return;
		roll(level);
		spawned++;
	}

	private BurntCollapseBudget() {}
}
