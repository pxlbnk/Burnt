package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.compat.BurnMappingRegistry;
import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.util.BurntProtection;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class TryStartBurnOffsetProcedure {
	public static void execute(LevelAccessor world, double chance, double cx, double cy, double cz, double dx, double dy, double dz) {
		double tx = 0;
		double ty = 0;
		double tz = 0;
		if (!world.isClientSide()) {
			tx = cx + dx;
			ty = cy + dy;
			tz = cz + dz;
			if (BurntProtection.isProtected(world, cx, cy, cz, tx, ty, tz)) return;
			if (world.hasChunkAt(BlockPos.containing(tx, ty, tz))) {
				var targetState = world.getBlockState(BlockPos.containing(tx, ty, tz));
				boolean canBurn = targetState.is(BurntTags.BLOCK_WILL_BURN)
						|| targetState.is(BurntTags.BLOCK_FLESH)
						|| BurntTags.isCompatBurnable(targetState)
						|| BurnMappingRegistry.get(targetState.getBlock()) != null;
				if (canBurn && !targetState.is(BurntTags.BLOCK_FIRE_RESISTANT)
						&& !targetState.is(BurntTags.BLOCK_WILL_NOT_BURN)) {
					// Fire climbs more easily than it falls, so wood below a fire is harder to light
					boolean _slowDown = targetState.is(BurntTags.BLOCK_SLOW_DOWNWARD_SPREAD);
					if (!_slowDown && targetState.is(BurntTags.BLOCK_SLOW_DOWNWARD_SPREAD_UNLESS_EXPOSED)) {
						// A log with no solid neighbour is a bare trunk, so let fire run down it
						_slowDown = !net.pixelbank.burnt.util.BurntBlockUtil.isHorizontallyExposed(world, tx, ty, tz);
					}
					if (dy < 0 && _slowDown) {
						chance *= Math.max(0.0, Math.min(1.0, BurntBasicConfigConfiguration.WOOD_DOWNWARD_SPREAD.get() / 100.0));
					}
					if (chance < 1) {
						if (Math.random() <= chance) {
							BurnBabyProcedure.execute(world, tx, ty, tz);
						}
					} else {
						BurnBabyProcedure.execute(world, tx, ty, tz);
					}
				}
				// Copper blocks adjacent to fire get copper flames placed on their free faces
				if (targetState.is(BurntTags.BLOCK_COPPER)) {
					CopperFlamesPlacementProcedure.execute(world, tx, ty, tz);
				}
			}
		}
	}
}
