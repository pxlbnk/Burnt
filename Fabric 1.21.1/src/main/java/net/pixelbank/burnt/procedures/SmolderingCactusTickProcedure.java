package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SmolderingCactusTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_CACTUS.defaultBlockState(), 3);
				return;
			}
			if (Math.random() <= 0.5) {
				if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.CACTUS) {
					world.setBlock(BlockPos.containing(x, y + 1, z), BurntModBlocks.SMOLDERING_CACTUS.defaultBlockState(), 3);
				} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.CACTUS) {
					if (Math.random() <= 0.5) {
						world.setBlock(BlockPos.containing(x, y - 1, z), BurntModBlocks.SMOLDERING_CACTUS.defaultBlockState(), 3);
					}
				}
			}
			if (Math.random() <= 0.1) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_CACTUS.defaultBlockState(), 3);
			}
		}
	}
}