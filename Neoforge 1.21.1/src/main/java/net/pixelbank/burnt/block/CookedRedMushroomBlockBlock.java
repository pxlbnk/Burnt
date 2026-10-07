package net.pixelbank.burnt.block;


import net.pixelbank.burnt.procedures.CookedRedMushroomNeighbourProcedure;
import net.pixelbank.burnt.procedures.CookedRedMushroomBlockAddedProcedure;
import net.pixelbank.burnt.procedures.CookedMushroomBlockstatesProcedure;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class CookedRedMushroomBlockBlock extends Block {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 64);
	public static final BooleanProperty HOT = BooleanProperty.create("hot");
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	private static final VoxelShape SHAPE = box(0, 0, 0, 16, 16, 16);

	public CookedRedMushroomBlockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 0.2f).lightLevel(s -> (new Object() {
			public int getLightLevel() {
				if (s.getValue(BLOCKSTATE) == 1)
					return 0;
				if (s.getValue(BLOCKSTATE) == 2)
					return 0;
				if (s.getValue(BLOCKSTATE) == 3)
					return 0;
				if (s.getValue(BLOCKSTATE) == 4)
					return 0;
				if (s.getValue(BLOCKSTATE) == 5)
					return 0;
				if (s.getValue(BLOCKSTATE) == 6)
					return 0;
				if (s.getValue(BLOCKSTATE) == 7)
					return 0;
				if (s.getValue(BLOCKSTATE) == 8)
					return 0;
				if (s.getValue(BLOCKSTATE) == 9)
					return 0;
				if (s.getValue(BLOCKSTATE) == 10)
					return 0;
				if (s.getValue(BLOCKSTATE) == 11)
					return 0;
				if (s.getValue(BLOCKSTATE) == 12)
					return 0;
				if (s.getValue(BLOCKSTATE) == 13)
					return 0;
				if (s.getValue(BLOCKSTATE) == 14)
					return 0;
				if (s.getValue(BLOCKSTATE) == 15)
					return 0;
				if (s.getValue(BLOCKSTATE) == 16)
					return 0;
				if (s.getValue(BLOCKSTATE) == 17)
					return 0;
				if (s.getValue(BLOCKSTATE) == 18)
					return 0;
				if (s.getValue(BLOCKSTATE) == 19)
					return 0;
				if (s.getValue(BLOCKSTATE) == 20)
					return 0;
				if (s.getValue(BLOCKSTATE) == 21)
					return 0;
				if (s.getValue(BLOCKSTATE) == 22)
					return 0;
				if (s.getValue(BLOCKSTATE) == 23)
					return 0;
				if (s.getValue(BLOCKSTATE) == 24)
					return 0;
				if (s.getValue(BLOCKSTATE) == 25)
					return 0;
				if (s.getValue(BLOCKSTATE) == 26)
					return 0;
				if (s.getValue(BLOCKSTATE) == 27)
					return 0;
				if (s.getValue(BLOCKSTATE) == 28)
					return 0;
				if (s.getValue(BLOCKSTATE) == 29)
					return 0;
				if (s.getValue(BLOCKSTATE) == 30)
					return 0;
				if (s.getValue(BLOCKSTATE) == 31)
					return 0;
				if (s.getValue(BLOCKSTATE) == 32)
					return 0;
				if (s.getValue(BLOCKSTATE) == 33)
					return 0;
				if (s.getValue(BLOCKSTATE) == 34)
					return 0;
				if (s.getValue(BLOCKSTATE) == 35)
					return 0;
				if (s.getValue(BLOCKSTATE) == 36)
					return 0;
				if (s.getValue(BLOCKSTATE) == 37)
					return 0;
				if (s.getValue(BLOCKSTATE) == 38)
					return 0;
				if (s.getValue(BLOCKSTATE) == 39)
					return 0;
				if (s.getValue(BLOCKSTATE) == 40)
					return 0;
				if (s.getValue(BLOCKSTATE) == 41)
					return 0;
				if (s.getValue(BLOCKSTATE) == 42)
					return 0;
				if (s.getValue(BLOCKSTATE) == 43)
					return 0;
				if (s.getValue(BLOCKSTATE) == 44)
					return 0;
				if (s.getValue(BLOCKSTATE) == 45)
					return 0;
				if (s.getValue(BLOCKSTATE) == 46)
					return 0;
				if (s.getValue(BLOCKSTATE) == 47)
					return 0;
				if (s.getValue(BLOCKSTATE) == 48)
					return 0;
				if (s.getValue(BLOCKSTATE) == 49)
					return 0;
				if (s.getValue(BLOCKSTATE) == 50)
					return 0;
				if (s.getValue(BLOCKSTATE) == 51)
					return 0;
				if (s.getValue(BLOCKSTATE) == 52)
					return 0;
				if (s.getValue(BLOCKSTATE) == 53)
					return 0;
				if (s.getValue(BLOCKSTATE) == 54)
					return 0;
				if (s.getValue(BLOCKSTATE) == 55)
					return 0;
				if (s.getValue(BLOCKSTATE) == 56)
					return 0;
				if (s.getValue(BLOCKSTATE) == 57)
					return 0;
				if (s.getValue(BLOCKSTATE) == 58)
					return 0;
				if (s.getValue(BLOCKSTATE) == 59)
					return 0;
				if (s.getValue(BLOCKSTATE) == 60)
					return 0;
				if (s.getValue(BLOCKSTATE) == 61)
					return 0;
				if (s.getValue(BLOCKSTATE) == 62)
					return 0;
				if (s.getValue(BLOCKSTATE) == 63)
					return 0;
				if (s.getValue(BLOCKSTATE) == 64)
					return 0;
				return 0;
			}
		}.getLightLevel())).pushReaction(PushReaction.DESTROY));
		this.registerDefaultState(this.stateDefinition.any().setValue(HOT, false).setValue(UP, true).setValue(DOWN, true).setValue(NORTH, true).setValue(EAST, true).setValue(SOUTH, true).setValue(WEST, true));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return (SHAPE);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(HOT, UP, DOWN, NORTH, EAST, SOUTH, WEST, BLOCKSTATE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(HOT, false).setValue(UP, true).setValue(DOWN, true).setValue(NORTH, true).setValue(EAST, true).setValue(SOUTH, true).setValue(WEST, true);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 1);
		CookedRedMushroomBlockAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		CookedRedMushroomNeighbourProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		CookedMushroomBlockstatesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		world.scheduleTick(pos, this, 1);
	}
}