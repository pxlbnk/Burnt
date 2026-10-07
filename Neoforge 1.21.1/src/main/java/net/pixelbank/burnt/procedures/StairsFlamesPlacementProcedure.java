package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class StairsFlamesPlacementProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp2 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp2)) == true) {
				if (Math.random() <= 0.8) {
					if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.AIR) {
						world.setBlock(BlockPos.containing(x + 1, y, z), (blockStateWithDirection(
								(BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_STAIRS_FIRE).getRandomElement(world.getRandom()).orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value())
										.defaultBlockState(),
								Direction.EAST)), 3);
					}
				}
				if (Math.random() <= 0.8) {
					if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.AIR) {
						world.setBlock(BlockPos.containing(x - 1, y, z), (blockStateWithDirection(
								(BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_STAIRS_FIRE).getRandomElement(world.getRandom()).orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value())
										.defaultBlockState(),
								Direction.WEST)), 3);
					}
				}
				if (Math.random() <= 0.8) {
					if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.AIR) {
						world.setBlock(BlockPos.containing(x, y, z + 1), (blockStateWithDirection(
								(BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_STAIRS_FIRE).getRandomElement(world.getRandom()).orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value())
										.defaultBlockState(),
								Direction.SOUTH)), 3);
					}
				}
				if (Math.random() <= 0.8) {
					if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.AIR) {
						world.setBlock(BlockPos.containing(x, y, z - 1), (blockStateWithDirection(
								(BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_STAIRS_FIRE).getRandomElement(world.getRandom()).orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value())
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