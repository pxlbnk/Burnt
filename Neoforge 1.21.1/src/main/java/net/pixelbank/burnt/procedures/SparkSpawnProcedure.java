package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class SparkSpawnProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true && !IsRainingProcedure.isGloballyRaining(world)) {
				if (world.getBlockState(BlockPos.containing(entity.getX() + 1, entity.getY(), entity.getZ())).canOcclude()) {
					ArrowFlamesPlacementProcedure.execute(world, entity.getX() + 1, entity.getY(), entity.getZ());
				}
				if (world.getBlockState(BlockPos.containing(entity.getX() - 1, entity.getY(), entity.getZ())).canOcclude()) {
					ArrowFlamesPlacementProcedure.execute(world, entity.getX() - 1, entity.getY(), entity.getZ());
				}
				if (world.getBlockState(BlockPos.containing(entity.getX(), entity.getY(), entity.getZ() + 1)).canOcclude()) {
					ArrowFlamesPlacementProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ() + 1);
				}
				if (world.getBlockState(BlockPos.containing(entity.getX(), entity.getY(), entity.getZ() - 1)).canOcclude()) {
					ArrowFlamesPlacementProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ() - 1);
				}
			} else if (BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == false) {
				if (world.getBlockState(BlockPos.containing(entity.getX() + 1, entity.getY(), entity.getZ())).canOcclude()) {
					ArrowFlamesPlacementProcedure.execute(world, entity.getX() + 1, entity.getY(), entity.getZ());
				}
				if (world.getBlockState(BlockPos.containing(entity.getX() - 1, entity.getY(), entity.getZ())).canOcclude()) {
					ArrowFlamesPlacementProcedure.execute(world, entity.getX() - 1, entity.getY(), entity.getZ());
				}
				if (world.getBlockState(BlockPos.containing(entity.getX(), entity.getY(), entity.getZ() + 1)).canOcclude()) {
					ArrowFlamesPlacementProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ() + 1);
				}
				if (world.getBlockState(BlockPos.containing(entity.getX(), entity.getY(), entity.getZ() - 1)).canOcclude()) {
					ArrowFlamesPlacementProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ() - 1);
				}
			}
		}
	}
}