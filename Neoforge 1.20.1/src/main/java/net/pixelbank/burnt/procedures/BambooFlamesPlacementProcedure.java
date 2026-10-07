package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class BambooFlamesPlacementProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) >= 1) {
				if (Math.random() <= 0.7) {
					if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BurntTags.BLOCK_FLAMES_VALID)) {
						world.setBlock(BlockPos.containing(x + 1, y, z), (blockStateWithDirection(
								(BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_WOOD_FIRE).getRandomElement(world.getRandom()).orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value())
										.defaultBlockState(),
								Direction.EAST)), 3);
					}
				}
				if (Math.random() <= 0.7) {
					if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BurntTags.BLOCK_FLAMES_VALID)) {
						world.setBlock(BlockPos.containing(x - 1, y, z), (blockStateWithDirection(
								(BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_WOOD_FIRE).getRandomElement(world.getRandom()).orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value())
										.defaultBlockState(),
								Direction.WEST)), 3);
					}
				}
				if (Math.random() <= 0.7) {
					if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BurntTags.BLOCK_FLAMES_VALID)) {
						world.setBlock(BlockPos.containing(x, y, z + 1), (blockStateWithDirection(
								(BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_WOOD_FIRE).getRandomElement(world.getRandom()).orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value())
										.defaultBlockState(),
								Direction.SOUTH)), 3);
					}
				}
				if (Math.random() <= 0.7) {
					if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BurntTags.BLOCK_FLAMES_VALID)) {
						world.setBlock(BlockPos.containing(x, y, z - 1), (blockStateWithDirection(
								(BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_WOOD_FIRE).getRandomElement(world.getRandom()).orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value())
										.defaultBlockState(),
								Direction.NORTH)), 3);
					}
				}
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