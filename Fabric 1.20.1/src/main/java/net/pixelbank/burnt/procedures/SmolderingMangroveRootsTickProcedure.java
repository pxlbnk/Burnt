package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class SmolderingMangroveRootsTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if ((net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true)) {
				if ((world.getLevelData().isRaining() || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z))) {
					BurntMod.queueServerWork((int) Mth.nextDouble(world.getRandom(), 20, 100), () -> {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.BURNT_MANGROVE_ROOTS.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					});
				}
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip7 ? blockstate.getValue(_getip7) : -1) >= 1) {
				if (Math.random() <= BiomeChanceProcedure.execute(world, x, y, z) / 100) {
					WillitburnProcedure.execute(world, x, y, z);
				}
				if (Math.random() <= 0.25) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_MANGROVE_ROOTS.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
		}
	}
}