package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModEntities;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class RandomCollapseProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.COLLAPSE.get() == true) {
				// Bail early if this second's collapse budget is already spent
				if (!net.pixelbank.burnt.util.BurntCollapseBudget.hasHeadroom(world))
					return;
				if (Math.random() <= (double) BurntBasicConfigConfiguration.COLLAPSE_CHANCE.get() / 500) {
					sx = -3;
					for (int index0 = 0; index0 < 6; index0++) {
						sy = -3;
						for (int index1 = 0; index1 < 6; index1++) {
							sz = -3;
							for (int index2 = 0; index2 < 6; index2++) {
								BlockPos _below = BlockPos.containing(x + sx, y + sy - 1, z + sz);
								// hasChunkAt first - an unloaded lookup forces a sync chunk load and stalls the server
								if (world.hasChunkAt(_below) && (world.getBlockState(_below)).getBlock() == Blocks.AIR) {
									BlockPos _cell = BlockPos.containing(x + sx, y + sy, z + sz);
									// Read the cell once - the original re-fetched it in every branch below
									BlockState _state = world.getBlockState(_cell);
									if (_state.getBlock() == BurntModBlocks.BURNT_PLANKS) {
										world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
										if (world instanceof ServerLevel _level) {
											net.pixelbank.burnt.util.BurntCollapseBudget.noteSpawn(world);
											Entity entityToSpawn = BurntModEntities.FALLING_SLAB.spawn(_level, BlockPos.containing(x + sx, y + sy - 0.5, z + sz), MobSpawnType.MOB_SUMMONED);
											if (entityToSpawn != null) {
											}
										}
									} else if (_state.is(BurntTags.BLOCK_BURNING_PLANKS)) {
										if (Math.random() <= 0.3) {
											world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
											if (world instanceof ServerLevel _level) {
												net.pixelbank.burnt.util.BurntCollapseBudget.noteSpawn(world);
												Entity entityToSpawn = BurntModEntities.FALLING_SLAB.spawn(_level, BlockPos.containing(x + sx, y + sy - 0.5, z + sz), MobSpawnType.MOB_SUMMONED);
												if (entityToSpawn != null) {
												}
											}
										}
									} else if (_state.getBlock() == Blocks.COBBLESTONE) {
										world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
										if (world instanceof ServerLevel _level)
											net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_COBBLESTONE.defaultBlockState());
									} else if (_state.getBlock() == Blocks.STONE_BRICKS) {
										world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
										if (world instanceof ServerLevel _level)
											net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_STONE_BRICKS.defaultBlockState());
									} else if (_state.getBlock() == Blocks.BRICKS) {
										world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
										if (world instanceof ServerLevel _level)
											net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.SOOTY_BRICKS.defaultBlockState());
									} else if (_state.getBlock() == BurntModBlocks.BURNT_STAIRS) {
										if (Math.random() <= 0.3) {
											world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
											if (world instanceof ServerLevel _level) {
												net.pixelbank.burnt.util.BurntCollapseBudget.noteSpawn(world);
												Entity entityToSpawn = BurntModEntities.FALLING_SLAB.spawn(_level, BlockPos.containing(x + sx, y + sy - 1, z + sz), MobSpawnType.MOB_SUMMONED);
												if (entityToSpawn != null) {
												}
											}
										}
									} else if (_state.getBlock() == BurntModBlocks.FALLING_BURNT_SLAB) {
										if (Math.random() <= 0.2) {
											world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
											if (world instanceof ServerLevel _level) {
												net.pixelbank.burnt.util.BurntCollapseBudget.noteSpawn(world);
												Entity entityToSpawn = BurntModEntities.FALLING_SLAB.spawn(_level, BlockPos.containing(x + sx, y + sy - 1, z + sz), MobSpawnType.MOB_SUMMONED);
												if (entityToSpawn != null) {
												}
											}
										}
									}
									// sz only advances inside the air check, as the original did - widening it means more collapses
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
}
