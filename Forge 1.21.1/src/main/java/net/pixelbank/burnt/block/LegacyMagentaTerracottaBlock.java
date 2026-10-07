package net.pixelbank.burnt.block;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.util.BurntBlockUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

// Legacy shim for the old mis-spelled id, so blocks in existing worlds still load
// Heals itself into cracked_magenta_terracotta on its next random tick
// Not registered as an item, so it never shows in creative

public class LegacyMagentaTerracottaBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public LegacyMagentaTerracottaBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 3f).randomTicks());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		level.setBlock(pos, BurntBlockUtil.copyProperties(state, BurntModBlocks.CRACKED_MAGENTA_TERRACOTTA.defaultBlockState()), 3);
	}
}
