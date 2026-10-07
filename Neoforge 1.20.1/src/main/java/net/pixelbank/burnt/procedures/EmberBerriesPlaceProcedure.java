package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class EmberBerriesPlaceProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (world.getBlockState(BlockPos.containing(x, y, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_CAVE_VINES)) {
				if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_AIR)) {
					world.setBlock(BlockPos.containing(x, y - 1, z), BurntModBlocks.BURNT_CAVE_VINES.defaultBlockState(), 3);
				}
			}
		}
	}
}