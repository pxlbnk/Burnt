package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import java.util.HashMap;
import java.util.Map;

// Turns sooted and smoldering blocks back into their vanilla counterparts
// Called once per block by the extinguish paths - water bucket, snowball, proximity decay
// One static block-to-block map, replacing the generated 110-branch if chain
// Blocks that aren't in the map are left alone

public class SootyExtinguishProcedure {

	private static volatile Map<Block, Block> MAP;

	private static Map<Block, Block> map() {
		Map<Block, Block> m = MAP;
		if (m == null) {
			m = new HashMap<>(160);
			// Grass start
			m.put(BurntModBlocks.SMOLDERING_GRASS_START,         Blocks.GRASS_BLOCK);
			// Sooty logs (vanilla woods)
			m.put(BurntModBlocks.SOOTY_OAK_LOG,                  Blocks.OAK_LOG);
			m.put(BurntModBlocks.SOOTY_SPRUCE_LOG,               Blocks.SPRUCE_LOG);
			m.put(BurntModBlocks.SOOTY_BIRCH_LOG,                Blocks.BIRCH_LOG);
			m.put(BurntModBlocks.SOOTY_DARK_OAK_LOG,             Blocks.DARK_OAK_LOG);
			m.put(BurntModBlocks.SOOTY_JUNGLE_LOG,               Blocks.JUNGLE_LOG);
			m.put(BurntModBlocks.SOOTY_MANGROVE_LOG,             Blocks.MANGROVE_LOG);
			m.put(BurntModBlocks.SOOTY_ACACIA_LOG,               Blocks.ACACIA_LOG);
			m.put(BurntModBlocks.SOOTY_CHERRY_LOG,               Blocks.CHERRY_LOG);
			// Sooty wood (bark-on)
			m.put(BurntModBlocks.SOOTY_OAK_WOOD,                 Blocks.OAK_WOOD);
			m.put(BurntModBlocks.SOOTY_SPRUCE_WOOD,               Blocks.SPRUCE_WOOD);
			m.put(BurntModBlocks.SOOTY_BIRCH_WOOD,               Blocks.BIRCH_WOOD);
			m.put(BurntModBlocks.SOOTY_DARK_OAK_WOOD,            Blocks.DARK_OAK_WOOD);
			m.put(BurntModBlocks.SOOTY_JUNGLE_WOOD,              Blocks.JUNGLE_WOOD);
			m.put(BurntModBlocks.SOOTY_MANGROVE_WOOD,            Blocks.MANGROVE_WOOD);
			m.put(BurntModBlocks.SOOTY_ACACIA_WOOD,              Blocks.ACACIA_WOOD);
			m.put(BurntModBlocks.SOOTY_CHERRY_WOOD,              Blocks.CHERRY_WOOD);
			// Sooty stripped logs
			m.put(BurntModBlocks.SOOTY_STRIPPED_OAK_LOG,         Blocks.STRIPPED_OAK_LOG);
			m.put(BurntModBlocks.SOOTY_STRIPPED_SPRUCE_LOG,      Blocks.STRIPPED_SPRUCE_LOG);
			m.put(BurntModBlocks.SOOTY_STRIPPED_BIRCH_LOG,       Blocks.STRIPPED_BIRCH_LOG);
			m.put(BurntModBlocks.SOOTY_STRIPPED_DARK_OAK_LOG,    Blocks.STRIPPED_DARK_OAK_LOG);
			m.put(BurntModBlocks.SOOTY_STRIPPED_JUNGLE_LOG,      Blocks.STRIPPED_JUNGLE_LOG);
			m.put(BurntModBlocks.SOOTY_STRIPPED_MANGROVE_LOG,    Blocks.STRIPPED_MANGROVE_LOG);
			m.put(BurntModBlocks.SOOTY_STRIPPED_ACACIA_LOG,      Blocks.STRIPPED_ACACIA_LOG);
			m.put(BurntModBlocks.SOOTY_STRIPPED_CHERRY_LOG,      Blocks.STRIPPED_CHERRY_LOG);
			// Sooty stripped wood
			m.put(BurntModBlocks.SOOTY_OAK_STRIPPED_WOOD,        Blocks.STRIPPED_OAK_WOOD);
			m.put(BurntModBlocks.SOOTY_SPRUCE_STRIPPED_WOOD,     Blocks.STRIPPED_SPRUCE_WOOD);
			m.put(BurntModBlocks.SOOTY_BIRCH_STRIPPED_WOOD,      Blocks.STRIPPED_BIRCH_WOOD);
			m.put(BurntModBlocks.SOOTY_DARK_OAK_STRIPPED_WOOD,   Blocks.STRIPPED_DARK_OAK_WOOD);
			m.put(BurntModBlocks.SOOTY_JUNGLE_STRIPPED_WOOD,     Blocks.STRIPPED_JUNGLE_WOOD);
			m.put(BurntModBlocks.SOOTY_MANGROVE_STRIPPED_WOOD,   Blocks.STRIPPED_MANGROVE_WOOD);
			m.put(BurntModBlocks.SOOTY_ACACIA_STRIPPED_WOOD,     Blocks.STRIPPED_ACACIA_WOOD);
			m.put(BurntModBlocks.SOOTY_CHERRY_STRIPPED_WOOD,     Blocks.STRIPPED_CHERRY_WOOD);
			// Sooty planks
			m.put(BurntModBlocks.SOOTY_OAK_PLANKS,               Blocks.OAK_PLANKS);
			m.put(BurntModBlocks.SOOTY_SPRUCE_PLANKS,            Blocks.SPRUCE_PLANKS);
			m.put(BurntModBlocks.SOOTY_BIRCH_PLANKS,             Blocks.BIRCH_PLANKS);
			m.put(BurntModBlocks.SOOTY_DARK_OAK_PLANKS,          Blocks.DARK_OAK_PLANKS);
			m.put(BurntModBlocks.SOOTY_JUNGLE_PLANKS,            Blocks.JUNGLE_PLANKS);
			m.put(BurntModBlocks.SOOTY_MANGROVE_PLANKS,          Blocks.MANGROVE_PLANKS);
			m.put(BurntModBlocks.SOOTY_ACACIA_PLANKS,            Blocks.ACACIA_PLANKS);
			m.put(BurntModBlocks.SOOTY_CHERRY_PLANKS,            Blocks.CHERRY_PLANKS);
			// Sooty wooden stairs
			m.put(BurntModBlocks.SOOTY_OAK_STAIRS,               Blocks.OAK_STAIRS);
			m.put(BurntModBlocks.SOOTY_SPRUCE_STAIRS,            Blocks.SPRUCE_STAIRS);
			m.put(BurntModBlocks.SOOTY_BIRCH_STAIRS,             Blocks.BIRCH_STAIRS);
			m.put(BurntModBlocks.SOOTY_DARK_OAK_STAIRS,          Blocks.DARK_OAK_STAIRS);
			m.put(BurntModBlocks.SOOTY_JUNGLE_STAIRS,            Blocks.JUNGLE_STAIRS);
			m.put(BurntModBlocks.SOOTY_MANGROVE_STAIRS,          Blocks.MANGROVE_STAIRS);
			m.put(BurntModBlocks.SOOTY_ACACIA_STAIRS,            Blocks.ACACIA_STAIRS);
			m.put(BurntModBlocks.SOOTY_CHERRY_STAIRS,            Blocks.CHERRY_STAIRS);
			// Sooty wooden slabs
			m.put(BurntModBlocks.SOOTY_OAK_SLAB,                 Blocks.OAK_SLAB);
			m.put(BurntModBlocks.SOOTY_SPRUCE_SLAB,              Blocks.SPRUCE_SLAB);
			m.put(BurntModBlocks.SOOTY_BIRCH_SLAB,               Blocks.BIRCH_SLAB);
			m.put(BurntModBlocks.SOOTY_DARK_OAK_SLAB,            Blocks.DARK_OAK_SLAB);
			m.put(BurntModBlocks.SOOTY_JUNGLE_SLAB,              Blocks.JUNGLE_SLAB);
			m.put(BurntModBlocks.SOOTY_MANGROVE_SLAB,            Blocks.MANGROVE_SLAB);
			m.put(BurntModBlocks.SOOTY_ACACIA_SLAB,              Blocks.ACACIA_SLAB);
			m.put(BurntModBlocks.SOOTY_CHERRY_SLAB,              Blocks.CHERRY_SLAB);
			// Sooty fences
			m.put(BurntModBlocks.SOOTY_OAK_FENCE,                Blocks.OAK_FENCE);
			m.put(BurntModBlocks.SOOTY_SPRUCE_FENCE,             Blocks.SPRUCE_FENCE);
			m.put(BurntModBlocks.SOOTY_BIRCH_FENCE,              Blocks.BIRCH_FENCE);
			m.put(BurntModBlocks.SOOTY_DARK_OAK_FENCE,           Blocks.DARK_OAK_FENCE);
			m.put(BurntModBlocks.SOOTY_JUNGLE_FENCE,             Blocks.JUNGLE_FENCE);
			m.put(BurntModBlocks.SOOTY_MANGROVE_FENCE,           Blocks.MANGROVE_FENCE);
			m.put(BurntModBlocks.SOOTY_ACACIA_FENCE,             Blocks.ACACIA_FENCE);
			m.put(BurntModBlocks.SOOTY_CHERRY_FENCE,             Blocks.CHERRY_FENCE);
			// Sooty fence gates (note self-map for MANGROVE - see file header)
			m.put(BurntModBlocks.SOOTY_OAK_FENCE_GATE,           Blocks.OAK_FENCE_GATE);
			m.put(BurntModBlocks.SOOTY_SPRUCE_FENCE_GATE,        Blocks.SPRUCE_FENCE_GATE);
			m.put(BurntModBlocks.SOOTY_BIRCH_FENCE_GATE,         Blocks.BIRCH_FENCE_GATE);
			m.put(BurntModBlocks.SOOTY_DARK_OAK_FENCE_GATE,      Blocks.DARK_OAK_FENCE_GATE);
			m.put(BurntModBlocks.SOOTY_JUNGLE_FENCE_GATE,        Blocks.JUNGLE_FENCE_GATE);
			m.put(Blocks.MANGROVE_FENCE_GATE,                    Blocks.MANGROVE_FENCE_GATE);  // self-map preserved from source (see header)
			m.put(BurntModBlocks.SOOTY_ACACIA_FENCE_GATE,        Blocks.ACACIA_FENCE_GATE);
			m.put(BurntModBlocks.SOOTY_CHERRY_FENCE_GATE,        Blocks.CHERRY_FENCE_GATE);
			// Pale oak - resolved by id, skipped when the vanilla block is not present
			putVanilla(m, BurntModBlocks.SOOTY_PALE_OAK_LOG,            "pale_oak_log");
			putVanilla(m, BurntModBlocks.SOOTY_PALE_OAK_WOOD,           "pale_oak_wood");
			putVanilla(m, BurntModBlocks.SOOTY_STRIPPED_PALE_OAK_LOG,   "stripped_pale_oak_log");
			putVanilla(m, BurntModBlocks.SOOTY_PALE_OAK_STRIPPED_WOOD,  "stripped_pale_oak_wood");
			putVanilla(m, BurntModBlocks.SOOTY_PALE_OAK_PLANKS,         "pale_oak_planks");
			putVanilla(m, BurntModBlocks.SOOTY_PALE_OAK_STAIRS,         "pale_oak_stairs");
			putVanilla(m, BurntModBlocks.SOOTY_PALE_OAK_SLAB,           "pale_oak_slab");
			putVanilla(m, BurntModBlocks.SOOTY_PALE_OAK_FENCE,          "pale_oak_fence");
			putVanilla(m, BurntModBlocks.SOOTY_PALE_OAK_FENCE_GATE,     "pale_oak_fence_gate");
			// Stone family
			m.put(BurntModBlocks.SOOTY_STONE,                    Blocks.STONE);
			m.put(BurntModBlocks.SOOTY_STONE_SLAB,               Blocks.STONE_SLAB);
			m.put(BurntModBlocks.SOOTY_STONE_STAIRS,             Blocks.STONE_STAIRS);
			// Cobblestone family
			m.put(BurntModBlocks.SOOTY_COBBLESTONE,              Blocks.COBBLESTONE);
			m.put(BurntModBlocks.SOOTY_COBBLESTONE_STAIRS,       Blocks.COBBLESTONE_STAIRS);
			m.put(BurntModBlocks.SOOTY_COBBLESTONE_SLAB,         Blocks.COBBLESTONE_SLAB);
			m.put(BurntModBlocks.SOOTY_COBBLESTONE_WALL,         Blocks.COBBLESTONE_WALL);
			// Mossy cobble family
			m.put(BurntModBlocks.SOOTY_MOSSY_COBBLESTONE,        Blocks.MOSSY_COBBLESTONE);
			m.put(BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS);
			m.put(BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_SLAB,   Blocks.MOSSY_COBBLESTONE_SLAB);
			m.put(BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_WALL,   Blocks.MOSSY_COBBLESTONE_WALL);
			// Stone brick family
			m.put(BurntModBlocks.SOOTY_STONE_BRICKS,             Blocks.STONE_BRICKS);
			m.put(BurntModBlocks.SOOTY_STONE_BRICK_STAIRS,       Blocks.STONE_BRICK_STAIRS);
			m.put(BurntModBlocks.SOOTY_STONE_BRICK_SLAB,         Blocks.STONE_BRICK_SLAB);
			m.put(BurntModBlocks.SOOTY_STONE_BRICK_WALL,         Blocks.STONE_BRICK_WALL);
			// Mossy stone brick family
			m.put(BurntModBlocks.SOOTY_MOSSY_STONE_BRICKS,       Blocks.MOSSY_STONE_BRICKS);
			m.put(BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS);
			m.put(BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_SLAB,   Blocks.MOSSY_STONE_BRICK_SLAB);
			m.put(BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_WALL,   Blocks.MOSSY_STONE_BRICK_WALL);
			// Bricks
			m.put(BurntModBlocks.SOOTY_BRICKS,                   Blocks.BRICKS);
			m.put(BurntModBlocks.SOOTY_BRICK_STAIRS,             Blocks.BRICK_STAIRS);
			m.put(BurntModBlocks.SOOTY_BRICK_SLAB,               Blocks.BRICK_SLAB);
			m.put(BurntModBlocks.SOOTY_BRICK_WALL,               Blocks.BRICK_WALL);
			// Mud bricks
			m.put(BurntModBlocks.SOOTY_MUD_BRICKS,               Blocks.MUD_BRICKS);
			m.put(BurntModBlocks.SOOTY_MUD_BRICK_STAIRS,         Blocks.MUD_BRICK_STAIRS);
			m.put(BurntModBlocks.SOOTY_MUD_BRICK_SLAB,           Blocks.MUD_BRICK_SLAB);
			m.put(BurntModBlocks.SOOTY_MUD_BRICK_WALL,           Blocks.MUD_BRICK_WALL);
			// Andesite
			m.put(BurntModBlocks.SOOTY_ANDESITE,                 Blocks.ANDESITE);
			m.put(BurntModBlocks.SOOTY_ANDESITE_STAIRS,          Blocks.ANDESITE_STAIRS);
			m.put(BurntModBlocks.SOOTY_ANDESITE_SLAB,            Blocks.ANDESITE_SLAB);
			m.put(BurntModBlocks.SOOTY_ANDESITE_WALL,            Blocks.ANDESITE_WALL);
			// Diorite
			m.put(BurntModBlocks.SOOTY_DIORITE,                  Blocks.DIORITE);
			m.put(BurntModBlocks.SOOTY_DIORITE_STAIRS,           Blocks.DIORITE_STAIRS);
			m.put(BurntModBlocks.SOOTY_DIORITE_SLAB,             Blocks.DIORITE_SLAB);
			m.put(BurntModBlocks.SOOTY_DIORITE_WALL,             Blocks.DIORITE_WALL);
			// Granite
			m.put(BurntModBlocks.SOOTY_GRANITE,                  Blocks.GRANITE);
			m.put(BurntModBlocks.SOOTY_GRANITE_STAIRS,           Blocks.GRANITE_STAIRS);
			m.put(BurntModBlocks.SOOTY_GRANITE_SLAB,             Blocks.GRANITE_SLAB);
			m.put(BurntModBlocks.SOOTY_GRANITE_WALL,             Blocks.GRANITE_WALL);
			MAP = m;
		}
		return m;
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) return;

		BlockPos pos = BlockPos.containing(x, y, z);
		BlockState here = world.getBlockState(pos);
		Block dest = map().get(here.getBlock());
		if (dest == null) return;

		placeWithCopiedProperties(world, pos, here, dest.defaultBlockState());
	}

	// Maps back to a vanilla block with no Blocks constant on this version, skipped if unregistered
	private static void putVanilla(Map<Block, Block> m, Block from, String vanillaId) {
		Block to = net.pixelbank.burnt.util.BurntVanilla.block(vanillaId);
		if (from != null && to != null) m.put(from, to);
	}

	// Copies matching properties onto the target, then writes it at pos
	@SuppressWarnings({"unchecked", "rawtypes"})
	private static void placeWithCopiedProperties(LevelAccessor world, BlockPos pos, BlockState source, BlockState target) {
		BlockState out = target;
		for (Property<?> propOld : source.getProperties()) {
			Property propNew = out.getBlock().getStateDefinition().getProperty(propOld.getName());
			if (propNew != null && out.getValue(propNew) != null) {
				try {
					out = out.setValue(propNew, source.getValue(propOld));
				} catch (Exception ignored) {
				}
			}
		}
		world.setBlock(pos, out, 3);
	}
}
