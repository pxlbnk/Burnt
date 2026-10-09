package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;
import net.minecraftforge.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.minecraft.resources.ResourceLocation;

public class DoubleTallGrassFireTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.TALL_GRASS_BURNT.defaultBlockState(), 3);
				return;
			}
			if (Math.random() <= 0.1) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.campfire.crackle")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.campfire.crackle")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			}
			if (BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true) {
				if (IsRainingProcedure.isGloballyRaining(world)) {
					if (IsRainingProcedure.execute(world, x, y, z) == true) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.TALL_GRASS_BURNT.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
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
					} else {
						// Globally raining but biome exempt (savanna/desert) - still burn out naturally
						if (Math.random() <= 0.1) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.TALL_GRASS_FIRE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
					}
				} else {
					if (Math.random() <= 0.1) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.TALL_GRASS_FIRE.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
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
					}
				}
			} else if (BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == false) {
				if (Math.random() <= 0.1) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.TALL_GRASS_FIRE.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
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
				}
			}
			if (ModList.get().isLoaded("cold_sweat")) {
				if (Math.random() <= 0.5) {
					if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(4 / 2d), e -> true).isEmpty()) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"/temp modify @p addition world 0.1 false");
						BurntMod.queueServerWork((int) Mth.nextDouble(world.getRandom(), 200, 500), () -> {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										"/temp modify @p addition world -0.1 false");
						});
					}
				}
			}
			{ // NOT gated on distant_plumes: long-range smoke must run with plumes off. Plumes gated inside.
				if (Math.random() <= 0.2) {
					SpawnSignalPlumeXYZProcedure.execute(world, x, y, z);
				}
			}
		}
	}
}