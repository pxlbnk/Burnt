package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlumeRiseTickProcedure {

	// Plumes despawn at this Y instead of rising forever, kept below the build ceiling
	private static final double MAX_PLUME_Y = 316.0;

	public static void execute(LevelAccessor world) {
		if (world.isClientSide()) return;
		if (!(world instanceof ServerLevel level)) return;
		if (!BurntBasicConfigConfiguration.DISTANT_PLUMES.get()) return;

		long gameTime = level.getLevelData().getGameTime();
		BurntModVariables.MapVariables mv = BurntModVariables.MapVariables.get(world);

		// Drift from wind angle and speed, in the original 8-direction, 3-speed buckets
		double dx = 0.0;
		double dz = 0.0;
		boolean hasWind = BurntBasicConfigConfiguration.WIND.get()
				&& mv.windSpeed > 5;

		if (hasWind) {
			double angleRad = Math.toRadians(
					Math.round(mv.windAngle * 10.0) / 10.0);
			double horizSpeed = mv.windSpeed > 40 ? 0.04
					: (mv.windSpeed > 20 ? 0.03 : 0.02);
			dx = -Math.round(Math.sin(angleRad)) * horizSpeed;
			dz =  Math.round(Math.cos(angleRad)) * horizSpeed;
		}

		// Snapshot the entity list first - setPos during iteration throws from the iterator
		// Far and distant tiers move on reduced tick rates
		List<Entity> snapshot = new ArrayList<>();
		try {
			level.getAllEntities().forEach(snapshot::add);
		} catch (Exception ex) {
			// Another mod churning entities off-thread can throw here - plumes are cosmetic, so skip the tick
			return;
		}
		for (Entity e : snapshot) {
			if (e == null || e.getType() != EntityType.ITEM_DISPLAY || e.isRemoved()) continue;

			Set<String> tags = e.getTags();
			if (!tags.contains("burnt_any_plume")) continue;

			// Despawn at the ceiling rather than climbing out of the world
			if (e.getY() >= MAX_PLUME_Y) {
				e.discard();
				net.pixelbank.burnt.util.PlumeManager.remove(e.getUUID());
				continue;
			}

			if (!hasWind) {
				// Tick rate by tier - near (31-50) every tick, far (51-70) every 2, distant (71-512) every 4
				if (tags.contains("far_burnt_plume") && gameTime % 2 != 0) continue;
				if (tags.contains("distant_burnt_plume") && gameTime % 4 != 0) continue;
			}

			e.setPos(e.getX() + dx, e.getY() + 0.03, e.getZ() + dz);
		}
	}
}
