package net.pixelbank.burnt.block;


import net.pixelbank.burnt.procedures.TestSootValidPlacementProcedure;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class SootBlockBlock extends Block {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 4);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	private static final VoxelShape SHAPE_1_NORTH = Shapes.or(box(0, 0, 15, 16, 16, 16), box(0, 0, 0, 1, 16, 16));
	private static final VoxelShape SHAPE_1_SOUTH = Shapes.or(box(0, 0, 0, 16, 16, 1), box(15, 0, 0, 16, 16, 16));
	private static final VoxelShape SHAPE_1_EAST = Shapes.or(box(0, 0, 0, 1, 16, 16), box(0, 0, 0, 16, 16, 1));
	private static final VoxelShape SHAPE_1_WEST = Shapes.or(box(15, 0, 0, 16, 16, 16), box(0, 0, 15, 16, 16, 16));
	private static final VoxelShape SHAPE_2_NORTH = Shapes.or(box(0, 0, 0, 16, 16, 1), box(0, 0, 15, 16, 16, 16));
	private static final VoxelShape SHAPE_2_SOUTH = Shapes.or(box(0, 0, 15, 16, 16, 16), box(0, 0, 0, 16, 16, 1));
	private static final VoxelShape SHAPE_2_EAST = Shapes.or(box(15, 0, 0, 16, 16, 16), box(0, 0, 0, 1, 16, 16));
	private static final VoxelShape SHAPE_2_WEST = Shapes.or(box(0, 0, 0, 1, 16, 16), box(15, 0, 0, 16, 16, 16));
	private static final VoxelShape SHAPE_3_NORTH = Shapes.or(box(0, 0, 0, 1, 16, 16), box(15, 0, 0, 16, 16, 16), box(0, 0, 15, 16, 16, 16));
	private static final VoxelShape SHAPE_3_SOUTH = Shapes.or(box(15, 0, 0, 16, 16, 16), box(0, 0, 0, 1, 16, 16), box(0, 0, 0, 16, 16, 1));
	private static final VoxelShape SHAPE_3_EAST = Shapes.or(box(0, 0, 0, 16, 16, 1), box(0, 0, 15, 16, 16, 16), box(0, 0, 0, 1, 16, 16));
	private static final VoxelShape SHAPE_3_WEST = Shapes.or(box(0, 0, 15, 16, 16, 16), box(0, 0, 0, 16, 16, 1), box(15, 0, 0, 16, 16, 16));
	private static final VoxelShape SHAPE_4_NORTH = Shapes.or(box(0, 0, 0, 16, 16, 1), box(15, 0, 0, 16, 16, 16), box(0, 0, 0, 1, 16, 16), box(0, 0, 15, 16, 16, 16));
	private static final VoxelShape SHAPE_4_SOUTH = Shapes.or(box(0, 0, 15, 16, 16, 16), box(0, 0, 0, 1, 16, 16), box(15, 0, 0, 16, 16, 16), box(0, 0, 0, 16, 16, 1));
	private static final VoxelShape SHAPE_4_EAST = Shapes.or(box(15, 0, 0, 16, 16, 16), box(0, 0, 15, 16, 16, 16), box(0, 0, 0, 16, 16, 1), box(0, 0, 0, 1, 16, 16));
	private static final VoxelShape SHAPE_4_WEST = Shapes.or(box(0, 0, 0, 1, 16, 16), box(0, 0, 0, 16, 16, 1), box(0, 0, 15, 16, 16, 16), box(15, 0, 0, 16, 16, 16));
	private static final VoxelShape SHAPE_NORTH = box(0, 0, 15, 16, 16, 16);
	private static final VoxelShape SHAPE_SOUTH = box(0, 0, 0, 16, 16, 1);
	private static final VoxelShape SHAPE_EAST = box(0, 0, 0, 1, 16, 16);
	private static final VoxelShape SHAPE_WEST = box(15, 0, 0, 16, 16, 16);

	public SootBlockBlock() {
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.SAND)
				.instabreak().lightLevel(s -> (new Object() {
					public int getLightLevel() {
						if (s.getValue(BLOCKSTATE) == 1)
							return 0;
						if (s.getValue(BLOCKSTATE) == 2)
							return 0;
						if (s.getValue(BLOCKSTATE) == 3)
							return 0;
						if (s.getValue(BLOCKSTATE) == 4)
							return 0;
						return 0;
					}
				}.getLightLevel())).noCollission().noOcclusion().pushReaction(PushReaction.DESTROY).isRedstoneConductor((bs, br, bp) -> false).replaceable());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(NORTH, false).setValue(SOUTH, false).setValue(EAST, false).setValue(WEST, false));
	}


	@Override
	public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.getBlock() == this ? true : super.skipRendering(state, adjacentBlockState, side);
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
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(BLOCKSTATE) == 1) {
			return (switch (state.getValue(FACING)) {
				case NORTH -> SHAPE_1_NORTH;
				case SOUTH -> SHAPE_1_SOUTH;
				case EAST -> SHAPE_1_EAST;
				case WEST -> SHAPE_1_WEST;
				default -> SHAPE_1_NORTH;
			});
		}
		if (state.getValue(BLOCKSTATE) == 2) {
			return (switch (state.getValue(FACING)) {
				case NORTH -> SHAPE_2_NORTH;
				case SOUTH -> SHAPE_2_SOUTH;
				case EAST -> SHAPE_2_EAST;
				case WEST -> SHAPE_2_WEST;
				default -> SHAPE_2_NORTH;
			});
		}
		if (state.getValue(BLOCKSTATE) == 3) {
			return (switch (state.getValue(FACING)) {
				case NORTH -> SHAPE_3_NORTH;
				case SOUTH -> SHAPE_3_SOUTH;
				case EAST -> SHAPE_3_EAST;
				case WEST -> SHAPE_3_WEST;
				default -> SHAPE_3_NORTH;
			});
		}
		if (state.getValue(BLOCKSTATE) == 4) {
			return (switch (state.getValue(FACING)) {
				case NORTH -> SHAPE_4_NORTH;
				case SOUTH -> SHAPE_4_SOUTH;
				case EAST -> SHAPE_4_EAST;
				case WEST -> SHAPE_4_WEST;
				default -> SHAPE_4_NORTH;
			});
		}
		return (switch (state.getValue(FACING)) {
			case NORTH -> SHAPE_NORTH;
			case SOUTH -> SHAPE_SOUTH;
			case EAST -> SHAPE_EAST;
			case WEST -> SHAPE_WEST;
			default -> SHAPE_NORTH;
		});
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, NORTH, SOUTH, EAST, WEST, BLOCKSTATE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (context.getClickedFace().getAxis() == Direction.Axis.Y)
			return super.getStateForPlacement(context).setValue(FACING, Direction.NORTH).setValue(NORTH, false).setValue(SOUTH, false).setValue(EAST, false).setValue(WEST, false);
		return super.getStateForPlacement(context).setValue(FACING, context.getClickedFace()).setValue(NORTH, false).setValue(SOUTH, false).setValue(EAST, false).setValue(WEST, false);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		if (worldIn instanceof LevelAccessor world) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return TestSootValidPlacementProcedure.execute(world, x, y, z);
		}
		return super.canSurvive(blockstate, worldIn, pos);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}
}