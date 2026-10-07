package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;
import net.neoforged.fml.ModList;

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

public class BurntFenceTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) >= 1) {
				if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
					BlockPos _kfbp = BlockPos.containing(x, y, z);
					BlockState _kfbs = BurntModBlocks.BURNT_FENCE.defaultBlockState();
					BlockState _kfbso = world.getBlockState(_kfbp);
					for (Property<?> _prop : _kfbso.getProperties()) {
						Property _propNew = _kfbs.getBlock().getStateDefinition().getProperty(_prop.getName());
						if (_propNew != null) try { _kfbs = _kfbs.setValue(_propNew, _kfbso.getValue(_prop)); } catch (Exception e) {}
					}
					world.setBlock(_kfbp, _kfbs, 3);
					return;
				}
				if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
					if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.BURNT_FENCE.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						{
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
						}
					} else {
						if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip28 ? blockstate.getValue(_getip28) : -1) == 1) {
							BurntFenceStage1Procedure.execute(world, x, y, z);
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip30 ? blockstate.getValue(_getip30) : -1) == 2) {
							BurntFenceStage2Procedure.execute(world, x, y, z);
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip32 ? blockstate.getValue(_getip32) : -1) == 3) {
							BurntFenceStage3Procedure.execute(world, x, y, z);
						}
					}
				} else {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_FENCE.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
					}
				}
			}
			if (ModList.get().isLoaded("cold_sweat")) {
				if (Math.random() <= 0.25) {
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
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}