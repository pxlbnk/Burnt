package net.pixelbank.burnt.block;

import net.pixelbank.burnt.block.entity.FlagBlockEntity;
import net.pixelbank.burnt.init.BurntModBlockEntities;
import net.pixelbank.burnt.procedures.FlagPoleBlockValidPlacementConditionProcedure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

// Shared base for every coloured flag block, replacing 19 near-identical classes
// The colour string is the texture and serialisation suffix, read by the renderer
// EntityBlock so each flag carries a FlagBlockEntity, which rides Create contraptions and
// Sable sub-levels for free - the point of the refactor
public class FlagBlock extends Block implements EntityBlock {

	/** Pole + cloth volume - same for every colour. */
	private static final VoxelShape SHAPE = box(4, 0, 4, 12, 32, 12);

	private final String colour;

	public FlagBlock(String colour) {
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.WOOD)
				.strength(1f, 10f)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY)
				.isRedstoneConductor((bs, br, bp) -> false));
		this.colour = colour;
	}

	/** Returns the colour identifier ("cyan", "rainbow", "pirate", ...). */
	public String getColour() {
		return colour;
	}

	// Block behaviour shared across all 19 colour subclasses.

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
		return SHAPE;
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		if (worldIn instanceof LevelAccessor world) {
			return FlagPoleBlockValidPlacementConditionProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		}
		return super.canSurvive(blockstate, worldIn, pos);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		return !state.canSurvive(world, currentPos)
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	// The block-entity renderer now draws the cloth, so the legacy flag entity isn't spawned

	// Each flag block has a FlagBlockEntity holding its wind state for the renderer

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new FlagBlockEntity(pos, state);
	}

	@Override
	@Nullable
	@SuppressWarnings({"unchecked", "rawtypes"})
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		// Server-only ticker - reads the wind and pushes an update packet when it changes meaningfully
		// Can't tick on the client: MapVariables only syncs on _syncDirty, which wind drift doesn't set
		if (level.isClientSide()) return null;
		return type == BurntModBlockEntities.FLAG
				? (BlockEntityTicker<T>) (BlockEntityTicker) (BlockEntityTicker<FlagBlockEntity>) FlagBlockEntity::tick
				: null;
	}
}
