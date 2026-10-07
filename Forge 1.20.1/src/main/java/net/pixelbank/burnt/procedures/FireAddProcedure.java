package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.level.LevelAccessor;

public class FireAddProcedure {
	public static void execute(LevelAccessor world) {
		BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount + 1;
		BurntModVariables.WorldVariables.get(world).markSyncDirty();
	}
}