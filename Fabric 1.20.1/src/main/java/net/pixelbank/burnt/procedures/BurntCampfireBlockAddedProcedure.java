package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BurntCampfireBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "tagName") >= 1) {
					if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_MC_VALID_SPAWN)) {
						{
							BlockPos _bp = BlockPos.containing(x, y - 1, z);
							BlockState _bs = BurntModBlocks.BURNT_GRASS.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.DIRT) {
						{
							BlockPos _bp = BlockPos.containing(x, y - 1, z);
							BlockState _bs = BurntModBlocks.BURNT_DIRT.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					}
				}
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return 0; // getPersistentData not available on vanilla BlockEntity; data was never written in Fabric
		return -1;
	}
}