package net.pixelbank.burnt.block;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.procedures.RecoveringGrassOnTickUpdateProcedure;
import net.pixelbank.burnt.procedures.RecoveringGrassBlockAddedProcedure;
import net.pixelbank.burnt.procedures.GrassPathProcedure;
import net.pixelbank.burnt.procedures.BurntFarmlandHoeProcedure;
import net.pixelbank.burnt.procedures.BurntDirtNeighbourBlockChangesProcedure;
import net.pixelbank.burnt.init.BurntModBlocks;


import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.BiomeColors;

public class RecoveringGrassBlock extends Block implements net.minecraft.world.level.block.BonemealableBlock {
	public RecoveringGrassBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).sound(SoundType.GRAVEL).strength(0.5f).randomTicks());
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(BurntModBlocks.BURNT_GRASS);
	}


	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		if (stack.is(net.pixelbank.burnt.util.BurntTags.ITEM_MC_HOES)) {
			BurntFarmlandHoeProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
			return ItemInteractionResult.SUCCESS;
		}
		if (stack.getItem() instanceof ShovelItem) {
			GrassPathProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
			return ItemInteractionResult.SUCCESS;
		}
		return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		ItemStack held = entity.getMainHandItem();
		if (held.is(net.pixelbank.burnt.util.BurntTags.ITEM_MC_HOES)) {
			BurntFarmlandHoeProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
			return InteractionResult.SUCCESS;
		}
		if (held.getItem() instanceof ShovelItem) {
			GrassPathProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		net.pixelbank.burnt.procedures.GrassStartProcedure.convertPlantsAbove(world, pos.getX(), pos.getY(), pos.getZ());
		RecoveringGrassBlockAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		// Start the greening chain now so the slider sets the pace from the start
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
		BurntDirtNeighbourBlockChangesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	// Greening runs on both a scheduled tick and the random tick

	// Random tick speeds greening up, chance is (speed/100)^2
	// Also restarts the scheduled chain for blocks that lost theirs
	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		if (!BurntBasicConfigConfiguration.GRASS_RECOVERY.get()) return;
		double speed = BurntBasicConfigConfiguration.GRASS_RECOVER_SPEED.get();
		if (speed <= 0.0) return;

		double p = (speed / 100.0) * (speed / 100.0);
		if (Math.random() < p) {
			RecoveringGrassOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		}

		// Re-read in case the step above greened this block into a grass_block.
		if (world.getBlockState(pos).getBlock() == this
				&& !world.getBlockTicks().hasScheduledTick(pos, this)) {
			scheduleNextStep(world, pos, speed);
		}
	}

	// Scheduled tick - green up, then reschedule if still recovering
	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		if (!BurntBasicConfigConfiguration.GRASS_RECOVERY.get()) return;
		double speed = BurntBasicConfigConfiguration.GRASS_RECOVER_SPEED.get();
		if (speed <= 0.0) return;

		RecoveringGrassOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());

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

	// Second step of the bonemeal chain: recovering grass becomes ordinary grass.
	@Override
	public boolean isValidBonemealTarget(net.minecraft.world.level.LevelReader level, BlockPos pos, BlockState state) {
		return net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration.GRASS_RECOVERY.get();
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		net.pixelbank.burnt.util.BurntBonemeal.spread(level, random, pos, this,
				net.minecraft.world.level.block.Blocks.GRASS_BLOCK.defaultBlockState());
	}
}
