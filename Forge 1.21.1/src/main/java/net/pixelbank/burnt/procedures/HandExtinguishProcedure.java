package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class HandExtinguishProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Object event, LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip3 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip3) : -1) >= 1) {
					ExtinguishProcedure.execute(world, x, y, z);
				}
			}
		}
	}
}