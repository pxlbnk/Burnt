package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class FragileMagmaUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (Math.random() <= 0.03) {
				world.levelEvent(2001, BlockPos.containing(x, y + 1, z), Block.getId(Blocks.AIR.defaultBlockState()));
				if (Math.random() <= 0.4) {
					if ((world.getFluidState(BlockPos.containing(x + 1, y, z)).createLegacyBlock()).getBlock() == Blocks.LAVA || (world.getFluidState(BlockPos.containing(x - 1, y, z)).createLegacyBlock()).getBlock() == Blocks.LAVA
							|| (world.getFluidState(BlockPos.containing(x, y, z + 1)).createLegacyBlock()).getBlock() == Blocks.LAVA || (world.getFluidState(BlockPos.containing(x, y, z - 1)).createLegacyBlock()).getBlock() == Blocks.LAVA
							|| (world.getFluidState(BlockPos.containing(x, y - 1, z)).createLegacyBlock()).getBlock() == Blocks.LAVA) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = Blocks.AIR.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					} else {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = Blocks.MAGMA_BLOCK.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					}
				}
			}
		}
	}
}