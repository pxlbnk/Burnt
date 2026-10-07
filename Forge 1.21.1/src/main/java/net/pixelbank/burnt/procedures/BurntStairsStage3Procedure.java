package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

public class BurntStairsStage3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double burnTime = 0;
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || (IsRainingProcedure.execute(world, x, y, z) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get())) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = BurntModBlocks.BURNT_STAIRS.defaultBlockState();
				BlockState _bso = world.getBlockState(_bp);
				_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
				world.setBlock(_bp, _bs, 3);
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bsNew = world.getBlockState(_pos);
				if (_bsNew.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bsNew.setValue(_booleanProp, false), 3);
				return;
			}
			if (Math.random() <= 0.05) {
				RandomCollapseProcedure.execute(world, x, y, z);
			}
			burnTime = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPEED.get() / 100.0, 1.0), 1.5) * 100.0;
			if (Mth.nextInt(world.getRandom(), 0, 500) <= burnTime) {
				if (Math.random() <= 0.01) {
					world.destroyBlock(BlockPos.containing(x, y, z), false);
				} else if (Math.random() <= 0.5) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.EMBER_STAIRS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp5
								? BurntModBlocks.EMBER_STAIRS.defaultBlockState().setValue(_withbp5, true)
								: BurntModBlocks.EMBER_STAIRS.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						BlockEntity _be = world.getBlockEntity(_bp);
						CompoundTag _bnbt = null;
						if (_be != null) {
							_bnbt = _be.saveWithFullMetadata(world.registryAccess());
							_be.setRemoved();
						}
						world.setBlock(_bp, _bs, 3);
						if (_bnbt != null) {
							_be = world.getBlockEntity(_bp);
							if (_be != null) {
								try {
									_be.loadWithComponents(_bnbt, world.registryAccess());
								} catch (Exception ignored) {
								}
							}
						}
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
						BlockEntity _be = world.getBlockEntity(_bp);
						CompoundTag _bnbt = null;
						if (_be != null) {
							_bnbt = _be.saveWithFullMetadata(world.registryAccess());
							_be.setRemoved();
						}
						world.setBlock(_bp, _bs, 3);
						if (_bnbt != null) {
							_be = world.getBlockEntity(_bp);
							if (_be != null) {
								try {
									_be.loadWithComponents(_bnbt, world.registryAccess());
								} catch (Exception ignored) {
								}
							}
						}
					}
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
					}
				}
			}
		}
	}
}