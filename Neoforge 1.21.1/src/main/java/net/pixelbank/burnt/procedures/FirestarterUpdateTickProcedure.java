package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class FirestarterUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if (IsRainingProcedure.isGloballyRaining(world)) {
				if (IsRainingProcedure.execute(world, x, y, z) == true) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.NETHERRACK) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = Blocks.FIRE.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.SOUL_SAND) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = Blocks.SOUL_FIRE.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("down") instanceof BooleanProperty _getbp10 && blockstate.getValue(_getbp10)) == true) {
				{
					int _value = 0;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			} else {
				if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbp13 && blockstate.getValue(_getbp13)) == true) {
					{
						int _value = 2;
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
				} else {
					if ((blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbp16 && blockstate.getValue(_getbp16)) == true) {
						{
							int _value = 3;
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
								world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
						}
					} else {
						if ((blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbp19 && blockstate.getValue(_getbp19)) == true) {
							{
								int _value = 4;
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
									world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
							}
						} else {
							if ((blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbp22 && blockstate.getValue(_getbp22)) == true) {
								{
									int _value = 5;
									BlockPos _pos = BlockPos.containing(x, y, z);
									BlockState _bs = world.getBlockState(_pos);
									if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
										world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
								}
							} else {
								if ((blockstate.getBlock().getStateDefinition().getProperty("up") instanceof BooleanProperty _getbp25 && blockstate.getValue(_getbp25)) == true) {
									{
										int _value = 6;
										BlockPos _pos = BlockPos.containing(x, y, z);
										BlockState _bs = world.getBlockState(_pos);
										if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
											world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
									}
								} else {
									{
										int _value = 0;
										BlockPos _pos = BlockPos.containing(x, y, z);
										BlockState _bs = world.getBlockState(_pos);
										if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
											world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
									}
								}
							}
						}
					}
				}
			}
			// Burnout chance is per tick from config - the old hard-coded 0.25 made fire vanish instantly
			double burnoutChance = net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration.FIRE_BURNOUT_CHANCE.get() / 100.0;
			if (burnoutChance > 0 && Math.random() <= burnoutChance) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
			// Ignition retry, deliberately rate-gated
			// Every tick made spread pressure scale with fire lifetime; only on place was too few, since the
			// block you light sits below the fire and downChance is 0.9, so ~1 in 10 lights silently failed
			// 1-in-12 per tick makes ignition effectively certain while keeping total pressure about the same
			if (Math.random() < 1.0 / 12.0) {
				WillitburnProcedure.execute(world, x, y, z);
			}
		}
	}
}