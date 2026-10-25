package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.entity.FlagEntity;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class FlagOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double pitch = 0;
		double multiplyer = 0;
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.WIND.get() == true) {
				{
					Entity _ent = entity;
					_ent.setYRot((float) BurntModVariables.MapVariables.get(world).windAngle);
					_ent.setXRot(0);
					_ent.setYBodyRot(_ent.getYRot());
					_ent.setYHeadRot(_ent.getYRot());
					_ent.yRotO = _ent.getYRot();
					_ent.xRotO = _ent.getXRot();
					if (_ent instanceof LivingEntity _entity) {
						_entity.yBodyRotO = _entity.getYRot();
						_entity.yHeadRotO = _entity.getYRot();
					}
				}
				multiplyer = ParticleWindMultiplyerProcedure.execute(world, x, y, z);
				// Living Wind spreads its own 0-2.5 range here rather than the clamped fire-spread value
				double rawSpeed = net.pixelbank.burnt.compat.LivingWindBridge.flagSpeed(world,
						BurntModVariables.MapVariables.get(world).windSpeed);
				if (entity instanceof FlagEntity _datEntSetI)
					_datEntSetI.getEntityData().set(FlagEntity.DATA_windSpeed, (int) Math.round(rawSpeed * multiplyer));
			}
			if (!(world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_FLAGS)) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
	}
}