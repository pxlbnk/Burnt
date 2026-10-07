package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

public class BurntLeavesStage3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double burnTime = 0;
		if (!world.isClientSide()) {
			burnTime = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPEED.get() / 100.0, 1.0), 1.5) * 100.0;
			if (Mth.nextInt(world.getRandom(), 0, 200) <= burnTime) {
				if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BurntTags.BLOCK_BURNT_LEAVES_STICK)
						|| (world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BurntTags.BLOCK_BURNT_LEAVES_STICK)
						|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_BURNT_LEAVES_STICK)
						|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_BURNT_LEAVES_STICK)
						|| (world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BurntTags.BLOCK_BURNT_LEAVES_STICK)
						|| (world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BurntTags.BLOCK_BURNT_LEAVES_STICK)) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						if (_bs.hasProperty(BlockStateProperties.WATERLOGGED))
							_bs = _bs.setValue(BlockStateProperties.WATERLOGGED, false);
						world.setBlock(_bp, _bs, 3);
					}
					BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount - 1;
					BurntModVariables.WorldVariables.get(world).markSyncDirty();
				} else {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount - 1;
					BurntModVariables.WorldVariables.get(world).markSyncDirty();
				}
			}
		}
	}
}