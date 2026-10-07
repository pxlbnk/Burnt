package net.pixelbank.burnt.block;


import net.pixelbank.burnt.procedures.LightSmokeParticlesProcedure;
import net.pixelbank.burnt.procedures.FireBarrelBlastProcedure;
import net.pixelbank.burnt.init.BurntModBlocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class FireBarrelActiveBlock extends Block {
	public FireBarrelActiveBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).sound(SoundType.WOOD).strength(1.8f, 10f).lightLevel(s -> 5).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).ignitedByLava()
				.instrument(NoteBlockInstrument.BASS));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
		return new ItemStack(BurntModBlocks.FIRE_BARREL);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 120);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		FireBarrelBlastProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 120);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		LightSmokeParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void wasExploded(Level world, BlockPos pos, Explosion e) {
		super.wasExploded(world, pos, e);
		FireBarrelBlastProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}