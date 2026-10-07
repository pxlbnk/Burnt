package net.pixelbank.burnt.block;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SmokeDetectorBlock extends Block {

	public static final BooleanProperty            POWERED = BlockStateProperties.POWERED;
	public static final EnumProperty<AttachFace>   FACE    = BlockStateProperties.ATTACH_FACE;
	public static final DirectionProperty           FACING  = BlockStateProperties.HORIZONTAL_FACING;

	private static final int RADIUS        = 7;
	private static final int SCAN_INTERVAL = 20;

	// Floor / ceiling - 8 px slab
	private static final VoxelShape SHAPE_FLOOR   = Block.box(0, 0, 0, 16,  8, 16);
	private static final VoxelShape SHAPE_CEILING = Block.box(0, 8, 0, 16, 16, 16);

	// Wall mount - FACING points away from the wall
	private static final VoxelShape SHAPE_WALL_NORTH = Block.box(0, 0,  8, 16, 16, 16); // wall to south
	private static final VoxelShape SHAPE_WALL_SOUTH = Block.box(0, 0,  0, 16, 16,  8); // wall to north
	private static final VoxelShape SHAPE_WALL_EAST  = Block.box(0, 0,  0,  8, 16, 16); // wall to west
	private static final VoxelShape SHAPE_WALL_WEST  = Block.box(8, 0,  0, 16, 16, 16); // wall to east

	public SmokeDetectorBlock() {
		super(BlockBehaviour.Properties.of()
				.mapColor(MapColor.METAL)
				.sound(SoundType.STONE)
				.strength(0.5f, 3f)
				.noOcclusion());
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACE,    AttachFace.FLOOR)
				.setValue(FACING,  Direction.NORTH)
				.setValue(POWERED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACE, FACING, POWERED);
	}

	// Placement

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		Direction clicked = ctx.getClickedFace();
		Direction hDir    = ctx.getHorizontalDirection().getOpposite();
		return switch (clicked) {
			case UP   -> defaultBlockState().setValue(FACE, AttachFace.FLOOR)  .setValue(FACING, hDir);
			case DOWN -> defaultBlockState().setValue(FACE, AttachFace.CEILING).setValue(FACING, hDir);
			default   -> defaultBlockState().setValue(FACE, AttachFace.WALL)   .setValue(FACING, clicked);
		};
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		return switch (state.getValue(FACE)) {
			case FLOOR   -> world.getBlockState(pos.below()).isFaceSturdy(world, pos.below(), Direction.UP);
			case CEILING -> world.getBlockState(pos.above()).isFaceSturdy(world, pos.above(), Direction.DOWN);
			case WALL    -> {
				// The wall block is in the direction opposite to where the face points.
				Direction wallDir = state.getValue(FACING).getOpposite();
				BlockPos  wallPos = pos.relative(wallDir);
				yield world.getBlockState(wallPos).isFaceSturdy(world, wallPos, state.getValue(FACING));
			}
		};
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
		return switch (state.getValue(FACE)) {
			case FLOOR   -> SHAPE_FLOOR;
			case CEILING -> SHAPE_CEILING;
			case WALL    -> switch (state.getValue(FACING)) {
				case NORTH -> SHAPE_WALL_NORTH;
				case SOUTH -> SHAPE_WALL_SOUTH;
				case EAST  -> SHAPE_WALL_EAST;
				case WEST  -> SHAPE_WALL_WEST;
				default    -> SHAPE_FLOOR;
			};
		};
	}

	// No collision, projectiles and entities pass through
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
		return Shapes.empty();
	}

	// Tick scheduling

	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		if (!world.isClientSide()) {
			world.scheduleTick(pos, this, SCAN_INTERVAL);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		boolean shouldBePowered = scanForFire(world, pos);
		if (shouldBePowered != state.getValue(POWERED)) {
			world.setBlock(pos, state.setValue(POWERED, shouldBePowered), Block.UPDATE_ALL);
			updateNeighbours(world, pos, state);
		}
		world.scheduleTick(pos, this, SCAN_INTERVAL);
	}

	/**
	 * Propagate the redstone change the way a lever does - updateNeighborsAt on this block and on
	 * the one it's mounted to, so strong power passes through the support. The old per-direction
	 * loop updated world redstone but never drove a dispenser on a Sable contraption.
	 */
	private void updateNeighbours(Level world, BlockPos pos, BlockState state) {
		world.updateNeighborsAt(pos, this);
		Direction supportDir = switch (state.getValue(FACE)) {
			case FLOOR   -> Direction.DOWN;
			case CEILING -> Direction.UP;
			default      -> state.getValue(FACING).getOpposite();
		};
		world.updateNeighborsAt(pos.relative(supportDir), this);
	}

	private boolean scanForFire(Level world, BlockPos center) {
		for (BlockPos nearby : BlockPos.betweenClosed(
				center.offset(-RADIUS, -RADIUS, -RADIUS),
				center.offset(RADIUS, RADIUS, RADIUS))) {
			if (world.getBlockState(nearby).is(BurntTags.BLOCK_ON_FIRE)) {
				return true;
			}
		}
		return false;
	}

	// Redstone output

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction dir) {
		return state.getValue(POWERED) ? 15 : 0;
	}

	/**
	 * Strong power as well as weak. Some consumers - notably redstone on a Sable contraption - only
	 * read strong power, so weak alone changed the texture but never reached a dispenser.
	 */
	@Override
	public int getDirectSignal(BlockState state, BlockGetter world, BlockPos pos, Direction dir) {
		return state.getValue(POWERED) ? 15 : 0;
	}
}
