package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.Pose;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

public class FireDamageProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			// Wake sleeping mobs first or they slide around in the sleeping pose
			if (entity instanceof LivingEntity _sleeper && (_sleeper.isSleeping() || _sleeper.getPose() == Pose.SLEEPING)) {
				_sleeper.stopSleeping();
				_sleeper.setPose(Pose.STANDING);
			}
			if (entity.getType().is(BurntTags.ENTITY_SCARED_OF_FIRE)) {
				if (entity instanceof Mob _entity) {
					// Flee away from the fire - plain Mobs need a manual vector, getPosAway needs PathfinderMob
					Vec3 _fleePos = null;
					if (_entity instanceof PathfinderMob _pmob) {
						_fleePos = DefaultRandomPos.getPosAway(_pmob, 16, 7, new Vec3(x, y, z));
					} else {
						Vec3 _away = _entity.position().subtract(x, y, z);
						if (_away.lengthSqr() < 1.0e-4)
							_away = new Vec3(world.getRandom().nextGaussian(), 0, world.getRandom().nextGaussian());
						_away = _away.normalize().scale(8 + world.getRandom().nextDouble() * 4);
						_fleePos = _entity.position().add(_away.x, 0, _away.z);
					}
					if (_fleePos != null) {
						_entity.getNavigation().moveTo(_fleePos.x, _fleePos.y, _fleePos.z, 1.2);
					}
					// Drop bed, job and meeting memories so villagers stop walking back into the fire
					if (_entity instanceof Villager _villager) {
						// Drop bed and job claims - MEETING_POINT is left alone so the bell draws them outside
						var _brain = _villager.getBrain();
						_brain.eraseMemory(MemoryModuleType.HOME);
						_brain.eraseMemory(MemoryModuleType.JOB_SITE);
						_brain.eraseMemory(MemoryModuleType.WALK_TARGET);
					}
				}
			}
			if (BurntBasicConfigConfiguration.BURN_DAMAGE.get() == true) {
				if (entity instanceof Player) {
					if (getEntityGameType(entity) == GameType.SURVIVAL) {
						if (!(entity instanceof LivingEntity _livEnt9 && _livEnt9.hasEffect(MobEffects.FIRE_RESISTANCE))) {
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