package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.core.BlockPos;

public class BurntFarmlandMoistTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if (Math.random() <= 0.25) {
				if (world instanceof Level _level) {
					BlockPos _bp = BlockPos.containing(x, y + 1, z);
					if (BoneMealItem.growCrop(new ItemStack(Items.BONE_MEAL), _level, _bp) || BoneMealItem.growWaterPlant(new ItemStack(Items.BONE_MEAL), _level, _bp, null)) {
						if (!_level.isClientSide())
							_level.levelEvent(2005, _bp, 0);
					}
				}
			}
			if (Math.random() <= 0.1) {
				if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.BURNT_CROPS) {
					world.setBlock(BlockPos.containing(x, y + 1, z), (blockStateWithInt(Blocks.WHEAT.defaultBlockState(), "age", 0)), 3);
				}
			}
			sx = -3;
			found = false;
			for (int index0 = 0; index0 < 6; index0++) {
				sy = 0;
				for (int index1 = 0; index1 < 6; index1++) {
					sz = -3;
					for (int index2 = 0; index2 < 6; index2++) {
						BlockState _bsCheck = world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz));
						if (_bsCheck.getBlock() == Blocks.WATER
								|| (_bsCheck.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp11
										&& _bsCheck.getValue(_getbp11) == true)) {
							found = true;
						}
						sz = sz + 1;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
			if (found == false) {
				if (Math.random() <= 0.5) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_FARMLAND.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
			if (((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock().getStateDefinition().getProperty("age") instanceof IntegerProperty _getip14 ? (world.getBlockState(BlockPos.containing(x, y + 1, z))).getValue(_getip14) : -1) >= 6) {
				world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(Blocks.FARMLAND.defaultBlockState(), "moisture", 7)), 3);
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}
