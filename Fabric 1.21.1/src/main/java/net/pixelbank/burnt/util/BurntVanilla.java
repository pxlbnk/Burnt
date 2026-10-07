package net.pixelbank.burnt.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

// Lookup for vanilla blocks missing from the version this tree targets, like pale oak on 1.21.1
// Resolves by id at runtime instead of a Blocks constant, so the same code compiles either way
// block() returns null when the id isn't registered

public final class BurntVanilla {
	private static final Map<String, Block> CACHE = new ConcurrentHashMap<>();

	private BurntVanilla() {
	}

	// Null when the block isn't registered. Only call in-world, and a miss is cached
	public static Block block(String path) {
		Block found = CACHE.computeIfAbsent(path, p -> {
			ResourceLocation id = ResourceLocation.fromNamespaceAndPath("minecraft", p);
			Block b = BuiltInRegistries.BLOCK.containsKey(id) ? BuiltInRegistries.BLOCK.get(id) : null;
			return b == null ? Blocks.AIR : b;
		});
		return found == Blocks.AIR ? null : found;
	}

	public static boolean isPresent(String path) {
		return block(path) != null;
	}

	// Never matches when the block is absent, so a missing id can't collide with air.
	public static boolean is(BlockState state, String path) {
		Block b = block(path);
		return b != null && state.getBlock() == b;
	}

	public static BlockState stateOf(String path) {
		Block b = block(path);
		return b != null ? b.defaultBlockState() : Blocks.AIR.defaultBlockState();
	}

	public static ItemStack itemOf(String path) {
		Block b = block(path);
		return b != null ? new ItemStack(b) : ItemStack.EMPTY;
	}
}
