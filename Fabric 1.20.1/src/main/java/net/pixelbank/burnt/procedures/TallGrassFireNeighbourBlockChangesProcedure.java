package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class TallGrassFireNeighbourBlockChangesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			var belowBlock = world.getBlockState(BlockPos.containing(x, y - 1, z)).getBlock();
			boolean validSupport = belowBlock == BurntModBlocks.BURNT_GRASS
					|| belowBlock == BurntModBlocks.SMOLDERING_GRASS
					|| belowBlock == BurntModBlocks.SMOLDERING_GRASS_START
					|| belowBlock == BurntModBlocks.RECOVERING_GRASS;
			if (!validSupport) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		}
	}
}