package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class DoorStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep2 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep2).toString() : "")
					.equalsIgnoreCase("lower")) {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.OAK_DOOR) {
					OakDoorStartProcedure.execute(world, x, y, z);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SPRUCE_DOOR) {
					SpruceDoorStartProcedure.execute(world, x, y, z);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BIRCH_DOOR) {
					BirchDoorStartProcedure.execute(world, x, y, z);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DARK_OAK_DOOR) {
					DarkOakDoorStartProcedure.execute(world, x, y, z);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.ACACIA_DOOR) {
					AcaciaDoorStartProcedure.execute(world, x, y, z);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.JUNGLE_DOOR) {
					JungleDoorStartProcedure.execute(world, x, y, z);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_DOOR) {
					MangroveDoorStartProcedure.execute(world, x, y, z);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CHERRY_DOOR) {
					CherryDoorStartProcedure.execute(world, x, y, z);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_DOOR) {
					BambooDoorStartProcedure.execute(world, x, y, z);
				} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "pale_oak_door")) {
					PaleOakDoorStartProcedure.execute(world, x, y, z);
				} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "bamboo_door")) {
					BambooDoorStartProcedure.execute(world, x, y, z);
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_WOODEN_DOORS) || (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_DOOR))
						&& !(world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FIRE_RESISTANT)) {
					BurntDoorStartProcedure.execute(world, x, y, z);
				}
			}
		}
	}
}