package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class LeafPileCatchProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			sx = -1;
			for (int index0 = 0; index0 < 3; index0++) {
				sy = -1;
				for (int index1 = 0; index1 < 3; index1++) {
					sz = -1;
					for (int index2 = 0; index2 < 3; index2++) {
						if (((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp2
								&& (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getValue(_getbp2)) == false) {
							if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.OAK_LEAVES) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_OAK_LEAVES.defaultBlockState(), 3);
								}
							} else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.SPRUCE_LEAVES) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_SPRUCE_LEAVES.defaultBlockState(), 3);
								}
							} else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.BIRCH_LEAVES) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_BIRCH_LEAVES.defaultBlockState(), 3);
								}
							} else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.JUNGLE_LEAVES) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_JUNGLE_LEAVES.defaultBlockState(), 3);
								}
							} else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.ACACIA_LEAVES) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_ACACIA_LEAVES.defaultBlockState(), 3);
								}
							} else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.DARK_OAK_LEAVES) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_DARK_OAK_LEAVES.defaultBlockState(), 3);
								}
							} else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.MANGROVE_LEAVES) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_MANGROVE_LEAVES.defaultBlockState(), 3);
								}
							} else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.CHERRY_LEAVES) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState(), 3);
								}
							} else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.AZALEA_LEAVES) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState(), 3);
								}
							} else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.FLOWERING_AZALEA_LEAVES) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState(), 3);
								}
							} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz)), "pale_oak_leaves")) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_PALE_OAK_LEAVES.defaultBlockState(), 3);
								}
							} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz)), "azalea_leaves")) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_PALE_OAK_LEAVES.defaultBlockState(), 3);
								}
							} else if (net.pixelbank.burnt.util.BurntVanilla.is(world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz)), "flowering_azalea_leaves")) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_PALE_OAK_LEAVES.defaultBlockState(), 3);
								}
							} else if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_MC_LEAVES)
									&& !(world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_NO_BURN)) {
								if (Math.random() <= 0.6) {
									world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.SMOLDERING_SPRUCE_LEAVES.defaultBlockState(), 3);
								}
							}
						}
						sz = sz + 1;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
		}
	}
}