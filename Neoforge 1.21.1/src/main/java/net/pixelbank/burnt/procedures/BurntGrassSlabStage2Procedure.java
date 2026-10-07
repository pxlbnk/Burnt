package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurntGrassSlabStage2Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || (IsRainingProcedure.execute(world, x, y, z) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get())) {
				world.setBlock(BlockPos.containing(x, y, z), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
				return;
			}
			if (((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_MC_REPLACEABLE)
					|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_MC_FLOWERS))
					&& !((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.TALL_GRASS_FIRE || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.DOUBLE_TALL_GRASS_FIRE
							|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == BurntModBlocks.TALL_GRASS_BURNT)) {
				world.destroyBlock(BlockPos.containing(x, y + 1, z), false);
			}
			GrassCatchProcedure.execute(world, x, y, z);
			// Fire crosses between full soil and slabs - GrassCatch one, GrassSlabCatch the other
			GrassSlabCatchProcedure.execute(world, x, y, z);
			{
				int _value = 3;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			if (Math.random() <= BiomeChanceProcedure.execute(world, x, y, z) / 200) {
				WillitburnProcedure.execute(world, x, y, z);
			}
		}
	}
}