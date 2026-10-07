package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class GrassStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double GrassSpeed = 0;
		double fireSpeed = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		boolean done = false;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_GRASS_BLOCKS)
					|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_IW_GRASS_BLOCKS)
					|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_RU_GRASS_BLOCKS)
					|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BOP_GRASS_BLOCKS)
					|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BWG_GRASS_BLOCKS)) {
				// Convert plants above before replacing the block below
				convertPlantsAbove(world, x, y, z);
				world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_GRASS_START.defaultBlockState(), "stage", 1)), 3);
			}
		}
	}

	// Turn plants above into their burning versions
	public static void convertPlantsAbove(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) return;
		BlockPos abovePos = BlockPos.containing(x, y + 1, z);
		var aboveBlock = world.getBlockState(abovePos).getBlock();
		// Skip our own blocks - this runs twice per conversion and would undo the first result
		ResourceLocation _key = BuiltInRegistries.BLOCK.getKey(aboveBlock);
		if (_key != null && "burnt".equals(_key.getNamespace())) return;
		if (aboveBlock == Blocks.FERN || aboveBlock == Blocks.LARGE_FERN) {
			world.setBlock(abovePos, BurntModBlocks.SMOLDERING_FERN.defaultBlockState(), 3);
		} else if (aboveBlock == Blocks.SHORT_GRASS || aboveBlock == Blocks.DEAD_BUSH) {
			world.setBlock(abovePos, BurntModBlocks.TALL_GRASS_FIRE.defaultBlockState(), 3);
		} else if (aboveBlock == Blocks.TALL_GRASS) {
			BlockState aboveState = world.getBlockState(abovePos);
			boolean isLower = (aboveState.getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty<?> _ep
					? aboveState.getValue(_ep).toString() : "").equals("lower");
			if (isLower) {
				world.setBlock(BlockPos.containing(x, y + 2, z), Blocks.AIR.defaultBlockState(), 3);
				world.setBlock(abovePos, BurntModBlocks.DOUBLE_TALL_GRASS_FIRE.defaultBlockState(), 3);
			}
		} else {
			BlockState aboveState = world.getBlockState(abovePos);
			if (aboveState.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_TALL_GRASS)) {
				// Modded tall grass - must stay above the catch-all below or it just gets destroyed
				world.setBlock(abovePos, BurntModBlocks.TALL_GRASS_FIRE.defaultBlockState(), 3);
			} else if (aboveState.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_FERN)) {
				world.setBlock(abovePos, BurntModBlocks.SMOLDERING_FERN.defaultBlockState(), 3);
			} else if (!aboveState.isAir() && !aboveState.canOcclude() && aboveState.getFluidState().isEmpty()
					&& !(aboveBlock instanceof FireBlock)) {
				// Remove loose plants so they don't drop items when the block below changes
				// Fire is excluded - it's the active burn source and must keep ticking
				world.destroyBlock(abovePos, false);
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}
