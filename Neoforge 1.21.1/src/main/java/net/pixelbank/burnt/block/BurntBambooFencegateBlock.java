package net.pixelbank.burnt.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class BurntBambooFencegateBlock extends FenceGateBlock {
	public BurntBambooFencegateBlock() {
		super(WoodType.OAK, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.WOOD).strength(1f).ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public net.minecraft.world.level.block.state.BlockState updateShape(net.minecraft.world.level.block.state.BlockState state, net.minecraft.core.Direction facing, net.minecraft.world.level.block.state.BlockState facingState, net.minecraft.world.level.LevelAccessor world, net.minecraft.core.BlockPos currentPos, net.minecraft.core.BlockPos facingPos) {
		return net.pixelbank.burnt.util.BurntSableGuard.keepInSubLevel(
				super.updateShape(state, facing, facingState, world, currentPos, facingPos),
				state, world, currentPos);
	}
}