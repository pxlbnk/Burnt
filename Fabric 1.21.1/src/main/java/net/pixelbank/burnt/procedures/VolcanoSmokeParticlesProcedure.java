package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

// Near-range vent smoke: the plank smoke emitter, but spawning the larger flameless
// volcano smoke. Pairs with BurntDistantSmoke.emitVolcano for the long-range half
public class VolcanoSmokeParticlesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double particleRadius = 0;
		double particleAmount = 0;
		double xDir = 0;
		double rad = 0;
		double xSpeed = 0;
		double multiplyer = 0;
		double turb = 0;
		double zSpeed = 0;
		double zDir = 0;
		if (world.isClientSide()) {
			if (((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? blockstate.getValue(_getip2) : -1) >= 1)
				|| (blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp && blockstate.getValue(_getbp)) == true) {
				if (BurntBasicConfigConfiguration.SMOKE.get() == true) {
					if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_AIR)) {
						if (BurntBasicConfigConfiguration.WIND.get() == true) {
							xSpeed = WindAngleXProcedure.execute(world);
							zSpeed = WindAngleZProcedure.execute(world);
							multiplyer = ParticleWindMultiplyerProcedure.execute(world, x, y, z);
							particleAmount = Mth.nextDouble(world.getRandom(), 0, 1) + BurntModVariables.MapVariables.get(world).windSpeed / 20;
							particleAmount *= net.pixelbank.burnt.util.BurntSmokeBudget.densityFactor();
							particleRadius = 0.6;
							for (int index0 = 0; index0 < (int) particleAmount; index0++) {
								if (!net.pixelbank.burnt.util.BurntSmokeBudget.allowSpawn(world.getRandom())) break;
								world.addParticle((SimpleParticleType) (BurntModParticleTypes.VOLCANO_SMOKE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius),
										(y + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius), ((xSpeed / 25) * multiplyer),
										(Mth.nextDouble(world.getRandom(), -0.001, 0.001)), ((zSpeed / 25) * multiplyer));
							}
						} else {
							particleAmount = Mth.nextDouble(world.getRandom(), 2, 4);
							particleAmount *= net.pixelbank.burnt.util.BurntSmokeBudget.densityFactor();
							particleRadius = 0.6;
							for (int index1 = 0; index1 < (int) particleAmount; index1++) {
								if (!net.pixelbank.burnt.util.BurntSmokeBudget.allowSpawn(world.getRandom())) break;
								world.addParticle((SimpleParticleType) (BurntModParticleTypes.VOLCANO_SMOKE), (x + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius),
										(y + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius), (z + 0.5 + Mth.nextDouble(world.getRandom(), -0.6, 0.6) * particleRadius), 0,
										(Mth.nextDouble(world.getRandom(), 0.02, 0.1)), 0);
							}
						}
					}
				}
			} else {
				WoodParticlesProcedure.execute(world, x, y, z);
			}
		}
	}
}