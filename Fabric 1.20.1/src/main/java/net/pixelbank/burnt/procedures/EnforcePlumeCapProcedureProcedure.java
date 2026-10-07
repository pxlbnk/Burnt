package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.util.PlumeManager;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;

public class EnforcePlumeCapProcedureProcedure {
	public static void execute(LevelAccessor world) {
		if (world.isClientSide()) return;
		if (!(world instanceof ServerLevel level)) return;

		final int cap = BurntBasicConfigConfiguration.PLUME_CAP.get();
		if (cap <= 0) return;

		// Collect all plume display entities
		ArrayList<Entity> plumes = new ArrayList<>();
		for (Entity e : level.getAllEntities()) {
			if (e.getType() == EntityType.ITEM_DISPLAY
					&& e.getTags().contains("burnt_any_plume")) {
				plumes.add(e);
			}
		}

		final int count = plumes.size();
		if (count <= cap) return;

		// Sort oldest first (highest age) using PlumeManager - no scoreboard needed
		plumes.sort((a, b) -> Integer.compare(
				PlumeManager.getAge(b.getUUID()),
				PlumeManager.getAge(a.getUUID())));

		// Discard the oldest extras
		for (int i = 0; i < (count - cap); i++) {
			Entity e = plumes.get(i);
			e.discard();
			PlumeManager.remove(e.getUUID());
		}
	}
}
