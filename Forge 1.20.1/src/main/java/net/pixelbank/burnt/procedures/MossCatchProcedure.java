package net.pixelbank.burnt.procedures;


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

public class MossCatchProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockState targetBlock = Blocks.AIR.defaultBlockState();
		boolean oxygen = false;
		double GrassSpeed = 0;
		double fireSpeed = 0;
		double targetz = 0;
		double targety = 0;
		double targetx = 0;
		double dist = 0;
		double spread = 0;
		if (!world.isClientSide()) {
			dist = (double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get();
			spread = (double) BurntBasicConfigConfiguration.BURN_SPREAD.get();
			if (BurntBasicConfigConfiguration.BIOME.get() == true) {
				GrassSpeed = BiomeSpecificGroundProcedure.execute(world, x, y, z);
			} else {
				GrassSpeed = (double) BurntBasicConfigConfiguration.GROUND_SPREAD.get();
			}
			if (Mth.nextInt(world.getRandom(), 0, 100) <= spread) {
				targetx = x + 1;
				targety = y;
				targetz = z;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x - 1;
				targety = y;
				targetz = z;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x;
				targety = y;
				targetz = z + 1;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x;
				targety = y;
				targetz = z - 1;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x + 1;
				targety = y + 1;
				targetz = z;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x - 1;
				targety = y + 1;
				targetz = z;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x;
				targety = y + 1;
				targetz = z + 1;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x;
				targety = y + 1;
				targetz = z - 1;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x + 1;
				targety = y - 1;
				targetz = z;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x - 1;
				targety = y - 1;
				targetz = z;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x;
				targety = y - 1;
				targetz = z + 1;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
					}
				}
			}
			if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(dist / 2d), e -> true).isEmpty()) {
				targetx = x;
				targety = y - 1;
				targetz = z - 1;
				targetBlock = (world.getBlockState(BlockPos.containing(targetx, targety, targetz)));
				oxygen = !(world.getBlockFloorHeight(BlockPos.containing(targetx + 1, targety, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx - 1, targety, targetz)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz - 1)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety, targetz + 1)) > 0)
						|| !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety + 1, targetz)) > 0) || !(world.getBlockFloorHeight(BlockPos.containing(targetx, targety - 1, targetz)) > 0);
				if (targetBlock.getBlock() == Blocks.MOSS_BLOCK && oxygen == true) {
					if (Mth.nextInt(world.getRandom(), 0, 100) <= GrassSpeed) {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					} else {
						world.setBlock(BlockPos.containing(targetx, targety, targetz), BurntModBlocks.BURNT_MOSS.defaultBlockState(), 3);
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