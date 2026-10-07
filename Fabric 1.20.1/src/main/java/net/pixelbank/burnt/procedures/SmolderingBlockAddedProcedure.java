package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SmolderingBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_SMOLDERING_LEAVES)) {
				BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount + 1;
				BurntModVariables.WorldVariables.get(world).markSyncDirty();
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip4 ? blockstate.getValue(_getip4) : -1) >= 1
					|| (blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp6 && blockstate.getValue(_getbp6)) == true) {
				BurntVinesPlacementProcedure.execute(world, x, y, z);
			}
		}
	}
}