package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

public class BurntVerticalSlabStage3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double burnTime = 0;
		if (!world.isClientSide()) {
			// On a Sable creation a burning weak-point slab may break away, eroding the hull. No-op on world
			if (SableBreakProcedure.execute(world, x, y, z)) return;
			burnTime = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPEED.get() / 100.0, 1.0), 1.5) * 100.0;
			if (Mth.nextInt(world.getRandom(), 0, 500) <= burnTime) {
				if (Math.random() <= 0.005) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_VERTICAL_SLAB.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.SMOLDERING_VERTICAL_SLAB.defaultBlockState()));
				} else if (Math.random() <= 0.01) {
					world.destroyBlock(BlockPos.containing(x, y, z), false);
				} else if (Math.random() <= 0.5) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.EMBER_VERTICAL_SLAB.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp7
								? BurntModBlocks.EMBER_VERTICAL_SLAB.defaultBlockState().setValue(_withbp7, true)
								: BurntModBlocks.EMBER_VERTICAL_SLAB.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				} else {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_VERTICAL_SLAB.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
		}
	}
}