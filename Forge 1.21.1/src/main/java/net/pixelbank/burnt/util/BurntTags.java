package net.pixelbank.burnt.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/** All the block, biome, item and entity tags used by Burnt Basic. */
public final class BurntTags {

    // BLOCK tags
    public static final TagKey<Block> BLOCK_BOP_GRASS_BLOCKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("biomesoplenty:grass_blocks"));
    public static final TagKey<Block> BLOCK_BWG_GRASS_BLOCKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("biomeswevegone:grass_blocks"));
    public static final TagKey<Block> BLOCK_AIR =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:air"));
    public static final TagKey<Block> BLOCK_BAMBOO_BLOCKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:bamboo_blocks"));
    public static final TagKey<Block> BLOCK_BLAST =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:blast"));
    public static final TagKey<Block> BLOCK_BREAKS_FALLING_SLABS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:breaks_falling_slabs"));
    public static final TagKey<Block> BLOCK_BURNING_BAMBOO =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_bamboo"));
    public static final TagKey<Block> BLOCK_BURNING_DOORS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_doors"));
    public static final TagKey<Block> BLOCK_BURNING_FENCE_GATES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_fence_gates"));
    public static final TagKey<Block> BLOCK_BURNING_FENCES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_fences"));
    public static final TagKey<Block> BLOCK_BURNING_GRASS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_grass"));
    public static final TagKey<Block> BLOCK_BURNING_LEAVES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_leaves"));
    public static final TagKey<Block> BLOCK_BURNING_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_logs"));
    public static final TagKey<Block> BLOCK_BURNING_PLANKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_planks"));
    public static final TagKey<Block> BLOCK_BURNING_SLABS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_slabs"));
    public static final TagKey<Block> BLOCK_BURNING_STAIRS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_stairs"));
    public static final TagKey<Block> BLOCK_BURNING_STRIPPED_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_stripped_logs"));
    public static final TagKey<Block> BLOCK_BURNING_STRIPPED_WOOD =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_stripped_wood"));
    public static final TagKey<Block> BLOCK_BURNING_TRAPDOORS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_trapdoors"));
    public static final TagKey<Block> BLOCK_BURNING_WOOD =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burning_wood"));
    public static final TagKey<Block> BLOCK_BURNT =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burnt"));
    public static final TagKey<Block> BLOCK_BURNT_DIRT =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burnt_dirt"));
    public static final TagKey<Block> BLOCK_BURNT_DOORS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burnt_doors"));
    public static final TagKey<Block> BLOCK_BURNT_FENCE_GATES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burnt_fence_gates"));
    public static final TagKey<Block> BLOCK_BURNT_FENCES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burnt_fences"));
    public static final TagKey<Block> BLOCK_BURNT_GROWS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burnt_grows"));
    public static final TagKey<Block> BLOCK_BURNT_LEAVES_STICK =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burnt_leaves_stick"));
    public static final TagKey<Block> BLOCK_BURNT_SUGAR_CANE_PLACES_ON =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burnt_sugar_cane_places_on"));
    public static final TagKey<Block> BLOCK_BURNT_WOOD =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burnt_wood"));
    public static final TagKey<Block> BLOCK_FIRE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:fire"));
    public static final TagKey<Block> BLOCK_FIRE_DESTROYS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:fire_destroys"));
    public static final TagKey<Block> BLOCK_FIRE_RESISTANT =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:fire_resistant"));
    public static final TagKey<Block> BLOCK_FLAGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:flags"));
    public static final TagKey<Block> BLOCK_FLAMES_VALID =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:flames_valid"));
    public static final TagKey<Block> BLOCK_FLESH =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:flesh"));
    public static final TagKey<Block> BLOCK_FURTILE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:furtile"));
    public static final TagKey<Block> BLOCK_GRASS_BLOCKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:grass_blocks"));

    // Compat tier tags - added to by datapacks / other mods
    /** Modded logs/wood that should burn to a generic smoldering log (standard speed). */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_LOG =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_log"));
    public static final TagKey<Block> BLOCK_BURNS_TO_HOLLOW_LOG =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_hollow_log"));
    /** Modded logs/wood that should burn slowly, like dark oak (uses smoldering_dark_oak_log). */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_LOG_SLOW =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_log_slow"));
    /** Modded thin/small log blocks (non-full-cube geometry) that should become small_smoldering_log. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMALL_LOG =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_small_log"));
    /** Modded grass blocks that should go through the standard smoldering-grass chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_GRASS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_grass"));
    /** Modded dirt blocks that should become burnt_dirt. */
    public static final TagKey<Block> BLOCK_BURNS_TO_BURNT_DIRT =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_burnt_dirt"));
    // Soil SLABS (Terrain Slabs and anything else shipping half-height soil). These need their
    // own tiers rather than reusing the full-block ones: the replacement has to be a slab too, or
    // a half-height block silently becomes a full cube when it burns.
    /** Half-height grass blocks that should go through the smoldering-grass-slab chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_GRASS_SLAB =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_grass_slab"));
    /** Half-height moss blocks that should become smoldering_moss_slab. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_MOSS_SLAB =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_moss_slab"));
    /** Half-height dirt blocks that should become burnt_dirt_slab. */
    public static final TagKey<Block> BLOCK_BURNS_TO_BURNT_DIRT_SLAB =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_burnt_dirt_slab"));
    /** Modded plank blocks that should go through the standard sooty->smoldering planks chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_PLANKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_planks"));
    /** Modded wooden slabs that should go through the standard sooty->smoldering slabs chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_SLABS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_slabs"));
    public static final TagKey<Block> BLOCK_BURNS_TO_WALL =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_wall"));
    public static final TagKey<Block> BLOCK_BURNS_TO_VERTICAL_SLAB =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_vertical_slab"));
    /** Modded wooden stairs that should go through the standard sooty->smoldering stairs chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_STAIRS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_stairs"));
    /** Modded wooden fences that should go through the standard sooty->smoldering fence chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_FENCES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_fences"));
    /** Modded wooden fence gates that should go through the standard sooty->smoldering fence gate chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_FENCE_GATES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_fence_gates"));
    /** Modded farmland blocks that should become burnt_farmland. */
    public static final TagKey<Block> BLOCK_BURNS_TO_BURNT_FARMLAND =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_burnt_farmland"));
    /** Modded crop blocks that should go through the standard smoldering crops chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_CROPS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_crops"));
    /** Modded fern-like plants that should become smoldering_fern. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_FERN =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_fern"));
    /** Modded single-block tall grasses that should become smoldering tall grass (tall_grass_fire). */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_TALL_GRASS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_tall_grass"));
    /** Modded two-block-tall grasses (half=upper/lower) that should become double_tall_grass_fire. */
    public static final TagKey<Block> BLOCK_BURNS_TO_DOUBLE_TALL_GRASS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_double_tall_grass"));
    /** Wooden building blocks that resist catching from a fire ABOVE them, so roof fires do not walk to the ground. */
    public static final TagKey<Block> BLOCK_SLOW_DOWNWARD_SPREAD =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:slow_downward_spread"));
    /** Logs: same downward resistance, but skipped when the log is horizontally exposed (a bare trunk). */
    public static final TagKey<Block> BLOCK_SLOW_DOWNWARD_SPREAD_UNLESS_EXPOSED =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:slow_downward_spread_unless_exposed"));
    /** Blocks that should be completely immune to the Burnt burn chain. Datapack-friendly alternative to fire_resistant. */
    public static final TagKey<Block> BLOCK_WILL_NOT_BURN =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:will_not_burn"));
    public static final TagKey<Block> BLOCK_VANILLA_FIRE_BURNS_ON =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:vanilla_fire_burns_on"));
    /** Blocks that should simply be destroyed (no drops) when caught in a fire. */
    public static final TagKey<Block> BLOCK_DESTROY_IN_BURN =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:destroy_in_burn"));
    /**
     * Player-made blocks. One next to a burnt_log means that log was part of a build rather than a
     * tree, which suppresses the sapling replant. Composed from vanilla umbrella tags, so modded
     * crafted blocks come along for free where mods tag them properly.
     */
    public static final TagKey<Block> BLOCK_CRAFTABLES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:craftables"));
    /** Create destroy-on-burn targets. Same as {@link #BLOCK_DESTROY_IN_BURN}, gated on burn_create_blocks. */
    public static final TagKey<Block> BLOCK_DESTROY_IN_BURN_CREATE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:destroy_in_burn_create"));
    /** Modded wood (bark) blocks that should go through the standard sooty->smoldering wood chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_WOOD =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_wood"));
    /** Modded stripped logs that should go through the standard stripped log burn chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_STRIPPED_LOG =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_stripped_log"));
    /** Modded stripped wood that should go through the standard stripped wood burn chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_STRIPPED_WOOD =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_stripped_wood"));
    /** Modded leaves with large broad canopy (like cherry) - becomes smoldering_cherry_leaves. */
    public static final TagKey<Block> BLOCK_BURNS_TO_LARGE_LEAVES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_large_leaves"));
    /** Modded leaves with medium density (like oak) - becomes smoldering_oak_leaves. */
    public static final TagKey<Block> BLOCK_BURNS_TO_MEDIUM_LEAVES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_medium_leaves"));
    /** Modded leaves with fine detail (like birch) - becomes smoldering_birch_leaves. */
    public static final TagKey<Block> BLOCK_BURNS_TO_FINE_LEAVES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_fine_leaves"));
    /** Modded moss blocks that should become smoldering_moss. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_MOSS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_moss"));
    /** Modded moss carpet blocks that should become smoldering_moss_carpet. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_MOSS_CARPET =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_moss_carpet"));
    /** Modded wool blocks that should become smoldering_wool. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_WOOL =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_wool"));
    /** Modded envelope blocks (aeronautics etc.) that should become smoldering_envelope. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_ENVELOPE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_envelope"));
    /** Create coloured sails that should become smoldering_sail. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_SAIL =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_sail"));
    /** Create sail_frame that should become smoldering_sail_frame. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_SAIL_FRAME =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_sail_frame"));
    /** Modded carpet blocks that should become smoldering_carpet. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_CARPET =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_carpet"));
    /** Modded wooden buttons that should become burnt_button. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_BUTTON =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_button"));
    /** Modded wooden pressure plates that should become burnt_pressure_plate. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_PRESSURE_PLATE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_pressure_plate"));
    /** Modded wooden doors that should go through the standard door burn chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_DOOR =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_door"));
    /** Modded wooden trapdoors that should go through the standard trapdoor burn chain. */
    public static final TagKey<Block> BLOCK_BURNS_TO_SMOLDERING_TRAPDOOR =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:burns_to_smoldering_trapdoor"));
    /**
     * Aggregator tag covering every burns_to_* compat tier. Populated entirely via tag references in
     * will_burn_compat.json - never add blocks here directly.
     */
    public static final TagKey<Block> BLOCK_WILL_BURN_COMPAT =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:will_burn_compat"));
    public static final TagKey<Block> BLOCK_NO_BURN =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:no_burn"));
    public static final TagKey<Block> BLOCK_ACTIVE_FIRE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:active_fire"));
    /** All log blocks (vanilla + every Burnt variant) that Weather2 fire tornadoes should not grab. */
    public static final TagKey<Block> BLOCK_TORNADO_GRAB_EXEMPT_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:tornado_grab_exempt_logs"));
    public static final TagKey<Block> BLOCK_ON_FIRE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:on_fire"));
    public static final TagKey<Block> BLOCK_PLANTS_WILL_BURN =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:plants_will_burn"));
    public static final TagKey<Block> BLOCK_SMOLDERING_LEAVES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:smoldering_leaves"));
    public static final TagKey<Block> BLOCK_SMOLDERING_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:smoldering_logs"));
    public static final TagKey<Block> BLOCK_SMOLDERING_PLANKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:smoldering_planks"));
    public static final TagKey<Block> BLOCK_SOOT_PLACES_ON =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:soot_places_on"));
    public static final TagKey<Block> BLOCK_SOOTY =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:sooty"));
    public static final TagKey<Block> BLOCK_STAIRS_FIRE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:stairs_fire"));
    public static final TagKey<Block> BLOCK_TALL_FLAMES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:tall_flames"));
    public static final TagKey<Block> BLOCK_TALL_GRASS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:tall_grass"));
    public static final TagKey<Block> BLOCK_WILL_BURN =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:will_burn"));
    public static final TagKey<Block> BLOCK_COPPER =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:copper"));
    public static final TagKey<Block> BLOCK_COPPER_FIRE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:copper_fire"));
    public static final TagKey<Block> BLOCK_WOOD_FIRE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:wood_fire"));
    public static final TagKey<Block> BLOCK_WOODEN_BUTTONS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:wooden_buttons"));
    public static final TagKey<Block> BLOCK_WOODEN_PRESSURE_PLATES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:wooden_pressure_plates"));
    public static final TagKey<Block> BLOCK_FORGE_FENCE_GATES_WOODEN =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:fence_gates"));
    public static final TagKey<Block> BLOCK_FORGE_FENCES_WOODEN =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:fences"));
    public static final TagKey<Block> BLOCK_FORGE_GLASS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("c:glass_blocks"));
    public static final TagKey<Block> BLOCK_FORGE_GLASS_PANES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("c:glass_panes"));
    public static final TagKey<Block> BLOCK_FORGE_MUSHROOM_BLOCKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:mushroom_blocks"));
    public static final TagKey<Block> BLOCK_FORGE_STRIPPED_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:stripped_logs"));
    public static final TagKey<Block> BLOCK_FORGE_STRIPPED_WOOD =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:stripped_wood"));
    public static final TagKey<Block> BLOCK_FORGE_WOOD =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:wood_blocks"));
    public static final TagKey<Block> BLOCK_IW_GRASS_BLOCKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("immersive_weathering:grass_blocks"));
    public static final TagKey<Block> BLOCK_IW_GRASSY_BLOCKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("immersive_weathering:grassy_blocks"));
    public static final TagKey<Block> BLOCK_IW_LEAF_PILES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("immersive_weathering:leaf_piles"));
    public static final TagKey<Block> BLOCK_MC_ACACIA_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:acacia_logs"));
    public static final TagKey<Block> BLOCK_MC_ANIMALS_SPAWNABLE_ON =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:animals_spawnable_on"));
    public static final TagKey<Block> BLOCK_MC_AZALEA_GROWS_ON =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:azalea_grows_on"));
    public static final TagKey<Block> BLOCK_MC_BAMBOO_PLANTABLE_ON =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:bamboo_plantable_on"));
    public static final TagKey<Block> BLOCK_MC_BANNERS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:banners"));
    public static final TagKey<Block> BLOCK_MC_BEDS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:beds"));
    public static final TagKey<Block> BLOCK_MC_BIRCH_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:birch_logs"));
    public static final TagKey<Block> BLOCK_MC_CAVE_VINES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:cave_vines"));
    public static final TagKey<Block> BLOCK_MC_CHERRY_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:cherry_logs"));
    // Pale oak isn't vanilla on this version - the tag points at the backport's tag, plus the ids directly
    public static final TagKey<Block> BLOCK_PALE_OAK_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:pale_oak_logs"));
    public static final TagKey<Block> BLOCK_MC_CROPS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:crops"));
    public static final TagKey<Block> BLOCK_MC_DARK_OAK_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:dark_oak_logs"));
    public static final TagKey<Block> BLOCK_MC_DIRT =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:dirt"));
    public static final TagKey<Block> BLOCK_MC_FENCE_GATES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:fence_gates"));
    public static final TagKey<Block> BLOCK_MC_FLOWERS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:flowers"));
    public static final TagKey<Block> BLOCK_MC_JUNGLE_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:jungle_logs"));
    public static final TagKey<Block> BLOCK_MC_LEAVES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:leaves"));
    public static final TagKey<Block> BLOCK_MC_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:logs"));
    public static final TagKey<Block> BLOCK_MC_LOGS_THAT_BURN =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:logs_that_burn"));
    public static final TagKey<Block> BLOCK_MC_MANGROVE_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:mangrove_logs"));
    public static final TagKey<Block> BLOCK_MC_OAK_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:oak_logs"));
    public static final TagKey<Block> BLOCK_MC_PLANKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:planks"));
    public static final TagKey<Block> BLOCK_MC_REPLACEABLE =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:replaceable"));
    public static final TagKey<Block> BLOCK_MC_SAND =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:sand"));
    public static final TagKey<Block> BLOCK_MC_SIGNS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:signs"));
    public static final TagKey<Block> BLOCK_MC_SPRUCE_LOGS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:spruce_logs"));
    public static final TagKey<Block> BLOCK_MC_STAIRS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:stairs"));
    public static final TagKey<Block> BLOCK_MC_VALID_SPAWN =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:valid_spawn"));
    public static final TagKey<Block> BLOCK_MC_WOODEN_DOORS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:wooden_doors"));
    public static final TagKey<Block> BLOCK_MC_WOODEN_FENCES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:wooden_fences"));
    public static final TagKey<Block> BLOCK_MC_WOODEN_SLABS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:wooden_slabs"));
    public static final TagKey<Block> BLOCK_MC_WOODEN_STAIRS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:wooden_stairs"));
    public static final TagKey<Block> BLOCK_MC_WOODEN_TRAPDOORS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:wooden_trapdoors"));
    public static final TagKey<Block> BLOCK_MC_WOOL =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:wool"));
    public static final TagKey<Block> BLOCK_MC_WOOL_CARPETS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("minecraft:wool_carpets"));
    public static final TagKey<Block> BLOCK_RU_BRANCHES =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("regions_unexplored:branches"));
    public static final TagKey<Block> BLOCK_RU_GRASS_BLOCKS =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("regions_unexplored:grass_blocks"));
    public static final TagKey<Block> BLOCK_TF_TOWERWOOD =
            TagKey.create(Registries.BLOCK, ResourceLocation.parse("twighlightforest:towerwood"));

    // BIOME tags
    public static final TagKey<Biome> BIOME_INCREASED_FIRE_BURNOUT =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("burnt:increased_fire_burnout"));
    public static final TagKey<Biome> BIOME_IS_CALM =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("burnt:is_calm"));
    public static final TagKey<Biome> BIOME_IS_COLD =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("burnt:is_cold"));
    public static final TagKey<Biome> BIOME_IS_DENSE =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("burnt:is_dense"));
    public static final TagKey<Biome> BIOME_IS_DRY =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("burnt:is_dry"));
    public static final TagKey<Biome> BIOME_IS_HOT =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("burnt:is_hot"));
    public static final TagKey<Biome> BIOME_IS_SPARSE =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("burnt:is_sparse"));
    public static final TagKey<Biome> BIOME_IS_SWAMP =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("burnt:is_swamp"));
    public static final TagKey<Biome> BIOME_IS_WET =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("burnt:is_wet"));
    public static final TagKey<Biome> BIOME_IS_WINDY =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("burnt:is_windy"));
    public static final TagKey<Biome> BIOME_MC_IS_CAVE =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:is_cave"));
    public static final TagKey<Biome> BIOME_MC_IS_COLD =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:is_cold"));
    public static final TagKey<Biome> BIOME_MC_IS_DRY =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:is_dry"));
    public static final TagKey<Biome> BIOME_MC_IS_HOT =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:is_hot"));
    public static final TagKey<Biome> BIOME_MC_IS_OVERWORLD =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:is_overworld"));
    public static final TagKey<Biome> BIOME_MC_IS_WET =
            TagKey.create(Registries.BIOME, ResourceLocation.parse("minecraft:is_wet"));

    // ENTITY_TYPE tags
    public static final TagKey<EntityType<?>> ENTITY_FIRE_RESISTANT =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("burnt:fire_resistant"));
    public static final TagKey<EntityType<?>> ENTITY_SCARED_OF_FIRE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("burnt:scared_of_fire"));
    public static final TagKey<EntityType<?>> ENTITY_SNOWBALL =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("burnt:snowball"));

    // ITEM tags
    public static final TagKey<Item> ITEM_BOWS =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("burnt:bows"));
    public static final TagKey<Item> ITEM_MC_BOOKSHELVES =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:bookshelves"));
    public static final TagKey<Item> ITEM_MC_FENCE_GATES_WOODEN =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:fence_gates/wooden"));
    public static final TagKey<Item> ITEM_MC_HOES =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:hoes"));
    public static final TagKey<Item> ITEM_MC_LEAVES =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:leaves"));
    public static final TagKey<Item> ITEM_MC_LOGS_THAT_BURN =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:logs_that_burn"));
    public static final TagKey<Item> ITEM_MC_NON_FLAMMABLE_WOOD =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:non_flammable_wood"));
    public static final TagKey<Item> ITEM_MC_PLANKS =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:planks"));
    public static final TagKey<Item> ITEM_MC_SEEDS =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:seeds"));
    public static final TagKey<Item> ITEM_MC_WOODEN_BUTTONS =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:wooden_buttons"));
    public static final TagKey<Item> ITEM_MC_WOODEN_DOORS =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:wooden_doors"));
    public static final TagKey<Item> ITEM_MC_WOODEN_FENCES =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:wooden_fences"));
    public static final TagKey<Item> ITEM_MC_WOODEN_PRESSURE_PLATES =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:wooden_pressure_plates"));
    public static final TagKey<Item> ITEM_MC_WOODEN_SLABS =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:wooden_slabs"));
    public static final TagKey<Item> ITEM_MC_WOODEN_STAIRS =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:wooden_stairs"));
    public static final TagKey<Item> ITEM_MC_WOODEN_TRAPDOORS =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:wooden_trapdoors"));
    public static final TagKey<Item> ITEM_MC_WOOL =
            TagKey.create(Registries.ITEM, ResourceLocation.parse("minecraft:wool"));

    private BurntTags() {}

    /**
     * True if the state is in any compat tier tag.
     * Checked tag by tag rather than through the aggregator, because chained tag-of-tag references
     * can fail to resolve on some NeoForge setups while individual lookups always work.
     */
    public static boolean isCompatBurnable(BlockState state) {
        return state.is(BLOCK_BURNS_TO_HOLLOW_LOG)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_LOG)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_LOG_SLOW)
                || state.is(BLOCK_BURNS_TO_SMALL_LOG)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_GRASS)
                || state.is(BLOCK_BURNS_TO_BURNT_DIRT)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_GRASS_SLAB)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_MOSS_SLAB)
                || state.is(BLOCK_BURNS_TO_BURNT_DIRT_SLAB)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_PLANKS)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_SLABS)
                || state.is(BLOCK_BURNS_TO_WALL)
                || state.is(BLOCK_BURNS_TO_VERTICAL_SLAB)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_STAIRS)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_FENCES)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_FENCE_GATES)
                || state.is(BLOCK_BURNS_TO_BURNT_FARMLAND)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_CROPS)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_FERN)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_TALL_GRASS)
                || state.is(BLOCK_BURNS_TO_DOUBLE_TALL_GRASS)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_WOOD)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_STRIPPED_LOG)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_STRIPPED_WOOD)
                || state.is(BLOCK_BURNS_TO_LARGE_LEAVES)
                || state.is(BLOCK_BURNS_TO_MEDIUM_LEAVES)
                || state.is(BLOCK_BURNS_TO_FINE_LEAVES)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_MOSS)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_MOSS_CARPET)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_WOOL)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_ENVELOPE)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_SAIL)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_SAIL_FRAME)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_CARPET)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_BUTTON)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_PRESSURE_PLATE)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_DOOR)
                || state.is(BLOCK_BURNS_TO_SMOLDERING_TRAPDOOR)
                || state.is(BLOCK_DESTROY_IN_BURN)
                // burnt:bamboo_blocks was missing here, so a modded block added to it failed the ignition gate
                || state.is(BLOCK_BAMBOO_BLOCKS)
                || state.is(BLOCK_DESTROY_IN_BURN_CREATE);
    }
}
