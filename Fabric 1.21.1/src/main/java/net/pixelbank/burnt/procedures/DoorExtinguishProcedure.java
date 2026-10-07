package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class DoorExtinguishProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_DOOR) {
				DoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_OAK_DOOR) {
				OakDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_DARK_OAK_DOOR) {
				DarkOakDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_SPRUCE_DOOR) {
				SpruceDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_BIRCH_DOOR) {
				BirchDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_JUNGLE_DOOR) {
				JungleDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_MANGROVE_DOOR) {
				MangroveDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_CHERRY_DOOR) {
				CherryDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_ACACIA_DOOR) {
				AcaciaDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_BAMBOO_DOOR) {
				BambooDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_PALE_OAK_DOOR) {
				PaleOakDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_ACACIA_DOOR) {
				AcaciaDoorTickProcedure.execute(world, x, y, z);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_BAMBOO_DOOR) {
				BambooDoorTickProcedure.execute(world, x, y, z);
			}
		}
	}
}