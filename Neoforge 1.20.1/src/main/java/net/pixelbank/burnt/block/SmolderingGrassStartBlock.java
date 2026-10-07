package net.pixelbank.burnt.block;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


import net.pixelbank.burnt.procedures.*;
import net.pixelbank.burnt.init.BurntModBlocks;


import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.BiomeColors;

public class SmolderingGrassStartBlock extends Block {
	public SmolderingGrassStartBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.GRAVEL).strength(0.5f).lightLevel(s -> 12).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return new ItemStack(Blocks.GRASS_BLOCK);
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
		// Convert plants above before the neighbour updates run, or they get destroyed
		GrassStartProcedure.convertPlantsAbove(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 5);
		SmolderingBlockAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		BurntDirtNeighbourBlockChangesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		BurntGrassStartTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 5);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		BurntGrassParticlesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void playerWillDestroy(Level world, BlockPos pos, BlockState blockstate, Player entity) {
		super.playerWillDestroy(world, pos, blockstate, entity);
		BurntGrassDestroyedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}

	@Override
	public void onProjectileHit(Level world, BlockState blockstate, BlockHitResult hit, Projectile entity) {
		BurntGrassSnowballProcedure.execute(world, hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ(), entity);
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		ItemStack stack = entity.getItemInHand(hand);
		if (stack.is(net.pixelbank.burnt.util.BurntTags.ITEM_MC_HOES)) {
			GrassHoeProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
			return InteractionResult.SUCCESS;
		}
		if (stack.getItem() instanceof ShovelItem) {
			GrassPathProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
}
