package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;

public class TheBurnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.WIND.get() == true) {
				// Wind bias comes from WindAdjustedChanceBlocks, cbrt softens it so upwind still spreads
				double faceChance = 1;
				double downChance = 0.9;
				// Horizontal face directions - wind-adjusted
				double adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, 1, 0));
				TryStartBurnOffsetProcedure.execute(world, adjChance, x, y, z, 1, 0, 0);
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, -1, 0));
				TryStartBurnOffsetProcedure.execute(world, adjChance, x, y, z, -1, 0, 0);
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, 0, 1));
				TryStartBurnOffsetProcedure.execute(world, adjChance, x, y, z, 0, 0, 1);
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, 0, -1));
				TryStartBurnOffsetProcedure.execute(world, adjChance, x, y, z, 0, 0, -1);
				// Vertical directions - no horizontal component, not wind-adjusted
				TryStartBurnOffsetProcedure.execute(world, faceChance * 5, x, y, z, 0, 1, 0);
				TryStartBurnOffsetProcedure.execute(world, downChance, x, y, z, 0, -1, 0);
				// Lower diagonals - wind-adjusted by horizontal component (same base as faceChance in wind mode)
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, 1, 0));
				TryStartBurnOffsetProcedure.execute(world, adjChance, x, y, z, 1, -1, 0);
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, -1, 0));
				TryStartBurnOffsetProcedure.execute(world, adjChance, x, y, z, -1, -1, 0);
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, 0, 1));
				TryStartBurnOffsetProcedure.execute(world, adjChance, x, y, z, 0, -1, 1);
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, 0, -1));
				TryStartBurnOffsetProcedure.execute(world, adjChance, x, y, z, 0, -1, -1);
				// Upper diagonals - wind-adjusted, boosted x5 for upward spread
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, 1, 0));
				TryStartBurnOffsetProcedure.execute(world, adjChance * 5, x, y, z, 1, 1, 0);
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, -1, 0));
				TryStartBurnOffsetProcedure.execute(world, adjChance * 5, x, y, z, -1, 1, 0);
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, 0, 1));
				TryStartBurnOffsetProcedure.execute(world, adjChance * 5, x, y, z, 0, 1, 1);
				adjChance = Math.cbrt(WindAdjustedChanceBlocksProcedure.execute(world, x, y, z, faceChance, 0, -1));
				TryStartBurnOffsetProcedure.execute(world, adjChance * 5, x, y, z, 0, 1, -1);
			} else {
				// No-wind mode: fixed chances per direction type
				double faceChance = 1;
				double downChance = 0.9;
				double diagonalChance = 0.8;
				TryStartBurnOffsetProcedure.execute(world, faceChance, x, y, z, 1, 0, 0);
				TryStartBurnOffsetProcedure.execute(world, faceChance, x, y, z, -1, 0, 0);
				TryStartBurnOffsetProcedure.execute(world, faceChance, x, y, z, 0, 0, 1);
				TryStartBurnOffsetProcedure.execute(world, faceChance, x, y, z, 0, 0, -1);
				TryStartBurnOffsetProcedure.execute(world, faceChance * 5, x, y, z, 0, 1, 0);
				TryStartBurnOffsetProcedure.execute(world, downChance, x, y, z, 0, -1, 0);
				TryStartBurnOffsetProcedure.execute(world, diagonalChance, x, y, z, 1, -1, 0);
				TryStartBurnOffsetProcedure.execute(world, diagonalChance, x, y, z, -1, -1, 0);
				TryStartBurnOffsetProcedure.execute(world, diagonalChance, x, y, z, 0, -1, 1);
				TryStartBurnOffsetProcedure.execute(world, diagonalChance, x, y, z, 0, -1, -1);
				TryStartBurnOffsetProcedure.execute(world, diagonalChance * 5, x, y, z, 1, 1, 0);
				TryStartBurnOffsetProcedure.execute(world, diagonalChance * 5, x, y, z, -1, 1, 0);
				TryStartBurnOffsetProcedure.execute(world, diagonalChance * 5, x, y, z, 0, 1, 1);
				TryStartBurnOffsetProcedure.execute(world, diagonalChance * 5, x, y, z, 0, 1, -1);
			}
		}
	}
}
