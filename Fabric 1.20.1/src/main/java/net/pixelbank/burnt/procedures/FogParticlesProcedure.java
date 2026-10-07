package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;

public class FogParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double particleAmount = 0;
		double particleRadius = 0;
		double xDir = 0;
		double rad = 0;
		double xSpeed = 0;
		double multiplyer = 0;
		double turb = 0;
		double zSpeed = 0;
		double zDir = 0;
		if (world.isClientSide()) {
			if (Math.random() <= 0.1) {
				if (BurntBasicConfigConfiguration.GROUND_SMOKE.get() == true) {
					if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(16 / 2d), e -> true).isEmpty()) {
						if ((blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp4 && blockstate.getValue(_getbp4)) == true) {
							FogGrassParticlesProcedure.execute(world, x, y, z);
						}
					}
				}
			}
		}
	}
}