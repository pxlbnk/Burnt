package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.Mth;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class GrassSlabTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double recovery = 0;
		if (!world.isClientSide()) {
			if ((double) BurntBasicConfigConfiguration.GRASS_RECOVER_SPEED.get() <= 100) {
				recovery = (double) BurntBasicConfigConfiguration.GRASS_RECOVER_SPEED.get();
			} else {
				recovery = 30;
			}
			if (world instanceof Level _lvl3 && _lvl3.isDay()) {
				if (Mth.nextInt(world.getRandom(), 0, 100) <= recovery) {
					if (((world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BurntTags.BLOCK_FURTILE)
							|| (world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BurntTags.BLOCK_FURTILE)
							|| (world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BurntTags.BLOCK_FURTILE)
							|| (world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BurntTags.BLOCK_FURTILE)) && (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR) {
						{
							BlockPos _bp = BlockPos.containing(x, y + 1, z);
							BlockState _bs = (BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_BURNT_GROWS).getRandomElement(world.getRandom())
									.orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value()).defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					}
					if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_BURNT_GROWS)
							&& net.pixelbank.burnt.compat.TerrainSlabsBridge.dirtSlab() != null) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = net.pixelbank.burnt.compat.TerrainSlabsBridge.dirtSlab().defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					}
				}
			}
			if ((world.getLevelData().isRaining() || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) && (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.AIR) {
				if (Math.random() <= 0.2) {
					{
						BlockPos _bp = BlockPos.containing(x, y + 1, z);
						BlockState _bs = (BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_BURNT_GROWS).getRandomElement(world.getRandom()).orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR))
								.value()).defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
			// Rain-driven greening goes back to the Terrain Slabs grass slab
			// Without that mod there's no half-height grass to become, so it stays burnt
			if ((world.getLevelData().isRaining() || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) && (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_BURNT_GROWS)
					&& net.pixelbank.burnt.compat.TerrainSlabsBridge.grassSlab() != null) {
				if (Math.random() <= 0.2) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = net.pixelbank.burnt.compat.TerrainSlabsBridge.grassSlab().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
		}
	}
}