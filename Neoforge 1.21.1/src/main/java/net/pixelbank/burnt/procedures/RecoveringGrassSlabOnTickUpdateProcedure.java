package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.core.BlockPos;

// Stage 2 of grass slab recovery: recovering grass slab to a Terrain Slabs grass slab
// Daylight only, and only on a chance roll, so the recovering texture lingers a while
// A successful step also gets a chance to sprout grass or moss

public class RecoveringGrassSlabOnTickUpdateProcedure {
	// Chance an eligible block finishes greening - a miss retries next tick
	private static final double GREEN_UP_CHANCE = 0.4;

	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide()) return;
		if (!BurntBasicConfigConfiguration.GRASS_RECOVERY.get()) return;
		if ((double) BurntBasicConfigConfiguration.GRASS_RECOVER_SPEED.get() <= 0.0) return;
		// Daytime only - recovery is sun-driven; sheltered/night blocks just wait.
		if (!(world instanceof Level _dayLvl && _dayLvl.isDay())) return;
		// Linger as recovering_grass a bit longer: only finish greening on a 25% roll.
		if (Math.random() > GREEN_UP_CHANCE) return;
		// Grows into the Terrain Slabs grass slab, so without that mod it just stays recovering
		net.minecraft.world.level.block.Block _living = net.pixelbank.burnt.compat.TerrainSlabsBridge.grassSlab();
		if (_living == null) return;
		{
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockState _bs = _living.defaultBlockState();
			BlockState _bso = world.getBlockState(_bp);
			_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
			world.setBlock(_bp, _bs, 3);
		}
		if (Math.random() <= 0.2) {
			if (world instanceof Level _level) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				// Best-effort sprout - some worldgen mods throw from the bonemeal event and would crash the tick
				try {
					if (BoneMealItem.growCrop(new ItemStack(Items.BONE_MEAL), _level, _bp) || BoneMealItem.growWaterPlant(new ItemStack(Items.BONE_MEAL), _level, _bp, null)) {
						if (!_level.isClientSide())
							_level.levelEvent(2005, _bp, 0);
					}
				} catch (Exception e) {
					// Misbehaving third-party bonemeal-event listener - skip the sprout this tick.
				}
			}
		}
	}
}
