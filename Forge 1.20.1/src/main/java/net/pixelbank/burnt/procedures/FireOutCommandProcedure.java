package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

public class FireOutCommandProcedure {

	// ChunkMap#getChunks() is AT'd to public - see META-INF/accesstransformer.cfg
	private static Iterable<ChunkHolder> getChunks(ChunkMap chunkMap) {
		return chunkMap.getChunks();
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!(world instanceof ServerLevel serverLevel)) return;

		// Phase 1 - clear all visual fire everywhere, except campfires
		for (ServerLevel level : serverLevel.getServer().getAllLevels()) {
			getChunks(level.getChunkSource().chunkMap).forEach(chunkHolder -> {
				LevelChunk chunk = chunkHolder.getTickingChunk();
				if (chunk == null) return;

				ChunkPos cp = chunk.getPos();
				int x0 = cp.getMinBlockX();
				int z0 = cp.getMinBlockZ();
				int minY = level.getMinBuildHeight();
				int maxY = level.getMaxBuildHeight();

				for (int bx = x0; bx < x0 + 16; bx++) {
					for (int bz = z0; bz < z0 + 16; bz++) {
						for (int by = minY; by < maxY; by++) {
							BlockPos pos = new BlockPos(bx, by, bz);
							BlockState state = chunk.getBlockState(pos);
							if (state.isAir()) continue;
							if (!state.is(BurntTags.BLOCK_ACTIVE_FIRE)) continue;
							level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
						}
					}
				}
			});
		}

		// Phase 2 - set killFire so smoldering blocks char next tick, pausing spread for 60s
		BurntMod.killFire = true;
		BurntMod.queueServerWork(1200, () -> BurntMod.killFire = false);

		// Phase 3 - force a short rain burst, or just set forceRain if it's raining already
		ServerLevel overworld = serverLevel.getServer().overworld();
		boolean alreadyRaining = overworld.getLevelData().isRaining();
		BurntMod.forceRain = true;
		if (alreadyRaining) {
			// Rain is ongoing - forceRain rides alongside it, clears quickly
			BurntMod.queueServerWork(40, () -> BurntMod.forceRain = false);
		} else {
			overworld.setWeatherParameters(0, 40, true, false);
			BurntMod.queueServerWork(40, () -> {
				BurntMod.forceRain = false;
				overworld.setWeatherParameters(6000, 0, false, false);
			});
		}
	}
}
