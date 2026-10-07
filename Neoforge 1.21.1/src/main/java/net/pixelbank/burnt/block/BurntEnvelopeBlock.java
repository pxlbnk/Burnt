package net.pixelbank.burnt.block;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import net.pixelbank.burnt.procedures.AshParticlesProcedure;
import net.pixelbank.burnt.procedures.SableBreakProcedure;


import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class BurntEnvelopeBlock extends Block {
	/** Scheduled-tick interval for the Sable break check (twice/sec). */
	private static final int TICK_INTERVAL = 10;

	public BurntEnvelopeBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY)
				.sound(SoundType.WOOL)
				.strength(0.8f).pushReaction(PushReaction.DESTROY).ignitedByLava());
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		// Scheduled-tick chain so charred envelopes on a Sable creation can break away when unsupported
		// SableBreakProcedure no-ops off a sub-level, so world-placed envelopes just tick cheaply
		if (!world.isClientSide()) world.scheduleTick(pos, this, TICK_INTERVAL);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		// Burnt envelopes break readily on a Sable creation, and immediately when unsupported,
		// so a burning ship's balloon collapses as the blocks around it fall away
		if (SableBreakProcedure.executeEnvelope(world, pos.getX(), pos.getY(), pos.getZ())) return;
		if (world.getBlockState(pos).getBlock() == this) {
			world.scheduleTick(pos, this, TICK_INTERVAL);
		}
	}


	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		AshParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}
