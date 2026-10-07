package net.pixelbank.burnt.item;

import net.pixelbank.burnt.procedures.SootPlaceProcedure;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

public class SootItem extends Item {
	public SootItem() {
		super(new Item.Properties().fireResistant());
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		SootPlaceProcedure.execute(
				context.getLevel(),
				context.getClickedPos().getX(),
				context.getClickedPos().getY(),
				context.getClickedPos().getZ(),
				context.getPlayer());
		return InteractionResult.SUCCESS;
	}
}
