package net.pixelbank.burnt.block;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import net.pixelbank.burnt.procedures.AshParticlesProcedure;


import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.util.RandomSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class BurntWoolBlock extends Block {
	public BurntWoolBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY)
				.sound(SoundType.WOOL)
				.strength(0.8f).pushReaction(PushReaction.DESTROY).ignitedByLava());
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}


	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		AshParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}