package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.pixelbank.burnt.util.BurntBlockUtil;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

public class BlazingPlanksTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if ((world.getLevelData().isRaining() || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z))) {
				if ((net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true)) {
					BurntMod.queueServerWork((int) Mth.nextDouble(world.getRandom(), 20, 60), () -> {
						if (Math.random() <= 0.5) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (BurntModBlocks.EMBER_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp4
										? BurntModBlocks.EMBER_PLANKS.defaultBlockState().setValue(_withbp4, true)
										: BurntModBlocks.EMBER_PLANKS.defaultBlockState());
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.BURNT_PLANKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
					});
				}
			}
			if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_PLANKS.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if (!((world.getLevelData().isRaining() || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) && IsRainingProcedure.execute(world, x, y, z))) {
				BurntPlanksStage3Procedure.execute(world, x, y, z);
			}
		}
	}
}