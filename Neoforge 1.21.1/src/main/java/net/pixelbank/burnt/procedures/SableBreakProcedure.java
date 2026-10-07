package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.compat.sable.BurntSableCompat;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

// Structural break-away for burning blocks on a Sable creation
// Removing a block makes Sable re-check connectivity and split the hull into physics bodies, so
// a burning ship erodes and falls apart
// Deliberately conservative: an earlier 35%/tick version over-drove Rapier's body creation and
// hard-crashed the game with no Java stack trace, so it throttles on three axes - an adjacency
// gate (only weak blocks break), a per-block chance, and a hard server-wide budget
// Returns true when the block was removed. NeoForge 1.21.1 only, as Sable is
public class SableBreakProcedure {

	// Break chance by structural weakness: 2 or fewer neighbours breaks readily, exactly 3 rarely,
	// more than 3 is interior or load-bearing and never breaks
	/** Break chance for very weak blocks (<=2 solid neighbours). */
	private static final double BREAK_CHANCE_WEAK = 0.75;
	/** Break chance for moderately-connected blocks (exactly 3 solid neighbours). */
	private static final double BREAK_CHANCE_MEDIUM = 0.25;

	// Global rate limiter (server-wide, across all ships)
	/** Length of the rolling budget window, in game ticks. */
	private static final long WINDOW_TICKS = 20L; // 1 second
	/** Server-wide removal ceiling per window - the crash guardrail. The original crash came from an
	 *  uncapped rate of dozens per tick. */
	private static final int MAX_BREAKS_PER_WINDOW = 10;
	private static long windowStart = Long.MIN_VALUE;
	private static int breaksThisWindow = 0;

	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) return false;
		if (!(world instanceof ServerLevel level)) return false;
		// Respect the collapse toggle - with structural collapse off, ships don't shed blocks either
		if (!BurntBasicConfigConfiguration.COLLAPSE.get()) return false;
		if (!BurntSableCompat.isActive()) return false;

		// Global budget gate first (cheap) - the crash guardrail.
		if (budgetExhausted(level)) return false;

		// Only act on Sable creations - world builds keep their normal break behaviour.
		if (!BurntSableCompat.isInSubLevel(level, x, y, z)) return false;

		// Weaker blocks break far more readily; more than 3 neighbours is load-bearing and never breaks
		int neighbours = countSolidNeighbours(level, x, y, z);
		double chance;
		if (neighbours <= 2) chance = BREAK_CHANCE_WEAK;
		else if (neighbours == 3) chance = BREAK_CHANCE_MEDIUM;
		else return false;
		if (Math.random() > chance) return false;

		breaksThisWindow++;
		// destroyBlock goes through the path Sable listens on, which triggers its connectivity split
		// Drops off - it's burning
		level.destroyBlock(BlockPos.containing(x, y, z), false);
		return true;
	}

	// Burnt-envelope break (driven from BurntEnvelopeBlock's scheduled tick)
	/** Chance per tick a charred envelope breaks away. They're the lift surface, so they go readily. */
	private static final double ENVELOPE_BREAK_CHANCE = 0.50;
	/** Below this many solid neighbours a burnt envelope breaks immediately rather than rolling. */
	private static final int ENVELOPE_MIN_SUPPORT = 2;

	// Envelope break-away, called every scheduled tick from BurntEnvelopeBlock
	// An unsupported edge breaks immediately, so exposed sections cascade as a ship sheds blocks;
	// otherwise it rolls the chance. Shares the same global budget as block breaks. No-op off-creation
	public static boolean executeEnvelope(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) return false;
		if (!(world instanceof ServerLevel level)) return false;
		if (!BurntBasicConfigConfiguration.COLLAPSE.get()) return false;
		if (!BurntSableCompat.isActive()) return false;
		if (budgetExhausted(level)) return false;
		if (!BurntSableCompat.isInSubLevel(level, x, y, z)) return false;

		int neighbours = countSolidNeighbours(level, x, y, z);
		boolean unsupported = neighbours < ENVELOPE_MIN_SUPPORT;
		if (!unsupported && Math.random() > ENVELOPE_BREAK_CHANCE) return false;

		breaksThisWindow++;
		level.destroyBlock(BlockPos.containing(x, y, z), false);
		return true;
	}

	/**
	 * Advances the rolling budget window and returns true when this window's quota is spent. Shared
	 * by block and envelope breaks, so one server-wide ceiling governs all Sable removal.
	 */
	private static boolean budgetExhausted(ServerLevel level) {
		long now = level.getGameTime();
		if (now - windowStart >= WINDOW_TICKS) {
			windowStart = now;
			breaksThisWindow = 0;
		}
		return breaksThisWindow >= MAX_BREAKS_PER_WINDOW;
	}

	/** Count of the 6 face-neighbours that are non-air (i.e. structural). */
	private static int countSolidNeighbours(ServerLevel level, double x, double y, double z) {
		BlockPos pos = BlockPos.containing(x, y, z);
		int count = 0;
		BlockPos.MutableBlockPos n = new BlockPos.MutableBlockPos();
		for (Direction d : Direction.values()) {
			n.setWithOffset(pos, d);
			BlockState s = level.getBlockState(n);
			if (!s.isAir()) count++;
		}
		return count;
	}

	private SableBreakProcedure() {}
}
