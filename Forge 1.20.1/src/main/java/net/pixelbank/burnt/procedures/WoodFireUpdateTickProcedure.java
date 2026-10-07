package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class WoodFireUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true) {
				if (IsRainingProcedure.isGloballyRaining(world)) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				}
			}
			if (Math.random() <= 0.5) {
				SootyProcedure.execute(world, x, y, z);
			} else if (Math.random() <= 0.3) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = Blocks.AIR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			// Spread copper flames to any copper block directly adjacent to this fire
			if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BurntTags.BLOCK_COPPER))
				CopperFlamesPlacementProcedure.execute(world, x + 1, y, z);
			if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BurntTags.BLOCK_COPPER))
				CopperFlamesPlacementProcedure.execute(world, x - 1, y, z);
			if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BurntTags.BLOCK_COPPER))
				CopperFlamesPlacementProcedure.execute(world, x, y, z + 1);
			if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BurntTags.BLOCK_COPPER))
				CopperFlamesPlacementProcedure.execute(world, x, y, z - 1);
		}
	}
}