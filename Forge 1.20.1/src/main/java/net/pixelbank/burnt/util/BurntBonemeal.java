package net.pixelbank.burnt.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Bonemeal pushes the burn recovery along by hand, over an area like bonemeal on moss or grass.
 * Converts through copyProperties so shape-carrying properties survive - without it a half-height
 * soil slab came back as a full-size default state.
 */
public final class BurntBonemeal {

	/** Horizontal reach in blocks. Vertical is kept to +-1 so it follows the ground. */
	private static final int RADIUS = 3;

	/** Distance at which the odds reach zero. A little past RADIUS so the edge is not a hard cut. */
	private static final double FALLOFF = 4.5;

	/** Converts {@code from} blocks around {@code origin} into {@code to}, skipping anything buried. */
	public static void spread(ServerLevel level, RandomSource random, BlockPos origin, Block from, BlockState to) {
		// The block actually clicked always converts, so a single use never feels like it missed.
		if (level.getBlockState(origin).is(from))
			level.setBlock(origin, BurntBlockUtil.copyProperties(level.getBlockState(origin), to), 3);

		for (BlockPos p : BlockPos.betweenClosed(origin.offset(-RADIUS, -1, -RADIUS), origin.offset(RADIUS, 1, RADIUS))) {
			// hasChunkAt guard - an unloaded lookup forces a sync chunk load and stalls the server
			if (!level.hasChunkAt(p))
				continue;
			if (!level.getBlockState(p).is(from))
				continue;
			// Needs headroom, same as grass - do not convert a block with something sitting on it.
			if (!level.getBlockState(p.above()).isAir() && level.getBlockState(p.above()).canOcclude())
				continue;
			double d = Math.sqrt(p.distSqr(origin));
			if (random.nextDouble() > 1.0 - (d / FALLOFF))
				continue;
			// betweenClosed hands back a shared mutable position, so take a copy before storing it.
			level.setBlock(p.immutable(), BurntBlockUtil.copyProperties(level.getBlockState(p), to), 3);
		}
	}

	private BurntBonemeal() {}
}
