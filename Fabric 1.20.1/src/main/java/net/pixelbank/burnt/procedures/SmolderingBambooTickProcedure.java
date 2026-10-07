package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.pixelbank.burnt.util.BurntBlockUtil;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class SmolderingBambooTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) >= 1) {
				if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
					if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
						BambooExtinguishProcedure.execute(world, x, y, z);
					} else {
						if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip21 ? blockstate.getValue(_getip21) : -1) == 1) {
							BurntBambooStage1Procedure.execute(world, x, y, z);
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip23 ? blockstate.getValue(_getip23) : -1) == 2) {
							BurntBambooStage2Procedure.execute(world, x, y, z);
						}
					}
				} else {
					BambooExtinguishProcedure.execute(world, x, y, z);
				}
			}
		}
	}
}