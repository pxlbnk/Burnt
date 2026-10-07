package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class FragileMagmaEntityWalksOnTheBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			BurntMod.queueServerWork(60, () -> {
				world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.AIR.defaultBlockState()));
			});
			BurntMod.queueServerWork(80, () -> {
				world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(Blocks.AIR.defaultBlockState()));
			});
			BurntMod.queueServerWork(100, () -> {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = Blocks.AIR.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			});
		}
	}
}