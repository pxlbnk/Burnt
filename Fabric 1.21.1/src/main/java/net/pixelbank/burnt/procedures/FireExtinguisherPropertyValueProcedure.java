package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.entity.Entity;

public class FireExtinguisherPropertyValueProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		if (BurntModVariables.PlayerVariables.get(entity).extinguisherOn == true) {
			return 1;
		}
		return 0;
	}
}