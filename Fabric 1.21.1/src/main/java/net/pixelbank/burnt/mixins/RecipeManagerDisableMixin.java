package net.pixelbank.burnt.mixins;

import com.google.gson.JsonElement;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashMap;
import java.util.Map;

// Drops the flag and fire-extinguisher recipes when their config option is off
// Strips the burnt:* ids out of the recipe map before it's indexed, so they never load
// One class covers all six trees - the apply signature is the same on 1.20.1 and 1.21.x

@Mixin(RecipeManager.class)
public class RecipeManagerDisableMixin {

	@ModifyVariable(method = "apply", at = @At("HEAD"), argsOnly = true)
	private Map<ResourceLocation, JsonElement> burnt$filterDisabledRecipes(Map<ResourceLocation, JsonElement> in) {
		try {
			boolean noFlags = !BurntBasicConfigConfiguration.ENABLE_FLAGS.get();
			boolean noExtinguisher = !BurntBasicConfigConfiguration.ENABLE_FIRE_EXTINGUISHER.get();
			if (!noFlags && !noExtinguisher) return in; // nothing disabled - leave the map untouched

			Map<ResourceLocation, JsonElement> out = new HashMap<>(in);
			out.keySet().removeIf(id -> id.getNamespace().equals("burnt") && (
					(noFlags && id.getPath().endsWith("_flag_recipe"))
					|| (noExtinguisher && id.getPath().equals("extinguisher_recipe"))));
			return out;
		} catch (Throwable t) {
			// Config not ready, or any unexpected error - never break recipe loading over this.
			return in;
		}
	}
}
