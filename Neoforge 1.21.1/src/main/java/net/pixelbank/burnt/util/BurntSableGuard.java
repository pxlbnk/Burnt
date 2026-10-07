package net.pixelbank.burnt.util;

import net.pixelbank.burnt.compat.sable.BurntSableCompat;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

// Sable sub-level guard for fragile connecting blocks - fences, gates, small logs
// A fragile block shattering during the Rapier step runs destroyBlock, and if a neighbour's
// updateShape returns a changed state that setBlock makes Sable rebuild the collider from inside
// its own physics step, which hangs the tick
// So inside a sub-level the block keeps its current state - connections stay as placed, which is
// cosmetic. No-op without Sable. NeoForge 1.21.1 only
public final class BurntSableGuard {

	public static BlockState keepInSubLevel(BlockState updated, BlockState original, LevelAccessor world, BlockPos pos) {
		if (updated == original)
			return updated;
		if (world instanceof ServerLevel sl
				&& BurntSableCompat.isInSubLevel(sl, pos.getX(), pos.getY(), pos.getZ()))
			return original;
		return updated;
	}

	private BurntSableGuard() {}
}
