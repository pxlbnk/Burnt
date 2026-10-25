package net.pixelbank.burnt.configuration;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import java.util.function.Supplier;
import java.util.List;
import java.util.ArrayList;

@Config(name = "burnt_basic")
public class BurntBasicConfigConfiguration implements ConfigData {

	// Global Options (default category - always rendered as first tab)

	@ConfigEntry.Gui.Tooltip
	public boolean burnt_on = true;

	@ConfigEntry.Gui.Tooltip
	public List<String> disabled_dimensions = new ArrayList<>();

	@ConfigEntry.Gui.Tooltip
	public boolean burn_in_modded_dimensions = true;

	@ConfigEntry.Gui.Tooltip
	public boolean rain_extinguish = true;

	@ConfigEntry.Gui.Tooltip
	public boolean vanilla_burn = true;

	@ConfigEntry.Gui.Tooltip
	public boolean lava_burn = false;

	@ConfigEntry.Gui.Tooltip
	public boolean lightning_ignites = false;

	@ConfigEntry.Gui.Tooltip
	public boolean biome_specific = true;

	@ConfigEntry.Gui.Tooltip
	public boolean wind = true;

	/** 0-10: how much wind affects fire spread */
	@ConfigEntry.Gui.Tooltip
	public double wind_influence = 6.0;

	/** Player-created iron golems will seek out and extinguish nearby fire blocks. */ // IRON GOLEM FIRE FIGHTING
	@ConfigEntry.Gui.Tooltip
	public boolean iron_golem_fight_fires = true; // IRON GOLEM FIRE FIGHTING

	/** Flame arrows that hit a creeper will ignite its fuse, causing it to swell and explode. */ // FLAME ARROW CREEPER
	public boolean flame_arrows_ignite_creepers = true; // FLAME ARROW CREEPER

	// When off, the flags are removed from the game. Recipe change needs a world reload
	@ConfigEntry.Gui.Tooltip
	public boolean enable_flags = true;

	// How hard flags react to wind. 5 is the tuned default; the scale is deliberately steep at
	// the top so 10 still looks like a gale with a wind mod that reports modest strengths
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(min = 0, max = 10)
	public int flag_sensitivity = 5;

	// When off, the fire extinguisher is removed from the game. Recipe change needs a world reload
	@ConfigEntry.Gui.Tooltip
	public boolean enable_fire_extinguisher = true;

	// Burn Settings

	/** 0-100: how quickly the burn spreads from one block to another */
	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public double burn_spread = 50.0;

	/** 0-100: structural wood (logs, planks, stairs, slabs, fences, gates) spreads at this % of burn_spread */
	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public double wood_spread = 30.0;

	/** 0-100: dark oak is dense, so it burns and spreads at this % of the normal wood rate */
	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public double dark_oak_spread = 50.0;

	/** 0-100: chance of fire spreading DOWNWARD into planks, slabs, stairs, fences and gates, as a % of normal */
	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public double wood_downward_spread = 15.0;

	/** Let Burnt fire burn inside chunks claimed by a claim mod. Off means claimed land is left alone. */
	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public boolean burn_in_claimed_chunks = false;

	/** % chance of fire spreading across grass/moss */
	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public double ground_spread = 50.0;

	/** 0-100: how quickly a smoldering block becomes burnt */
	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public double burn_speed = 25.0;

	// 0-100: chance per tick that a lit fire burns itself out. 0 = never self-extinguishes
	// Fire ticks every tick, so 25 meant an average life of only ~0.2s
	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public double fire_burnout_chance = 2.0;

	// Blocks: how far a fire's long-range smoke carries. 0 = off, max useful 512
	// Distant plumes are the separate distant_plumes toggle. This is also the performance lever
	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	public double smoke_particle_range = 96.0;

	/** Distance from a player the burn remains active */
	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public double player_distance = 256.0;

	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public boolean ember_spread = false;

	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public boolean flame_bow = true;

	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public boolean fire_aspect = false;

	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public boolean burn_damage = true;

	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public boolean random_collapses = true;

