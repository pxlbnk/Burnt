package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

// Hollow log breakdown - fragile logs crumble away over time
// No sapling replant, a hollow log isn't a tree

public class BurntHollowLogUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (world.isClientSide()) return;
		if (!BurntBasicConfigConfiguration.FOREST_RECOVERY.get()) return;
		double speed = BurntBasicConfigConfiguration.FOREST_RECOVER_SPEED.get();
		if (speed <= 0.0) return;
		if (!(blockstate.getBlock().getStateDefinition().getProperty("fragile") instanceof BooleanProperty _fp) || !blockstate.getValue(_fp)) return;
		BlockPos pos = BlockPos.containing(x, y, z);
		if (!world.isEmptyBlock(pos.above()) && !world.isEmptyBlock(pos.below())) return;
		double p = Math.pow(Math.min(speed / 100.0, 1.0), 1.5);
		if (Math.random() < p) {
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		}
	}
}
