package net.pixelbank.burnt.util;

import net.neoforged.fml.ModList;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;

// What a burnt block drops when a player breaks it
// 25% of the time it crumbles: charcoal instead of the block, plus ash with Supplementaries
// Done in getDrops so the roll can replace the block, and /loot and loot modifiers see it
// Player-gated, so explosions and machine breaks are unaffected

public final class BurntAshDrops {

	/** Odds of a break crumbling the block to charcoal instead of dropping it. */
	private static final float CRUMBLE_CHANCE = 0.25f;

	public static void applyBurntDrops(List<ItemStack> drops, LootParams.Builder builder) {
		try {
			if (!(builder.getOptionalParameter(LootContextParams.THIS_ENTITY) instanceof Player)) return;
			RandomSource random = builder.getLevel().getRandom();
			if (random.nextFloat() > CRUMBLE_CHANCE) return;

			// Crumbled: the block itself is gone, so clear its drops before adding what's left of it.
			drops.clear();
			drops.add(new ItemStack(Items.CHARCOAL));

			if (!ModList.get().isLoaded("supplementaries")) return;
			Item ash = BuiltInRegistries.ITEM.get(ResourceLocation.parse("supplementaries:ash"));
			if (ash == Items.AIR) return;
			int count = Mth.nextInt(random, 0, 2);
			if (count > 0) drops.add(new ItemStack(ash, count));
		} catch (Throwable ignored) {
		}
	}

	private BurntAshDrops() {}
}
