package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class TallFlamesValidPlacementProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!IsRainingProcedure.isGloballyRaining(world)) {
			if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.TALL_FLAMES)
					&& ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.BURNT_VINE)) {
				if ((getDirectionFromBlockState(blockstate)) == Direction.WEST && ((world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BurntTags.BLOCK_MC_LOGS)
						|| (world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BurntTags.BLOCK_MC_STAIRS))) {
					return true;
				} else if ((getDirectionFromBlockState(blockstate)) == Direction.EAST && ((world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BurntTags.BLOCK_MC_LOGS)
						|| (world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BurntTags.BLOCK_MC_STAIRS))) {
					return true;
				} else if ((getDirectionFromBlockState(blockstate)) == Direction.NORTH && ((world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BurntTags.BLOCK_MC_LOGS)
						|| (world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BurntTags.BLOCK_MC_STAIRS))) {
					return true;
				} else if ((getDirectionFromBlockState(blockstate)) == Direction.SOUTH && ((world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BurntTags.BLOCK_MC_LOGS)
						|| (world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BurntTags.BLOCK_MC_STAIRS))) {
					return true;
				}
			}
		} else if (IsRainingProcedure.isGloballyRaining(world)) {
			return false;
		}
		return false;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (prop instanceof DirectionProperty dp)
			return blockState.getValue(dp);
		prop = blockState.getBlock().getStateDefinition().getProperty("axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().toArray()[0] instanceof Direction.Axis ? Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE) : Direction.NORTH;
	}
}