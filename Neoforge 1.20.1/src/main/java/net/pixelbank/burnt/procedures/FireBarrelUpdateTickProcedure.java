package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class FireBarrelUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double Radius = 0;
		if (!world.isClientSide()) {
			if (!world.isClientSide()) {
				sx = -1;
				found = false;
				for (int index0 = 0; index0 < 6; index0++) {
					sy = -1;
					for (int index1 = 0; index1 < 6; index1++) {
						sz = -1;
						for (int index2 = 0; index2 < 6; index2++) {
							if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BLAST)) {
								found = true;
								world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.FIRE_BARREL_BLAST_BLOCK.defaultBlockState(), 3);
							}
							sz = sz + 1;
						}
						sy = sy + 1;
					}
					sx = sx + 1;
				}
			}
		}
	}
}