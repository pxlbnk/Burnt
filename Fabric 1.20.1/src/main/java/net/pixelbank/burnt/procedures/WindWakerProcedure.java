package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.compat.LivingWindBridge;
import net.pixelbank.burnt.compat.ImmersiveWindsBridge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;

public class WindWakerProcedure {

	public static void execute(LevelAccessor world) {
		if (BurntBasicConfigConfiguration.WIND.get() != true) return;
		if (world.isClientSide()) return;

		// Living Wind bridge - ranks above the rest, it is the most detailed of them
		if (LivingWindBridge.isActive()) {
			BurntModVariables.MapVariables mvLW = BurntModVariables.MapVariables.get(world);
			double newSpeed = LivingWindBridge.getBurntSpeed(world);
			double newAngle = LivingWindBridge.getBurntAngle(world);
			boolean changedLW = false;
			if (Math.abs(newSpeed - mvLW.windSpeed) > 0.1) { mvLW.windSpeed = newSpeed; changedLW = true; }
			if (Math.abs(newAngle - mvLW.windAngle) > 0.1) { mvLW.windAngle = newAngle; changedLW = true; }
			if (changedLW) mvLW.markSyncDirty();
			return;
		}

		// Immersive Winds bridge - if IW is loaded, use its wind instead of ours
		if (ImmersiveWindsBridge.isActive()) {
			BurntModVariables.MapVariables mv = BurntModVariables.MapVariables.get(world);
			double newSpeed = ImmersiveWindsBridge.getBurntSpeed();
			double newAngle = ImmersiveWindsBridge.getBurntAngle();
			boolean changed = false;
			if (Math.abs(newSpeed - mv.windSpeed) > 0.1) { mv.windSpeed = newSpeed; changed = true; }
			if (Math.abs(newAngle - mv.windAngle) > 0.1) { mv.windAngle = newAngle; changed = true; }
			if (changed) mv.markSyncDirty();
			return;
		}
		// 

		// Cache the single MapVariables object - avoids ~20+ hash-map lookups per call
		BurntModVariables.MapVariables mv = BurntModVariables.MapVariables.get(world);
		boolean changed = false;
		long gameTime = world.getLevelData().getGameTime();

		// A new world starts every wind field at zero, and the simulation only rolls a target on a
		// 10% chance every 150 ticks, so wind can stay dead for a minute or more. Grass spread is
		// gated on Math.random() <= windSpeed / 100, which never passes at zero, so grass lit in
		// those first minutes does not spread at all. Seed a believable wind on the first tick.
		// Also recovers a world that somehow saved with its wind at a standstill.
		if (!mv.windSpeedLock && mv.windSpeed <= 0 && mv.windBaseSpeed <= 0 && mv.windTargetSpeed <= 0) {
			// Same bands the simulation picks from, minus its 0-10 one, so the wind is never born dead
			double seedRoll = Math.random();
			int seeded = seedRoll < 0.05 ? Mth.nextInt(world.getRandom(), 31, 50)
					: seedRoll < 0.25 ? Mth.nextInt(world.getRandom(), 21, 30)
					: Mth.nextInt(world.getRandom(), 11, 20);
			mv.windTargetSpeed = seeded;
			mv.windBaseSpeed = seeded;
			mv.windSpeed = seeded;
			// Without this every world would start with a due-north wind
			if (!mv.windAngleLock && mv.windAngle == 0 && mv.windTargetAngle == 0) {
				double seededAngle = Mth.nextDouble(world.getRandom(), 0, 360);
				mv.windAngle = seededAngle;
				mv.windTargetAngle = seededAngle;
			}
			changed = true;
		}

		// Wind speed simulation
		if (!mv.windSpeedLock) {

			// Periodically randomise target speed (every 150 ticks, 25% chance)
			if (gameTime % 150 == 0 && Math.random() <= 0.25) {
				double r = Math.random();
				boolean targetChanged = false;
				int newTarget = 0;
				if (r < 0.01) {
					newTarget = Mth.nextInt(world.getRandom(), 31, 50);
					targetChanged = true;
				} else if (r < 0.1) {
					newTarget = Mth.nextInt(world.getRandom(), 21, 30);
					targetChanged = true;
				} else if (r < 0.3) {
					newTarget = Mth.nextInt(world.getRandom(), 11, 20);
					targetChanged = true;
				} else if (r < 0.4) {
					newTarget = Mth.nextInt(world.getRandom(), 0, 10);
					targetChanged = true;
				}
				if (targetChanged) {
					mv.windTargetSpeed = newTarget;
					changed = true;
				}
			}

			// Interpolate base speed toward target - only write when meaningfully changed
			double speed = mv.windBaseSpeed;
			double newSpeed = Math.max(0, Math.min(100, speed + (mv.windTargetSpeed - speed) * 0.05));
			if (Math.abs(newSpeed - speed) > 0.01) {
				mv.windBaseSpeed = newSpeed;
				changed = true;
			}

			// Gust logic - runs every 5 ticks
			if (gameTime % 5 == 0) {
				if (mv.gustTimer <= 0) {
					// Possibly start a new gust (every 20 ticks)
					if (gameTime % 20 == 0) {
						double gustStartChance = 0.05 + 0.2 * (mv.windBaseSpeed / 100.0);
						if (Math.random() < gustStartChance) {
							mv.gustTimer = Mth.nextInt(world.getRandom(), 40, 140);
							mv.gustTarget = Mth.nextDouble(world.getRandom(), 0.3, 1.0);
							mv.gustMaxAdd = Mth.nextInt(world.getRandom(), 10, 35);
							changed = true;
						}
					}
				} else {
					// Tick down gust timer
					mv.gustTimer = Math.max(0, mv.gustTimer - 5);
					changed = true;

					// Interpolate gust strength toward target
					double gs = mv.gustStrength;
					double newGs = Math.max(0, Math.min(1, gs + (mv.gustTarget - gs) * 0.2));
					if (Math.abs(newGs - gs) > 0.001) {
						mv.gustStrength = newGs;
						changed = true;
					}
				}

				// Fade gust strength back to zero when timer has expired
				if (mv.gustTimer <= 0) {
					if (mv.gustTarget != 0) {
						mv.gustTarget = 0;
						changed = true;
					}
					double gs = mv.gustStrength;
					double newGs = Math.max(0, Math.min(1, gs + (0 - gs) * 0.08));
					if (Math.abs(newGs - gs) > 0.001) {
						mv.gustStrength = newGs;
						changed = true;
					}
				}

				// Wind angle jitter during active gusts (every 10 ticks)
				if (mv.gustStrength > 0.2 && gameTime % 10 == 0) {
					double jitterMax = 2 + 10 * mv.gustStrength;
					mv.windAngle = (mv.windAngle + Mth.nextDouble(world.getRandom(), -jitterMax, jitterMax) + 360) % 360;
					changed = true;
				}
			}

			// Final wind speed = base speed + gust contribution
			double gustAdd = mv.gustStrength * mv.gustMaxAdd;
			double finalSpeed = Math.max(0, Math.min(100, mv.windBaseSpeed + gustAdd));
			if (Math.abs(finalSpeed - mv.windSpeed) > 0.01) {
				mv.windSpeed = finalSpeed;
				changed = true;
			}
		}

		// Wind angle simulation
		if (!mv.windAngleLock) {
			if (gameTime % 100 == 0) {
				double angle = mv.windAngle;
				double maxTurn = 1.2;
				double delta = (mv.windTargetAngle - angle) % 360 - 180;
				delta = Math.max(-maxTurn, Math.min(maxTurn, delta));
				double newAngle = (angle + delta + 360) % 360;
				if (Math.abs(newAngle - angle) > 0.001) {
					mv.windAngle = newAngle;
					changed = true;
				}
			}
		}

		// Single sync per tick - only fires when something actually changed
		if (changed) mv.markSyncDirty();
	}
}
