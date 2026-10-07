package net.pixelbank.burnt.block;


import net.pixelbank.burnt.procedures.WoodParticlesProcedure;
import net.pixelbank.burnt.procedures.EmberStairsTickProcedure;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class EmberStairsBlock extends StairBlock {
	public static final BooleanProperty HOT = BooleanProperty.create("hot");

	public EmberStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).sound(SoundType.WOOD).strength(1.5f, 2f).lightLevel(s -> 8).randomTicks().ignitedByLava().instrument(NoteBlockInstrument.BASS));
		this.registerDefaultState(this.stateDefinition.any().setValue(HOT, false));
	}

	@Override
	public float getExplosionResistance() {
		return 2f;
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
		// Scheduled tick guarantees decay - random ticks starve with no player nearby
		world.scheduleTick(pos, this, world.getRandom().nextInt(600) + 600); // 30-60s
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		EmberStairsTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		// Re-schedule; scheduled ticks are auto-discarded once the block at pos changes.
		world.scheduleTick(pos, this, world.getRandom().nextInt(600) + 600);
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		EmberStairsTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		WoodParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
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
