package net.pixelbank.burnt.compat.sable;

import dev.ryanhcode.sable.api.sublevel.ServerSubLevelContainer;
import dev.ryanhcode.sable.api.sublevel.SubLevelContainer;
import dev.ryanhcode.sable.companion.math.BoundingBox3dc;
import dev.ryanhcode.sable.companion.math.BoundingBox3ic;
import dev.ryanhcode.sable.companion.math.Pose3dc;
import dev.ryanhcode.sable.sublevel.ServerSubLevel;
import dev.ryanhcode.sable.sublevel.plot.LevelPlot;

import org.joml.Vector3d;
import org.joml.Vector3dc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.state.BlockState;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.procedures.BurnBabyProcedure;
import net.pixelbank.burnt.util.BurntTags;

import java.util.List;
import java.util.Random;

// The Sable-touching half of BurntSableCompat
// References dev.ryanhcode.sable.* in imports, fields and signatures, which the verifier
// resolves at load time - so this must never load without Sable
// Every call site goes through the facade, which gates on ModList.isLoaded first
// New entry points reading or writing Sable types go in the facade, not here
final class BurntSableCompatImpl {

	private static final Random RNG = new Random();
	/** One-shot diagnostic flags so the logs stay clean. */
	private static volatile boolean firstSubLevelLogged = false;
	private static volatile boolean firstPlumeMapLogged = false;

	// Rate-limited spread diagnostics - flip DIAG_VERBOSE on to debug, off for release
	// The one-shot startup sub-level dump fires either way
	private static final boolean DIAG_VERBOSE = false;
	private static final long DIAG_THROTTLE_TICKS = 40L; // ~2s
	private static long lastNeighbourLogTick  = Long.MIN_VALUE;
	private static long lastHitLogTick        = Long.MIN_VALUE;

	/** True at most once per throttle window for the given last-log timestamp holder. */
	private static boolean diagDue(long now, long last) {
		return DIAG_VERBOSE && (now - last >= DIAG_THROTTLE_TICKS);
	}

	/** Server tick interval between scans (10 = twice per second). */
	private static final int TICK_INTERVAL = 10;

	private BurntSableCompatImpl() {}

	/** Entry point - guarded by the facade's {@code MOD_PRESENT} check. */
	static void serverTick(ServerLevel level) {
		if (level.getServer().getTickCount() % TICK_INTERVAL != 0) return;
		try {
			tickGuarded(level);
		} catch (Throwable t) {
			BurntMod.LOGGER.debug("[Burnt] BurntSableCompat tick suppressed: {}", t.toString());
		}
	}

	private static void tickGuarded(ServerLevel level) {
		// Airship-to-world spread is driven from each burning block's own tick, the only path that
		// reliably resolves a sub-level block's world position
		// The old per-tick chunk scanner couldn't see sub-level blocks and is retired, so all this
		// tick does now is the one-shot startup diagnostic
		if (firstSubLevelLogged) return;
		if (!level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) return;

		SubLevelContainer container = SubLevelContainer.getContainer(level);
		if (!(container instanceof ServerSubLevelContainer serverContainer)) return;
		List<ServerSubLevel> subLevels = serverContainer.getAllSubLevels();
		if (subLevels == null || subLevels.isEmpty()) return;

		for (ServerSubLevel subLevel : subLevels) {
			if (subLevel == null || subLevel.isRemoved()) continue;
			firstSubLevelLogged = true;
			LevelPlot p = subLevel.getPlot();
			BoundingBox3ic s = p != null ? p.getBoundingBox() : null;
			BoundingBox3dc w = subLevel.boundingBox();
			Pose3dc pose = subLevel.logicalPose();
			Vector3dc posePos = pose != null ? pose.position() : null;
			BlockPos center = p != null ? p.getCenterBlock() : null;
			BurntMod.LOGGER.info("[Burnt][Sable] First sub-level seen: name='{}'", subLevel.getName());
			if (s != null) BurntMod.LOGGER.info("[Burnt][Sable]   storage AABB: [{},{},{}] - [{},{},{}]",
					s.minX(), s.minY(), s.minZ(), s.maxX(), s.maxY(), s.maxZ());
			if (w != null) BurntMod.LOGGER.info("[Burnt][Sable]   world   AABB: [{},{},{}] - [{},{},{}]",
					w.minX(), w.minY(), w.minZ(), w.maxX(), w.maxY(), w.maxZ());
			if (posePos != null) BurntMod.LOGGER.info("[Burnt][Sable]   pose.position: ({}, {}, {})",
					posePos.x(), posePos.y(), posePos.z());
			if (center != null) BurntMod.LOGGER.info("[Burnt][Sable]   plot.centerBlock: ({}, {}, {})",
					center.getX(), center.getY(), center.getZ());
			break;
		}
	}

