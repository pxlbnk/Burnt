package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class FireBarrelBlastProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			BlastParticlesProcedure.execute(world, x, y, z);
			// Remove the blast block before exploding - physics mods capture it and it re-fires forever
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.FIRE_BARREL_BLAST_BLOCK) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.FIRE_SET_BLOCK.defaultBlockState(), 3);
			}
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, x, y, z, 1, Level.ExplosionInteraction.TNT);
			int horizontalRadiusSphere = (int) 3 - 1;
			int verticalRadiusSphere = (int) 3 - 1;
			int yIterationsSphere = verticalRadiusSphere;
			for (int i = -yIterationsSphere; i <= yIterationsSphere; i++) {
				for (int xi = -horizontalRadiusSphere; xi <= horizontalRadiusSphere; xi++) {
					for (int zi = -horizontalRadiusSphere; zi <= horizontalRadiusSphere; zi++) {
						double distanceSq = (xi * xi) / (double) (horizontalRadiusSphere * horizontalRadiusSphere) + (i * i) / (double) (verticalRadiusSphere * verticalRadiusSphere)
								+ (zi * zi) / (double) (horizontalRadiusSphere * horizontalRadiusSphere);
						if (distanceSq <= 1.0) {
							if ((world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi))).canBeReplaced() || (world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi))).is(BurntTags.BLOCK_AIR)) {
								world.setBlock(BlockPos.containing(x + xi, y + i, z + zi), BurntModBlocks.FIRE_SET_BLOCK.defaultBlockState(), 3);
							}
							if ((world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi))).getBlock() == BurntModBlocks.FIRE_BARREL
									|| (world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi))).getBlock() == BurntModBlocks.FIRE_BARREL_ACTIVE) {
								world.setBlock(BlockPos.containing(x + xi, y + i, z + zi), BurntModBlocks.FIRE_BARREL_BLAST_BLOCK.defaultBlockState(), 3);
							}
							if ((world.getBlockState(BlockPos.containing(x + xi, y + i, z + zi))).getBlock() == Blocks.TNT) {
								{
									BlockPos _bp = BlockPos.containing(x + xi, y + i, z + zi);
									BlockState _bs = BurntModBlocks.FIRE_BARREL_BLAST_BLOCK.defaultBlockState();
									BlockState _bso = world.getBlockState(_bp);
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
									world.setBlock(_bp, _bs, 3);
								}
							}
						}
					}
				}
			}
			LeavesCollapseProcedure.execute(world, x, y, z);
			if (Math.random() <= 0.3) {
				CollapseProcedure.execute(world, x, y, z);
			}
		}
	}
}