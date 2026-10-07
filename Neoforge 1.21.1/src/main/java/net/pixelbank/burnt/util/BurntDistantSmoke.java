package net.pixelbank.burnt.util;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.procedures.ParticleWindMultiplyerProcedure;
import net.pixelbank.burnt.procedures.WindAngleXProcedure;
import net.pixelbank.burnt.procedures.WindAngleZProcedure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;

/**
 * Long-distance smoke for burning blocks, force-sent from the server.
 * Vanilla culls non-forced particles past 32 blocks and animateTick only walks blocks near the
 * camera, so a distant fire is otherwise invisible. Rate-capped, because a spreading fire can
 * have thousands of burning blocks ticking at once.
 */
public final class BurntDistantSmoke {

	/** Players nearer than this already get the dense animateTick smoke - don't double up. */
	private static final double NEAR_CUTOFF = 28.0;

	/** Hard ceiling on force-sent emissions per server tick, across ALL fires. 4 x 2 = 8 particles/tick. */
	private static final int MAX_EMITS_PER_TICK = 4;

	/** Particles per emission. Sent count=0 so we control velocity (wind) exactly, 1 packet each. */
	private static final int PARTICLES_PER_EMIT = 2;

	/** Glowing embers riding up with the smoke. Deliberately sparse - each one is an extra packet. */
	private static final int EMBERS_PER_EMIT = 1;

	/** Flames at the base of the plume. A couple is plenty - they read as one fire from far off. */
	private static final int FLAMES_PER_EMIT = 1;

	/** Odds of an emission carrying a flame at all - thins them below one-per-emission. */
	private static final double FLAME_EMIT_CHANCE = 0.5;

	/** How far up to search for an air gap (canopy//overhang headroom). */
	private static final int LIFT_SEARCH = 8;

	private static long lastTick = -1L;
	private static int emittedThisTick = 0;

	/** @return true if smoke was actually sent (not throttled, and someone was in range). */
	public static boolean emit(ServerLevel level, double x, double y, double z) {
		double range = BurntBasicConfigConfiguration.SMOKE_PARTICLE_RANGE.get();
		if (range <= 0)
			return false;                                    // feature off
		if (!BurntBasicConfigConfiguration.SMOKE.get())
			return false;                                    // respect the smoke-particles toggle
		if (BurntDimensions.isDisabled(level))
			return false;

		// Honour smoke_density here too - past NEAR_CUTOFF every particle the player sees comes from this send
		// Rolled per emission rather than scaled per particle, which would floor to 0 and switch it off
		double _density = BurntSmokeBudget.densityFactor();
		if (_density <= 0.0)
			return false;
		if (_density < 1.0 && level.getRandom().nextDouble() >= _density)
			return false;

		// The budget has to gate these too, or a saturated scene throttled only the smoke nobody could see
		if (!BurntSmokeBudget.allowSpawn(level.getRandom()))
			return false;
		// Walk up to the first air gap - a burning leaf nearly always has more canopy directly above it
		int lift = -1;
		for (int i = 0; i < LIFT_SEARCH; i++) {
			if (level.getBlockState(BlockPos.containing(x, y + i, z)).is(BurntTags.BLOCK_AIR)) {
				lift = i;
				break;
			}
		}
		if (lift < 0)
			return false; // fully buried - nothing would be visible anyway
		// Keep flames at the pre-lift height, or they hang in mid-air above the fire
		double baseY = y;
		y += lift;

		// Per-tick throttle. Server thread only, so plain fields are fine.
		long now = level.getGameTime();
		if (now != lastTick) {
			lastTick = now;
			emittedThisTick = 0;
		}
		if (emittedThisTick >= MAX_EMITS_PER_TICK)
			return false;

		// Match the near-range drift so distant smoke leans with the same wind as smoke you stand next to.
		double vx = 0, vz = 0;
		if (BurntBasicConfigConfiguration.WIND.get()) {
			double mult = ParticleWindMultiplyerProcedure.execute(level, x, y, z);
			vx = (WindAngleXProcedure.execute(level) / 25.0) * mult;
			vz = (WindAngleZProcedure.execute(level) / 25.0) * mult;
		}

		double maxSq = range * range;
		double nearSq = NEAR_CUTOFF * NEAR_CUTOFF;
		boolean sent = false;
		for (ServerPlayer player : level.players()) {
			double d = player.distanceToSqr(x, y, z);
			if (d <= nearSq || d > maxSq)
				continue;
			for (int i = 0; i < PARTICLES_PER_EMIT; i++) {
				double px = x + Mth.nextDouble(level.getRandom(), -1.1, 1.1);
				double py = y + Mth.nextDouble(level.getRandom(), -0.3, 1.4);
				double pz = z + Mth.nextDouble(level.getRandom(), -1.1, 1.1);
				// count=0 makes the dist arguments velocity rather than spread, and force=true is required past 32 blocks
				level.sendParticles(player, (SimpleParticleType) BurntModParticleTypes.FLAME_SMOKE, true,
						px, py, pz, 0, vx, Mth.nextDouble(level.getRandom(), 0.02, 0.08), vz, 1.0);
			}
			for (int i = 0; i < EMBERS_PER_EMIT; i++) {
				// Embers climb faster than the smoke and start lower, so they read as rising out of the fire
				double ex = x + Mth.nextDouble(level.getRandom(), -0.5, 0.5);
				double ey = y + Mth.nextDouble(level.getRandom(), -0.8, 0.2);
				double ez = z + Mth.nextDouble(level.getRandom(), -0.5, 0.5);
				level.sendParticles(player, (SimpleParticleType) BurntModParticleTypes.FIRE_PARTICLE, true,
						ex, ey, ez, 0, vx, Mth.nextDouble(level.getRandom(), 0.25, 0.45), vz, 1.0);
			}
			// Below a count of 1 the only way to thin flames out is to skip emissions, so roll per player
			if (level.getRandom().nextDouble() < FLAME_EMIT_CHANCE) {
				for (int i = 0; i < FLAMES_PER_EMIT; i++) {
					// Keep flames inside the burning block, leaving room for their upward drift
					double fx = x + Mth.nextDouble(level.getRandom(), -0.3, 0.3);
					double fy = baseY + Mth.nextDouble(level.getRandom(), -1.15, -0.75);
					double fz = z + Mth.nextDouble(level.getRandom(), -0.3, 0.3);
					level.sendParticles(player, (SimpleParticleType) BurntModParticleTypes.FLAMES, true,
							fx, fy, fz, 0, 0.0, 0.0, 0.0, 1.0);
				}
			}
			sent = true;
		}
		if (sent)
			emittedThisTick++;
		return sent;
	}

	private BurntDistantSmoke() {}
}
