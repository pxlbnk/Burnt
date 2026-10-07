package net.pixelbank.burnt.block;


import net.pixelbank.burnt.procedures.SmallLogsTickProcedure;
import net.pixelbank.burnt.procedures.SmallLogsSnowballProcedure;
import net.pixelbank.burnt.procedures.BurntLogsParticlesProcedure;
import net.pixelbank.burnt.procedures.BurntLogsFireDamageProcedure;
import net.pixelbank.burnt.init.BurntModBlocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class SmallSmolderingLogBlock extends Block implements SimpleWaterloggedBlock {
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

	// A plain post joins along its own axis so it renders full length
	// A log that came with connections keeps its own shape
	public static BlockState deriveConnections(BlockState state) {
		if (!state.hasProperty(NORTH))
			return state;
		if (state.getValue(NORTH) || state.getValue(EAST) || state.getValue(SOUTH)
				|| state.getValue(WEST) || state.getValue(UP) || state.getValue(DOWN))
			return state;
		return switch (state.getValue(AXIS)) {
			case X -> state.setValue(EAST, true).setValue(WEST, true);
			case Z -> state.setValue(NORTH, true).setValue(SOUTH, true);
			default -> state.setValue(UP, true).setValue(DOWN, true);
		};
	}

	// Rebuild all six connections from the neighbours, so the order logs char in doesn't matter
	// The two faces along the log's own axis always join so it renders full length
	public static BlockState withConnections(LevelAccessor world, BlockPos pos, BlockState state) {
		if (!state.hasProperty(NORTH))
			return state;
		for (Direction d : Direction.values()) {
			boolean axisEnd = d.getAxis() == state.getValue(AXIS);
			state = state.setValue(propertyFor(d),
					axisEnd || connectable(world.getBlockState(pos.relative(d))));
		}
		return state;
	}

	public static boolean connectable(BlockState neighbour) {
		return neighbour.is(BurntModBlocks.SMALL_SMOLDERING_LOG)
				|| neighbour.is(BurntModBlocks.SMALL_BURNT_LOG)
				|| neighbour.is(net.pixelbank.burnt.util.BurntTags.BLOCK_BURNS_TO_SMALL_LOG);
	}

	private static BooleanProperty propertyFor(Direction d) {
		return switch (d) {
			case NORTH -> NORTH;
			case EAST -> EAST;
			case SOUTH -> SOUTH;
			case WEST -> WEST;
			case UP -> UP;
			case DOWN -> DOWN;
		};
	}

	public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);
	private static final VoxelShape SHAPE_X = box(0, 4, 4, 16, 12, 12);
	private static final VoxelShape SHAPE_Y = box(4, 0, 4, 12, 16, 12);
	private static final VoxelShape SHAPE_Z = box(4, 4, 0, 12, 12, 16);

	public SmallSmolderingLogBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.WOOD).strength(2f).lightLevel(s -> 10).noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)
				.isRedstoneConductor((bs, br, bp) -> false).ignitedByLava().instrument(NoteBlockInstrument.BASS));
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y).setValue(STAGE, 0).setValue(WATERLOGGED, false).setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false));
	}

	@Override
	public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.getBlock() == this ? true : super.skipRendering(state, adjacentBlockState, side);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
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
		return (switch (state.getValue(AXIS)) {
			case X -> SHAPE_X;
			case Y -> SHAPE_Y;
			case Z -> SHAPE_Z;
			default -> SHAPE_Y;
		});
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(AXIS, STAGE, WATERLOGGED, NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		BlockState placed = super.getStateForPlacement(context).setValue(AXIS, context.getClickedFace().getAxis()).setValue(STAGE, 0).setValue(WATERLOGGED, flag);
		return withConnections(context.getLevel(), context.getClickedPos(), placed);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return RotatedPillarBlock.rotatePillar(state, rot);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		state = withConnections(world, currentPos, state);
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(BurntModBlocks.SMALL_BURNT_LOG);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 16);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		SmallLogsTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		world.scheduleTick(pos, this, 16);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		BurntLogsParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}


	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		BurntLogsFireDamageProcedure.execute(world, blockstate, entity);
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
		super.stepOn(world, pos, blockstate, entity);
		BurntLogsFireDamageProcedure.execute(world, blockstate, entity);
	}

	@Override
	public void onProjectileHit(Level world, BlockState blockstate, BlockHitResult hit, Projectile entity) {
		SmallLogsSnowballProcedure.execute(world, hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ(), entity);
	}

	// Can crumble to charcoal instead of dropping itself - see BurntAshDrops
	// Lives in getDrops so /loot and tree-felling mods get the same result
	@Override
	public java.util.List<net.minecraft.world.item.ItemStack> getDrops(BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder builder) {
		java.util.List<net.minecraft.world.item.ItemStack> drops = super.getDrops(state, builder);
		net.pixelbank.burnt.util.BurntAshDrops.applyBurntDrops(drops, builder);
		return drops;
	}
}
