package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class SootBlockTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp2 && blockstate.getValue(_getbp2)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp4 && blockstate.getValue(_getbp4)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp6 && blockstate.getValue(_getbp6)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp8 && blockstate.getValue(_getbp8)) == true) {
				{
					int _value = 4;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp12 && blockstate.getValue(_getbp12)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp14 && blockstate.getValue(_getbp14)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp16 && blockstate.getValue(_getbp16)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp18 && blockstate.getValue(_getbp18)) == false) {
				{
					int _value = 0;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.NORTH;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp23 && blockstate.getValue(_getbp23)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp25 && blockstate.getValue(_getbp25)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp27 && blockstate.getValue(_getbp27)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp29 && blockstate.getValue(_getbp29)) == false) {
				{
					int _value = 0;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.WEST;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp34 && blockstate.getValue(_getbp34)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp36 && blockstate.getValue(_getbp36)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp38 && blockstate.getValue(_getbp38)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp40 && blockstate.getValue(_getbp40)) == false) {
				{
					int _value = 0;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.SOUTH;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp45 && blockstate.getValue(_getbp45)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp47 && blockstate.getValue(_getbp47)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp49 && blockstate.getValue(_getbp49)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp51 && blockstate.getValue(_getbp51)) == true) {
				{
					int _value = 0;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.EAST;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp56 && blockstate.getValue(_getbp56)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp58 && blockstate.getValue(_getbp58)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp60 && blockstate.getValue(_getbp60)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp62 && blockstate.getValue(_getbp62)) == false) {
				{
					int _value = 2;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.NORTH;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp67 && blockstate.getValue(_getbp67)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp69 && blockstate.getValue(_getbp69)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp71 && blockstate.getValue(_getbp71)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp73 && blockstate.getValue(_getbp73)) == true) {
				{
					int _value = 2;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.EAST;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp78 && blockstate.getValue(_getbp78)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp80 && blockstate.getValue(_getbp80)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp82 && blockstate.getValue(_getbp82)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp84 && blockstate.getValue(_getbp84)) == true) {
				{
					int _value = 3;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.NORTH;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp89 && blockstate.getValue(_getbp89)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp91 && blockstate.getValue(_getbp91)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp93 && blockstate.getValue(_getbp93)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp95 && blockstate.getValue(_getbp95)) == true) {
				{
					int _value = 3;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.EAST;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp100 && blockstate.getValue(_getbp100)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp102 && blockstate.getValue(_getbp102)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp104 && blockstate.getValue(_getbp104)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp106 && blockstate.getValue(_getbp106)) == true) {
				{
					int _value = 3;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.SOUTH;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp111 && blockstate.getValue(_getbp111)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp113 && blockstate.getValue(_getbp113)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp115 && blockstate.getValue(_getbp115)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp117 && blockstate.getValue(_getbp117)) == false) {
				{
					int _value = 3;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.WEST;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp122 && blockstate.getValue(_getbp122)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp124 && blockstate.getValue(_getbp124)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp126 && blockstate.getValue(_getbp126)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp128 && blockstate.getValue(_getbp128)) == false) {
				{
					int _value = 1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.WEST;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp133 && blockstate.getValue(_getbp133)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp135 && blockstate.getValue(_getbp135)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp137 && blockstate.getValue(_getbp137)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp139 && blockstate.getValue(_getbp139)) == false) {
				{
					int _value = 1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.SOUTH;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp144 && blockstate.getValue(_getbp144)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp146 && blockstate.getValue(_getbp146)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp148 && blockstate.getValue(_getbp148)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp150 && blockstate.getValue(_getbp150)) == true) {
				{
					int _value = 1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.EAST;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp155 && blockstate.getValue(_getbp155)) == true
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp157 && blockstate.getValue(_getbp157)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp159 && blockstate.getValue(_getbp159)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp161 && blockstate.getValue(_getbp161)) == true) {
				{
					int _value = 1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				{
					Direction _dir = Direction.NORTH;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
					if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
						world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
					} else {
						_property = _bs.getBlock().getStateDefinition().getProperty("axis");
						if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
							world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
					}
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp166 && blockstate.getValue(_getbp166)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp168 && blockstate.getValue(_getbp168)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp170 && blockstate.getValue(_getbp170)) == false
					&& (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp172 && blockstate.getValue(_getbp172)) == false) {
				world.destroyBlock(BlockPos.containing(x, y, z), false);
			}
		}
	}
}