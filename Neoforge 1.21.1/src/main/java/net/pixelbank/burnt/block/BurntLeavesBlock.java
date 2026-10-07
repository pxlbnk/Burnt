package net.pixelbank.burnt.block;

import net.pixelbank.burnt.procedures.BurntLeavesTickProcedure;
import net.pixelbank.burnt.procedures.BurntLeavesShearsProcedure;
import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.core.Direction;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class BurntLeavesBlock extends LeavesBlock {
	public BurntLeavesBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.GRASS).strength(0.2f).noOcclusion().randomTicks().pushReaction(PushReaction.DESTROY).isRedstoneConductor((bs, br, bp) -> false).ignitedByLava()
				.isSuffocating((bs, br, bp) -> false).isViewBlocking((bs, br, bp) -> false));
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 3;
	}

	// Drop floating burnt leaves on tick, unless a tree-stick block is adjacent
	// A charred canopy whose trunk a Dynamic Trees fall removed is still part of a tree, so keep it
	// Mirrors the stick check in BurntLeavesStage3Procedure so every stage uses the same test
	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int newDistance = computeDistance(level, pos);
		if (newDistance == 7) {
			if (hasStickNeighbour(level, pos)) {
				// Skeleton still there - leave the leaf, just don't update DISTANCE
				return;
			}
			Block.dropResources(state, level, pos);
			level.removeBlock(pos, false);
			return;
		}
		if (newDistance != state.getValue(DISTANCE)) {
			level.setBlock(pos, state.setValue(DISTANCE, newDistance), 3);
		}
	}

	// True if any face-neighbour is in BLOCK_BURNT_LEAVES_STICK, the same test the stage-3 procedure uses
	private static boolean hasStickNeighbour(ServerLevel level, BlockPos pos) {
		BlockPos.MutableBlockPos m = new BlockPos.MutableBlockPos();
		for (Direction dir : Direction.values()) {
			m.setWithOffset(pos, dir);
			if (level.getBlockState(m).is(BurntTags.BLOCK_BURNT_LEAVES_STICK)) return true;
		}
		return false;
	}

	private static int computeDistance(ServerLevel level, BlockPos pos) {
		int dist = 7;
		BlockPos.MutableBlockPos m = new BlockPos.MutableBlockPos();
		for (Direction dir : Direction.values()) {
			m.setWithOffset(pos, dir);
			int d = LeavesBlock.getOptionalDistanceAt(level.getBlockState(m)).orElse(7);
			dist = Math.min(dist, d + 1);
			if (dist == 1) break;
		}
		return dist;
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		BurntLeavesTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState blockstate, Player entity) {
		blockstate = super.playerWillDestroy(world, pos, blockstate, entity);
		BurntLeavesShearsProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
		return blockstate;
	}
}