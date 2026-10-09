package net.pixelbank.burnt.block;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


import net.pixelbank.burnt.procedures.*;
import net.pixelbank.burnt.init.BurntModBlocks;


import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class SmolderingGrassBlock extends Block {
	public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);

	public SmolderingGrassBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_NYLIUM).sound(SoundType.GRAVEL).strength(0.5f).lightLevel(s -> 10));
		this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(STAGE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(STAGE, 0);
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return new ItemStack(BurntModBlocks.BURNT_GRASS);
	}


	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		// Low-intensity contact damage - once per second so it's gentler than hotter blocks
		if (world.getGameTime() % 20 == 0)
			TallGrassFireDamageProcedure.execute(world, entity);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		net.pixelbank.burnt.procedures.GrassStartProcedure.convertPlantsAbove(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 15);
		SmolderingGrassAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		BurntDirtNeighbourBlockChangesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		BurntGrassTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		world.scheduleTick(pos, this, 15);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		GrassFlameParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		LightSmokeParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void onProjectileHit(Level world, BlockState blockstate, BlockHitResult hit, Projectile entity) {
		BurntGrassSnowballProcedure.execute(world, hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ(), entity);
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		// Also fires when sneaking with an item - check the held item so block placement isn't consumed
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
}