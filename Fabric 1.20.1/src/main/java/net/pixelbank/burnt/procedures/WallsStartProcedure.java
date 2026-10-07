package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.util.BurntBlockUtil;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

// Modded wood wall to smoldering wall, stage 1
// Walls extend vanilla WallBlock, so copyProperties carries every connection by name

public class WallsStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide())
			return;
		BlockPos _bp = BlockPos.containing(x, y, z);
		BlockState _bso = world.getBlockState(_bp);
		BlockState _bs = BurntModBlocks.SMOLDERING_WALL.defaultBlockState();
		_bs = BurntBlockUtil.copyProperties(_bso, _bs);
		if (_bs.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _stage && _stage.getPossibleValues().contains(1))
			_bs = _bs.setValue(_stage, 1);
		world.setBlock(_bp, _bs, 3);
	}
}
