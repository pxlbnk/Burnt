package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class FireBarrelNeighbourBlockChangesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock()).toString()).equals("supplementaries:gunpowder")
					&& ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock().getStateDefinition().getProperty("burning") instanceof IntegerProperty _getip4
							? (world.getBlockState(BlockPos.containing(x + 1, y, z))).getValue(_getip4)
							: -1) >= 1
					|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock()).toString()).equals("supplementaries:gunpowder")
							&& ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock().getStateDefinition().getProperty("burning") instanceof IntegerProperty _getip8
									? (world.getBlockState(BlockPos.containing(x - 1, y, z))).getValue(_getip8)
									: -1) >= 1
					|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock()).toString()).equals("supplementaries:gunpowder")
							&& ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock().getStateDefinition().getProperty("burning") instanceof IntegerProperty _getip12
									? (world.getBlockState(BlockPos.containing(x, y, z + 1))).getValue(_getip12)
									: -1) >= 1
					|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock()).toString()).equals("supplementaries:gunpowder")
							&& ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock().getStateDefinition().getProperty("burning") instanceof IntegerProperty _getip16
									? (world.getBlockState(BlockPos.containing(x, y, z - 1))).getValue(_getip16)
									: -1) >= 1
					|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock()).toString()).equals("supplementaries:gunpowder")
							&& ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock().getStateDefinition().getProperty("burning") instanceof IntegerProperty _getip20
									? (world.getBlockState(BlockPos.containing(x, y - 1, z))).getValue(_getip20)
									: -1) >= 1) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.FIRE_BARREL_ACTIVE.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}