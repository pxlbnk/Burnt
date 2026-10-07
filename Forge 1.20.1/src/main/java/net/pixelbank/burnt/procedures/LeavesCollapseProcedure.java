package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

public class LeavesCollapseProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double particleRadius = 0;
		double particleAmount = 0;
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.COLLAPSE.get() == true) {
				// Skip the whole scan when this seconds collapse budget is already spent.
				if (!net.pixelbank.burnt.util.BurntCollapseBudget.hasHeadroom(world))
					return;
				sx = -5;
				for (int index0 = 0; index0 < 10; index0++) {
					sy = -12;
					for (int index1 = 0; index1 < 24; index1++) {
						sz = -5;
						for (int index2 = 0; index2 < 10; index2++) {
							BlockPos _cell = BlockPos.containing(x + sx, y + sy, z + sz);
							// 10x24x10 cells, so an unguarded lookup here is the worst case for sync chunk loads
							if (world.hasChunkAt(_cell) && (world.getBlockState(_cell)).is(BurntTags.BLOCK_MC_LEAVES)) {
								if (Math.random() <= 0.2) {
									{
										BlockPos _bp = _cell;
										BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
										BlockState _bso = world.getBlockState(_bp);
										_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
										if (_bs.hasProperty(BlockStateProperties.WATERLOGGED))
											_bs = _bs.setValue(BlockStateProperties.WATERLOGGED, false);
										world.setBlock(_bp, _bs, 3);
									}
								} else if (Math.random() <= 0.2) {
									if (world instanceof ServerLevel _level)
										net.pixelbank.burnt.util.BurntBlockUtil.fallNoDrop(_level, _cell, BurntModBlocks.BLAST_LEAVES.defaultBlockState());
								} else if (Math.random() <= 0.5) {
									world.setBlock(_cell, Blocks.AIR.defaultBlockState(), 3);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (BurntModParticleTypes.BURNT_LEAVES_PARTICLE), (x + sx), (y + sy), (z + sz), 1, 1, 1, 1, 0.15);
									if (world instanceof ServerLevel _level)
										_level.sendParticles((SimpleParticleType) (BurntModParticleTypes.SMOLDER_LEAVES_PARTICLE), (x + sx), (y + sy), (z + sz), 2, 1, 1, 1, 0.2);
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
}
