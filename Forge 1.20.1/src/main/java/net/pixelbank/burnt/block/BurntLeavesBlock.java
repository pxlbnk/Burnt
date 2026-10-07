package net.pixelbank.burnt.block;

import net.pixelbank.burnt.procedures.BurntLeavesTickProcedure;
import net.pixelbank.burnt.procedures.BurntLeavesShearsProcedure;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.LeavesBlock;
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

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		BurntLeavesTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState blockstate, Player entity) {
		super.playerWillDestroy(world, pos, blockstate, entity);
		BurntLeavesShearsProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}
}