package net.pixelbank.burnt.procedures;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class FlamablityProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (world.hasChunkAt(BlockPos.containing(x, y, z))) {
			net.minecraft.world.level.block.state.BlockState _state = world.getBlockState(BlockPos.containing(x, y, z));
			FlammableBlockRegistry.Entry _entry = FlammableBlockRegistry.getDefaultInstance().get(_state.getBlock());
			return _entry != null && _entry.getBurnChance() > 0;
		}
		return false;
	}
}