package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class LightningStrikeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Object event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (IsRainingProcedure.isGloballyRaining(world) || world.getLevelData().isThundering()) {
				if (BurntBasicConfigConfiguration.LIGHTNING_IGNITES.get() == true) {
					if (entity instanceof LightningBolt) {
						int horizontalRadiusSphere = (int) 2 - 1;
						int verticalRadiusSphere = (int) 2 - 1;
						int yIterationsSphere = verticalRadiusSphere;
						for (int i = -yIterationsSphere; i <= yIterationsSphere; i++) {
							for (int xi = -horizontalRadiusSphere; xi <= horizontalRadiusSphere; xi++) {
								for (int zi = -horizontalRadiusSphere; zi <= horizontalRadiusSphere; zi++) {
									double distanceSq = (xi * xi) / (double) (horizontalRadiusSphere * horizontalRadiusSphere) + (i * i) / (double) (verticalRadiusSphere * verticalRadiusSphere)
											+ (zi * zi) / (double) (horizontalRadiusSphere * horizontalRadiusSphere);
									if (distanceSq <= 1.0) {
										if (!(world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi))).is(BurntTags.BLOCK_FIRE_RESISTANT) && !(world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi)).getBlock() instanceof net.minecraft.world.level.block.LightningRodBlock)) {
											world.setBlock(BlockPos.containing(x + xi, y + i, z + zi), BurntModBlocks.FIRE_SET_BLOCK.defaultBlockState(), 3);
										}
										if ((world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi))).getBlock() == BurntModBlocks.FIRE_BARREL) {
											world.setBlock(BlockPos.containing(x + xi, y + i, z + zi), BurntModBlocks.FIRE_BARREL_BLAST_BLOCK.defaultBlockState(), 3);
										}
									}
								}
							}
						}
						LeavesCollapseProcedure.execute(world, x, y, z);
					}
				}
			}
		}
	}
}