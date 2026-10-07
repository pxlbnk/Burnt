package net.pixelbank.burnt.block;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import net.pixelbank.burnt.procedures.SmolderingCreateSailTickProcedure;
import net.pixelbank.burnt.procedures.SmolderingPlanksParticlesProcedure;
import net.pixelbank.burnt.procedures.BurntLogsFireDamageProcedure;
import net.pixelbank.burnt.procedures.SnowballExtinguishProcedure;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.entity.projectile.Projectile;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class SmolderingCreateSailBlock extends Block {
	public static final BooleanProperty HOT = BooleanProperty.create("hot");
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	// Bounding box for default (facing=UP) orientation: frame y=5..9, sail (with canvas) y=5..10.
	private final VoxelShape shapeUp;
	private final VoxelShape shapeDown;
	private final VoxelShape shapeNorth;
	private final VoxelShape shapeSouth;
	private final VoxelShape shapeEast;
	private final VoxelShape shapeWest;

	public SmolderingCreateSailBlock() {
		this(true);
	}

	public SmolderingCreateSailBlock(boolean hasCanvas) {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.FIRE)
				.sound(SoundType.WOOL)
				.strength(0.8f).lightLevel(s -> 12).pushReaction(PushReaction.DESTROY).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).ignitedByLava());
		this.registerDefaultState(this.stateDefinition.any().setValue(HOT, false).setValue(FACING, Direction.UP));
		int top = hasCanvas ? 10 : 9;
		this.shapeUp    = Block.box(0,         5, 0,        16, top,       16);
		this.shapeDown  = Block.box(0,    16 - top, 0,       16,  11,       16);
		this.shapeNorth = Block.box(0,         0,  16 - top, 16,  16,       11);
		this.shapeSouth = Block.box(0,         0,  5,        16,  16,      top);
		this.shapeEast  = Block.box(16 - top,  0,  0,        11,  16,       16);
		this.shapeWest  = Block.box(5,         0,  0,       top,  16,       16);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case DOWN  -> shapeDown;
			case NORTH -> shapeNorth;
			case SOUTH -> shapeSouth;
			case EAST  -> shapeEast;
			case WEST  -> shapeWest;
			default    -> shapeUp;
		};
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(HOT, FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(HOT, false).setValue(FACING, context.getClickedFace().getOpposite());
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(BurntModBlocks.BURNT_SAIL);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		SmolderingCreateSailTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		SmolderingPlanksParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
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
		SnowballExtinguishProcedure.execute(world, hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ(), entity);
	}
}
