package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SmolderingWheatBlockValidPlacementProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.FARMLAND || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.BURNT_FARMLAND
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.BURNT_FARMLAND_MOIST) {
			return true;
		}
		return false;
	}
}