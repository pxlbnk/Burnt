package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class ExtinguishByEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			sx = -2;
			for (int index0 = 0; index0 < 5; index0++) {
				sy = -2;
				for (int index1 = 0; index1 < 5; index1++) {
					sz = -2;
					for (int index2 = 0; index2 < 5; index2++) {
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_FIRE)) {
							world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), Blocks.AIR.defaultBlockState(), 3);
							BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount - 1;
							BurntModVariables.WorldVariables.get(world).markSyncDirty();
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_COAL) {
							world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), Blocks.COAL_BLOCK.defaultBlockState(), 3);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.FIRE_BARREL_ACTIVE) {
							world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.FIRE_BARREL.defaultBlockState(), 3);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_HAY) {
							world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.BURNT_HAY.defaultBlockState(), 3);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_IW_LEAF_PILES)) {
							world.destroyBlock(BlockPos.containing(x + sx, y + sy, z + sz), false);
						}
						if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock()).toString()).equals("supplementaries:lumisene")) {
							world.destroyBlock(BlockPos.containing(x + sx, y + sy, z + sz), false);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_CROPS) {
							world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.BURNT_CROPS.defaultBlockState(), 3);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_MOSS) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_MOSS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT
								|| (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_CAVE_VINES) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_CAVE_VINES_PLANT.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_MOSS_CARPET) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_MOSS_CARPET.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_AZALEA) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_AZALEA.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_WOOL) {
							world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.BURNT_WOOL.defaultBlockState(), 3);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_CARPET) {
							world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.BURNT_CARPET.defaultBlockState(), 3);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == Blocks.CAMPFIRE) {
							{
								BlockPos _pos = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("lit") instanceof BooleanProperty _booleanProp)
									world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.EMBER_CAMPFIRE) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_CAMPFIRE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_SOOTY)) {
							SootyExtinguishProcedure.execute(world, x + sx, y + sy, z + sz);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_GRASS)) {
							world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.BURNT_GRASS.defaultBlockState(), 3);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_LEAVES)) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_BAMBOO)) {
							BambooExtinguishProcedure.execute(world, x + sx, y + sy, z + sz);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_DOORS)) {
							DoorExtinguishProcedure.execute(world, x + sx, y + sy, z + sz);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_TRAPDOORS)) {
							TrapdoorExtinguishProcedure.execute(world, x + sx, y + sy, z + sz);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_LOGS)) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_HOLLOW_LOG || (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.EMBER_HOLLOW_LOG || (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.BLAZING_HOLLOW_LOG) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_HOLLOW_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.BROKEN_BLAZING_LOG) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BROKEN_BURNT_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_WOOD)) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_STRIPPED_LOGS)) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.STRIPPED_BURNT_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_STRIPPED_WOOD)) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.STRIPPED_BURNT_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							if (!world.isClientSide()) {
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null) {
								}
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == BurntModBlocks.SMOLDERING_MANGROVE_ROOTS) {
							world.setBlock(BlockPos.containing(x + sx, y + sy, z + sz), BurntModBlocks.BURNT_MANGROVE_ROOTS.defaultBlockState(), 3);
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_PLANKS)) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_PLANKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_STAIRS)) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_SLABS)) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_FENCES)) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_FENCE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BurntTags.BLOCK_BURNING_FENCE_GATES)) {
							{
								BlockPos _bp = BlockPos.containing(x + sx, y + sy, z + sz);
								BlockState _bs = BurntModBlocks.BURNT_FENCEGATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						sz = sz + 1;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
		}
	}
}