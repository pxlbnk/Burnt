package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SmolderingMossSlabStage3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				world.setBlock(BlockPos.containing(x, y, z), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.BURNT_MOSS_SLAB.defaultBlockState()), 3);
				return;
			}
			if (Math.random() <= 0.1) {
				world.setBlock(BlockPos.containing(x, y, z), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.BURNT_MOSS_SLAB.defaultBlockState()), 3);
			}
		}
	}
}