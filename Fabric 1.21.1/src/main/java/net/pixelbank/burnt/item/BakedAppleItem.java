package net.pixelbank.burnt.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class BakedAppleItem extends Item {
	public BakedAppleItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(5).saturationModifier(0.4f).build()));
	}
}