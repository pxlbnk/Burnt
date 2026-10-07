package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class CopperFireUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			// Extinguish in rain
			if (BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true && world.getLevelData().isRaining()) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				return;
			}

			BlockPos firePos = BlockPos.containing(x, y, z);

			// Find the attached copper block (one of the 4 horizontal neighbours)
			BlockPos copperPos = null;
			for (Direction horiz : new Direction[]{ Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH }) {
				BlockPos candidate = firePos.relative(horiz);
				if (world.getBlockState(candidate).is(BurntTags.BLOCK_COPPER)) {
					copperPos = candidate;
					break;
				}
			}

			// No copper block found - canSurvive would have caught this, but remove cleanly
			if (copperPos == null) {
				world.setBlock(firePos, Blocks.AIR.defaultBlockState(), 3);
				return;
			}

			// Only lives while a neighbour is on fire - copper_fire isn't in burnt:on_fire, so no loop
			boolean hasFireSource = false;
			for (Direction d : Direction.values()) {
				BlockPos checkPos = copperPos.relative(d);
				if (!checkPos.equals(firePos) && world.getBlockState(checkPos).is(BurntTags.BLOCK_ON_FIRE)) {
					hasFireSource = true;
					break;
				}
			}

			if (!hasFireSource) {
				world.setBlock(firePos, Blocks.AIR.defaultBlockState(), 3);
			}
		}
	}
}
