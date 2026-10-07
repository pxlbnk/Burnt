package net.pixelbank.burnt.compat;

import net.neoforged.fml.ModList;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

// Optional bridge to Create: The Factory Must Grow (modid "tfmg")
// Its extinguisher doesn't touch Burnt blocks, so while the player sprays we spawn Burnt's own
// SprayEntity from their aim, which carries the full extinguish behaviour
// Referenced by modid and item id only, so it no-ops without TFMG. NeoForge 1.21.1 only
public final class TFMGBridge {

	private static final boolean MOD_PRESENT = ModList.get().isLoaded("tfmg");
	private static final ResourceLocation FIRE_EXTINGUISHER =
			ResourceLocation.fromNamespaceAndPath("tfmg", "fire_extinguisher");

	public static boolean isActive() {
		return MOD_PRESENT;
	}

	// True if the stack is TFMG's fire extinguisher (matched by registry id, no class ref).
	public static boolean isFireExtinguisher(ItemStack stack) {
		if (!MOD_PRESENT || stack == null || stack.isEmpty())
			return false;
		return FIRE_EXTINGUISHER.equals(BuiltInRegistries.ITEM.getKey(stack.getItem()));
	}

	// True when the extinguisher still has dry-ice charge
	// TFMG keeps it in its own tfmg:AMOUNT component and bails at <= 0, so honour the same rule
	// Read by reflection; falls back to true if the component can't be resolved
	private static volatile boolean amountResolved = false;
	private static DataComponentType<Integer> AMOUNT;

	public static boolean hasCharge(ItemStack stack) {
		if (!MOD_PRESENT || stack == null || stack.isEmpty())
			return false;
		if (!amountResolved)
			resolveAmount();
		if (AMOUNT == null)
			return true;
		Integer amount = stack.getOrDefault(AMOUNT, Integer.valueOf(0));
		return amount != null && amount > 0;
	}

	@SuppressWarnings("unchecked")
	private static synchronized void resolveAmount() {
		if (amountResolved)
			return;
		amountResolved = true;
		try {
			Object type = Class.forName("com.drmangotea.tfmg.registry.TFMGDataComponents").getField("AMOUNT").get(null);
			if (type instanceof DataComponentType)
				AMOUNT = (DataComponentType<Integer>) type;
		} catch (Throwable ignored) {
			AMOUNT = null;
		}
	}

	private TFMGBridge() {}
}
