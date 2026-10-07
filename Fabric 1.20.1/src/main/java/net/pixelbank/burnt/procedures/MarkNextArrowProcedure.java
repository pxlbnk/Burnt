package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;

import javax.annotation.Nullable;

import net.minecraft.core.registries.Registries;

public class MarkNextArrowProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		execute(null, world, x, y, z, itemstack);
	}

	private static void execute(@Nullable Object event, LevelAccessor world, double x, double y, double z, ItemStack itemstack) {
		ItemStack used = ItemStack.EMPTY;
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				used = itemstack.copy();
				if (used.is(BurntTags.ITEM_BOWS)) {
					if (BurntBasicConfigConfiguration.FLAME_BOW.get() == true) {
						if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemstack) > 0) {
							BurntMod.queueServerWork(40, () -> {
								SetArrowFireProcedure.execute(world, x, y, z);
							});
						}
					}
				}
			}
		}
	}
}