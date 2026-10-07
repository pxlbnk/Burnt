package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.network.FireSkyPayload;
import net.pixelbank.burnt.network.SmokeFogPayload;
import net.pixelbank.burnt.util.PlumeManager;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlumeAgeCleanupProcedure {

	private static final TagKey<Block> FIRE_TAG =
		TagKey.create(Registries.BLOCK, new ResourceLocation("burnt", "fire"));

	// Per-player fire intensity, smoothed to stop flicker
	private static final Map<UUID, Float> smoothedHaze = new HashMap<>();
	private static final Map<UUID, Float> smoothedFog  = new HashMap<>();
	private static final float EMA_WEIGHT = 0.85f;

	// Per-dimension backoff for the disabled sweep - calls left before the next one
	// Must be per dimension: execute runs once per level, so one flag would mask another
	private static final Map<String, Integer> disabledSweepDelay = new HashMap<>();

	// Cleanup calls between sweeps once a dimension has come back clean (~50s)
	private static final int IDLE_SWEEP_INTERVAL = 40;

	public static void execute(LevelAccessor world) {
		if (world.isClientSide()) return;
		if (!(world instanceof ServerLevel level)) return;
		if (level.getLevelData().getGameTime() % 25 != 0) return;

		boolean modOn = BurntBasicConfigConfiguration.BURNT_ON.get();
		if (!BurntBasicConfigConfiguration.DISTANT_PLUMES.get() || !modOn) {
			// Plumes persist in the save, so re-check occasionally rather than sweeping forever
			String dim = level.dimension().location().toString();
			int delay = disabledSweepDelay.getOrDefault(dim, 0);
			if (delay > 0) {
				disabledSweepDelay.put(dim, delay - 1);
			} else {
				int found = 0;
				for (Entity e : level.getAllEntities()) {
					if (e == null) continue;
					if (e.getType() == EntityType.ITEM_DISPLAY
							&& e.getTags().contains("burnt_any_plume")) {
						e.discard();
						PlumeManager.remove(e.getUUID());
						found++;
					}
				}
				disabledSweepDelay.put(dim, found == 0 ? IDLE_SWEEP_INTERVAL : 0);
			}
			// Fog and haze scan for fire blocks, not plumes - turning plumes off used to kill them too
			if (modOn) updateFogAndHaze(level);
			return;
		}

		// Plumes are enabled again - clear the backoff so the next disable sweeps immediately.
		disabledSweepDelay.remove(level.dimension().location().toString());

		// Collect every living plume entity and its UUID
		List<Entity> plumes = new ArrayList<>();
		Set<UUID> living = new HashSet<>();
		for (Entity e : level.getAllEntities()) {
			if (e == null) continue;
			if (e.getType() == EntityType.ITEM_DISPLAY
					&& !e.isRemoved()
					&& e.getTags().contains("burnt_any_plume")) {
				plumes.add(e);
				living.add(e.getUUID());
			}
		}

		// Tick the age and life counters - 160 matches the original scoreboard kill
		List<UUID> toKill = PlumeManager.tick(living, 160);

		if (!toKill.isEmpty()) {
			Set<UUID> killSet = new HashSet<>(toKill);
			for (Entity e : plumes) {
				if (killSet.contains(e.getUUID())) {
					e.discard();
					PlumeManager.remove(e.getUUID());
				}
			}
		}

		EnforcePlumeCapProcedureProcedure.execute(world);

		updateFogAndHaze(level);
	}

	// Heat haze and smoke fog - scans for fire blocks near players, independent of plumes
	private static void updateFogAndHaze(ServerLevel level) {
		boolean doHaze = BurntBasicConfigConfiguration.HEAT_HAZE.get();
		boolean doFog  = BurntBasicConfigConfiguration.SMOKE_FOG.get();
		if (doHaze || doFog) {
			final int   fireRadius     = 16;
			final float hazeThreshold  = 18f; // heat haze: full intensity at 18 fire blocks
			final float fogThreshold   =  9f; // smoke fog: full intensity at 9 fire blocks
			// Scan up to the larger threshold; both effects read from the same count
			final float scanLimit = Math.max(hazeThreshold, fogThreshold);
			for (ServerPlayer player : level.players()) {
				BlockPos origin = player.blockPosition();
				int fireyBlocks = 0;
				BlockPos.MutableBlockPos scan = new BlockPos.MutableBlockPos();
				outer:
				for (int dx = -fireRadius; dx <= fireRadius; dx++) {
					for (int dz = -fireRadius; dz <= fireRadius; dz++) {
						int wx = origin.getX() + dx;
						int wz = origin.getZ() + dz;
						// Skip unloaded chunks - getBlockState there forces a sync load and stalls the tick
						if (!level.hasChunkAt(wx, wz)) continue;
						for (int dy = -fireRadius; dy <= fireRadius; dy++) {
							if (level.getBlockState(scan.set(wx, origin.getY() + dy, wz)).is(FIRE_TAG)) {
								if (++fireyBlocks >= scanLimit) break outer;
							}
						}
					}
				}
				UUID pid = player.getUUID();
				float rawHaze = Math.min(fireyBlocks / hazeThreshold, 1.0f);
				float rawFog  = Math.min(fireyBlocks / fogThreshold,  1.0f);

				// EMA: blend new raw value into the smoothed value to avoid flicker
				float sHaze = smoothedHaze.getOrDefault(pid, rawHaze);
				float sFog  = smoothedFog.getOrDefault(pid, rawFog);
				sHaze = sHaze + EMA_WEIGHT * (rawHaze - sHaze);
				sFog  = sFog  + EMA_WEIGHT * (rawFog  - sFog);
				smoothedHaze.put(pid, sHaze);
				smoothedFog.put(pid, sFog);

				if (doHaze) { FriendlyByteBuf _hb = PacketByteBufs.create(); FireSkyPayload.write(_hb, new FireSkyPayload(sHaze)); ServerPlayNetworking.send(player, FireSkyPayload.ID, _hb); }
				if (doFog)  { FriendlyByteBuf _fb = PacketByteBufs.create(); SmokeFogPayload.write(_fb, new SmokeFogPayload(sFog));  ServerPlayNetworking.send(player, SmokeFogPayload.ID, _fb); }
			}
		}
	}
}
