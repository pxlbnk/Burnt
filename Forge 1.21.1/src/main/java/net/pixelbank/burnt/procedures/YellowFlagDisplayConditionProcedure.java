package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.entity.FlagEntity;

import net.minecraft.world.entity.Entity;

public class YellowFlagDisplayConditionProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof FlagEntity _datEntS ? _datEntS.getEntityData().get(FlagEntity.DATA_color) : "").equals("yellow")) {
			return true;
		}
		return false;
	}
}