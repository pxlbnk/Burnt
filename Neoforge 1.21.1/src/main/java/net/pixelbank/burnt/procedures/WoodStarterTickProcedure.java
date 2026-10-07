package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class WoodStarterTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_WOOD.defaultBlockState(), 3);
				return;
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp2 && blockstate.getValue(_getbp2)) == true) {
				if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
					if (BurntBasicConfigConfiguration.BIOME.get() == false) {
						fireSpeed = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPREAD.get() / 100.0, 1.0), 1.5) * 0.99;
					} else if (BurntBasicConfigConfiguration.BIOME.get() == true) {
						fireSpeed = BiomeSpecificProcedure.execute(world, x, y, z);
					}
					if (Math.random() <= fireSpeed) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_BIRCH_WOOD) {
							world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.BIRCH_WOOD.defaultBlockState()));
						} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_WOOD) {
							world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.JUNGLE_WOOD.defaultBlockState()));
						} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_MANGROVE_WOOD) {
							world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.MANGROVE_WOOD.defaultBlockState()));
						} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_CHERRY_WOOD) {
							world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.CHERRY_WOOD.defaultBlockState()));
						} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SOOTY_PALE_OAK_WOOD) {
							world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(net.pixelbank.burnt.util.BurntVanilla.stateOf("pale_oak_wood")));
						}
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							net.minecraft.world.level.block.Block _targetWood =
									(world.getBlockState(_bp)).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_WOOD
											? BurntModBlocks.SMOLDERING_DARK_OAK_WOOD
											: BurntModBlocks.SMOLDERING_WOOD;
							BlockState _bs = (blockStateWithInt(_targetWood.defaultBlockState(), "stage", 1));
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					}
				} else {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_WOOD.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
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