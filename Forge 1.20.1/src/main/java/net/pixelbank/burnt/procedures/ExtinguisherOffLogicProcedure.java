package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModItems;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class ExtinguisherOffLogicProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BurntModItems.EXTINGUISHER) {
			{
				BurntModVariables.PlayerVariables _vars = BurntModVariables.PlayerVariables.get(entity);
				_vars.extinguisherOn = false;
				_vars.markSyncDirty();
			}
		}
	}
}