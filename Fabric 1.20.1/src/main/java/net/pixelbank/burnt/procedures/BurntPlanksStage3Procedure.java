package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

public class BurntPlanksStage3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double burnTime = 0;
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || (IsRainingProcedure.execute(world, x, y, z) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get())) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_PLANKS.defaultBlockState(), 3);
				return;
			}
			if (Math.random() <= 0.05) {
				RandomCollapseProcedure.execute(world, x, y, z);
			}
			burnTime = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPEED.get() / 100.0, 1.0), 1.5) * 100.0;
			if (Mth.nextInt(world.getRandom(), 0, 250) <= burnTime) {
				if (Math.random() <= 0.05) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_SLAB.defaultBlockState();
						BlockEntity _be = world.getBlockEntity(_bp);
						CompoundTag _bnbt = null;
						if (_be != null) {
							_bnbt = _be.saveWithFullMetadata();
							_be.setRemoved();
						}
						world.setBlock(_bp, _bs, 3);
						if (_bnbt != null) {
							_be = world.getBlockEntity(_bp);
							if (_be != null) {
								try {
									_be.load(_bnbt);
								} catch (Exception ignored) {
								}
							}
						}
					}
					world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.SMOLDERING_PLANKS.defaultBlockState()));
				} else if (Math.random() <= 0.05) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.SMOLDERING_PLANKS.defaultBlockState()));
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (BurntModParticleTypes.FIRE_PARTICLE), (x + 0.5), (y + 0.5), (z + 0.5), 10, 1, 1, 1, 0.1);
				} else if (Math.random() <= 0.1) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BLAZING_PLANKS.defaultBlockState();
						BlockEntity _be = world.getBlockEntity(_bp);
						CompoundTag _bnbt = null;
						if (_be != null) {
							_bnbt = _be.saveWithFullMetadata();
							_be.setRemoved();
						}
						world.setBlock(_bp, _bs, 3);
						if (_bnbt != null) {
							_be = world.getBlockEntity(_bp);
							if (_be != null) {
								try {
									_be.load(_bnbt);
								} catch (Exception ignored) {
								}
							}
						}
					}
				} else if (Math.random() <= 0.2) {
					world.setBlock(BlockPos.containing(x, y, z),
							(BurntModBlocks.EMBER_PLANKS.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp10
									? BurntModBlocks.EMBER_PLANKS.defaultBlockState().setValue(_withbp10, true)
									: BurntModBlocks.EMBER_PLANKS.defaultBlockState()),
							3);
				} else {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_PLANKS.defaultBlockState(), 3);
				}
			}
		}
	}
}