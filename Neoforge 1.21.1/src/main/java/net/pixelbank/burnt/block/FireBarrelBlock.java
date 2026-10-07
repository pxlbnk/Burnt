package net.pixelbank.burnt.block;

import net.pixelbank.burnt.procedures.FireBarrelRedstoneOnProcedure;
import net.pixelbank.burnt.procedures.FireBarrelNeighbourBlockChangesProcedure;
import net.pixelbank.burnt.procedures.FireBarrelEntityCollidesProcedure;
import net.pixelbank.burnt.procedures.FireBarrelBlastProcedure;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class FireBarrelBlock extends Block {
	public FireBarrelBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_NYLIUM).sound(SoundType.WOOD).strength(1.8f, 10f).ignitedByLava().instrument(NoteBlockInstrument.BASS));
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}


	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		if (world.getBestNeighborSignal(pos) > 0) {
			FireBarrelRedstoneOnProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		}
		FireBarrelNeighbourBlockChangesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void wasExploded(Level world, BlockPos pos, Explosion e) {
		super.wasExploded(world, pos, e);
		FireBarrelBlastProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	// Suppress drops - our small blast passes survives_explosion and dropped the barrel itself
	@Override
	public boolean dropFromExplosion(Explosion explosion) {
		return false;
	}

	// Flint & steel / fire charge ignition (mirrors vanilla TNT).
	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (stack.is(Items.FLINT_AND_STEEL) || stack.is(Items.FIRE_CHARGE)) {
			FireBarrelRedstoneOnProcedure.execute(level, pos.getX(), pos.getY(), pos.getZ());
			level.playSound(player, pos,
					stack.is(Items.FIRE_CHARGE) ? SoundEvents.FIRECHARGE_USE : SoundEvents.FLINTANDSTEEL_USE,
					SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
			if (!player.isCreative()) {
				if (stack.is(Items.FLINT_AND_STEEL)) {
					stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
				} else {
					stack.consume(1, player);
				}
			}
			player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
			return ItemInteractionResult.sidedSuccess(level.isClientSide);
		}
		return super.useItemOn(stack, state, level, pos, player, hand, hit);
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		FireBarrelEntityCollidesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
		super.stepOn(world, pos, blockstate, entity);
		FireBarrelEntityCollidesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), entity);
	}
}