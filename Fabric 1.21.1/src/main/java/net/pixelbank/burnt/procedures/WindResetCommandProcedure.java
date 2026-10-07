package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.level.LevelAccessor;

public class WindResetCommandProcedure {
	public static void execute(LevelAccessor world) {
		BurntModVariables.MapVariables.get(world).windAngleLock = false;
		BurntModVariables.MapVariables.get(world).windSpeedLock = false;
		BurntModVariables.MapVariables.get(world).markSyncDirty();
	}
}