	/** % chance of collapse over time */
	@ConfigEntry.Category("burnsettings")
	@ConfigEntry.Gui.Tooltip
	public double collapse_chance = 20.0;

	// Block Settings

	@ConfigEntry.Category("blocksettings")
	@ConfigEntry.Gui.Tooltip
	public boolean crafted_blocks_burn = true;

	@ConfigEntry.Category("blocksettings")
	@ConfigEntry.Gui.Tooltip
	public boolean burn_barrels = true;

	@ConfigEntry.Category("blocksettings")
	@ConfigEntry.Gui.Tooltip
	public boolean wool_burn = true;

	@ConfigEntry.Category("blocksettings")
	@ConfigEntry.Gui.Tooltip
	public boolean soot = true;

	@ConfigEntry.Category("blocksettings")
	@ConfigEntry.Gui.Tooltip
	public boolean crack_terracotta = true;

	/** Whether Create-ecosystem blocks (sails, envelopes, etc.) can burn. */
	@ConfigEntry.Category("blocksettings")
	@ConfigEntry.Gui.Tooltip
	public boolean burn_create_blocks = true;

	// Visual Effects

	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	public boolean smoke_particles = true;

	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	public boolean flame_particles = true;

	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	public boolean ash_particles = true;

	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	public boolean ground_smoke = true;

	/** 0-100: how much smoke a fire makes. Lower this if large fires cost performance. */
	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	public double smoke_density = 40.0;

	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	public boolean distant_plumes = false;

	/** Maximum number of smoke plume entities allowed at once (0 = unlimited) */
	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	public int plume_cap = 250;

	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	public boolean heat_haze = true;

	/** 0 = disabled, 5 = default strength, 10 = full original intensity */
	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(min = 0, max = 10)
	public int heat_haze_intensity = 3;


	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	public boolean smoke_fog = false;

	/** 0 = disabled, 1 = very subtle, 5 = 10 blocks at max fire, 10 = near-blind */
	@ConfigEntry.Category("visual_effects")
	@ConfigEntry.Gui.Tooltip
	@ConfigEntry.BoundedDiscrete(min = 0, max = 10)
	public int smoke_fog_intensity = 3;

	// Recovery Settings

	@ConfigEntry.Category("recoverysettings")
	@ConfigEntry.Gui.Tooltip
	public boolean grass_recovery = true;

	// 0-100: how quickly burnt grass recovers, through recovering_grass back to grass
	// Roughly 100 ~ 10s, 50 ~ 5 min, 10 ~ 60 min, 0 = off
	@ConfigEntry.Category("recoverysettings")
	@ConfigEntry.Gui.Tooltip
	public double grass_recover_speed = 25.0;

	@ConfigEntry.Category("recoverysettings")
	@ConfigEntry.Gui.Tooltip
	public boolean forest_recovery = true;

	// 0-100: how quickly burnt logs break down
	// Roughly 100 ~ one every 5s, 50 ~ one a minute, 0 = off
	@ConfigEntry.Category("recoverysettings")
	@ConfigEntry.Gui.Tooltip
	public double forest_recover_speed = 50.0;

	// 0-100: chance a log breakdown also spawns a sapling, species chosen by biome
	@ConfigEntry.Category("recoverysettings")
	@ConfigEntry.Gui.Tooltip
	public double forest_replant_chance = 50.0;

	// Registration & Access

	@ConfigEntry.Gui.Excluded
	private static BurntBasicConfigConfiguration instance;

	public static void register() {
		backUpExistingConfig();
		AutoConfig.register(BurntBasicConfigConfiguration.class, GsonConfigSerializer::new);
		instance = AutoConfig.getConfigHolder(BurntBasicConfigConfiguration.class).getConfig();
	}

