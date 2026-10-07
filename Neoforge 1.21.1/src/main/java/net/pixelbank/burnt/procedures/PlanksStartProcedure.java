package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

public class PlanksStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double sx = 0;
		double sy = 0;
		double sz = 0;
		boolean found = false;
		boolean done = false;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.OAK_PLANKS) {
				world.setBlock(BlockPos.containing(x, y, z),
						(BurntModBlocks.SOOTY_OAK_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp3
								? BurntModBlocks.SOOTY_OAK_PLANKS.defaultBlockState().setValue(_withbp3, true)
								: BurntModBlocks.SOOTY_OAK_PLANKS.defaultBlockState()),
						3);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SPRUCE_PLANKS) {
				world.setBlock(BlockPos.containing(x, y, z),
						(BurntModBlocks.SOOTY_SPRUCE_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp7
								? BurntModBlocks.SOOTY_SPRUCE_PLANKS.defaultBlockState().setValue(_withbp7, true)
								: BurntModBlocks.SOOTY_SPRUCE_PLANKS.defaultBlockState()),
						3);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BIRCH_PLANKS) {
				world.setBlock(BlockPos.containing(x, y, z),
						(BurntModBlocks.SOOTY_BIRCH_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp11
								? BurntModBlocks.SOOTY_BIRCH_PLANKS.defaultBlockState().setValue(_withbp11, true)
								: BurntModBlocks.SOOTY_BIRCH_PLANKS.defaultBlockState()),
						3);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.JUNGLE_PLANKS) {
				world.setBlock(BlockPos.containing(x, y, z),
						(BurntModBlocks.SOOTY_JUNGLE_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp15
								? BurntModBlocks.SOOTY_JUNGLE_PLANKS.defaultBlockState().setValue(_withbp15, true)
								: BurntModBlocks.SOOTY_JUNGLE_PLANKS.defaultBlockState()),
						3);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.ACACIA_PLANKS) {
				world.setBlock(BlockPos.containing(x, y, z),
						(BurntModBlocks.SOOTY_ACACIA_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp19
								? BurntModBlocks.SOOTY_ACACIA_PLANKS.defaultBlockState().setValue(_withbp19, true)
								: BurntModBlocks.SOOTY_ACACIA_PLANKS.defaultBlockState()),
						3);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DARK_OAK_PLANKS) {
				world.setBlock(BlockPos.containing(x, y, z),
						(BurntModBlocks.SOOTY_DARK_OAK_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp23
								? BurntModBlocks.SOOTY_DARK_OAK_PLANKS.defaultBlockState().setValue(_withbp23, true)
								: BurntModBlocks.SOOTY_DARK_OAK_PLANKS.defaultBlockState()),
						3);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_PLANKS) {
				world.setBlock(BlockPos.containing(x, y, z),
						(BurntModBlocks.SOOTY_MANGROVE_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp27
								? BurntModBlocks.SOOTY_MANGROVE_PLANKS.defaultBlockState().setValue(_withbp27, true)
								: BurntModBlocks.SOOTY_MANGROVE_PLANKS.defaultBlockState()),
						3);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CHERRY_PLANKS) {
				world.setBlock(BlockPos.containing(x, y, z),
						(BurntModBlocks.SOOTY_CHERRY_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp31
								? BurntModBlocks.SOOTY_CHERRY_PLANKS.defaultBlockState().setValue(_withbp31, true)
								: BurntModBlocks.SOOTY_CHERRY_PLANKS.defaultBlockState()),
						3);
			} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO_PLANKS) {
				world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_PLANKS.defaultBlockState(), "stage", 1)), 3);
			} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "pale_oak_planks")) {
				world.setBlock(BlockPos.containing(x, y, z),
						(BurntModBlocks.SOOTY_PALE_OAK_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbppo31
								? BurntModBlocks.SOOTY_PALE_OAK_PLANKS.defaultBlockState().setValue(_withbppo31, true)
								: BurntModBlocks.SOOTY_PALE_OAK_PLANKS.defaultBlockState()),
						3);
			} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x, y, z)), "bamboo_planks")) {
				world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_BAMBOO_PLANKS.defaultBlockState(), "stage", 1)), 3);
			} else if (((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_PLANKS) || (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_PLANKS))
					&& !(world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FIRE_RESISTANT)
					&& !(world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_TF_TOWERWOOD)) {
				// No flame placement here - it made modded wood flash over in seconds
				final net.minecraft.world.level.block.Block _origBlock = world.getBlockState(BlockPos.containing(x, y, z)).getBlock();
				BurntMod.queueServerWork(Mth.nextInt(world.getRandom(), 1, 200), () -> {
					// Bail if something already converted this block - the delay lets duplicates queue up
					if (world.getBlockState(BlockPos.containing(x, y, z)).getBlock() != _origBlock)
						return;
					world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_PLANKS.defaultBlockState(), "stage", 1)), 3);
				});
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}