	// Plume world-position mapping (storage coord -> visible world coord)

	// Maps a sub-level storage position to the visible world position, or null
	// Detection uses getPlot(ChunkPos), the same chunk-grid lookup Sable itself uses - the per-plot
	// AABB is lazily computed and was seen stale by ~6000 blocks, so it can't be trusted
	static double[] subLevelWorldPos(ServerLevel level, double x, double y, double z) {
		try {
			SubLevelContainer container = SubLevelContainer.getContainer(level);
			if (container == null) return null;

			int bx = (int) Math.floor(x);
			int by = (int) Math.floor(y);
			int bz = (int) Math.floor(z);

			// Authoritative: is this position's chunk owned by a sub-level plot?
			ChunkPos chunkPos = new ChunkPos(bx >> 4, bz >> 4);
			if (!container.inBounds(chunkPos)) return null;
			LevelPlot plot = container.getPlot(chunkPos);
			if (plot == null) return null;
			ServerSubLevel subLevel = (plot.getSubLevel() instanceof ServerSubLevel ssl) ? ssl : null;
			if (subLevel == null || subLevel.isRemoved()) return null;

			Pose3dc pose = subLevel.logicalPose();
			BlockPos centerBlock = plot.getCenterBlock();
			if (pose == null || centerBlock == null) {
				// In a sub-level but untransformable - signal with NaNs so the caller still suppresses
				return new double[] { Double.NaN, Double.NaN, Double.NaN };
			}

			// transformPosition wants the ABSOLUTE storage coordinate - the pose already bakes in its own
			// rotation pivot, so don't pre-subtract centerBlock (that gave coordinates in the millions)
			Vector3d world = pose.transformPosition(new Vector3d(x, y, z), new Vector3d());

			if (!firstPlumeMapLogged) {
				firstPlumeMapLogged = true;
				BurntMod.LOGGER.info(
						"[Burnt][Sable] Plume re-homed: storage=({},{},{}) -> world=({}) (ship pose.position={})",
						bx, by, bz, fmtVec(world), fmtVec(pose.position()));
			}
			return new double[] { world.x, world.y, world.z };
		} catch (Throwable t) {
			BurntMod.LOGGER.debug("[Burnt] subLevelWorldPos suppressed: {}", t.toString());
		}
		return null;
	}

	private static String fmtVec(org.joml.Vector3dc v) {
		if (v == null) return "null";
		return String.format("%.2f, %.2f, %.2f", v.x(), v.y(), v.z());
	}

	/**
	 * Cheap "is this on a Sable creation?" test - the same getPlot(ChunkPos) detection, without the
	 * pose transform. Lets burn procedures erode ships while leaving world builds alone.
	 */
	static boolean isInSubLevel(ServerLevel level, double x, double y, double z) {
		try {
			SubLevelContainer container = SubLevelContainer.getContainer(level);
			if (container == null) return false;
			ChunkPos chunkPos = new ChunkPos(((int) Math.floor(x)) >> 4, ((int) Math.floor(z)) >> 4);
			if (!container.inBounds(chunkPos)) return false;
			LevelPlot plot = container.getPlot(chunkPos);
			return plot != null && plot.getSubLevel() instanceof ServerSubLevel ssl && !ssl.isRemoved();
		} catch (Throwable t) {
			return false;
		}
	}

	// Direct ground ignition for airship-to-world spread
	// Hung off the per-block plume hook, which does resolve a world position, rather than the
	// chunk scanner, which couldn't see sub-level blocks

