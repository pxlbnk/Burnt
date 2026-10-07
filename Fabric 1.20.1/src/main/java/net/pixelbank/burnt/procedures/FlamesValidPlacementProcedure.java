package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class FlamesValidPlacementProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.getLevelData().isRaining() && !(world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_TALL_FLAMES)) {
			if ((getDirectionFromBlockState(blockstate)) == Direction.WEST && !((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.AIR)) {
				return true;
			} else if ((getDirectionFromBlockState(blockstate)) == Direction.EAST && !((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.AIR)) {
				return true;
			} else if ((getDirectionFromBlockState(blockstate)) == Direction.NORTH && !((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.AIR)) {
				return true;
			} else if ((getDirectionFromBlockState(blockstate)) == Direction.SOUTH && !((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.AIR)) {
				return true;
			}
		} else if (world.getLevelData().isRaining()) {
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