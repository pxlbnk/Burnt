package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class FallenBurntSlabOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("placed") instanceof BooleanProperty _getbp2 && blockstate.getValue(_getbp2)) == false) {
				if (Math.random() <= 0.5) {
					world.destroyBlock(BlockPos.containing(x, y, z), false);
				}
			}
		}
	}
}