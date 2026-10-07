package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.pixelbank.burnt.util.BurntBlockUtil;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class BurntHollowLogsTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			// Embers only spread from actively-burning blocks (stage >= 1), with sufficient wind
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip0 ? blockstate.getValue(_getip0) : -1) >= 1) {
				if (BurntBasicConfigConfiguration.EMBER_SPREAD.get() && BurntModVariables.MapVariables.get(world).windSpeed > 30) {
					if (Math.random() <= BiomeChanceProcedure.execute(world, x, y, z) / 10000) {
						EmberSpreadProcedure.execute(world, x, y, z);
					}
				}
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) >= 1) {
				if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
					if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = (BurntModBlocks.BURNT_HOLLOW_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp24
									? BurntModBlocks.BURNT_HOLLOW_LOG.defaultBlockState().setValue(_withbp24, true)
									: BurntModBlocks.BURNT_HOLLOW_LOG.defaultBlockState());
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					} else {
						if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip27 ? blockstate.getValue(_getip27) : -1) == 1) {
							BurntHollowLogsStage1Procedure.execute(world, x, y, z);
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip29 ? blockstate.getValue(_getip29) : -1) == 2) {
							BurntHollowLogsStage2Procedure.execute(world, x, y, z);
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip31 ? blockstate.getValue(_getip31) : -1) == 3) {
							BurntHollowLogsStage3Procedure.execute(world, x, y, z);
						}
					}
				} else {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.BURNT_HOLLOW_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp32
								? BurntModBlocks.BURNT_HOLLOW_LOG.defaultBlockState().setValue(_withbp32, true)
								: BurntModBlocks.BURNT_HOLLOW_LOG.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
			{ // NOT gated on distant_plumes: long-range smoke must run with plumes off. Plumes gated inside.
				if (Math.random() <= 0.25) {
					SpawnSignalPlumeXYZProcedure.execute(world, x, y, z);
				}
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}