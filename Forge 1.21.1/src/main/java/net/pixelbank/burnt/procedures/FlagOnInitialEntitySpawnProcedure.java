package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.entity.FlagEntity;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class FlagOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.WHITE_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "white");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.BLACK_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "black");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.RED_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "red");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.YELLOW_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "yellow");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.PINK_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "pink");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.BLUE_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "blue");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.LIGHT_BLUE_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "light_blue");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.CYAN_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "cyan");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.GREEN_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "green");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.LIME_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "lime");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.PURPLE_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "purple");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.MAGENTA_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "magenta");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.ORANGE_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "orange");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.BROWN_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "brown");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.GREY_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "grey");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.LIGHT_GREY_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "light_grey");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.WIND_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "wind");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.RAINBOW_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "rainbow");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.PIRATE_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "pirate");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.CREEPER_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "creeper");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.WILDERNESS_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "wilderness");
		} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == BurntModBlocks.FIRE_FLAG) {
			if (entity instanceof FlagEntity _datEntSetS)
				_datEntSetS.getEntityData().set(FlagEntity.DATA_color, "fire");
		}
	}
}