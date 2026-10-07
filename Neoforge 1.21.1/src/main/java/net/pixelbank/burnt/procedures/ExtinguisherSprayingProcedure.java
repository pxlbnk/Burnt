package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModEntities;
import net.pixelbank.burnt.entity.SprayEntity;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

public class ExtinguisherSprayingProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (BurntModVariables.PlayerVariables.get(entity).extinguisherOn == true) {
				spawnSpray(world, entity);
				if (getEntityGameType(entity) == GameType.SURVIVAL) {
					if (world instanceof ServerLevel _level) {
						itemstack.hurtAndBreak(1, _level, null, _stkprov -> {
						});
					}
				}
			}
		}
	}

	/**
	 * Spawn one spray projectile from the entity's aim - the shared spray mechanic, used by the
	 * extinguisher item and by compat hooks like TFMG's. Server-only.
	 */
	public static void spawnSpray(LevelAccessor world, Entity entity) {
		if (entity == null || world.isClientSide())
			return;
		if (world instanceof ServerLevel projectileLevel) {
			Projectile _entityToSpawn = initArrowProjectile(new SprayEntity(BurntModEntities.SPRAY, projectileLevel), null, 0, true, false, false, AbstractArrow.Pickup.DISALLOWED);
			_entityToSpawn.setPos((entity.getX() + entity.getLookAngle().x), (entity.getY() + entity.getLookAngle().y + 0.8), (entity.getZ() + entity.getLookAngle().z));
			_entityToSpawn.shoot((entity.getLookAngle().x), (entity.getLookAngle().y + 0.2), (entity.getLookAngle().z), 1, 0);
			projectileLevel.addFreshEntity(_entityToSpawn);
		}
	}

	private static AbstractArrow initArrowProjectile(AbstractArrow entityToSpawn, Entity shooter, float damage, boolean silent, boolean fire, boolean particles, AbstractArrow.Pickup pickup) {
		entityToSpawn.setOwner(shooter);
		entityToSpawn.setBaseDamage(damage);
		if (silent)
			entityToSpawn.setSilent(true);
		if (fire)
			entityToSpawn.igniteForSeconds(100);
		if (particles)
			entityToSpawn.setCritArrow(true);
		entityToSpawn.pickup = pickup;
		return entityToSpawn;
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