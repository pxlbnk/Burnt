package net.pixelbank.burnt.block;


import net.pixelbank.burnt.procedures.FireBarrelBlastProcedure;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;

public class FireBarrelBlastBlockBlock extends Block {
	public FireBarrelBlastBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.EMPTY).strength(10f, 0f).lightLevel(s -> 15).noCollission().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).replaceable());
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(Blocks.AIR);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		FireBarrelBlastProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}