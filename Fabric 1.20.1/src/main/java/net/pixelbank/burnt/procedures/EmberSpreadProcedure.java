package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModEntities;
import net.pixelbank.burnt.entity.EmberProjectileEntity;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;

public class EmberSpreadProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double particleRadius = 0;
		double particleAmount = 0;
		double xDir = 0;
		double rad = 0;
		double xSpeed = 0;
		double turb = 0;
		double zSpeed = 0;
		double zDir = 0;
		double multiplyer = 0;
		if (!world.isClientSide()) {
			// Embers are entirely wind-driven - only spawn when wind is enabled
			if (BurntBasicConfigConfiguration.WIND.get() == true) {
				multiplyer = ParticleWindMultiplyerProcedure.execute(world, x, y, z);
				xSpeed = WindAngleXProcedure.execute(world) * multiplyer;
				zSpeed = WindAngleZProcedure.execute(world) * multiplyer;
				if (world instanceof ServerLevel projectileLevel) {
					Projectile _entityToSpawn = initArrowProjectile(new EmberProjectileEntity(BurntModEntities.EMBER_PROJECTILE, projectileLevel), null, 0, true, false, false, AbstractArrow.Pickup.DISALLOWED);
					_entityToSpawn.setPos((x + xSpeed / 10), (y + 1), (z + zSpeed / 10));
					_entityToSpawn.shoot(((xSpeed / 10) * multiplyer), (Mth.nextDouble(world.getRandom(), -0.001, 0.001)), ((zSpeed / 10) * multiplyer), 0.5f, 1);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
			}
		}
	}

	private static AbstractArrow initArrowProjectile(AbstractArrow entityToSpawn, Entity shooter, float damage, boolean silent, boolean fire, boolean particles, AbstractArrow.Pickup pickup) {
		entityToSpawn.setOwner(shooter);
		entityToSpawn.setBaseDamage(damage);
		if (silent)
			entityToSpawn.setSilent(true);
		if (fire)
			entityToSpawn.setSecondsOnFire(100);
		if (particles)
			entityToSpawn.setCritArrow(true);
		entityToSpawn.pickup = pickup;
		return entityToSpawn;
	}
}