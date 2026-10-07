package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurningCaveVinesUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_CAVE_VINES_PLANT.defaultBlockState(), 3);
				return;
			}
			// Smoldering spreads down ahead of the burning wave, so each section smolders first
			if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.CAVE_VINES_PLANT) {
				{
					BlockPos _bp = BlockPos.containing(x, y - 1, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if (!((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.CAVE_VINES_PLANT)) {
				if (Math.random() <= 0.025) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_CAVE_VINES_PLANT.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
		}
	}
}
