package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;
import net.minecraftforge.fml.ModList;

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
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class BurntLogsTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			// Embers only spread from actively-burning blocks (stage >= 1), with sufficient wind
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip0 ? blockstate.getValue(_getip0) : -1) >= 1) {
				if (BurntBasicConfigConfiguration.EMBER_SPREAD.get() && BurntModVariables.MapVariables.get(world).windSpeed > 30) {
					if (Math.random() <= BiomeChanceProcedure.execute(world, x, y, z) / 100) {
						EmberSpreadProcedure.execute(world, x, y, z);
					}
				}
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) >= 1) {
				if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
					if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = (BurntModBlocks.BURNT_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp24
									? BurntModBlocks.BURNT_LOG.defaultBlockState().setValue(_withbp24, true)
									: BurntModBlocks.BURNT_LOG.defaultBlockState());
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					} else {
						if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip27 ? blockstate.getValue(_getip27) : -1) == 1) {
							BurntLogsStage1Procedure.execute(world, x, y, z);
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip29 ? blockstate.getValue(_getip29) : -1) == 2) {
							BurntLogsStage2Procedure.execute(world, x, y, z);
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip31 ? blockstate.getValue(_getip31) : -1) == 3) {
							BurntLogsStage3Procedure.execute(world, x, y, z);
						}
					}
				} else {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.BURNT_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp32
								? BurntModBlocks.BURNT_LOG.defaultBlockState().setValue(_withbp32, true)
								: BurntModBlocks.BURNT_LOG.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
			if (ModList.get().isLoaded("cold_sweat")) {
				if (Math.random() <= 0.5) {
					if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(4 / 2d), e -> true).isEmpty()) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"/temp modify @p addition world 0.1 false");
						BurntMod.queueServerWork((int) Mth.nextDouble(world.getRandom(), 200, 500), () -> {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										"/temp modify @p addition world -0.1 false");
						});
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