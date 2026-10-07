package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class IsGrassBlockProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.GRASS_BLOCK) {
			return true;
		}
		if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("biomeswevegone:lush_grass_block")) {
			return true;
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_IW_GRASSY_BLOCKS)) {
			return true;
		}
		return false;
	}
}