	/** Per-tick chance of attempting a ground ignition. Low, because a burning ship has many blocks. */
	private static final double IGNITE_CHANCE = 0.04;
	/** How far below the hull block's world position to search for ground. */
	private static final int IGNITE_DROP_SCAN = 4;

	/**
	 * For a burning sub-level block, look down from its visible world position for the first burnable
	 * world block and ignite it, plus anything in the 3x3 column beneath. No-op outside sub-levels.
	 */
	static void igniteGroundUnder(ServerLevel level, double x, double y, double z) {
		try {
			if (RNG.nextDouble() > IGNITE_CHANCE) return;

			double[] wp = subLevelWorldPos(level, x, y, z);
			if (wp == null) return; // not in a sub-level - normal world handles itself
			if (Double.isNaN(wp[0]) || Double.isNaN(wp[1]) || Double.isNaN(wp[2])) return;

			int wx = (int) Math.floor(wp[0]);
			int wyTop = (int) Math.floor(wp[1]);
			int wz = (int) Math.floor(wp[2]);

			BlockPos.MutableBlockPos n = new BlockPos.MutableBlockPos();
			long now = level.getGameTime();
			boolean ignitedAny = false;

			// 3x3 column from the hull block downward - bridges a small air gap under a hovering ship
			for (int dx = -1; dx <= 1; dx++) {
				for (int dz = -1; dz <= 1; dz++) {
					for (int dy = 0; dy <= IGNITE_DROP_SCAN; dy++) {
						n.set(wx + dx, wyTop - dy, wz + dz);
						BlockState ground = level.getBlockState(n);
						if (ground.isAir()) continue;
						if (!isWorldBurnable(ground)) break; // hit solid non-burnable - stop this column
						BurnBabyProcedure.execute(level, n.getX(), n.getY(), n.getZ());
						ignitedAny = true;
						if (diagDue(now, lastHitLogTick)) {
							lastHitLogTick = now;
							BurntMod.LOGGER.info(
									"[Burnt][Sable] IGNITED ground: hull world ~({},{},{}) lit {} ({})",
									wx, wyTop, wz, n.immutable(),
									BuiltInRegistries.BLOCK.getKey(ground.getBlock()));
						}
						break; // one ignition per column per call
					}
				}
			}

			// Nothing ignited - dump the column below the hull so we can see what's actually there
			if (!ignitedAny && diagDue(now, lastNeighbourLogTick)) {
				lastNeighbourLogTick = now;
				StringBuilder sb = new StringBuilder();
				sb.append("[Burnt][Sable] ground scan under hull world (")
				  .append(wx).append(',').append(wyTop).append(',').append(wz).append("):");
				for (int dy = 0; dy <= IGNITE_DROP_SCAN; dy++) {
					n.set(wx, wyTop - dy, wz);
					BlockState g = level.getBlockState(n);
					sb.append(" y").append(wyTop - dy).append('=')
					  .append(BuiltInRegistries.BLOCK.getKey(g.getBlock()))
					  .append(isWorldBurnable(g) ? "[BURNABLE]" : "");
				}
				BurntMod.LOGGER.info(sb.toString());
			}
		} catch (Throwable t) {
			BurntMod.LOGGER.debug("[Burnt] igniteGroundUnder suppressed: {}", t.toString());
		}
	}

	/**
	 * The same burnability gate normal spread uses - vanilla will_burn, flesh, compat tags and the
	 * mapping registry, minus fire-resistant. The old code checked only isCompatBurnable, so ground
	 * spread onto ordinary terrain never fired.
	 */
	private static boolean isWorldBurnable(BlockState state) {
		if (state.is(BurntTags.BLOCK_FIRE_RESISTANT) || state.is(BurntTags.BLOCK_WILL_NOT_BURN)) return false;
		return state.is(BurntTags.BLOCK_WILL_BURN)
				|| state.is(BurntTags.BLOCK_FLESH)
				|| BurntTags.isCompatBurnable(state)
				|| net.pixelbank.burnt.compat.BurnMappingRegistry.get(state.getBlock()) != null;
	}

}
