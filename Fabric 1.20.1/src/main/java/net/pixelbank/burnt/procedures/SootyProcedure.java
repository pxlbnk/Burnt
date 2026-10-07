package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.HashMap;
import java.util.Map;

// Soots vanilla stone, cobble and brick, and cracks terracotta, in a 3x3x3 box around a fire
// Two static block-to-block maps, replacing the generated 80-branch if chain
// Each match rolls 30% independently

public class SootyProcedure {

	// Sooty mappings, built lazily so they don't run during static init
	private static volatile Map<Block, Block> SOOT;

	// Cracked terracotta mappings, built lazily
	private static volatile Map<Block, Block> CRACKS;

	private static Map<Block, Block> sootMap() {
		Map<Block, Block> m = SOOT;
		if (m == null) {
			m = new HashMap<>();
			m.put(Blocks.STONE,                    BurntModBlocks.SOOTY_STONE);
			m.put(Blocks.STONE_SLAB,               BurntModBlocks.SOOTY_STONE_SLAB);
			m.put(Blocks.STONE_STAIRS,             BurntModBlocks.SOOTY_STONE_STAIRS);
			m.put(Blocks.ANDESITE,                 BurntModBlocks.SOOTY_ANDESITE);
			m.put(Blocks.ANDESITE_SLAB,            BurntModBlocks.SOOTY_ANDESITE_SLAB);
			m.put(Blocks.ANDESITE_STAIRS,          BurntModBlocks.SOOTY_ANDESITE_STAIRS);
			m.put(Blocks.ANDESITE_WALL,            BurntModBlocks.SOOTY_ANDESITE_WALL);
			m.put(Blocks.DIORITE,                  BurntModBlocks.SOOTY_DIORITE);   // deduped from 4x in source
			m.put(Blocks.GRANITE,                  BurntModBlocks.SOOTY_GRANITE);
			m.put(Blocks.GRANITE_SLAB,             BurntModBlocks.SOOTY_GRANITE_SLAB);
			m.put(Blocks.GRANITE_STAIRS,           BurntModBlocks.SOOTY_GRANITE_STAIRS);
			m.put(Blocks.GRANITE_WALL,             BurntModBlocks.SOOTY_GRANITE_WALL);
			m.put(Blocks.COBBLESTONE,              BurntModBlocks.SOOTY_COBBLESTONE);
			m.put(Blocks.COBBLESTONE_STAIRS,       BurntModBlocks.SOOTY_COBBLESTONE_STAIRS);
			m.put(Blocks.COBBLESTONE_SLAB,         BurntModBlocks.SOOTY_COBBLESTONE_SLAB);
			m.put(Blocks.COBBLESTONE_WALL,         BurntModBlocks.SOOTY_COBBLESTONE_WALL);
			m.put(Blocks.MOSSY_COBBLESTONE,        BurntModBlocks.SOOTY_MOSSY_COBBLESTONE);
			m.put(Blocks.MOSSY_COBBLESTONE_STAIRS, BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_STAIRS);
			m.put(Blocks.MOSSY_COBBLESTONE_SLAB,   BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_SLAB);  // deduped from 2x in source
			m.put(Blocks.STONE_BRICKS,             BurntModBlocks.SOOTY_STONE_BRICKS);
			m.put(Blocks.CHISELED_STONE_BRICKS,    BurntModBlocks.SOOTY_CHISELED_STONE_BRICKS);
			m.put(Blocks.STONE_BRICK_STAIRS,       BurntModBlocks.SOOTY_STONE_BRICK_STAIRS);
			m.put(Blocks.STONE_BRICK_SLAB,         BurntModBlocks.SOOTY_STONE_BRICK_SLAB);
			m.put(Blocks.STONE_BRICK_WALL,         BurntModBlocks.SOOTY_STONE_BRICK_WALL);
			m.put(Blocks.MOSSY_STONE_BRICKS,       BurntModBlocks.SOOTY_MOSSY_STONE_BRICKS);
			m.put(Blocks.MOSSY_STONE_BRICK_STAIRS, BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_STAIRS);
			m.put(Blocks.MOSSY_STONE_BRICK_SLAB,   BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_SLAB);
			m.put(Blocks.MOSSY_STONE_BRICK_WALL,   BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_WALL);
			m.put(Blocks.BRICKS,                   BurntModBlocks.SOOTY_BRICKS);
			m.put(Blocks.BRICK_STAIRS,             BurntModBlocks.SOOTY_BRICK_STAIRS);
			m.put(Blocks.BRICK_SLAB,               BurntModBlocks.SOOTY_BRICK_SLAB);
			m.put(Blocks.BRICK_WALL,               BurntModBlocks.SOOTY_BRICK_WALL);
			m.put(Blocks.MUD_BRICKS,               BurntModBlocks.SOOTY_MUD_BRICKS);
			m.put(Blocks.MUD_BRICK_STAIRS,         BurntModBlocks.SOOTY_MUD_BRICK_STAIRS);
			m.put(Blocks.MUD_BRICK_SLAB,           BurntModBlocks.SOOTY_MUD_BRICK_SLAB);
			m.put(Blocks.MUD_BRICK_WALL,           BurntModBlocks.SOOTY_MUD_BRICK_WALL);
			SOOT = m;
		}
		return m;
	}

