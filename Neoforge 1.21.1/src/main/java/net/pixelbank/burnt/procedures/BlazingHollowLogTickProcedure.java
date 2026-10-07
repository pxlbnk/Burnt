package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BlazingHollowLogTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if ((net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true)) {
				if ((IsRainingProcedure.isGloballyRaining(world) || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z))) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.BURNT_HOLLOW_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp3
								? BurntModBlocks.BURNT_HOLLOW_LOG.defaultBlockState().setValue(_withbp3, true)
								: BurntModBlocks.BURNT_HOLLOW_LOG.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
			if (Math.random() <= 0.2) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.EMBER_HOLLOW_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp5
							? BurntModBlocks.EMBER_HOLLOW_LOG.defaultBlockState().setValue(_withbp5, true)
							: BurntModBlocks.EMBER_HOLLOW_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}