package net.pixelbank.burnt.compat;

import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

// Runtime registry of the datapack burn mappings, refilled on every resource reload

public final class BurnMappingRegistry {

    private static final Map<Block, Block> MAPPINGS = new HashMap<>();

    /** Clears all mappings - called by {@link BurnMappingLoader} before each reload. */
    public static void clear() { MAPPINGS.clear(); }

    /** Registers a source -> destination mapping. */
    public static void put(Block from, Block to) { MAPPINGS.put(from, to); }

    /** Returns the destination block for {@code source}, or {@code null} if no mapping exists. */
    public static Block get(Block source) { return MAPPINGS.get(source); }

    private BurnMappingRegistry() {}
}
