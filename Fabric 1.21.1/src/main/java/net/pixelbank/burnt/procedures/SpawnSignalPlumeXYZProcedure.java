package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.util.PlumeManager;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelAccessor;

// Spawns distant smoke plume item_display entities so far-off fires stay visible
// Built straight from NBT rather than /summon - the command event could deadlock balm

public class SpawnSignalPlumeXYZProcedure {

	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) return;
		if (!(world instanceof ServerLevel level)) return;
		if (!BurntBasicConfigConfiguration.BURNT_ON.get()) return;

		// Vanilla culls smoke past ~32 blocks, so send a sparse plume to players further out
		// Hard-capped in the util - this runs from every burning block's tick
		net.pixelbank.burnt.util.BurntDistantSmoke.emit(level, x + 0.5, y + 1.5, z + 0.5);

		// Jitter the plume spawn here, after the smoke - callers used to offset it and flames hung too high
		x += net.minecraft.util.Mth.nextInt(level.getRandom(), 0, 3);
		y += net.minecraft.util.Mth.nextInt(level.getRandom(), 0, 3);
		z += net.minecraft.util.Mth.nextInt(level.getRandom(), 0, 3);

		// Plumes are gated separately from the long-range smoke, everything below is plume-only
		if (!BurntBasicConfigConfiguration.DISTANT_PLUMES.get()) return;

		// Refresh the nearest plume within 3 blocks instead of stacking a new one
		Entity nearest = null;
		double nearestDistSq = 9.0; // 3^2
		for (Entity e : level.getAllEntities()) {
			if (e.getType() == EntityType.ITEM_DISPLAY
					&& e.getTags().contains("burnt_any_plume")) {
				double d = e.distanceToSqr(x, y, z);
				if (d < nearestDistSq) {
					nearestDistSq = d;
					nearest = e;
				}
			}
		}
		if (nearest != null) {
			PlumeManager.resetLife(nearest.getUUID());
		}

		if (!world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) return;

		// Bail unless a non-spectator player is within 15 blocks
		if (anyPlayerWithin(level, x, y, z, 15.0)) return;

		// Cap is enforced in EnforcePlumeCapProcedureProcedure, not here

		// Pre-compute the tier gates so we don't walk the entity list per roll
		// Tiers start at 75% of smoke_particle_range so plumes take over where smoke stops
		double particleRange = BurntBasicConfigConfiguration.SMOKE_PARTICLE_RANGE.get();
		double plumeFloor = particleRange > 0 ? particleRange * 0.75 : 0.0;
		boolean inBandDistant = anyPlayerInBand(level, x, y, z, Math.max(71.0, plumeFloor), 512.0);
		boolean inBandFar     = anyPlayerInBand(level, x, y, z, Math.max(51.0, plumeFloor),  70.0);
		boolean inBandNear    = anyPlayerInBand(level, x, y, z, Math.max(31.0, plumeFloor),  50.0);
		if (!inBandDistant && !inBandFar && !inBandNear) return;

		// One pass for existing plumes that would block a spawn, past 20 blocks is irrelevant
		boolean distantPlumeNear20 = false;
		boolean plumeNear20        = false;
		boolean plumeNear10        = false;
		for (Entity e : level.getAllEntities()) {
			if (e.getType() != EntityType.ITEM_DISPLAY) continue;
			double d = e.distanceToSqr(x, y, z);
			if (d > 400.0) continue; // 20^2
			if (e.getTags().contains("distant_burnt_plume")) distantPlumeNear20 = true;
			if (e.getTags().contains("burnt_plume")) {
				plumeNear20 = true;
				if (d <= 100.0) plumeNear10 = true; // 10^2
			}
		}

		// Roll 1 - distant tier, blocked by an existing distant_burnt_plume within 20.
		if (inBandDistant && !distantPlumeNear20 && Math.random() <= 0.004) {
			spawnPlume(level, x, y, z, "burnt:tall_smoke_sprite", "distant_burnt_plume", 16.0f);
		}
		// Roll 2 - distant tier, blocked by an existing burnt_plume within 20.
		if (inBandDistant && !plumeNear20 && Math.random() <= 0.004) {
			spawnPlume(level, x, y, z, "burnt:tall_smoke_sprite_2", "distant_burnt_plume", 8.0f);
		}
		// Rolls 3-5 - far tier, blocked by an existing burnt_plume within 10.
		if (inBandFar && !plumeNear10) {
			if (Math.random() <= 0.01) {
				spawnPlume(level, x, y, z, "burnt:tall_smoke_sprite_2", "far_burnt_plume", 8.0f);
			}
			if (Math.random() <= 0.02) {
				spawnPlume(level, x, y, z, "burnt:tall_smoke_sprite",   "far_burnt_plume", 12.0f);
			}
			if (Math.random() <= 0.02) {
				spawnPlume(level, x, y, z, "burnt:smoke_sprite",        "far_burnt_plume", 6.0f);
			}
		}
		// Rolls 6-8 - near tier, also blocked by an existing burnt_plume within 10.
		if (inBandNear && !plumeNear10) {
			if (Math.random() <= 0.2) {
				spawnPlume(level, x, y, z, "burnt:distant_smoke_sprite", "burnt_plume", 4.0f);
			}
			if (Math.random() <= 0.2) {
				spawnPlume(level, x, y, z, "burnt:tall_smoke_sprite",    "burnt_plume", 8.0f);
			}
			if (Math.random() <= 0.2) {
				spawnPlume(level, x, y, z, "burnt:smoke_sprite",         "burnt_plume", 4.0f);
			}
		}
	}

	// Player-distance helpers (replace @a[gamemode=!spectator,distance=...]).

	private static boolean anyPlayerWithin(ServerLevel level, double x, double y, double z, double radius) {
		double r2 = radius * radius;
		for (ServerPlayer p : level.players()) {
			if (p.isSpectator()) continue;
			if (p.distanceToSqr(x, y, z) <= r2) return true;
		}
		return false;
	}

	private static boolean anyPlayerInBand(ServerLevel level, double x, double y, double z, double min, double max) {
		double minSq = min * min;
		double maxSq = max * max;
		for (ServerPlayer p : level.players()) {
			if (p.isSpectator()) continue;
			double d = p.distanceToSqr(x, y, z);
			if (d >= minSq && d <= maxSq) return true;
		}
		return false;
	}

	// Builds the old /summon item_display NBT directly, skipping the command path that deadlocked balm

	private static void spawnPlume(ServerLevel level, double x, double y, double z,
	                               String itemId, String primaryTag, float xyScale) {
		CompoundTag nbt = new CompoundTag();
		nbt.putString("id", "minecraft:item_display");

		// Spawn one block up, using the block corner like the old "~ ~1 ~"
		// Sub-block jitter stops sprites at identical positions flickering at range
		double jx = (level.getRandom().nextDouble() - 0.5) * 0.8;
		double jz = (level.getRandom().nextDouble() - 0.5) * 0.8;
		double jy = (level.getRandom().nextDouble() - 0.5) * 0.4;
		ListTag pos = new ListTag();
		pos.add(DoubleTag.valueOf(x + jx));
		pos.add(DoubleTag.valueOf(y + 1 + jy));
		pos.add(DoubleTag.valueOf(z + jz));
		nbt.put("Pos", pos);

		ListTag motion = new ListTag();
		motion.add(DoubleTag.valueOf(0));
		motion.add(DoubleTag.valueOf(0));
		motion.add(DoubleTag.valueOf(0));
		nbt.put("Motion", motion);

		ListTag rotation = new ListTag();
		rotation.add(FloatTag.valueOf(0));
		rotation.add(FloatTag.valueOf(0));
		nbt.put("Rotation", rotation);

		ListTag tagList = new ListTag();
		tagList.add(StringTag.valueOf(primaryTag));
		tagList.add(StringTag.valueOf("burnt_any_plume"));
		nbt.put("Tags", tagList);

		CompoundTag item = new CompoundTag();
		item.putString("id", itemId);
		item.putInt("count", 1);
		nbt.put("item", item);

		nbt.putString("billboard", "center");
		nbt.putString("item_display_transform", "none");
		nbt.putInt("interpolation_duration", 10);

		// Identity rotation, caller's XY scale, paper-thin 0.05 z
		CompoundTag transformation = new CompoundTag();
		transformation.put("left_rotation",  identityQuat());
		transformation.put("right_rotation", identityQuat());
		transformation.put("translation",    vec3f(0f, 0f, 0f));
		transformation.put("scale",          vec3f(xyScale, xyScale, 0.05f));
		nbt.put("transformation", transformation);

		EntityType.create(nbt, level).ifPresent(level::addFreshEntity);
	}

	private static ListTag identityQuat() {
		ListTag t = new ListTag();
		t.add(FloatTag.valueOf(0f));
		t.add(FloatTag.valueOf(0f));
		t.add(FloatTag.valueOf(0f));
		t.add(FloatTag.valueOf(1f));
		return t;
	}

	private static ListTag vec3f(float a, float b, float c) {
		ListTag t = new ListTag();
		t.add(FloatTag.valueOf(a));
		t.add(FloatTag.valueOf(b));
		t.add(FloatTag.valueOf(c));
		return t;
	}
}
