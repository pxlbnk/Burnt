package net.pixelbank.burnt.block;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import net.pixelbank.burnt.procedures.AshParticlesProcedure;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.RandomSource;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.pixelbank.burnt.init.BurntModBlocks;

public class BurntSailBlock extends Block {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	// Frame geometry occupies y=5..9 in the default (facing=UP) model orientation.
	private static final VoxelShape SHAPE_UP    = Block.box(0,  5,  0, 16,  9, 16);
	private static final VoxelShape SHAPE_DOWN  = Block.box(0,  7,  0, 16, 11, 16);
	private static final VoxelShape SHAPE_NORTH = Block.box(0,  0,  7, 16, 16, 11);
	private static final VoxelShape SHAPE_SOUTH = Block.box(0,  0,  5, 16, 16,  9);
	private static final VoxelShape SHAPE_EAST  = Block.box(7,  0,  0, 11, 16, 16);
	private static final VoxelShape SHAPE_WEST  = Block.box(5,  0,  0,  9, 16, 16);

	public BurntSailBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY)
				.sound(SoundType.WOOL)
				.strength(0.8f).pushReaction(PushReaction.DESTROY).ignitedByLava());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case DOWN  -> SHAPE_DOWN;
			case NORTH -> SHAPE_NORTH;
			case SOUTH -> SHAPE_SOUTH;
			case EAST  -> SHAPE_EAST;
			case WEST  -> SHAPE_WEST;
			default    -> SHAPE_UP;
		};
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getClickedFace().getOpposite());
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(BurntModBlocks.BURNT_SAIL);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		AshParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}
