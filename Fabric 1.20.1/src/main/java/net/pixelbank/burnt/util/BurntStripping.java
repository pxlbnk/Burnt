package net.pixelbank.burnt.util;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.IdentityHashMap;
import java.util.Map;

import javax.annotation.Nullable;

// Axe-stripping for the burnt logs and wood
// These are in #minecraft:logs, so vanilla's strip interaction fires but nothing used to convert them
// Driven from one AXE_STRIP handler in BurntMod against the map below

public final class BurntStripping {

	private static Map<Block, Block> map;

	private static Map<Block, Block> map() {
		Map<Block, Block> m = map;
		if (m == null) {
			m = new IdentityHashMap<>();
			// Logs -> stripped burnt log
			put(m, BurntModBlocks.BURNT_LOG, BurntModBlocks.STRIPPED_BURNT_LOG);
			put(m, BurntModBlocks.EMBER_LOG, BurntModBlocks.STRIPPED_BURNT_LOG);
			// Active-fire logs -> smoldering stripped log
			put(m, BurntModBlocks.SMOLDERING_LOG, BurntModBlocks.SMOLDERING_STRIPPED_LOG);
			put(m, BurntModBlocks.SMOLDERING_LOG_2, BurntModBlocks.SMOLDERING_STRIPPED_LOG);
			put(m, BurntModBlocks.SMOLDERING_LOG_3, BurntModBlocks.SMOLDERING_STRIPPED_LOG);
			put(m, BurntModBlocks.BLAZING_LOG, BurntModBlocks.SMOLDERING_STRIPPED_LOG);
			// Broken logs -> stripped broken burnt log
			put(m, BurntModBlocks.BROKEN_BURNT_LOG, BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG);
			put(m, BurntModBlocks.BROKEN_BLAZING_LOG, BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG);
			put(m, BurntModBlocks.BROKEN_BLAZING_LOG_2, BurntModBlocks.STRIPPED_BROKEN_BURNT_LOG);
			// Wood (bark on all sides) -> stripped burnt wood
			put(m, BurntModBlocks.BURNT_WOOD, BurntModBlocks.STRIPPED_BURNT_WOOD);
			put(m, BurntModBlocks.EMBER_WOOD, BurntModBlocks.STRIPPED_BURNT_WOOD);
			put(m, BurntModBlocks.SMOLDERING_WOOD, BurntModBlocks.SMOLDERING_STRIPPED_WOOD);
			put(m, BurntModBlocks.BLAZING_WOOD, BurntModBlocks.SMOLDERING_STRIPPED_WOOD);
			map = m;
		}
		return m;
	}

	private static void put(Map<Block, Block> m, @Nullable Block from, @Nullable Block to) {
		if (from != null && to != null) m.put(from, to);
	}

	// Sooty logs and wood strip to their plain vanilla equivalent, dropping one soot item
	// Only the un-stripped sooty bark variants are covered
	private static Map<Block, Block> sootyMap;

	private static Map<Block, Block> sootyMap() {
		Map<Block, Block> m = sootyMap;
		if (m == null) {
			m = new IdentityHashMap<>();
			put(m, BurntModBlocks.SOOTY_OAK_LOG,      net.minecraft.world.level.block.Blocks.STRIPPED_OAK_LOG);
			put(m, BurntModBlocks.SOOTY_SPRUCE_LOG,   net.minecraft.world.level.block.Blocks.STRIPPED_SPRUCE_LOG);
			put(m, BurntModBlocks.SOOTY_BIRCH_LOG,    net.minecraft.world.level.block.Blocks.STRIPPED_BIRCH_LOG);
			put(m, BurntModBlocks.SOOTY_JUNGLE_LOG,   net.minecraft.world.level.block.Blocks.STRIPPED_JUNGLE_LOG);
			put(m, BurntModBlocks.SOOTY_ACACIA_LOG,   net.minecraft.world.level.block.Blocks.STRIPPED_ACACIA_LOG);
			put(m, BurntModBlocks.SOOTY_DARK_OAK_LOG, net.minecraft.world.level.block.Blocks.STRIPPED_DARK_OAK_LOG);
			put(m, BurntModBlocks.SOOTY_MANGROVE_LOG, net.minecraft.world.level.block.Blocks.STRIPPED_MANGROVE_LOG);
			put(m, BurntModBlocks.SOOTY_CHERRY_LOG,   net.minecraft.world.level.block.Blocks.STRIPPED_CHERRY_LOG);
			put(m, BurntModBlocks.SOOTY_PALE_OAK_LOG, net.pixelbank.burnt.util.BurntVanilla.block("stripped_pale_oak_log"));
			put(m, BurntModBlocks.SOOTY_OAK_WOOD,      net.minecraft.world.level.block.Blocks.STRIPPED_OAK_WOOD);
			put(m, BurntModBlocks.SOOTY_SPRUCE_WOOD,   net.minecraft.world.level.block.Blocks.STRIPPED_SPRUCE_WOOD);
			put(m, BurntModBlocks.SOOTY_BIRCH_WOOD,    net.minecraft.world.level.block.Blocks.STRIPPED_BIRCH_WOOD);
			put(m, BurntModBlocks.SOOTY_JUNGLE_WOOD,   net.minecraft.world.level.block.Blocks.STRIPPED_JUNGLE_WOOD);
			put(m, BurntModBlocks.SOOTY_ACACIA_WOOD,   net.minecraft.world.level.block.Blocks.STRIPPED_ACACIA_WOOD);
			put(m, BurntModBlocks.SOOTY_DARK_OAK_WOOD, net.minecraft.world.level.block.Blocks.STRIPPED_DARK_OAK_WOOD);
			put(m, BurntModBlocks.SOOTY_MANGROVE_WOOD, net.minecraft.world.level.block.Blocks.STRIPPED_MANGROVE_WOOD);
			put(m, BurntModBlocks.SOOTY_CHERRY_WOOD,   net.minecraft.world.level.block.Blocks.STRIPPED_CHERRY_WOOD);
			put(m, BurntModBlocks.SOOTY_PALE_OAK_WOOD, net.pixelbank.burnt.util.BurntVanilla.block("stripped_pale_oak_wood"));
			sootyMap = m;
		}
		return m;
	}

	// True if stripping this block should also drop a soot item (i.e. it's a sooty log/wood).
	public static boolean dropsSoot(BlockState state) {
		return sootyMap().containsKey(state.getBlock());
	}

	/** The stripped BlockState for {@code state}, keeping matching properties, or null if it isn't strippable. */
	@Nullable
	public static BlockState getStripped(BlockState state) {
		Block to = map().get(state.getBlock());
		if (to == null) to = sootyMap().get(state.getBlock());
		if (to == null) return null;
		return BurntBlockUtil.copyProperties(state, to.defaultBlockState());
	}

	/** Fabric strip wiring - Forge uses an event, Fabric needs StrippableBlockRegistry. Call once from onInitialize. */
	public static void registerFabric() {
		for (Map.Entry<Block, Block> e : map().entrySet()) {
			try {
				net.fabricmc.fabric.api.registry.StrippableBlockRegistry.register(e.getKey(), e.getValue());
			} catch (Throwable ignored) {
			}
		}
	}

	private BurntStripping() {}
}
