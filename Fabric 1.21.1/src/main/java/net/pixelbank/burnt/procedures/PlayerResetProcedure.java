package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class PlayerResetProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Object event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			{
				BurntModVariables.PlayerVariables _vars = BurntModVariables.PlayerVariables.get(entity);
				_vars.extinguisherOn = false;
				_vars.markSyncDirty();
			}
		}
	}
}