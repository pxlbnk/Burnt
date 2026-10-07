package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CookedMushroomStemNeighbourProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.MUSHROOM_STEM || (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == BurntModBlocks.COOKED_MUSHROOM_STEM)
					&& !((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.AIR)) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			}
			if (((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.MUSHROOM_STEM || (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == BurntModBlocks.COOKED_MUSHROOM_STEM)
					&& !((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.AIR)) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			}
			if (((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.MUSHROOM_STEM || (world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == BurntModBlocks.COOKED_MUSHROOM_STEM)
					&& !((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.AIR)) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			}
			if (((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.MUSHROOM_STEM || (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == BurntModBlocks.COOKED_MUSHROOM_STEM)
					&& !((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.AIR)) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			}
			if (((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.MUSHROOM_STEM || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.COOKED_MUSHROOM_STEM)
					&& !((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR)) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("up") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			}
			if (((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.MUSHROOM_STEM || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.COOKED_MUSHROOM_STEM)
					&& !((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.AIR)) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("down") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
				}
			}
		}
	}
}