package net.pixelbank.burnt.block;


import net.pixelbank.burnt.procedures.*;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class FirestarterBlock extends FireBlock {
	// FireBlock already declares the other directions and AGE, we just add DOWN
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

	public FirestarterBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.EMPTY).instabreak()
				.lightLevel(s -> 15).noCollission().noOcclusion()
				.pushReaction(PushReaction.DESTROY)
				.hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true)
				.isRedstoneConductor((bs, br, bp) -> false)
				.replaceable());
		// Override FireBlock's default state to include our DOWN property.
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FireBlock.AGE, 0)
				.setValue(FireBlock.UP, false)
				.setValue(FireBlock.NORTH, false)
				.setValue(FireBlock.EAST, false)
				.setValue(FireBlock.SOUTH, false)
				.setValue(FireBlock.WEST, false)
				.setValue(DOWN, false));
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

	// Don't override getShape - the 1px slab is needed for clicking and quad culling

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder); // registers AGE, UP, NORTH, EAST, SOUTH, WEST
		builder.add(DOWN);                          // our additional property
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(DOWN, false);
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		if (worldIn instanceof LevelAccessor world) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return FirestarterBlockValidPlacementConditionProcedure.execute(world, x, y, z);
		}
		// Don't call super.canSurvive - it needs a solid block below and would reject valid spots
		return true;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		// Don't call super.updateShape - it re-runs canSurvive, which we've already done
		return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : state;
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
		return new ItemStack(Blocks.AIR);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		// Don't call super.onPlace - FireBlock schedules a 30-60 tick tick that fights ours
		world.scheduleTick(pos, this, 1);
		FiresstarterBlockPlacedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		// Don't call super.neighborChanged - it fights our occlusion-based procedure
		FirestarterNeighbourChangesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		// Do NOT call super.tick() - FireBlock.tick runs vanilla fire spread and burn logic.
		FirestarterUpdateTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		world.scheduleTick(pos, this, 1);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		// Keep super.animateTick for the vanilla fire particles and crackle
		super.animateTick(blockstate, world, pos, random);
		WoodParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		// Don't call super.entityInside - it would double up on FireDamageProcedure
		FireDamageProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}
}
