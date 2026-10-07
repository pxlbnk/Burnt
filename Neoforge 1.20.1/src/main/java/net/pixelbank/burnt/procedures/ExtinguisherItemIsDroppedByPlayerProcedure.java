package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.entity.Entity;

public class ExtinguisherItemIsDroppedByPlayerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			BurntModVariables.PlayerVariables _vars = BurntModVariables.PlayerVariables.get(entity);
			_vars.extinguisherOn = false;
			_vars.markSyncDirty();
		}
	}
}