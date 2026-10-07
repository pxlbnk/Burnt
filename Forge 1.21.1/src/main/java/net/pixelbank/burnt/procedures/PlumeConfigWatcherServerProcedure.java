package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.util.PlumeManager;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

// Watches the plume config toggles and reacts within a tick
// Despawns every plume as soon as distant smoke, or the mod itself, is switched off
// Discards entities directly rather than running /kill, which could deadlock other mods

public class PlumeConfigWatcherServerProcedure {
	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Object event, LevelAccessor world) {
		if (world.isClientSide()) return;
		if (!(world instanceof ServerLevel level)) return;

		boolean enabled = BurntBasicConfigConfiguration.DISTANT_PLUMES.get()
				&& BurntBasicConfigConfiguration.BURNT_ON.get();
		BurntModVariables.MapVariables vars = BurntModVariables.MapVariables.get(world);

		// Enabled -> disabled this tick: clear every plume now.
		if (vars.prevDistantSmoke && !enabled) {
			for (Entity e : level.getAllEntities()) {
				if (e != null && e.getType() == EntityType.ITEM_DISPLAY
						&& e.getTags().contains("burnt_any_plume")) {
					e.discard();
					PlumeManager.remove(e.getUUID());
				}
			}
		}

		// Track the combined state (field is reused as "plumes were enabled last tick").
		if (vars.prevDistantSmoke != enabled) {
			vars.prevDistantSmoke = enabled;
			vars.markSyncDirty();
		}
	}
}
