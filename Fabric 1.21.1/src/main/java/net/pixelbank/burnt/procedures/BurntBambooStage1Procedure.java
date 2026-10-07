package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;

public class BurntBambooStage1Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				if (BurntBasicConfigConfiguration.BIOME.get() == false) {
					fireSpeed = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPREAD.get() / 100.0, 1.0), 1.5) * 0.99;
				} else if (BurntBasicConfigConfiguration.BIOME.get() == true) {
					fireSpeed = BiomeSpecificProcedure.execute(world, x, y, z);
				}
				if (Math.random() <= fireSpeed) {
					if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(((double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get()) / 2d), e -> true).isEmpty()) {
						WillitburnProcedure.execute(world, x, y, z);
						{
							int _value = 2;
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
								world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
						}
					}
				}
			} else {
				BambooExtinguishProcedure.execute(world, x, y, z);
			}
		}
	}
}