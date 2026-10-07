package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import net.minecraft.resources.ResourceLocation;

public class BurntMossCompostProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Object event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double particleRadius = 0;
		double particleAmount = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BurntModBlocks.BURNT_MOSS.asItem()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.COMPOSTER) {
				if (getEntityGameType(entity) == GameType.SURVIVAL) {
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(BurntModBlocks.BURNT_MOSS);
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
					}
				}
				if (Math.random() <= 0.5) {
					{
						int _value = ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("level") instanceof IntegerProperty _getip7
								? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip7)
								: -1) + 1;
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("level") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.gravel.place")), SoundSource.NEUTRAL, (float) 0.5, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.gravel.place")), SoundSource.NEUTRAL, (float) 0.5, 1, false);
					}
				}
				particleAmount = 8;
				particleRadius = 0.5;
				for (int index0 = 0; index0 < (int) particleAmount; index0++) {
					world.addParticle(ParticleTypes.COMPOSTER, (x + 0.5 + Mth.nextDouble(world.getRandom(), -0.5, 0.5) * particleRadius), (y + 0.5 + Mth.nextDouble(world.getRandom(), -0.5, 0.7)),
							(z + 0.5 + Mth.nextDouble(world.getRandom(), -0.5, 0.5) * particleRadius), 0, 0, 0);
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