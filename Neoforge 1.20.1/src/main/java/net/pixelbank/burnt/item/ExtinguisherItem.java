package net.pixelbank.burnt.item;

import net.pixelbank.burnt.procedures.ExtinguisherSprayingProcedure;
import net.pixelbank.burnt.procedures.ExtinguisherOnLogicProcedure;
import net.pixelbank.burnt.procedures.ExtinguisherOffLogicProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

public class ExtinguisherItem extends Item {
	public ExtinguisherItem() {
		super(new Item.Properties().durability(1000).fireResistant());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.NONE; // NOT BLOCK: UseAnim.BLOCK makes any item act as an unbreakable, durability-free, axe-proof shield (Player.isBlocking keys off the anim). The spray on/off still works via startUsingItem (independent of anim).
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 1000;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		entity.startUsingItem(hand);
		ExtinguisherOnLogicProcedure.execute(entity);
		return ar;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		ExtinguisherOffLogicProcedure.execute(entity);
		return retval;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			ExtinguisherSprayingProcedure.execute(world, entity, itemstack);
	}

	@Override
	public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {
		ExtinguisherOffLogicProcedure.execute(entity);
	}
}