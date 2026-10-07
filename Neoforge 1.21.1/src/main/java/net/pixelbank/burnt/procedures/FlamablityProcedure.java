package net.pixelbank.burnt.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class FlamablityProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (world.hasChunkAt(BlockPos.containing(x, y, z))) {
			BlockState _state = world.getBlockState(BlockPos.containing(x, y, z));
			return _state.getBlock().getFlammability(_state, null, BlockPos.ZERO, Direction.UP) > 0;
		}
		return false;
	}
}
