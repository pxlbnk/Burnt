package net.pixelbank.burnt.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class ExtinguisherSprayItem extends Item {
	public ExtinguisherSprayItem() {
		super(new Item.Properties());
	}

	@Override
	public float getDestroySpeed(ItemStack itemstack, BlockState state) {
		return 0f;
	}
}