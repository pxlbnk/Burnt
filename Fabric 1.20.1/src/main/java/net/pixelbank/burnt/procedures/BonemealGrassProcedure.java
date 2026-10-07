package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import net.minecraft.resources.ResourceLocation;

public class BonemealGrassProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Object event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(BurntTags.ITEM_BOWS)
					|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(BurntTags.ITEM_BOWS))) {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.BURNT_GRASS && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.BONE_MEAL) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.bone_meal.use")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.bone_meal.use")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.RECOVERING_GRASS.defaultBlockState(), 3);
					if (getEntityGameType(entity) == GameType.SURVIVAL) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(Items.BONE_MEAL);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.COMPOSTER, x, (y + 1), z, 10, 1, 1, 1, 0.2);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.RECOVERING_GRASS
						&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.BONE_MEAL) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.bone_meal.use")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.bone_meal.use")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					world.setBlock(BlockPos.containing(x, y, z), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
					if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == BurntModBlocks.BURNT_GRASS) {
						world.setBlock(BlockPos.containing(x + 1, y, z), BurntModBlocks.RECOVERING_GRASS.defaultBlockState(), 3);
					}
					if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == BurntModBlocks.BURNT_GRASS) {
						world.setBlock(BlockPos.containing(x - 1, y, z), BurntModBlocks.RECOVERING_GRASS.defaultBlockState(), 3);
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == BurntModBlocks.BURNT_GRASS) {
						world.setBlock(BlockPos.containing(x, y, z - 1), BurntModBlocks.RECOVERING_GRASS.defaultBlockState(), 3);
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == BurntModBlocks.BURNT_GRASS) {
						world.setBlock(BlockPos.containing(x, y, z + 1), BurntModBlocks.RECOVERING_GRASS.defaultBlockState(), 3);
					}
					if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == BurntModBlocks.RECOVERING_GRASS) {
						world.setBlock(BlockPos.containing(x + 1, y, z), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
					}
					if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == BurntModBlocks.RECOVERING_GRASS) {
						world.setBlock(BlockPos.containing(x - 1, y, z), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == BurntModBlocks.RECOVERING_GRASS) {
						world.setBlock(BlockPos.containing(x, y, z - 1), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == BurntModBlocks.RECOVERING_GRASS) {
						world.setBlock(BlockPos.containing(x, y, z + 1), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
					}
					if (getEntityGameType(entity) == GameType.SURVIVAL) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(Items.BONE_MEAL);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.COMPOSTER, x, (y + 1), z, 10, 1, 1, 1, 0.2);
				}
			}
		}
	}

	private static GameType getEntityGameType(Entity entity) {
		if (entity instanceof ServerPlayer serverPlayer) {
			return serverPlayer.gameMode.getGameModeForPlayer();
		} else if (entity instanceof Player player && player.level().isClientSide()) {
			PlayerInfo playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
			if (playerInfo != null)
				return playerInfo.getGameMode();
		}
		return null;
	}
}