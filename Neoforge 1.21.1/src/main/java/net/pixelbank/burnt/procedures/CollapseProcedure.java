package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class CollapseProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.COLLAPSE.get() == true) {
				// Skip the whole scan when this seconds collapse budget is already spent.
				if (!net.pixelbank.burnt.util.BurntCollapseBudget.hasHeadroom(world))
					return;
				sx = -4;
				for (int index0 = 0; index0 < 8; index0++) {
					sy = -4;
					for (int index1 = 0; index1 < 8; index1++) {
						sz = -4;
						for (int index2 = 0; index2 < 8; index2++) {
							BlockPos _below = BlockPos.containing(x + sx, y + sy - 1, z + sz);
							// hasChunkAt first - an unloaded lookup forces a sync chunk load and stalls the server
							if (world.hasChunkAt(_below) && (world.getBlockState(_below)).getBlock() == Blocks.AIR) {
								BlockPos _cell = BlockPos.containing(x + sx, y + sy, z + sz);
								// Read the cell ONCE instead of re-fetching it in every branch below.
								BlockState _state = world.getBlockState(_cell);
								if (_state.is(BurntTags.BLOCK_MC_PLANKS)) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.EMBER_PLANKS.defaultBlockState());
								} else if (_state.getBlock() == Blocks.COBBLESTONE) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_COBBLESTONE_SLAB.defaultBlockState());
								} else if (_state.is(BurntTags.BLOCK_MC_LOGS)) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.EMBER_LOG.defaultBlockState());
								} else if (_state.is(BurntTags.BLOCK_MC_WOODEN_STAIRS)) {
									if (Math.random() <= 0.3) {
										world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
										if (world instanceof ServerLevel _level)
											net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.FALLING_BURNT_SLAB.defaultBlockState());
									}
								} else if (_state.is(BurntTags.BLOCK_MC_WOODEN_SLABS)) {
									if (Math.random() <= 0.2) {
										world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
										if (world instanceof ServerLevel _level)
											net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.FALLING_BURNT_SLAB.defaultBlockState());
									}
								} else if (_state.getBlock() == Blocks.STONE_BRICKS) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_STONE_BRICKS.defaultBlockState());
								} else if (_state.getBlock() == Blocks.BRICKS) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_BRICKS.defaultBlockState());
								} else if (_state.getBlock() == Blocks.STONE) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_STONE.defaultBlockState());
								} else if (_state.getBlock() == Blocks.COBBLESTONE_STAIRS) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_COBBLESTONE_SLAB.defaultBlockState());
								} else if (_state.getBlock() == Blocks.BRICK_STAIRS) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_BRICK_SLAB.defaultBlockState());
								} else if (_state.getBlock() == Blocks.COBBLESTONE_SLAB) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_COBBLESTONE_SLAB.defaultBlockState());
								} else if (_state.getBlock() == Blocks.BRICK_SLAB) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_BRICK_SLAB.defaultBlockState());
								}
								sz = sz + 1;
							}
						}
						sy = sy + 1;
					}
					sx = sx + 1;
				}
			}
		}
	}
}
