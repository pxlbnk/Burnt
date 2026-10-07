package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

public class BurntGrassStage3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double burnTime = 0;
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || (IsRainingProcedure.execute(world, x, y, z) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get())) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_GRASS.defaultBlockState(), 3);
				return;
			}
			burnTime = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPEED.get() / 100.0, 1.0), 1.5) * 100.0;
			if (Mth.nextInt(world.getRandom(), 0, 500) <= burnTime) {
				world.setBlock(BlockPos.containing(x, y, z),
						(BurntModBlocks.BURNT_GRASS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp4
								? BurntModBlocks.BURNT_GRASS.defaultBlockState().setValue(_withbp4, true)
								: BurntModBlocks.BURNT_GRASS.defaultBlockState()),
						3);
			}
		}
	}
}