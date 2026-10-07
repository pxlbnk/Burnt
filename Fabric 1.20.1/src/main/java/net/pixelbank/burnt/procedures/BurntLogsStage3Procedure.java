package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

public class BurntLogsStage3Procedure {

	public static void execute(LevelAccessor world, double x, double y, double z) {
		double burnTime = 0;
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || (IsRainingProcedure.execute(world, x, y, z) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get())) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.BURNT_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp10
						? BurntModBlocks.BURNT_LOG.defaultBlockState().setValue(_withbp10, true)
						: BurntModBlocks.BURNT_LOG.defaultBlockState());
				BlockState _bso = world.getBlockState(_bp);
				_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
				world.setBlock(_bp, _bs, 3);
				return;
			}
			burnTime = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPEED.get() / 100.0, 1.0), 1.5) * 100.0;
			if (Mth.nextInt(world.getRandom(), 0, 500) <= burnTime) {
				if (Math.random() <= 0.1) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (new Object() {
							public BlockState with(BlockState _bs, String _property, int _newValue) {
								Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty(_property);
								return _prop instanceof IntegerProperty _ip && _prop.getPossibleValues().contains(_newValue) ? _bs.setValue(_ip, _newValue) : _bs;
							}
						}.with(BurntModBlocks.BROKEN_BLAZING_LOG.defaultBlockState(), "blockstate", Mth.nextInt(world.getRandom(), 0, 1)));
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				} else if (Math.random() <= 0.1) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BLAZING_LOG.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				} else if (Math.random() <= 0.4) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.EMBER_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp8
								? BurntModBlocks.EMBER_LOG.defaultBlockState().setValue(_withbp8, true)
								: BurntModBlocks.EMBER_LOG.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				} else {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.BURNT_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp10
								? BurntModBlocks.BURNT_LOG.defaultBlockState().setValue(_withbp10, true)
								: BurntModBlocks.BURNT_LOG.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
			// Keep copper flames alive on adjacent copper while the log burns hard
			if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BurntTags.BLOCK_COPPER))
				CopperFlamesPlacementProcedure.execute(world, x + 1, y, z);
			if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BurntTags.BLOCK_COPPER))
				CopperFlamesPlacementProcedure.execute(world, x - 1, y, z);
			if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BurntTags.BLOCK_COPPER))
				CopperFlamesPlacementProcedure.execute(world, x, y, z + 1);
			if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BurntTags.BLOCK_COPPER))
				CopperFlamesPlacementProcedure.execute(world, x, y, z - 1);
		}
	}
}