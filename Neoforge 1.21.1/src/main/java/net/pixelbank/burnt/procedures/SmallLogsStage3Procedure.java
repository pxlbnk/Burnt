package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SmallLogsStage3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || (IsRainingProcedure.execute(world, x, y, z) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get())) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = BurntModBlocks.SMALL_BURNT_LOG.defaultBlockState();
				BlockState _bso = world.getBlockState(_bp);
				_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
				world.setBlock(_bp, _bs, 3);
				return;
			}
			if (Math.random() <= 0.3) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMALL_BURNT_LOG.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}