package net.pixelbank.burnt.util;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.List;

// Per-dimension kill switch
// A dimension is exempt when modded dimensions are off and it isn't vanilla, or it's in disabled_dimensions
// Cheap in the common case - one boolean read and an isEmpty

public final class BurntDimensions {

	public static boolean isDisabled(LevelAccessor world) {
		if (world == null)
			return false;
		if (!(world instanceof Level lvl))
			return false;
		ResourceKey<Level> dim = lvl.dimension();
		// Blanket toggle: when "burn in modded dimensions" is off, every non-vanilla dimension is exempt.
		if (!BurntBasicConfigConfiguration.BURN_MODDED_DIMENSIONS.get() && !isVanilla(dim))
			return true;
		// Explicit per-dimension exemption list.
		List<String> list = BurntBasicConfigConfiguration.DISABLED_DIMENSIONS.get();
		if (list != null && !list.isEmpty() && list.contains(dim.location().toString()))
			return true;
		return false;
	}

	private static boolean isVanilla(ResourceKey<Level> dim) {
		return dim.equals(Level.OVERWORLD) || dim.equals(Level.NETHER) || dim.equals(Level.END);
	}

	private BurntDimensions() {}
}
