package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CheckBurnOffsetProcedure {
	public static void execute(LevelAccessor world, double baseX, double baseY, double baseZ, double dx, double dy, double dz) {
		double nx = 0;
		double ny = 0;
		double nz = 0;
		if (world.hasChunkAt(BlockPos.containing(nx, ny, nz))) {
			nx = baseX + dx;
			ny = baseY + dy;
			nz = baseZ + dz;
			if ((world.getBlockState(BlockPos.containing(nx, ny, nz))).is(BurntTags.BLOCK_WILL_BURN)) {
				WillitburnProcedure.execute(world, nx, ny, nz);
			}
		}
	}
}