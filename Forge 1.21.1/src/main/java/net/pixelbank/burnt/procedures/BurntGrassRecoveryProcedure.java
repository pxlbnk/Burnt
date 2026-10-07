package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

// Stage 1 of grass recovery: burnt grass to recovering grass
// Cools a freshly burnt block first, then waits for daylight and a neighbour to creep from
// Speed comes from the scheduled-tick interval the grass_recover_speed slider drives

public class BurntGrassRecoveryProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (world.isClientSide()) return;
		if (!BurntBasicConfigConfiguration.GRASS_RECOVERY.get()) return;
		if ((double) BurntBasicConfigConfiguration.GRASS_RECOVER_SPEED.get() <= 0.0) return;

		boolean isHot = blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _hp
				&& blockstate.getValue(_hp);
		if (isHot) {
			// Cool down one step before recovery can begin.
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _booleanProp)
				world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
			return;
		}

		// Recovery is sun-driven; sheltered/night blocks just wait.
		if (!(world instanceof Level _lvl && _lvl.isDay())) return;

		if (!isRecoveryTopClear(world, x, y + 1, z)) return;
		boolean seedNeighbour = isRecoverySeed(world, x + 1, y, z)
				|| isRecoverySeed(world, x - 1, y, z)
				|| isRecoverySeed(world, x, y, z - 1)
				|| isRecoverySeed(world, x, y, z + 1)
				|| isRecoverySeed(world, x + 1, y + 1, z)
				|| isRecoverySeed(world, x - 1, y + 1, z)
				|| isRecoverySeed(world, x + 1, y - 1, z)
				|| isRecoverySeed(world, x - 1, y - 1, z)
				|| isRecoverySeed(world, x, y + 1, z + 1)
				|| isRecoverySeed(world, x, y + 1, z - 1)
				|| isRecoverySeed(world, x, y - 1, z + 1)
				|| isRecoverySeed(world, x, y - 1, z - 1);
		if (seedNeighbour) {
			world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.RECOVERING_GRASS.defaultBlockState(), 3);
		}
	}

	// A seed is live grass or a block already recovering, so the front keeps moving
	private static boolean isRecoverySeed(LevelAccessor world, double x, double y, double z) {
		Block b = world.getBlockState(BlockPos.containing(x, y, z)).getBlock();
		// Terrain Slabs soil seeds too, so the front crosses between full and half-height grass
		Block livingSlab = net.pixelbank.burnt.compat.TerrainSlabsBridge.grassSlab();
		return b == Blocks.GRASS_BLOCK || b == BurntModBlocks.RECOVERING_GRASS
				|| (livingSlab != null && b == livingSlab)
				|| b == BurntModBlocks.RECOVERING_GRASS_SLAB;
	}

	// Recovery needs an open top - air, or the burnt remains of ground cover, counts
	// Checked by namespace: the old block whitelist missed most burnt plants
	private static boolean isRecoveryTopClear(LevelAccessor world, double x, double y, double z) {
		BlockState above = world.getBlockState(BlockPos.containing(x, y, z));
		if (above.isAir())
			return true;
		if (above.canOcclude() || above.is(net.pixelbank.burnt.util.BurntTags.BLOCK_ON_FIRE))
			return false;
		net.minecraft.resources.ResourceLocation _key = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(above.getBlock());
		return _key != null && "burnt".equals(_key.getNamespace());
	}
}
