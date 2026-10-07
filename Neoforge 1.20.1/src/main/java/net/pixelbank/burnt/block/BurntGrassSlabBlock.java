package net.pixelbank.burnt.block;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.procedures.FogParticlesProcedure;
import net.pixelbank.burnt.procedures.BurntGrassSlabRecoveryProcedure;
import net.pixelbank.burnt.procedures.BurntGrassDestroyedProcedure;
import net.pixelbank.burnt.procedures.BurntGrassBlockAddedProcedure;
import net.pixelbank.burnt.procedures.GrassPathProcedure;
import net.pixelbank.burnt.procedures.BurntFarmlandHoeProcedure;
import net.pixelbank.burnt.procedures.BurntDirtSlabNeighbourBlockChangesProcedure;
import net.pixelbank.burnt.init.BurntModBlocks;


import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.BiomeColors;

public class BurntGrassSlabBlock extends SlabBlock implements net.minecraft.world.level.block.BonemealableBlock {
	public static final BooleanProperty HOT = BooleanProperty.create("hot");

	public BurntGrassSlabBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).sound(SoundType.GRAVEL).strength(0.5f).randomTicks());
		this.registerDefaultState(this.stateDefinition.any().setValue(HOT, false));
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(HOT);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(HOT, false);
	}


	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		net.pixelbank.burnt.procedures.GrassStartProcedure.convertPlantsAbove(world, pos.getX(), pos.getY(), pos.getZ());
		BurntGrassBlockAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		// Start the recovery chain now so the slider sets the pace from the start
		if (world.isClientSide()) return;
		if (!(world instanceof ServerLevel serverLevel)) return;
		if (!BurntBasicConfigConfiguration.GRASS_RECOVERY.get()) return;
		double speed = BurntBasicConfigConfiguration.GRASS_RECOVER_SPEED.get();
		if (speed <= 0.0) return;
		if (!serverLevel.getBlockTicks().hasScheduledTick(pos, this)) {
			scheduleNextStep(serverLevel, pos, speed);
		}
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		BurntDirtSlabNeighbourBlockChangesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	// Recovery runs on both a scheduled tick and the random tick

	// Random tick speeds recovery up, chance is (speed/100)^2
	// Also restarts the scheduled chain for blocks from old saves
	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		if (!BurntBasicConfigConfiguration.GRASS_RECOVERY.get()) return;
		double speed = BurntBasicConfigConfiguration.GRASS_RECOVER_SPEED.get();
		if (speed <= 0.0) return;

		double p = (speed / 100.0) * (speed / 100.0);
		if (Math.random() < p) {
			BurntGrassSlabRecoveryProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		}

		// Re-read in case the step above converted this block to recovering_grass.
		if (world.getBlockState(pos).getBlock() == this
				&& !world.getBlockTicks().hasScheduledTick(pos, this)) {
			scheduleNextStep(world, pos, speed);
		}
	}

	// Scheduled tick - run a recovery step, then reschedule if still burnt grass
	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		if (!BurntBasicConfigConfiguration.GRASS_RECOVERY.get()) return;
		double speed = BurntBasicConfigConfiguration.GRASS_RECOVER_SPEED.get();
		if (speed <= 0.0) return;

		BurntGrassSlabRecoveryProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);

		if (world.getBlockState(pos).getBlock() == this) {
			scheduleNextStep(world, pos, speed);
		}
	}

	private void scheduleNextStep(ServerLevel world, BlockPos pos, double speed) {
		world.scheduleTick(pos, this, ticksUntilNextStep(speed));
	}

	// Speed slider to tick interval: 20 * 41^((100 - speed) / 50)
	// 100 = 1s, 50 = 41s, 25 = 4.4 min, 10 = 13 min
	private static int ticksUntilNextStep(double speed) {
		double exp = (100.0 - speed) / 50.0;
		double ticks = 20.0 * Math.pow(41.0, exp);
		return (int) Math.max(20, ticks);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		FogParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		ItemStack stack = entity.getItemInHand(hand);
		if (stack.is(net.pixelbank.burnt.util.BurntTags.ITEM_MC_HOES)) {
			BurntFarmlandHoeProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
			return InteractionResult.SUCCESS;
		}
		if (stack.getItem() instanceof ShovelItem) {
			GrassPathProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState blockstate, Player entity) {
		super.playerWillDestroy(world, pos, blockstate, entity);
		BurntGrassDestroyedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}

	// Bonemeal pushes recovery on by hand, over an area like moss or grass
	@Override
	public boolean isValidBonemealTarget(net.minecraft.world.level.LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
		return net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration.GRASS_RECOVERY.get();
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		net.pixelbank.burnt.util.BurntBonemeal.spread(level, random, pos, this,
				BurntModBlocks.RECOVERING_GRASS_SLAB.defaultBlockState());
	}
}
