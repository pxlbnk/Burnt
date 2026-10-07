package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
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

public class BurningMossTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
				return;
			}
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
				} else {
					if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip22 ? blockstate.getValue(_getip22) : -1) == 1) {
						SmolderingMossStage1Procedure.execute(world, x, y, z);
					} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip24 ? blockstate.getValue(_getip24) : -1) == 2) {
						SmolderingMossStage2Procedure.execute(world, x, y, z);
					} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip26 ? blockstate.getValue(_getip26) : -1) == 3) {
						SmolderingMossStage3Procedure.execute(world, x, y, z);
					}
				}
			} else {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
			}
		}
	}
}