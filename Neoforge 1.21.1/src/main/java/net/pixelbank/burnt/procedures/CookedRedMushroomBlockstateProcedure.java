package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CookedRedMushroomBlockstateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp2 && blockstate.getValue(_getbp2)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp4 && blockstate.getValue(_getbp4)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp6 && blockstate.getValue(_getbp6)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp8 && blockstate.getValue(_getbp8)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("up") instanceof BooleanProperty _getbp10 && blockstate.getValue(_getbp10)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("down") instanceof BooleanProperty _getbp12 && blockstate.getValue(_getbp12)) == true) {
				{
					int _value = 1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp15 && blockstate.getValue(_getbp15)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp17 && blockstate.getValue(_getbp17)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp19 && blockstate.getValue(_getbp19)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp21 && blockstate.getValue(_getbp21)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("up") instanceof BooleanProperty _getbp23 && blockstate.getValue(_getbp23)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("down") instanceof BooleanProperty _getbp25 && blockstate.getValue(_getbp25)) == false) {
				{
					int _value = 2;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp28 && blockstate.getValue(_getbp28)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp30 && blockstate.getValue(_getbp30)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp32 && blockstate.getValue(_getbp32)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp34 && blockstate.getValue(_getbp34)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("up") instanceof BooleanProperty _getbp36 && blockstate.getValue(_getbp36)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("down") instanceof BooleanProperty _getbp38 && blockstate.getValue(_getbp38)) == true) {
				{
					int _value = 3;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp41 && blockstate.getValue(_getbp41)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp43 && blockstate.getValue(_getbp43)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp45 && blockstate.getValue(_getbp45)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp47 && blockstate.getValue(_getbp47)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("up") instanceof BooleanProperty _getbp49 && blockstate.getValue(_getbp49)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("down") instanceof BooleanProperty _getbp51 && blockstate.getValue(_getbp51)) == false) {
				{
					int _value = 4;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
		}
	}
}