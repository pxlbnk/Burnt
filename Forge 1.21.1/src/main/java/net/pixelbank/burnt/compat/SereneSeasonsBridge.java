package net.pixelbank.burnt.compat;

import net.minecraftforge.fml.ModList;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;

/**
 * Optional bridge to Serene Seasons (modid "sereneseasons").
 *
 * Summer is hot and dry so fire rages, winter cold and damp so it struggles, autumn gets a
 * secondary peak from dead leaves. The modifier is an additive offset on Burnt's 0-100 spread
 * scale, so biome modifiers still apply on top.
 *
 * Safe without Serene Seasons installed - the isActive() guard keeps its classes untouched.
 */
public class SereneSeasonsBridge {

    private static final boolean MOD_PRESENT = ModList.get().isLoaded("sereneseasons");

    static {
        if (MOD_PRESENT) {
            BurntMod.LOGGER.info("[Burnt] Serene Seasons detected - seasonal fire spread enabled.");
        }
    }

    /** Returns true if Serene Seasons is installed and available at runtime. */
    public static boolean isActive() {
        return MOD_PRESENT;
    }

    /**
     * Additive spread offset for the current season, on Burnt's 0-100 scale. Positive is drier and
     * faster, negative wetter and slower. Peaks at +25 in late summer and -25 in mid wet season;
     * see the switch below for every value. Returns 0 without Serene Seasons, or on any error.
     */
    public static double getSpreadModifier(LevelAccessor world, double x, double y, double z) {
        try {
            if (!(world instanceof Level level)) return 0.0;

            sereneseasons.api.season.ISeasonState seasonState =
                    sereneseasons.api.season.SeasonHelper.getSeasonState(level);
            if (seasonState == null) return 0.0;

            // Check if this biome uses tropical seasons (wet/dry cycle instead of 4-season)
            Holder<Biome> biome = world.getBiome(BlockPos.containing(x, y, z));
            if (sereneseasons.api.season.SeasonHelper.usesTropicalSeasons(biome)) {
                return tropicalModifier(seasonState.getTropicalSeason());
            } else {
                return standardModifier(seasonState.getSubSeason());
            }
        } catch (Exception e) {
            BurntMod.LOGGER.error("[Burnt] SereneSeasonsBridge.getSpreadModifier() failed: {}", e.getMessage());
            return 0.0;
        }
    }

    private static double standardModifier(sereneseasons.api.season.Season.SubSeason subSeason) {
        if (subSeason == null) return 0.0;
        return switch (subSeason) {
            case EARLY_SPRING ->  -5.0;
            case MID_SPRING   -> -15.0;
            case LATE_SPRING  ->  -5.0;
            case EARLY_SUMMER -> +10.0;
            case MID_SUMMER   -> +20.0;
            case LATE_SUMMER  -> +25.0;
            case EARLY_AUTUMN -> +15.0;
            case MID_AUTUMN   ->  +5.0;
            case LATE_AUTUMN  ->  -5.0;
            case EARLY_WINTER -> -15.0;
            case MID_WINTER   -> -20.0;
            case LATE_WINTER  -> -15.0;
        };
    }

    private static double tropicalModifier(sereneseasons.api.season.Season.TropicalSeason tropicalSeason) {
        if (tropicalSeason == null) return 0.0;
        return switch (tropicalSeason) {
            case EARLY_DRY -> +15.0;
            case MID_DRY   -> +25.0;
            case LATE_DRY  -> +15.0;
            case EARLY_WET -> -10.0;
            case MID_WET   -> -25.0;
            case LATE_WET  -> -15.0;
        };
    }
}
