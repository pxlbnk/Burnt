package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.util.WoodSpecies;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SnowLayerBlock;

public class LogStarterTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp2 && blockstate.getValue(_getbp2)) == true) {
				// Melt any snow layer sitting on top of a hot log
				BlockPos _abovePos = BlockPos.containing(x, y + 1, z);
				if (world.getBlockState(_abovePos).getBlock() == Blocks.SNOW) {
					world.destroyBlock(_abovePos, false);
				}
				if ((world.getLevelData().isRaining() || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z))) {
					if ((net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true)) {
						{
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
						}
					}
				}
				if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
					if (BurntBasicConfigConfiguration.BIOME.get() == false) {
						fireSpeed = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPREAD.get() / 100.0, 1.0), 1.5);
					} else if (BurntBasicConfigConfiguration.BIOME.get() == true) {
						fireSpeed = BiomeSpecificProcedure.execute(world, x, y, z);
					}
					if (Math.random() <= fireSpeed) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_BIRCH_LOG) {
							if (Math.random() <= 0.5) {
								world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.BIRCH_LOG.defaultBlockState()));
							}
						} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_LOG) {
							if (Math.random() <= 0.2) {
								world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.JUNGLE_LOG.defaultBlockState()));
							}
						} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_MANGROVE_LOG) {
							if (Math.random() <= 0.2) {
								world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.MANGROVE_LOG.defaultBlockState()));
							}
						} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_CHERRY_LOG) {
							if (Math.random() <= 0.2) {
								world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.CHERRY_LOG.defaultBlockState()));
							}
						} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_PALE_OAK_LOG) {
							if (Math.random() <= 0.2) {
								world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(net.pixelbank.burnt.util.BurntVanilla.stateOf("pale_oak_log")));
							}
						}
						if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_LOG) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_DARK_OAK_LOG.defaultBlockState(), "stage", 1));
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								_bs = WoodSpecies.applyTo(_bs, WoodSpecies.fromBlock(_bso.getBlock()));
								world.setBlock(_bp, _bs, 3);
							}
						} else if (Math.random() <= 0.3) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_LOG_2.defaultBlockState(), "stage", 1));
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								_bs = WoodSpecies.applyTo(_bs, WoodSpecies.fromBlock(_bso.getBlock()));
								world.setBlock(_bp, _bs, 3);
							}
						} else if (Math.random() <= 0.3) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_LOG_3.defaultBlockState(), "stage", 1));
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								_bs = WoodSpecies.applyTo(_bs, WoodSpecies.fromBlock(_bso.getBlock()));
								world.setBlock(_bp, _bs, 3);
							}
						} else {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_LOG.defaultBlockState(), "stage", 1));
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								_bs = WoodSpecies.applyTo(_bs, WoodSpecies.fromBlock(_bso.getBlock()));
								world.setBlock(_bp, _bs, 3);
							}
						}
					}
				} else {
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
					}
				}
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}