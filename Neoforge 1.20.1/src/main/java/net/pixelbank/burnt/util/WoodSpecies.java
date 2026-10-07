package net.pixelbank.burnt.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

// Tracks a log's wood species so the forest replants the right sapling
// Set at the sooty-to-smoldering transition and carried to burnt_log by the usual property copy
// Falls back to the biome lookup when the species is UNKNOWN, so modded woods still work

public enum WoodSpecies implements StringRepresentable {

	UNKNOWN ("unknown",  (Block) null),
	OAK     ("oak",      Blocks.OAK_SAPLING),
	BIRCH   ("birch",    Blocks.BIRCH_SAPLING),
	SPRUCE  ("spruce",   Blocks.SPRUCE_SAPLING),
	JUNGLE  ("jungle",   Blocks.JUNGLE_SAPLING),
	DARK_OAK("dark_oak", Blocks.DARK_OAK_SAPLING),
	ACACIA  ("acacia",   Blocks.ACACIA_SAPLING),
	CHERRY  ("cherry",   Blocks.CHERRY_SAPLING),
	MANGROVE("mangrove", Blocks.MANGROVE_PROPAGULE),
	// Pale oak has no Blocks constant on this version - its sapling is looked up by id.
	PALE_OAK("pale_oak", "pale_oak_sapling");

	private final String name;
	private final Block sapling;
	private final String saplingId;

	WoodSpecies(String name, Block sapling) {
		this.name = name;
		this.sapling = sapling;
		this.saplingId = null;
	}

	// For species whose sapling is not in Blocks on this version - resolved at first use.
	WoodSpecies(String name, String saplingId) {
		this.name = name;
		this.sapling = null;
		this.saplingId = saplingId;
	}

	@Override public String getSerializedName() { return name; }
	public  String getName()                    { return name; }

	/** Returns the sapling block for this species, or null for {@link #UNKNOWN}. */
	public Block getSapling() { return sapling != null ? sapling : (saplingId != null ? BurntVanilla.block(saplingId) : null); }

	/** Returns the sapling's default block state, or {@link Blocks#AIR}'s for {@link #UNKNOWN}. */
	public BlockState getSaplingState() {
		Block s = getSapling();
		return s != null ? s.defaultBlockState() : Blocks.AIR.defaultBlockState();
	}

	// Best-effort species from a log's registered id, stripping sooty_ and stripped_ prefixes and the _log/_wood suffix
	// e.g. burnt:sooty_stripped_birch_log -> BIRCH, minecraft:mangrove_wood -> MANGROVE, modded:redwood_log -> UNKNOWN
	public static WoodSpecies fromBlockId(ResourceLocation id) {
		if (id == null) return UNKNOWN;
		String path = id.getPath();
		if (path.startsWith("sooty_"))    path = path.substring(6);
		if (path.startsWith("stripped_")) path = path.substring(9);
		if (path.endsWith("_log"))        path = path.substring(0, path.length() - 4);
		if (path.endsWith("_wood"))       path = path.substring(0, path.length() - 5);
		for (WoodSpecies s : values()) {
			if (s != UNKNOWN && s.name.equals(path)) return s;
		}
		return UNKNOWN;
	}

	/** Convenience: detect from a block's resource location lookup. */
	public static WoodSpecies fromBlock(Block block) {
		return fromBlockId(BuiltInRegistries.BLOCK.getKey(block));
	}

	/** {@code state} with its species property set, or unchanged if the block doesn't carry the property. */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static BlockState applyTo(BlockState state, WoodSpecies species) {
		if (state == null || species == null) return state;
		Property<?> prop = state.getBlock().getStateDefinition().getProperty("species");
		if (prop instanceof EnumProperty<?>) {
			try {
				return state.setValue((EnumProperty) prop, species);
			} catch (Exception ignored) {
				return state;
			}
		}
		return state;
	}

	/** Reads the species property from a state, returning {@link #UNKNOWN} if absent. */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static WoodSpecies readFrom(BlockState state) {
		if (state == null) return UNKNOWN;
		Property<?> prop = state.getBlock().getStateDefinition().getProperty("species");
		if (prop instanceof EnumProperty<?>) {
			try {
				Object value = state.getValue((EnumProperty) prop);
				return value instanceof WoodSpecies ws ? ws : UNKNOWN;
			} catch (Exception ignored) {
				return UNKNOWN;
			}
		}
		return UNKNOWN;
	}
}
