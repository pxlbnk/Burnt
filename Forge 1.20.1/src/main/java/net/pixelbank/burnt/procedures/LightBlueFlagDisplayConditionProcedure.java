package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.entity.FlagEntity;

import net.minecraft.world.entity.Entity;

public class LightBlueFlagDisplayConditionProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof FlagEntity _datEntS ? _datEntS.getEntityData().get(FlagEntity.DATA_color) : "").equals("light_blue")) {
			return true;
		}
		return false;
	}
}