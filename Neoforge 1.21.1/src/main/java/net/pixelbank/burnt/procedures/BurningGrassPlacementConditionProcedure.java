package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

// Survival check for the burning tall-grass blocks
// True when the block below counts as ground for grass
// Return false and vanilla reverts a freshly placed block straight back to air

public class BurningGrassPlacementConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		BlockState below = world.getBlockState(BlockPos.containing(x, y - 1, z));

		// Vanilla + this mod's own grass chain (the original allow-list).
		if (below.getBlock() == BurntModBlocks.BURNT_GRASS
				|| below.getBlock() == BurntModBlocks.SMOLDERING_GRASS
				|| below.getBlock() == BurntModBlocks.SMOLDERING_GRASS_START
				|| below.getBlock() == BurntModBlocks.RECOVERING_GRASS
				|| below.getBlock() == Blocks.GRASS_BLOCK
				|| below.getBlock() == Blocks.MOSS_BLOCK) {
			return true;
		}

		// Modded grass - compat tag plus the per-mod tags, for mods not in the compat tag yet
		return below.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_GRASS)
				|| below.is(BurntTags.BLOCK_GRASS_BLOCKS)
				|| below.is(BurntTags.BLOCK_BOP_GRASS_BLOCKS)
				|| below.is(BurntTags.BLOCK_BWG_GRASS_BLOCKS)
				|| below.is(BurntTags.BLOCK_IW_GRASS_BLOCKS)
				|| below.is(BurntTags.BLOCK_IW_GRASSY_BLOCKS)
				|| below.is(BurntTags.BLOCK_RU_GRASS_BLOCKS);
	}
}
