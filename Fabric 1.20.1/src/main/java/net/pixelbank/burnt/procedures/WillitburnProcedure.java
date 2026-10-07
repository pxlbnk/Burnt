package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameRules;
import net.minecraft.core.BlockPos;

public class WillitburnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		// Claimed ground: the spread engine - one check here covers all 51 call sites.
		if (net.pixelbank.burnt.util.BurntProtection.isProtected(world, x, y, z))
			return;
		boolean rainOK = false;
		boolean isCave = false;
		boolean waterNear = false;
		boolean isRaining = false;
		boolean allowBurn = false;
		if (!world.isClientSide()) {
			boolean _doFireTick = world.getLevelData().getGameRules().getBoolean(GameRules.RULE_DOFIRETICK);
			boolean _burntOn = BurntBasicConfigConfiguration.BURNT_ON.get();
			if (_doFireTick) {
				if (_burntOn) {
					if (world.getLevelData().isRaining()) {
						isRaining = IsRainingProcedure.execute(world, x, y, z);
					} else {
						isRaining = false;
					}
					if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_MC_IS_CAVE)) {
						isCave = true;
					}
					if (BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true) {
						if (isRaining == false) {
							rainOK = true;
						} else if (isCave == true) {
							rainOK = true;
						}
					} else {
						rainOK = true;
					}
					if (rainOK == true) {
						// Cache the state once - getFluidState calls getBlockState internally
						var bs1 = world.getBlockState(BlockPos.containing(x + 1, y - 1, z));
						var bs2 = world.getBlockState(BlockPos.containing(x - 1, y - 1, z));
						var bs3 = world.getBlockState(BlockPos.containing(x, y - 1, z - 1));
						var bs4 = world.getBlockState(BlockPos.containing(x, y - 1, z + 1));
						var bs5 = world.getBlockState(BlockPos.containing(x, y - 1, z));
						if ((bs1.getFluidState().createLegacyBlock()).getBlock() == Blocks.WATER
								|| (bs1.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp9
										&& bs1.getValue(_getbp9)) == true
								|| (bs2.getFluidState().createLegacyBlock()).getBlock() == Blocks.WATER
								|| (bs2.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp13
										&& bs2.getValue(_getbp13)) == true
								|| (bs3.getFluidState().createLegacyBlock()).getBlock() == Blocks.WATER
								|| (bs3.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp17
										&& bs3.getValue(_getbp17)) == true
								|| (bs4.getFluidState().createLegacyBlock()).getBlock() == Blocks.WATER
								|| (bs4.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp21
										&& bs4.getValue(_getbp21)) == true
								|| (bs5.getFluidState().createLegacyBlock()).getBlock() == Blocks.WATER
								|| (bs5.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp25
										&& bs5.getValue(_getbp25)) == true) {
							waterNear = true;
						}
						if (!(waterNear == true)) {
							allowBurn = true;
						}
						if (allowBurn == true) {
							TheBurnProcedure.execute(world, x, y, z);
						}
					}
				}
			}
		}
	}
}
