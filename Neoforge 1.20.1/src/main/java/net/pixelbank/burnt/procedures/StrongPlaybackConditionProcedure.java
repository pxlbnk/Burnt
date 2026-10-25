package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.entity.FlagEntity;

import net.minecraft.world.entity.Entity;

public class StrongPlaybackConditionProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof FlagEntity _datEntI ? _datEntI.getEntityData().get(FlagEntity.DATA_windSpeed) : 0) >= 65 && (entity instanceof FlagEntity _datEntI ? _datEntI.getEntityData().get(FlagEntity.DATA_windSpeed) : 0) < 85) {
			return true;
		}
		return false;
	}
}