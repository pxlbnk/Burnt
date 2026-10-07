package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

public class BurntFireDamageProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.BURN_DAMAGE.get() == true) {
				if (entity instanceof Player) {
					if (getEntityGameType(entity) == GameType.SURVIVAL) {
						if (!entity.fireImmune() && !(entity instanceof LivingEntity _livEnt4 && _livEnt4.hasEffect(MobEffects.FIRE_RESISTANCE))) {
							entity.igniteForSeconds(2);
							entity.hurt(new DamageSource(world.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE).getOrThrow(DamageTypes.IN_FIRE)), 1);
						}
					}
				} else if (!entity.getType().is(BurntTags.ENTITY_FIRE_RESISTANT)) {
					entity.igniteForSeconds(2);
					entity.hurt(new DamageSource(world.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE).getOrThrow(DamageTypes.IN_FIRE)), 1);
				}
				if (entity instanceof ItemEntity && !entity.fireImmune()) {
					entity.igniteForSeconds(5);
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