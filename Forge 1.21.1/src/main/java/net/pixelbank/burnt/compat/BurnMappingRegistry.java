package net.pixelbank.burnt.compat;

import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

// Runtime registry of the datapack burn mappings, refilled on every resource reload
// Checked at the top of BurnBabyProcedure, so a mapping overrides every tag-based tier
public final class BurnMappingRegistry {

    private static final Map<Block, Block> MAPPINGS = new HashMap<>();

    /** Clears all mappings - called by {@link BurnMappingLoader} before each reload. */
    public static void clear() {
        MAPPINGS.clear();
    }

    /** Registers a source -> destination mapping. */
    public static void put(Block from, Block to) {
        MAPPINGS.put(from, to);
    }

    /** The destination block for {@code source}, or null if there is no mapping. */
    public static Block get(Block source) {
        return MAPPINGS.get(source);
    }

    private BurnMappingRegistry() {}
}
