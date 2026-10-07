package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class FireBarrelBlockDestroyedByPlayerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.FIRE_BARREL_BLAST_BLOCK.defaultBlockState(), 3);
		}
	}
}