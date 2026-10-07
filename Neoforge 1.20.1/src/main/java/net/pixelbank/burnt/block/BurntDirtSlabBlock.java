package net.pixelbank.burnt.block;

import net.pixelbank.burnt.procedures.GrassSlabTickProcedure;
import net.pixelbank.burnt.procedures.GrassPathProcedure;
import net.pixelbank.burnt.procedures.BurntFarmlandHoeProcedure;
import net.pixelbank.burnt.procedures.BurntDirtSlabNeighbourBlockChangesProcedure;
import net.pixelbank.burnt.procedures.BurntDirtBlockAddedProcedure;

import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
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

public class BurntDirtSlabBlock extends SlabBlock {
	public BurntDirtSlabBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).sound(SoundType.GRAVEL).strength(0.5f).randomTicks());
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
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
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		BurntDirtBlockAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		BurntDirtSlabNeighbourBlockChangesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		GrassSlabTickProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}