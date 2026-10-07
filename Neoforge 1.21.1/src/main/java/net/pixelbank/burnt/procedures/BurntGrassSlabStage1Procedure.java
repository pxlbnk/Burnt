package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import java.io.File;

public class BurntGrassSlabStage1Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		com.google.gson.JsonObject mainjsonobject = new com.google.gson.JsonObject();
		File burnt = new File("");
		double fireSpeed = 0;
		boolean done = false;
		if (!world.isClientSide()) {
			// Always melt snow on top - nesting this inside a spread roll made it inconsistent
			BlockPos _abovePos = BlockPos.containing(x, y + 1, z);
			if (world.getBlockState(_abovePos).getBlock() == Blocks.SNOW) {
				world.destroyBlock(_abovePos, false);
			}
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				world.setBlock(BlockPos.containing(x, y, z), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
				return;
			}
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				if (BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true) {
					boolean rainHitsBlock = IsRainingProcedure.execute(world, x, y, z);
					if (rainHitsBlock) {
						world.setBlock(BlockPos.containing(x, y, z), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
					} else {
						if (BurntBasicConfigConfiguration.WIND.get() == true) {
							if (Math.random() <= BurntModVariables.MapVariables.get(world).windSpeed / 100) {
								if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.SNOW) {
									world.destroyBlock(BlockPos.containing(x, y + 1, z), false);
								}
								if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.MOSS_CARPET) {
									{
										BlockPos _bp = BlockPos.containing(x, y + 1, z);
										BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS_CARPET.defaultBlockState(), "stage", 1));
										BlockState _bso = world.getBlockState(_bp);
										_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
										BlockEntity _be = world.getBlockEntity(_bp);
										CompoundTag _bnbt = null;
										if (_be != null) {
											_bnbt = _be.saveWithFullMetadata(world.registryAccess());
											_be.setRemoved();
										}
										world.setBlock(_bp, _bs, 3);
										if (_bnbt != null) {
											_be = world.getBlockEntity(_bp);
											if (_be != null) {
												try {
													_be.loadWithComponents(_bnbt, world.registryAccess());
												} catch (Exception ignored) {
												}
											}
										}
									}
								}
								if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AZALEA || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.FLOWERING_AZALEA) {
									{
										BlockPos _bp = BlockPos.containing(x, y + 1, z);
										BlockState _bs = BurntModBlocks.SMOLDERING_AZALEA.defaultBlockState();
										BlockState _bso = world.getBlockState(_bp);
										_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
										BlockEntity _be = world.getBlockEntity(_bp);
										CompoundTag _bnbt = null;
										if (_be != null) {
											_bnbt = _be.saveWithFullMetadata(world.registryAccess());
											_be.setRemoved();
										}
										world.setBlock(_bp, _bs, 3);
										if (_bnbt != null) {
											_be = world.getBlockEntity(_bp);
											if (_be != null) {
												try {
													_be.loadWithComponents(_bnbt, world.registryAccess());
												} catch (Exception ignored) {
												}
											}
										}
									}
								}
								if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(((double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get()) / 2d), e -> true).isEmpty()) {
									{
										BlockPos _bp = BlockPos.containing(x, y, z);
										BlockState _bs = (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 2));
										BlockEntity _be = world.getBlockEntity(_bp);
										CompoundTag _bnbt = null;
										if (_be != null) {
											_bnbt = _be.saveWithFullMetadata(world.registryAccess());
											_be.setRemoved();
										}
										world.setBlock(_bp, _bs, 3);
										if (_bnbt != null) {
											_be = world.getBlockEntity(_bp);
											if (_be != null) {
												try {
													_be.loadWithComponents(_bnbt, world.registryAccess());
												} catch (Exception ignored) {
												}
											}
										}
									}
								}
							}
						} else {
							if (Math.random() <= Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPREAD.get() / 100.0, 1.0), 1.5)) {
								if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.SNOW) {
									world.destroyBlock(BlockPos.containing(x, y + 1, z), false);
								}
								if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.MOSS_CARPET) {
									{
										BlockPos _bp = BlockPos.containing(x, y + 1, z);
										BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS_CARPET.defaultBlockState(), "stage", 1));
										BlockState _bso = world.getBlockState(_bp);
										_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
										BlockEntity _be = world.getBlockEntity(_bp);
										CompoundTag _bnbt = null;
										if (_be != null) {
											_bnbt = _be.saveWithFullMetadata(world.registryAccess());
											_be.setRemoved();
										}
										world.setBlock(_bp, _bs, 3);
										if (_bnbt != null) {
											_be = world.getBlockEntity(_bp);
											if (_be != null) {
												try {
													_be.loadWithComponents(_bnbt, world.registryAccess());
												} catch (Exception ignored) {
												}
											}
										}
									}
								}
								if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AZALEA || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.FLOWERING_AZALEA) {
									{
										BlockPos _bp = BlockPos.containing(x, y + 1, z);
										BlockState _bs = BurntModBlocks.SMOLDERING_AZALEA.defaultBlockState();
										BlockState _bso = world.getBlockState(_bp);
										_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
										BlockEntity _be = world.getBlockEntity(_bp);
										CompoundTag _bnbt = null;
										if (_be != null) {
											_bnbt = _be.saveWithFullMetadata(world.registryAccess());
											_be.setRemoved();
										}
										world.setBlock(_bp, _bs, 3);
										if (_bnbt != null) {
											_be = world.getBlockEntity(_bp);
											if (_be != null) {
												try {
													_be.loadWithComponents(_bnbt, world.registryAccess());
												} catch (Exception ignored) {
												}
											}
										}
									}
								}
								if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(((double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get()) / 2d), e -> true).isEmpty()) {
									{
										BlockPos _bp = BlockPos.containing(x, y, z);
										BlockState _bs = (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 2));
										BlockEntity _be = world.getBlockEntity(_bp);
										CompoundTag _bnbt = null;
										if (_be != null) {
											_bnbt = _be.saveWithFullMetadata(world.registryAccess());
											_be.setRemoved();
										}
										world.setBlock(_bp, _bs, 3);
										if (_bnbt != null) {
											_be = world.getBlockEntity(_bp);
											if (_be != null) {
												try {
													_be.loadWithComponents(_bnbt, world.registryAccess());
												} catch (Exception ignored) {
												}
											}
										}
									}
								}
							}
						}
					}
				} else if (BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == false) {
					if (Math.random() <= 0.2) {
						if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.SNOW) {
							world.destroyBlock(BlockPos.containing(x, y + 1, z), false);
						}
						if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.MOSS_CARPET) {
							{
								BlockPos _bp = BlockPos.containing(x, y + 1, z);
								BlockState _bs = (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS_CARPET.defaultBlockState(), "stage", 1));
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								BlockEntity _be = world.getBlockEntity(_bp);
								CompoundTag _bnbt = null;
								if (_be != null) {
									_bnbt = _be.saveWithFullMetadata(world.registryAccess());
									_be.setRemoved();
								}
								world.setBlock(_bp, _bs, 3);
								if (_bnbt != null) {
									_be = world.getBlockEntity(_bp);
									if (_be != null) {
										try {
											_be.loadWithComponents(_bnbt, world.registryAccess());
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
						if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AZALEA || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.FLOWERING_AZALEA) {
							{
								BlockPos _bp = BlockPos.containing(x, y + 1, z);
								BlockState _bs = BurntModBlocks.SMOLDERING_AZALEA.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								BlockEntity _be = world.getBlockEntity(_bp);
								CompoundTag _bnbt = null;
								if (_be != null) {
									_bnbt = _be.saveWithFullMetadata(world.registryAccess());
									_be.setRemoved();
								}
								world.setBlock(_bp, _bs, 3);
								if (_bnbt != null) {
									_be = world.getBlockEntity(_bp);
									if (_be != null) {
										try {
											_be.loadWithComponents(_bnbt, world.registryAccess());
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
						if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(((double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get()) / 2d), e -> true).isEmpty()) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (blockStateWithInt(net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState()), "stage", 2));
								BlockEntity _be = world.getBlockEntity(_bp);
								CompoundTag _bnbt = null;
								if (_be != null) {
									_bnbt = _be.saveWithFullMetadata(world.registryAccess());
									_be.setRemoved();
								}
								world.setBlock(_bp, _bs, 3);
								if (_bnbt != null) {
									_be = world.getBlockEntity(_bp);
									if (_be != null) {
										try {
											_be.loadWithComponents(_bnbt, world.registryAccess());
										} catch (Exception ignored) {
										}
									}
								}
							}
						}
					}
				}
			} else {
				world.setBlock(BlockPos.containing(x, y, z), net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(BlockPos.containing(x, y, z)), BurntModBlocks.BURNT_GRASS_SLAB.defaultBlockState()), 3);
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}
