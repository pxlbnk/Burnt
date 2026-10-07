package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class ExtinguishBlockProcedure {
	// Extinguisher item - shows the white spray
	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(world, x, y, z, true);
	}

	// Pass false to skip the spray, used by the golem stomp
	public static void execute(LevelAccessor world, double x, double y, double z, boolean showSpray) {
		boolean found = false;
		double dx = 0;
		double dy = 0;
		double dz = 0;
		double px = 0;
		double py = 0;
		double pz = 0;
		BlockState blockLocal = Blocks.AIR.defaultBlockState();
		if (!world.isClientSide()) {
			if (showSpray) {
				world.levelEvent(2001, BlockPos.containing(x, y, z), Block.getId(BurntModBlocks.SPLASH.defaultBlockState()));
			}
			dx = -3;
			for (int index0 = 0; index0 < 6; index0++) {
				dy = -3;
				for (int index1 = 0; index1 < 6; index1++) {
					dz = -3;
					for (int index2 = 0; index2 < 6; index2++) {
						px = x + dx;
						py = y + dy;
						pz = z + dz;
						blockLocal = (world.getBlockState(BlockPos.containing(px, py, pz)));
						if (blockLocal.is(BurntTags.BLOCK_FIRE)) {
							world.setBlock(BlockPos.containing(px, py, pz), Blocks.AIR.defaultBlockState(), 3);
							BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount - 1;
							BurntModVariables.WorldVariables.get(world).markSyncDirty();
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_COAL) {
							world.setBlock(BlockPos.containing(px, py, pz), Blocks.COAL_BLOCK.defaultBlockState(), 3);
						}
						if (blockLocal.getBlock() == BurntModBlocks.FIRE_BARREL_ACTIVE) {
							world.setBlock(BlockPos.containing(px, py, pz), BurntModBlocks.FIRE_BARREL.defaultBlockState(), 3);
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_HAY) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_HAY.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.is(BurntTags.BLOCK_IW_LEAF_PILES)) {
							world.destroyBlock(BlockPos.containing(px, py, pz), false);
						}
						if ((BuiltInRegistries.BLOCK.getKey(blockLocal.getBlock()).toString()).equals("supplementaries:lumisene")) {
							world.destroyBlock(BlockPos.containing(px, py, pz), false);
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_CROPS) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_CROPS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_MOSS) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_MOSS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT || blockLocal.getBlock() == BurntModBlocks.SMOLDERING_CAVE_VINES) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_CAVE_VINES_PLANT.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_MOSS_CARPET) {
							world.setBlock(BlockPos.containing(px, py, pz), BurntModBlocks.BURNT_MOSS_CARPET.defaultBlockState(), 3);
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_AZALEA) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_AZALEA.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_WOOL) {
							world.setBlock(BlockPos.containing(px, py, pz), BurntModBlocks.BURNT_WOOL.defaultBlockState(), 3);
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_CARPET) {
							world.setBlock(BlockPos.containing(px, py, pz), BurntModBlocks.BURNT_CARPET.defaultBlockState(), 3);
						}
						// Extinguishing a Create envelope or sail leaves the burnt version, colour is already lost
						// Sails are directional, so copy facing across
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_ENVELOPE) {
							world.setBlock(BlockPos.containing(px, py, pz), BurntModBlocks.BURNT_ENVELOPE.defaultBlockState(), 3);
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_SAIL || blockLocal.getBlock() == BurntModBlocks.SMOLDERING_SAIL_FRAME) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_SAIL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_SYMMETRIC_SAIL) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_SYMMETRIC_SAIL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.getBlock() == Blocks.CAMPFIRE) {
							{
								BlockPos _pos = BlockPos.containing(px, py, pz);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("lit") instanceof BooleanProperty _booleanProp)
									world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
							}
						}
						if (blockLocal.getBlock() == BurntModBlocks.EMBER_CAMPFIRE) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_CAMPFIRE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.is(BurntTags.BLOCK_SOOTY)) {
							SootyExtinguishProcedure.execute(world, px, py, pz);
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_GRASS)) {
							world.setBlock(BlockPos.containing(px, py, pz), BurntModBlocks.BURNT_GRASS.defaultBlockState(), 3);
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_LEAVES)) {
							world.setBlock(BlockPos.containing(px, py, pz), BurntModBlocks.BURNT_LEAVES.defaultBlockState(), 3);
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_BAMBOO)) {
							BambooExtinguishProcedure.execute(world, px, py, pz);
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_DOORS)) {
							DoorExtinguishProcedure.execute(world, px, py, pz);
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_TRAPDOORS)) {
							TrapdoorExtinguishProcedure.execute(world, px, py, pz);
						}
						// Smoldering DT branch, root or trunk shell - swap smoldering_X for burnt_X by registry name,
						// keeping radius and the rest via the usual property copy
						if (blockLocal.is(BurntTags.BLOCK_BURNING_DT_BRANCHES)) {
							BlockPos _bp = BlockPos.containing(px, py, pz);
							net.minecraft.resources.ResourceLocation srcId = BuiltInRegistries.BLOCK.getKey(blockLocal.getBlock());
							if (srcId != null && srcId.getPath().startsWith("smoldering_")) {
								net.minecraft.resources.ResourceLocation burntId = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
										srcId.getNamespace(),
										"burnt_" + srcId.getPath().substring("smoldering_".length()));
								Block burntBlock = BuiltInRegistries.BLOCK.get(burntId);
								if (burntBlock != null && burntBlock != Blocks.AIR) {
									BlockState _bs = burntBlock.defaultBlockState();
									BlockState _bso = world.getBlockState(_bp);
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
									world.setBlock(_bp, _bs, 3);
								}
							}
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_LOGS)) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = (BurntModBlocks.BURNT_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp63
										? BurntModBlocks.BURNT_LOG.defaultBlockState().setValue(_withbp63, true)
										: BurntModBlocks.BURNT_LOG.defaultBlockState());
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_HOLLOW_LOG || blockLocal.getBlock() == BurntModBlocks.EMBER_HOLLOW_LOG || blockLocal.getBlock() == BurntModBlocks.BLAZING_HOLLOW_LOG) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = (BurntModBlocks.BURNT_HOLLOW_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp63
										? BurntModBlocks.BURNT_HOLLOW_LOG.defaultBlockState().setValue(_withbp63, true)
										: BurntModBlocks.BURNT_HOLLOW_LOG.defaultBlockState());
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.getBlock() == BurntModBlocks.BROKEN_BLAZING_LOG) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = (BurntModBlocks.BROKEN_BURNT_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp67
										? BurntModBlocks.BROKEN_BURNT_LOG.defaultBlockState().setValue(_withbp67, true)
										: BurntModBlocks.BROKEN_BURNT_LOG.defaultBlockState());
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_WOOD)) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_STRIPPED_LOGS)) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.STRIPPED_BURNT_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_STRIPPED_WOOD)) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.STRIPPED_BURNT_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.getBlock() == BurntModBlocks.SMOLDERING_MANGROVE_ROOTS) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_MANGROVE_ROOTS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_PLANKS)) {
							world.setBlock(BlockPos.containing(px, py, pz), BurntModBlocks.BURNT_PLANKS.defaultBlockState(), 3);
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_STAIRS)) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_SLABS)) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_FENCES)) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_FENCE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (blockLocal.is(BurntTags.BLOCK_BURNING_FENCE_GATES)) {
							{
								BlockPos _bp = BlockPos.containing(px, py, pz);
								BlockState _bs = BurntModBlocks.BURNT_FENCEGATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						dz = dz + 1;
					}
					dy = dy + 1;
				}
				dx = dx + 1;
			}
		}
	}
}