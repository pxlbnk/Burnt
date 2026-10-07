package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.pixelbank.burnt.util.BurntBlockUtil;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.core.BlockPos;

public class BurntGrassStartTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || (IsRainingProcedure.execute(world, x, y, z) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get())) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_GRASS.defaultBlockState(), 3);
				return;
			}
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_GRASS.defaultBlockState(), 3);
				} else {
					BurntGrassStage1Procedure.execute(world, x, y, z);
				}
			} else {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_GRASS.defaultBlockState(), 3);
			}
		}
	}
}