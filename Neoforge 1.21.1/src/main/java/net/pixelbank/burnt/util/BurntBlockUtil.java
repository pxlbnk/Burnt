package net.pixelbank.burnt.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

// Shared helpers for the block procedures.
public final class BurntBlockUtil {

	// One random source for all the tick procedures to share.
	public static final RandomSource RAND = RandomSource.create();

	// #burnt:is_water - blocks that count as water for putting fires out.
	public static final TagKey<Block> TAG_IS_WATER =
			TagKey.create(Registries.BLOCK, ResourceLocation.parse("burnt:is_water"));

	// Called by ~30 tick procedures every tick, so it reuses a MutableBlockPos and caches one slot
	// No locking - server thread only, and a stale read just recomputes
	private static final ThreadLocal<BlockPos.MutableBlockPos> SCRATCH_POS =
			ThreadLocal.withInitial(BlockPos.MutableBlockPos::new);

	private static long cacheTick = Long.MIN_VALUE;
	private static long cachePackedPos = 0L;
	private static int cacheWorldHash = 0;
	private static boolean cacheResult = false;

	// True if any of the 6 neighbouring blocks counts as water.
	public static boolean hasAdjacentWater(LevelAccessor world, double x, double y, double z) {
		int bx = (int) Math.floor(x);
		int by = (int) Math.floor(y);
		int bz = (int) Math.floor(z);

		// Only cache when the tick is known - a non-Level caller has no gameTime and recomputes
		long now = (world instanceof Level lvl) ? lvl.getGameTime() : Long.MIN_VALUE;
		long key = BlockPos.asLong(bx, by, bz);
		int worldHash = System.identityHashCode(world);
		if (now != Long.MIN_VALUE
				&& now == cacheTick
				&& key == cachePackedPos
				&& worldHash == cacheWorldHash) {
			return cacheResult;
		}

		BlockPos.MutableBlockPos pos = SCRATCH_POS.get();
		boolean result =
				   world.getBlockState(pos.set(bx + 1, by, bz)).is(TAG_IS_WATER)
				|| world.getBlockState(pos.set(bx - 1, by, bz)).is(TAG_IS_WATER)
				|| world.getBlockState(pos.set(bx, by, bz + 1)).is(TAG_IS_WATER)
				|| world.getBlockState(pos.set(bx, by, bz - 1)).is(TAG_IS_WATER)
				|| world.getBlockState(pos.set(bx, by - 1, bz)).is(TAG_IS_WATER)
				|| world.getBlockState(pos.set(bx, by + 1, bz)).is(TAG_IS_WATER);

		cacheTick = now;
		cachePackedPos = key;
		cacheWorldHash = worldHash;
		cacheResult = result;
		return result;
	}

	// Like canSeeSkyFromBelowWater, but leaves don't block rain
	// Vanilla's heightmap counts leaves as solid, so burning leaves under a canopy never went out
	public static boolean rainCanReach(LevelAccessor world, BlockPos pos) {
		int maxY = world.getMaxBuildHeight();
		BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
		while (cursor.getY() < maxY) {
			BlockState state = world.getBlockState(cursor);
			Block block = state.getBlock();
			boolean isLeaf = state.is(BlockTags.LEAVES) || block instanceof LeavesBlock;
			if (state.isAir() || isLeaf) {
				if (world.canSeeSkyFromBelowWater(cursor)) return true;
				cursor.move(0, 1, 0);
				continue;
			}
			return false;
		}
		return true;
	}

	// Carry over every property the two blocks share, so the new block keeps its orientation
	// The try/catch covers two mods using the same property name for different value types
	public static BlockState copyProperties(BlockState from, BlockState to) {
		for (Property<?> prop : from.getProperties()) {
			Property<?> match = to.getBlock().getStateDefinition().getProperty(prop.getName());
			if (match != null) {
				to = setValue(to, match, from.getValue(prop));
			}
		}
		return to;
	}

	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>> BlockState setValue(BlockState state, Property<T> prop, Comparable<?> value) {
		try {
			return state.setValue(prop, (T) value);
		} catch (Exception e) {
			return state;
		}
	}

	/**
	 * Spawns a falling block that never drops an item.
	 * Vanilla drops it whenever it cannot place on landing, which is constant for collapse debris
	 * coming to rest on slabs, carpets and fences.
	 */
	public static FallingBlockEntity fallNoDrop(ServerLevel level, BlockPos pos, BlockState state) {
		BurntCollapseBudget.noteSpawn(level);
		FallingBlockEntity _fbe = FallingBlockEntity.fall(level, pos, state);
		_fbe.dropItem = false;
		return _fbe;
	}

	/** True for a dedicated dark oak variant, or a generic smoldering block carrying species=dark_oak. */
	public static boolean isDarkOak(BlockState state) {
		if (state == null) return false;
		if (WoodSpecies.readFrom(state) == WoodSpecies.DARK_OAK) return true;
		ResourceLocation id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
		return id != null && id.getPath().contains("dark_oak");
	}

	/** Spread-rate multiplier. Dark oak is dense, so it smoulders longer before lighting neighbours. 1.0 otherwise. */
	public static double darkOakSpreadFactor(LevelAccessor world, double x, double y, double z) {
		if (!isDarkOak(world.getBlockState(BlockPos.containing(x, y, z)))) return 1.0;
		return Math.max(0.0, Math.min(1.0, BurntBasicConfigConfiguration.DARK_OAK_SPREAD.get() / 100.0));
	}

	/**
	 * True when nothing solid sits on any of the four horizontal sides - a bare trunk rather than
	 * part of a wall. Anything that doesn't occlude counts as exposed, so a trunk running up through
	 * its own canopy is still a trunk.
	 */
	public static boolean isHorizontallyExposed(LevelAccessor world, double x, double y, double z) {
		BlockPos pos = BlockPos.containing(x, y, z);
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			BlockState side = world.getBlockState(pos.relative(dir));
			if (!side.isAir() && side.canOcclude()) return false;
		}
		return true;
	}

	private BurntBlockUtil() {}
}
