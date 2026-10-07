package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class FallingSlabOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_MC_WOOL_CARPETS)) {
				world.setBlock(BlockPos.containing(x, y - 1, z), (blockStateWithDirection(BurntModBlocks.FALLEN_BURNT_SLAB.defaultBlockState(), Direction.getRandom(world.getRandom()))), 3);
				world.levelEvent(2001, BlockPos.containing(x, y - 1, z), Block.getId(BurntModBlocks.BURNT_SLAB.defaultBlockState()));
				if (!entity.level().isClientSide())
					entity.discard();
			} else if ((world.getBlockState(BlockPos.containing(x, y - 2, z))).is(BurntTags.BLOCK_BREAKS_FALLING_SLABS)
					|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_BREAKS_FALLING_SLABS)) {
				world.levelEvent(2001, BlockPos.containing(x, y - 1, z), Block.getId(BurntModBlocks.BURNT_SLAB.defaultBlockState()));
				if (!entity.level().isClientSide())
					entity.discard();
			} else if (!world.getBlockState(BlockPos.containing(x, y, z)).canOcclude() && !((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.AIR)) {
				world.setBlock(BlockPos.containing(x, y, z), (blockStateWithDirection(BurntModBlocks.FALLEN_BURNT_SLAB.defaultBlockState(), Direction.getRandom(world.getRandom()))), 3);
				world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.BURNT_SLAB.defaultBlockState()));
				if (!entity.level().isClientSide())
					entity.discard();
			} else if (world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude()) {
				world.setBlock(BlockPos.containing(x, y, z), (blockStateWithDirection(BurntModBlocks.FALLEN_BURNT_SLAB.defaultBlockState(), Direction.getRandom(world.getRandom()))), 3);
				world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.BURNT_SLAB.defaultBlockState()));
				if (!entity.level().isClientSide())
					entity.discard();
			} else if (!((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.AIR)) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
	}

	private static BlockState blockStateWithDirection(BlockState blockState, Direction newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (prop instanceof DirectionProperty dp && dp.getPossibleValues().contains(newValue))
			return blockState.setValue(dp, newValue);
		prop = blockState.getBlock().getStateDefinition().getProperty("axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().contains(newValue.getAxis()) ? blockState.setValue(ep, newValue.getAxis()) : blockState;
	}
}