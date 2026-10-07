package net.pixelbank.burnt.block;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.procedures.BurntHollowLogUpdateProcedure;
import net.pixelbank.burnt.procedures.BurntLogsParticlesProcedure;
import net.pixelbank.burnt.util.WoodSpecies;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class BurntHollowLogBlock extends Block {
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	public static final BooleanProperty FRAGILE = BooleanProperty.create("fragile");
	// Wood species carried over from the original log, used when replanting
	public static final EnumProperty<WoodSpecies> SPECIES = EnumProperty.create("species", WoodSpecies.class);

	// 0 = always run on random tick, so randomTickSpeed works at any speed setting
	private static final double RANDOM_TICK_GATE = 0.0;

	public BurntHollowLogBlock() {
		super(BlockBehaviour.Properties.of().noOcclusion().isRedstoneConductor((bs, br, bp) -> false)
				.mapColor(MapColor.TERRACOTTA_BLACK)
				.sound(SoundType.WOOD)
				.strength(2f)
				.randomTicks()
				.ignitedByLava()
				.instrument(NoteBlockInstrument.BASS));
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y).setValue(FRAGILE, false).setValue(SPECIES, WoodSpecies.UNKNOWN));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(AXIS, FRAGILE, SPECIES);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(AXIS, context.getClickedFace().getAxis()).setValue(FRAGILE, false).setValue(SPECIES, WoodSpecies.UNKNOWN);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return RotatedPillarBlock.rotatePillar(state, rot);
	}

	// Tick driving - random tick + scheduled tick paths.

	// Random tick always runs breakdown, so randomTickSpeed helps at any speed setting
	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(state, world, pos, random);
		if (!BurntBasicConfigConfiguration.FOREST_RECOVERY.get()) return;
		double speed = BurntBasicConfigConfiguration.FOREST_RECOVER_SPEED.get();
		if (speed <= 0.0) return;

		if (speed >= RANDOM_TICK_GATE) {
			BurntHollowLogUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), state);
		}

		// Start the chain for fragile logs that don't have one
		// Re-read the state - the breakdown above may have destroyed the log
		BlockState current = world.getBlockState(pos);
		if (current.getBlock() == this && current.getValue(FRAGILE)
				&& !world.getBlockTicks().hasScheduledTick(pos, this)) {
			scheduleNextBreakdown(world, pos, speed);
		}
	}

	// Scheduled tick - run breakdown, then reschedule if the log is still fragile
	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(state, world, pos, random);
		if (!BurntBasicConfigConfiguration.FOREST_RECOVERY.get()) return;
		double speed = BurntBasicConfigConfiguration.FOREST_RECOVER_SPEED.get();
		if (speed <= 0.0) return;

		BurntHollowLogUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), state);

		BlockState current = world.getBlockState(pos);
		if (current.getBlock() == this && current.getValue(FRAGILE)) {
			scheduleNextBreakdown(world, pos, speed);
		}
	}

	private void scheduleNextBreakdown(ServerLevel world, BlockPos pos, double speed) {
		world.scheduleTick(pos, this, ticksUntilNextBreakdown(speed));
	}

	// Speed config to tick interval: 20 * 60^((100 - speed) / 50)
	// 100 = 1s, 50 = 60s, 25 = 8 min, 10 = 23 min, 0 = off
	private static int ticksUntilNextBreakdown(double speed) {
		double exp = (100.0 - speed) / 50.0;
		double ticks = 20.0 * Math.pow(60.0, exp);
		return (int) Math.max(20, ticks);
	}

	// Start the breakdown chain as soon as a fragile log appears, not on the first random tick
	// Player-placed logs aren't fragile, so they never decay
	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(state, world, pos, oldState, moving);
		if (world.isClientSide()) return;
		if (!(world instanceof ServerLevel serverLevel)) return;
		if (!state.getValue(FRAGILE)) return;
		if (!BurntBasicConfigConfiguration.FOREST_RECOVERY.get()) return;
		double speed = BurntBasicConfigConfiguration.FOREST_RECOVER_SPEED.get();
		if (speed <= 0.0) return;
		if (!serverLevel.getBlockTicks().hasScheduledTick(pos, this)) {
			scheduleNextBreakdown(serverLevel, pos, speed);
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		BurntLogsParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}


	// Ash drops via loot too, so tree-felling mods still yield it - see BurntAshDrops
	@Override
	public java.util.List<net.minecraft.world.item.ItemStack> getDrops(BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder builder) {
		java.util.List<net.minecraft.world.item.ItemStack> drops = super.getDrops(state, builder);
		net.pixelbank.burnt.util.BurntAshDrops.applyBurntDrops(drops, builder);
		return drops;
	}
}
