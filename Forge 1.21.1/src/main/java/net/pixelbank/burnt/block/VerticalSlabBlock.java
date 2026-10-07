package net.pixelbank.burnt.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

// Vertical slab base, mirroring Quark's VerticalSlabBlock
// Type orientation plus waterlogging, per-orientation shape, click-a-face placement, double combining
// Shapes and type names match Quark so a burnt slab keeps the footprint of the one it replaced

public class VerticalSlabBlock extends Block implements SimpleWaterloggedBlock {
	public static final EnumProperty<VerticalSlabType> TYPE = EnumProperty.create("type", VerticalSlabType.class);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public VerticalSlabBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(TYPE, VerticalSlabType.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	public boolean useShapeForLightOcclusion(BlockState state) {
		return state.getValue(TYPE) != VerticalSlabType.DOUBLE;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPE, WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return state.getValue(TYPE).shape;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		BlockState existing = context.getLevel().getBlockState(pos);
		if (existing.is(this)) {
			return existing.setValue(TYPE, VerticalSlabType.DOUBLE).setValue(WATERLOGGED, false);
		}
		boolean water = context.getLevel().getFluidState(pos).getType() == Fluids.WATER;
		return this.defaultBlockState()
				.setValue(TYPE, VerticalSlabType.fromDirection(getDirectionForPlacement(context)))
				.setValue(WATERLOGGED, water);
	}

	private Direction getDirectionForPlacement(BlockPlaceContext context) {
		Direction face = context.getClickedFace();
		if (face.getAxis().isHorizontal())
			return face.getOpposite();
		Vec3 hit = context.getClickLocation();
		double dx = hit.x - context.getClickedPos().getX() - 0.5;
		double dz = hit.z - context.getClickedPos().getZ() - 0.5;
		return Math.abs(dx) > Math.abs(dz)
				? (dx > 0.0 ? Direction.EAST : Direction.WEST)
				: (dz > 0.0 ? Direction.SOUTH : Direction.NORTH);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		VerticalSlabType type = state.getValue(TYPE);
		if (type == VerticalSlabType.DOUBLE || !context.getItemInHand().is(this.asItem()))
			return false;
		if (!context.replacingClickedOnBlock())
			return true;
		return context.getClickedFace() == type.direction;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
		return state.getValue(TYPE) != VerticalSlabType.DOUBLE && SimpleWaterloggedBlock.super.placeLiquid(world, pos, state, fluidState);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED))
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}
}
