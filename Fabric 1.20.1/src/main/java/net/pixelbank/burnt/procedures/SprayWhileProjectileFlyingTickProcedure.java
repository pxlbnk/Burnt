package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class SprayWhileProjectileFlyingTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
		if (immediatesourceentity == null)
			return;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() instanceof LiquidBlock) {
				if (!immediatesourceentity.level().isClientSide())
					immediatesourceentity.discard();
				if (Math.random() <= 0.3) {
					world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.SPLASH.defaultBlockState()));
				}
			}
			if ((world.getFluidState(BlockPos.containing(x, y, z)).createLegacyBlock()).getBlock() == Blocks.LAVA && (world.getFluidState(BlockPos.containing(x, y, z)).createLegacyBlock()).getFluidState().isSource()) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.FRAGILE_MAGMA.defaultBlockState(), 3);
			} else if ((world.getFluidState(BlockPos.containing(x, y, z)).createLegacyBlock()).getBlock() == Blocks.LAVA) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		}
	}
}