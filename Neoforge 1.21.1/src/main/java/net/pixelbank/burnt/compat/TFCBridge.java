package net.pixelbank.burnt.compat;

import net.neoforged.fml.ModList;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// Optional bridge to TerraFirmaCraft (modid "tfc")
// TFC drives the vanilla rain flag but replaces the per-biome precipitation model, so Burnt's
// "the biome must report RAIN" gate skipped TFC rain entirely and fire never went out
// Asks TFC's own Climate.getPrecipitation instead, by reflection, so it's a no-op without TFC
public class TFCBridge {

	private static final boolean MOD_PRESENT = ModList.get().isLoaded("tfc");

	private static volatile boolean resolved = false;
	private static Method getPrecipitation;  // Climate.getPrecipitation(Level, BlockPos) -> Biome.Precipitation
	private static Method warmEnoughToRain;  // Climate.warmEnoughToRain(Level, BlockPos) -> boolean (fallback)

	static {
		if (MOD_PRESENT) {
			BurntMod.LOGGER.info("[Burnt] TerraFirmaCraft detected - climate-aware rain extinguish enabled.");
		}
	}

	public static boolean isActive() {
		return MOD_PRESENT;
	}

	// True when TFC's climate says it's raining here, gated on the vanilla rain flag TFC drives
	public static boolean isRainingAt(LevelAccessor world, BlockPos pos) {
		if (!MOD_PRESENT) return false;
		if (!world.getLevelData().isRaining()) return false;
		if (!resolved) resolve();

		if (getPrecipitation != null) {
			try {
				Object p = getPrecipitation.invoke(null, world, pos);
				if (p instanceof Enum<?> e) return "RAIN".equals(e.name());
			} catch (Throwable ignored) {
			}
		}
		if (warmEnoughToRain != null) {
			try {
				Object w = warmEnoughToRain.invoke(null, world, pos);
				if (w instanceof Boolean b) return b; // note: temperature-only, doesn't exclude arid spots
			} catch (Throwable ignored) {
			}
		}
		return false; // couldn't ask TFC - leave the vanilla check to decide
	}

	private static synchronized void resolve() {
		if (resolved) return;
		resolved = true;
		try {
			Class<?> climate = Class.forName("net.dries007.tfc.util.climate.Climate");
			for (Method m : climate.getMethods()) {
				if (!Modifier.isStatic(m.getModifiers())) continue;
				Class<?>[] p = m.getParameterTypes();
				if (p.length != 2 || p[1] != BlockPos.class) continue;
				if (!p[0].isAssignableFrom(net.minecraft.server.level.ServerLevel.class)) continue;
				if (m.getName().equals("getPrecipitation") && getPrecipitation == null) getPrecipitation = m;
				else if (m.getName().equals("warmEnoughToRain") && m.getReturnType() == boolean.class && warmEnoughToRain == null) warmEnoughToRain = m;
			}
		} catch (Throwable ignored) {
			// TFC not present or API changed - stays a no-op.
		}
	}

	private TFCBridge() {}
}