	/**
	 * Keep a copy of the config beside it before Auto Config reads it.
	 *
	 * <p>Auto Config resets every setting to its default when it cannot read the file - a renamed
	 * field, a changed type, a half-written file from a crash - and then saves that over the
	 * original. The player sees one log line and a world where things burn that did not before.
	 * The copy taken here is from just before that happens, so renaming it back restores them.
	 *
	 * <p>Only a file that still parses is copied, so a broken config cannot overwrite a good
	 * backup. Nothing here is allowed to stop the mod loading.
	 */
	private static void backUpExistingConfig() {
		try {
			java.nio.file.Path live = me.shedaniel.autoconfig.util.Utils.getConfigFolder()
					.resolve("burnt_basic.json");
			if (!java.nio.file.Files.isRegularFile(live))
				return;
			try (java.io.BufferedReader reader = java.nio.file.Files.newBufferedReader(live)) {
				com.google.gson.JsonParser.parseReader(reader);
			} catch (Exception malformed) {
				// About to be reset, and an older backup is worth more than this file
				net.pixelbank.burnt.BurntMod.LOGGER.warn(
						"[Burnt] {} could not be read and will be reset to defaults by Cloth Config. "
						+ "An earlier copy may still be at burnt_basic.json.bak", live);
				return;
			}
			java.nio.file.Files.copy(live, live.resolveSibling("burnt_basic.json.bak"),
					java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		} catch (Throwable t) {
			net.pixelbank.burnt.BurntMod.LOGGER.warn("[Burnt] Could not back up the config: {}", t.toString());
		}
	}

	/** Direct access to the live config instance. */
	public static BurntBasicConfigConfiguration get() {
		return instance;
	}

	// Static accessors so procedures can keep calling CONFIG.FIELD.get()
	// Both Gui.Excluded and Category are needed, or Cloth Config invents a "default" tab

	public static final class ConfigValue<T> {
		private final Supplier<T> getter;
		public ConfigValue(Supplier<T> getter) { this.getter = getter; }
		public T get() { return getter.get(); }
	}

	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> BURNT_ON           = new ConfigValue<>(() -> instance.burnt_on);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<List<String>> DISABLED_DIMENSIONS = new ConfigValue<>(() -> instance.disabled_dimensions);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> BURN_MODDED_DIMENSIONS = new ConfigValue<>(() -> instance.burn_in_modded_dimensions);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> RAIN_EXTINGUISH    = new ConfigValue<>(() -> instance.rain_extinguish);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> VANILLA_BURN       = new ConfigValue<>(() -> instance.vanilla_burn);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> LAVA_BURN          = new ConfigValue<>(() -> instance.lava_burn);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> LIGHTNING_IGNITES     = new ConfigValue<>(() -> instance.lightning_ignites);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> BIOME              = new ConfigValue<>(() -> instance.biome_specific);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> WIND               = new ConfigValue<>(() -> instance.wind);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Double>  WIND_INFLUENCE     = new ConfigValue<>(() -> instance.wind_influence);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> IRON_GOLEM_FIGHT_FIRES = new ConfigValue<>(() -> instance.iron_golem_fight_fires); // IRON GOLEM FIRE FIGHTING
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> FLAME_ARROWS_IGNITE_CREEPERS = new ConfigValue<>(() -> instance.flame_arrows_ignite_creepers); // FLAME ARROW CREEPER
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> ENABLE_FLAGS       = new ConfigValue<>(() -> instance.enable_flags);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Integer> FLAG_SENSITIVITY   = new ConfigValue<>(() -> instance.flag_sensitivity);
	@ConfigEntry.Gui.Excluded public static final ConfigValue<Boolean> ENABLE_FIRE_EXTINGUISHER = new ConfigValue<>(() -> instance.enable_fire_extinguisher);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Double>  BURN_SPREAD        = new ConfigValue<>(() -> instance.burn_spread);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Double>  WOOD_SPREAD        = new ConfigValue<>(() -> instance.wood_spread);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Double>  DARK_OAK_SPREAD    = new ConfigValue<>(() -> instance.dark_oak_spread);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Double>  WOOD_DOWNWARD_SPREAD = new ConfigValue<>(() -> instance.wood_downward_spread);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Boolean> BURN_IN_CLAIMED_CHUNKS = new ConfigValue<>(() -> instance.burn_in_claimed_chunks);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Double>  GROUND_SPREAD      = new ConfigValue<>(() -> instance.ground_spread);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Double>  BURN_SPEED         = new ConfigValue<>(() -> instance.burn_speed);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Double>  FIRE_BURNOUT_CHANCE = new ConfigValue<>(() -> instance.fire_burnout_chance);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Double>  SMOKE_PARTICLE_RANGE = new ConfigValue<>(() -> instance.smoke_particle_range);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Double>  PLAYER_DISTANCE    = new ConfigValue<>(() -> instance.player_distance);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Boolean> EMBER_SPREAD       = new ConfigValue<>(() -> instance.ember_spread);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Boolean> FLAME_BOW          = new ConfigValue<>(() -> instance.flame_bow);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Boolean> FIRE_ASPECT        = new ConfigValue<>(() -> instance.fire_aspect);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Boolean> BURN_DAMAGE        = new ConfigValue<>(() -> instance.burn_damage);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Boolean> COLLAPSE           = new ConfigValue<>(() -> instance.random_collapses);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("burnsettings")  public static final ConfigValue<Double>  COLLAPSE_CHANCE    = new ConfigValue<>(() -> instance.collapse_chance);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("blocksettings") public static final ConfigValue<Boolean> CRAFTED_BURN       = new ConfigValue<>(() -> instance.crafted_blocks_burn);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("blocksettings") public static final ConfigValue<Boolean> BURN_BARRELS       = new ConfigValue<>(() -> instance.burn_barrels);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("blocksettings") public static final ConfigValue<Boolean> WOOL_BURN          = new ConfigValue<>(() -> instance.wool_burn);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("blocksettings") public static final ConfigValue<Boolean> SOOT               = new ConfigValue<>(() -> instance.soot);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("blocksettings") public static final ConfigValue<Boolean> CRACKS             = new ConfigValue<>(() -> instance.crack_terracotta);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("blocksettings") public static final ConfigValue<Boolean> BURN_CREATE_BLOCKS = new ConfigValue<>(() -> instance.burn_create_blocks);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Boolean> SMOKE             = new ConfigValue<>(() -> instance.smoke_particles);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Boolean> FLAMES            = new ConfigValue<>(() -> instance.flame_particles);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Boolean> ASH               = new ConfigValue<>(() -> instance.ash_particles);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Boolean> GROUND_SMOKE      = new ConfigValue<>(() -> instance.ground_smoke);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Double>  SMOKE_DENSITY     = new ConfigValue<>(() -> instance.smoke_density);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Boolean> DISTANT_PLUMES     = new ConfigValue<>(() -> instance.distant_plumes);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Integer> PLUME_CAP         = new ConfigValue<>(() -> instance.plume_cap);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Boolean> HEAT_HAZE         = new ConfigValue<>(() -> instance.heat_haze);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Integer> HEAT_HAZE_INTENSITY = new ConfigValue<>(() -> instance.heat_haze_intensity);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Boolean> SMOKE_FOG         = new ConfigValue<>(() -> instance.smoke_fog);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("visual_effects") public static final ConfigValue<Integer> SMOKE_FOG_INTENSITY = new ConfigValue<>(() -> instance.smoke_fog_intensity);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("recoverysettings") public static final ConfigValue<Boolean> GRASS_RECOVERY  = new ConfigValue<>(() -> instance.grass_recovery);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("recoverysettings") public static final ConfigValue<Double>  GRASS_RECOVER_SPEED = new ConfigValue<>(() -> instance.grass_recover_speed);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("recoverysettings") public static final ConfigValue<Boolean> FOREST_RECOVERY = new ConfigValue<>(() -> instance.forest_recovery);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("recoverysettings") public static final ConfigValue<Double>  FOREST_RECOVER_SPEED   = new ConfigValue<>(() -> instance.forest_recover_speed);
	@ConfigEntry.Gui.Excluded @ConfigEntry.Category("recoverysettings") public static final ConfigValue<Double>  FOREST_REPLANT_CHANCE  = new ConfigValue<>(() -> instance.forest_replant_chance);
}
