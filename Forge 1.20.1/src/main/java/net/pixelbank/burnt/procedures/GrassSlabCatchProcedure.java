package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

public class GrassSlabCatchProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockState targetBlock = Blocks.AIR.defaultBlockState();
		boolean burn = false;
		boolean done = false;
		double GrassSpeed = 0;
		double fireSpeed = 0;
		double targetx = 0;
		double targety = 0;
		double targetz = 0;
		double dist = 0;
		double ground = 0;
		double windChance = 0;
		double windChancePercent = 0;
		double finalChance01 = 0;
		double ground01 = 0;
		if (!world.isClientSide()) {
			// No half-height soil to spread to without Terrain Slabs
			final net.minecraft.world.level.block.Block _grassSlab = net.pixelbank.burnt.compat.TerrainSlabsBridge.grassSlab();
			if (_grassSlab == null) return;
			if (BurntBasicConfigConfiguration.WIND.get() == true) {
				dist = (double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get();
				if (BurntBasicConfigConfiguration.BIOME.get() == true) {
					ground = BiomeSpecificGroundProcedure.execute(world, x, y, z);
				} else {
					ground = (double) BurntBasicConfigConfiguration.GROUND_SPREAD.get();
				}
				// ground01 is constant for all directions - calculate once
				ground01 = ground / 100;
				if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
					// Same level - east
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, 1, 0);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x + 1;
						targety = y;
						targetz = z;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Same level - west
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, -1, 0);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x - 1;
						targety = y;
						targetz = z;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Same level - south
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, 0, 1);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x;
						targety = y;
						targetz = z + 1;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Same level - north
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, 0, -1);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x;
						targety = y;
						targetz = z - 1;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Upper level - east
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, 1, 0);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x + 1;
						targety = y + 1;
						targetz = z;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Upper level - west
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, -1, 0);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x - 1;
						targety = y + 1;
						targetz = z;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Upper level - south
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, 0, 1);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x;
						targety = y + 1;
						targetz = z + 1;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Upper level - north
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, 0, -1);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x;
						targety = y + 1;
						targetz = z - 1;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Lower level - east
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, 1, 0);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x + 1;
						targety = y - 1;
						targetz = z;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Lower level - west
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, -1, 0);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x - 1;
						targety = y - 1;
						targetz = z;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Lower level - south
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, 0, 1);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x;
						targety = y - 1;
						targetz = z + 1;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
					// Lower level - north
					windChance = WindAdjustedChanceProcedure.execute(world, x, y, z, 1, 0, -1);
					finalChance01 = windChance * ground01;
					windChancePercent = finalChance01 * 100;
					if (Mth.nextInt(world.getRandom(), 1, 100) <= windChancePercent) {
						targetx = x;
						targety = y - 1;
						targetz = z - 1;
						targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
						if (targetBlock.getBlock() == _grassSlab) {
							if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
							} else {
								world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), _grassSlab.defaultBlockState()), 3);
							}
						}
					}
				}
			} else {
				dist = (double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get();
				if (BurntBasicConfigConfiguration.BIOME.get() == true) {
					ground = BiomeSpecificGroundProcedure.execute(world, x, y, z);
				} else {
					ground = (double) BurntBasicConfigConfiguration.GROUND_SPREAD.get();
				}
				if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
					targetx = x + 1;
					targety = y;
					targetz = z;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x - 1;
					targety = y;
					targetz = z;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x;
					targety = y;
					targetz = z + 1;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x;
					targety = y;
					targetz = z - 1;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x + 1;
					targety = y + 1;
					targetz = z;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x - 1;
					targety = y + 1;
					targetz = z;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x;
					targety = y + 1;
					targetz = z + 1;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x;
					targety = y + 1;
					targetz = z - 1;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x + 1;
					targety = y - 1;
					targetz = z;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x - 1;
					targety = y - 1;
					targetz = z;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x;
					targety = y - 1;
					targetz = z + 1;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
					targetx = x;
					targety = y - 1;
					targetz = z - 1;
					targetBlock = world.getBlockState(BlockPos.containing(targetx, targety, targetz));
					if (targetBlock.getBlock() == _grassSlab) {
						if (Mth.nextInt(world.getRandom(), 0, 100) <= ground) {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 1)), 3);
						} else {
							world.setBlock(BlockPos.containing(targetx, targety, targetz), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(targetx, targety, targetz)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
						}
					}
				}
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}
