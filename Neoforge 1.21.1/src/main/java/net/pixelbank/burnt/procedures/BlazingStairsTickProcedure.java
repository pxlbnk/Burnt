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

public class BlazingStairsTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if ((IsRainingProcedure.isGloballyRaining(world) || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z))) {
				if ((net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true)) {
					BurntMod.queueServerWork((int) Mth.nextDouble(world.getRandom(), 20, 60), () -> {
						if (Math.random() <= 0.5) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (BurntModBlocks.EMBER_STAIRS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp4
										? BurntModBlocks.EMBER_STAIRS.defaultBlockState().setValue(_withbp4, true)
										: BurntModBlocks.EMBER_STAIRS.defaultBlockState());
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							{
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
									world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
							}
						} else {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.BURNT_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							{
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
									world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
							}
						}
					});
				}
			}
			if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_STAIRS.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			}
			if (!((IsRainingProcedure.isGloballyRaining(world) || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) && IsRainingProcedure.execute(world, x, y, z))) {
				BurntStairsStage3Procedure.execute(world, x, y, z);
			}
		}
	}
}