	private static Map<Block, Block> crackMap() {
		Map<Block, Block> m = CRACKS;
		if (m == null) {
			m = new HashMap<>();
			m.put(Blocks.TERRACOTTA,             BurntModBlocks.CRACKED_TERRACOTTA);
			m.put(Blocks.WHITE_TERRACOTTA,       BurntModBlocks.CRACKED_WHITE_TERRACOTTA);
			m.put(Blocks.ORANGE_TERRACOTTA,      BurntModBlocks.CRACKED_ORANGE_TERRACOTTA);
			m.put(Blocks.MAGENTA_TERRACOTTA,     BurntModBlocks.CRACKED_MAGENTA_TERRACOTTA);
			m.put(Blocks.LIGHT_BLUE_TERRACOTTA,  BurntModBlocks.CRACKED_LIGHT_BLUE_TERRACOTTA);
			m.put(Blocks.YELLOW_TERRACOTTA,      BurntModBlocks.CRACKED_YELLOW_TERRACOTTA);
			m.put(Blocks.LIME_TERRACOTTA,        BurntModBlocks.CRACKED_LIME_TERRACOTTA);
			m.put(Blocks.PINK_TERRACOTTA,        BurntModBlocks.CRACKED_PINK_TERRACOTTA);
			m.put(Blocks.GRAY_TERRACOTTA,        BurntModBlocks.CRACKED_GREY_TERRACOTTA);
			m.put(Blocks.LIGHT_GRAY_TERRACOTTA,  BurntModBlocks.CRACKED_LIGHT_GREY_TERRACOTTA);
			m.put(Blocks.CYAN_TERRACOTTA,        BurntModBlocks.CRACKED_CYAN_TERRACOTTA);
			m.put(Blocks.PURPLE_TERRACOTTA,      BurntModBlocks.CRACKED_PURPLE_TERRACOTTA);
			m.put(Blocks.BLUE_TERRACOTTA,        BurntModBlocks.CRACKED_BLUE_TERRACOTTA);
			m.put(Blocks.BROWN_TERRACOTTA,       BurntModBlocks.CRACKED_BROWN_TERRACOTTA);
			m.put(Blocks.GREEN_TERRACOTTA,       BurntModBlocks.CRACKED_GREEN_TERRACOTTA);
			m.put(Blocks.RED_TERRACOTTA,         BurntModBlocks.CRACKED_RED_TERRACOTTA);
			m.put(Blocks.BLACK_TERRACOTTA,       BurntModBlocks.CRACKED_BLACK_TERRACOTTA);
			CRACKS = m;
		}
		return m;
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) return;

		boolean doSoot   = BurntBasicConfigConfiguration.SOOT.get();
		boolean doCracks = BurntBasicConfigConfiguration.CRACKS.get();
		if (!doSoot && !doCracks) return;

		Map<Block, Block> soot   = doSoot   ? sootMap()  : null;
		Map<Block, Block> cracks = doCracks ? crackMap() : null;

		// Callers pass whole numbers, but floor anyway in case one ever doesn't
		int bx = (int) Math.floor(x);
		int by = (int) Math.floor(y);
		int bz = (int) Math.floor(z);

		BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();

		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				for (int dz = -1; dz <= 1; dz++) {
					cursor.set(bx + dx, by + dy, bz + dz);
					BlockState here = world.getBlockState(cursor);
					Block hereBlock = here.getBlock();

					if (soot != null) {
						Block sootDest = soot.get(hereBlock);
						if (sootDest != null && Math.random() <= 0.3) {
							placeWithCopiedProperties(world, cursor.immutable(), here, sootDest.defaultBlockState());
							continue;  // cell handled - don't also try the crack map
						}
					}

					if (cracks != null) {
						Block crackDest = cracks.get(hereBlock);
						if (crackDest != null && Math.random() <= 0.3) {
							BlockState crackedState = blockStateWithDirection(
									crackDest.defaultBlockState(),
									Direction.from2DDataValue(world.getRandom().nextInt(4)));
							placeWithCopiedProperties(world, cursor.immutable(), here, crackedState);
						}
					}
				}
			}
		}
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

	private static BlockState blockStateWithDirection(BlockState blockState, Direction newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (prop instanceof DirectionProperty dp && dp.getPossibleValues().contains(newValue))
			return blockState.setValue(dp, newValue);
		prop = blockState.getBlock().getStateDefinition().getProperty("axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().contains(newValue.getAxis())
				? blockState.setValue(ep, newValue.getAxis())
				: blockState;
	}
}
