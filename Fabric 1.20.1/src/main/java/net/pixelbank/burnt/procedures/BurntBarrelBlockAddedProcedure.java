package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;

public class BurntBarrelBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp2 && blockstate.getValue(_getbp2)) == true) {
				FlamesPlacementProcedure.execute(world, x, y, z);
			}
		}
	}
}