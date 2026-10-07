package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class LargeFlamesValidPlacementProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.getLevelData().isRaining()) {
			if ((getDirectionFromBlockState(blockstate)) == Direction.WEST
					&& ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& ((world.getBlockState(BlockPos.containing(x, y + 1, z + 1))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y + 1, z + 1))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x, y + 1, z + 1))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& world.getBlockState(BlockPos.containing(x - 1, y, z)).canOcclude() && world.getBlockState(BlockPos.containing(x - 1, y, z - 1)).canOcclude()) {
				return true;
			} else if ((getDirectionFromBlockState(blockstate)) == Direction.EAST
					&& ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& ((world.getBlockState(BlockPos.containing(x, y + 1, z - 1))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y + 1, z - 1))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x, y + 1, z - 1))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& world.getBlockState(BlockPos.containing(x + 1, y, z)).canOcclude() && world.getBlockState(BlockPos.containing(x + 1, y, z - 1)).canOcclude()) {
				return true;
			} else if ((getDirectionFromBlockState(blockstate)) == Direction.NORTH
					&& ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& ((world.getBlockState(BlockPos.containing(x + 1, y + 1, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x + 1, y + 1, z))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x + 1, y + 1, z))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& world.getBlockState(BlockPos.containing(x, y, z + 1)).canOcclude() && world.getBlockState(BlockPos.containing(x + 1, y, z + 1)).canOcclude()) {
				return true;
			} else if ((getDirectionFromBlockState(blockstate)) == Direction.SOUTH
					&& ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& ((world.getBlockState(BlockPos.containing(x - 1, y + 1, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x - 1, y + 1, z))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x - 1, y + 1, z))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.AIR || (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.VINE
							|| (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == BurntModBlocks.BURNT_VINE)
					&& world.getBlockState(BlockPos.containing(x, y, z - 1)).canOcclude() && world.getBlockState(BlockPos.containing(x - 1, y, z - 1)).canOcclude()) {
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