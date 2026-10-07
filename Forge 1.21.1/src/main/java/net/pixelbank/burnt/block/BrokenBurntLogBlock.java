package net.pixelbank.burnt.block;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


import net.pixelbank.burnt.procedures.BrokenWaterloggedProcedure;
import net.pixelbank.burnt.procedures.BrokenBurntLogsParticlesProcedure;
import net.pixelbank.burnt.procedures.BrokenBurntLogRandomProcedure;


import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
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
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class BrokenBurntLogBlock extends Block implements SimpleWaterloggedBlock {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 1);
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty FRAGILE = BooleanProperty.create("fragile");
	private static final VoxelShape SHAPE_1_X = Shapes.or(box(0, 0, 7, 16, 7, 16), box(0, 7, 9, 16, 9, 16), box(0, 7, 0, 16, 16, 9));
	private static final VoxelShape SHAPE_1_Y = Shapes.or(box(7, 0, 0, 16, 16, 7), box(9, 0, 7, 16, 16, 9), box(0, 0, 7, 9, 16, 16));
	private static final VoxelShape SHAPE_1_Z = Shapes.or(box(7, 0, 0, 16, 7, 16), box(9, 7, 0, 16, 9, 16), box(0, 7, 0, 9, 16, 16));
	private static final VoxelShape SHAPE_X = Shapes.or(box(0, 0, 0, 16, 7, 9), box(0, 7, 0, 16, 9, 7), box(0, 7, 7, 16, 16, 16));
	private static final VoxelShape SHAPE_Y = Shapes.or(box(0, 0, 0, 9, 16, 7), box(0, 0, 7, 7, 16, 9), box(7, 0, 7, 16, 16, 16));
	private static final VoxelShape SHAPE_Z = Shapes.or(box(0, 0, 0, 9, 7, 16), box(0, 7, 0, 7, 9, 16), box(7, 7, 0, 16, 16, 16));

	public BrokenBurntLogBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.WOOD).strength(1f, 0f).lightLevel(s -> (new Object() {
			public int getLightLevel() {
				if (s.getValue(BLOCKSTATE) == 1)
					return 0;
				return 0;
			}
		}.getLightLevel())).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).ignitedByLava().instrument(NoteBlockInstrument.BASS));
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y).setValue(FRAGILE, false).setValue(WATERLOGGED, false));
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
		if (state.getValue(BLOCKSTATE) == 1) {
			return (switch (state.getValue(AXIS)) {
				case X -> SHAPE_1_X;
				case Y -> SHAPE_1_Y;
				case Z -> SHAPE_1_Z;
				default -> SHAPE_1_Y;
			});
		}
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
		builder.add(AXIS, FRAGILE, WATERLOGGED, BLOCKSTATE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(AXIS, context.getClickedFace().getAxis()).setValue(FRAGILE, false).setValue(WATERLOGGED, flag);
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
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		BrokenWaterloggedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		BrokenBurntLogsParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}


	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		BrokenBurntLogRandomProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	// Ash drops via loot too, so tree-felling mods still yield it - see BurntAshDrops
	@Override
	public java.util.List<net.minecraft.world.item.ItemStack> getDrops(BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder builder) {
		java.util.List<net.minecraft.world.item.ItemStack> drops = super.getDrops(state, builder);
		net.pixelbank.burnt.util.BurntAshDrops.applyBurntDrops(drops, builder);
		return drops;
	}
}
