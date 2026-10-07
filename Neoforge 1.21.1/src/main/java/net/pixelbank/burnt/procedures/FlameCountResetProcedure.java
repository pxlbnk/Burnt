package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.level.LevelAccessor;

import javax.annotation.Nullable;

public class FlameCountResetProcedure {
	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Object event, LevelAccessor world) {
		double count = 0;
		count = BurntModVariables.WorldVariables.get(world).flameCount;
		if (count < 0) {
			count = 0;
		}
		if (world.getLevelData().getGameTime() % 10 == 0) {
			if (IsRainingProcedure.isGloballyRaining(world)) {
				count = count - 50;
			} else {
				count = count - 25;
			}
		}
		if (count > 10000) {
			count = 10000;
		}
		BurntModVariables.WorldVariables.get(world).flameCount = count;
		BurntModVariables.WorldVariables.get(world).markSyncDirty();
	}
}