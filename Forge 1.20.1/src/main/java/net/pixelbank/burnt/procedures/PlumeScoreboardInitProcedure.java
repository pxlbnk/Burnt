package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.LevelAccessor;

// Does nothing now - plume tracking moved to PlumeManager, kept so call sites compile
public class PlumeScoreboardInitProcedure {
	public static void execute(LevelAccessor world) {
		// No-op: scoreboard objectives replaced by PlumeManager (Java HashMap).
	}
}
