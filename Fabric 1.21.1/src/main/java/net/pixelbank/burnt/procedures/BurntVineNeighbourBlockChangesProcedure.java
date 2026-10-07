package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class BurntVineNeighbourBlockChangesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == (blockStateWithDirection(BurntModBlocks.BURNT_VINE.defaultBlockState(), Direction.EAST)).getBlock()) {
				if (!world.getBlockState(BlockPos.containing(x + 1, y, z)).canOcclude() && (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR) {
					BurntMod.queueServerWork(1, () -> {
						world.destroyBlock(BlockPos.containing(x, y, z), false);
					});
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == (blockStateWithDirection(BurntModBlocks.BURNT_VINE.defaultBlockState(), Direction.WEST)).getBlock()) {
				if (!world.getBlockState(BlockPos.containing(x - 1, y, z)).canOcclude() && (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR) {
					BurntMod.queueServerWork(1, () -> {
						world.destroyBlock(BlockPos.containing(x, y, z), false);
					});
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == (blockStateWithDirection(BurntModBlocks.BURNT_VINE.defaultBlockState(), Direction.SOUTH)).getBlock()) {
				if (!world.getBlockState(BlockPos.containing(x, y, z + 1)).canOcclude() && (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR) {
					BurntMod.queueServerWork(1, () -> {
						world.destroyBlock(BlockPos.containing(x, y, z), false);
					});
				}
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == (blockStateWithDirection(BurntModBlocks.BURNT_VINE.defaultBlockState(), Direction.NORTH)).getBlock()) {
				if (!world.getBlockState(BlockPos.containing(x, y, z - 1)).canOcclude() && (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR) {
					BurntMod.queueServerWork(1, () -> {
						world.destroyBlock(BlockPos.containing(x, y, z), false);
					});
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