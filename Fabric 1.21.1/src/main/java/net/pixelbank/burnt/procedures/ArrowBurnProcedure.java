package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.compat.BurnMappingRegistry;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class ArrowBurnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double faceChance = 0;
		double downChance = 0;
		double diagonalChance = 0;
		double rnd = 0;
		boolean isCave = false;
		boolean isRaining = false;
		boolean rainOK = false;
		boolean isOverworld = false;
		boolean dimOK = false;
		boolean waterNear = false;
		boolean allowBurn = false;
		boolean isWaterlogged = false;
		boolean isFireResist = false;
		boolean craftedOn = false;
		boolean done = false;
		boolean isLeaves = false;
		boolean isGlass = false;
		boolean isPlantsWillBurn = false;
		boolean isLogsThatBurn = false;
		boolean isSooty = false;
		boolean isMushroom = false;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FIRE_RESISTANT)) {
			isFireResist = true;
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_PLANTS_WILL_BURN)) {
			isPlantsWillBurn = true;
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_SOOTY)) {
			isSooty = true;
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FORGE_GLASS) || (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FORGE_GLASS_PANES)) {
			isGlass = true;
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_LEAVES)) {
			isLeaves = true;
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_LOGS_THAT_BURN)) {
			isLogsThatBurn = true;
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FORGE_MUSHROOM_BLOCKS)) {
			isMushroom = true;
		}
		if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp18 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp18)) == true) {
			isWaterlogged = true;
		}
		craftedOn = BurntBasicConfigConfiguration.CRAFTED_BURN.get();
		rnd = Math.random();
		done = false;
		if (done == false) {
			if (FabricLoader.getInstance().isModLoaded("the_flesh_that_hates")) {
				if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_coublestone")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_block")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_sand")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_tree")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_plank")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_bricks")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_pile")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:tumor")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:purulent")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:purulent")) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_FLESH_BLOCK.defaultBlockState(), 3);
					done = true;
				}
				if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_terns")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_spike_grass")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_grass")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_small_grass")) {
					world.destroyBlock(BlockPos.containing(x, y, z), false);
					done = true;
				}
				if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("the_flesh_that_hates:flesh_foliage")) {
					LeavesStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			if (FabricLoader.getInstance().isModLoaded("biomesoplenty")) {
				if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("biomesoplenty:high_grass")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("biomesoplenty:high_grass_plant")) {
					if (world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude()) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.DOUBLE_TALL_GRASS_FIRE.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						done = true;
					}
				}
			}
			if (FabricLoader.getInstance().isModLoaded("immersive_weathering")) {
				if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("immersive_weathering:grassy_earthen_clay")) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_GRASSY_EARTHEN_CLAY.defaultBlockState(), 3);
					done = true;
				}
				if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("immersive_weathering:grassy_sandy_dirt")) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_GRASSY_SANDY_D_IRT.defaultBlockState(), 3);
					done = true;
				}
			}
			if (FabricLoader.getInstance().isModLoaded("supplementaries")) {
				if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("supplementaries:gunpowder")) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.FIRESTARTER.defaultBlockState(), 3);
					done = true;
				}
			}
			if (FabricLoader.getInstance().isModLoaded("regions_unexplored")) {
				if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("regions_unexplored:small_oak_log")
						|| (BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("regions_unexplored:stripped_small_oak_log")) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.SOOTY_SMALL_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp81
								? BurntModBlocks.SOOTY_SMALL_LOG.defaultBlockState().setValue(_withbp81, true)
								: BurntModBlocks.SOOTY_SMALL_LOG.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					done = true;
				}
			}
			if (FabricLoader.getInstance().isModLoaded("regions_unexplored")) {
				if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals("regions_unexplored:bamboo_log")) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_BAMBOO_LOG.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					done = true;
				}
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.TALL_GRASS) {
				if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep93 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep93).toString() : "")
						.equals("lower")) {
					world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.AIR.defaultBlockState(), 3);
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.DOUBLE_TALL_GRASS_FIRE.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					done = true;
				}
			}
		}
		if (done == false) {
			var _plantBlock = (world.getBlockState(BlockPos.containing(x, y, z))).getBlock();
			if (_plantBlock == Blocks.FERN || _plantBlock == Blocks.LARGE_FERN) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_FERN.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			} else if (_plantBlock == Blocks.SHORT_GRASS || _plantBlock == Blocks.DEAD_BUSH) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.TALL_GRASS_FIRE.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_GRASS_BLOCKS)
					|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_IW_GRASS_BLOCKS)
					|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_RU_GRASS_BLOCKS)
					|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BOP_GRASS_BLOCKS)
					|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BWG_GRASS_BLOCKS)) {
				GrassStartProcedure.execute(world, x, y, z);
				done = true;
			}
		}
		if (done == false) {
			if (isLeaves == true) {
				if (!(world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_ON_FIRE)) {
					if (isWaterlogged == false) {
						LeavesStartProcedure.execute(world, x, y, z);
						ArrowFlamesPlacementProcedure.execute(world, x, y, z);
						done = true;
					}
				}
			}
		}
		if (done == false) {
			if (isSooty == true) {
				{
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _booleanProp)
						world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
				}
				ArrowFlamesPlacementProcedure.execute(world, x, y, z);
				done = true;
			}
		}
		if (done == false) {
			if (craftedOn == true) {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FORGE_STRIPPED_WOOD)) {
					StrippedWoodStartProcedure.execute(world, x, y, z);
					ArrowFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
		}
		if (done == false) {
			if (craftedOn == true) {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FORGE_STRIPPED_LOGS)) {
					StrippedLogStartProcedure.execute(world, x, y, z);
					ArrowFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_FORGE_WOOD)) {
				WoodStartProcedure.execute(world, x, y, z);
				ArrowFlamesPlacementProcedure.execute(world, x, y, z);
				done = true;
			}
		}
		if (done == false) {
			if (isLogsThatBurn == true) {
				LogStartProcedure.execute(world, x, y, z);
				ArrowFlamesPlacementProcedure.execute(world, x, y, z);
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.DIRT) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_DIRT.defaultBlockState(), 3);
				ArrowFlamesPlacementProcedure.execute(world, x, y, z);
				done = true;
			}
		}
		if (done == false) {
			if (craftedOn == true) {
				if (isFireResist == false) {
					if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_PLANKS)) {
						PlanksStartProcedure.execute(world, x, y, z);
						ArrowFlamesPlacementProcedure.execute(world, x, y, z);
						done = true;
					}
					if (isWaterlogged == false) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_WOODEN_STAIRS)) {
							StairsStartProcedure.execute(world, x, y, z);
							StairsFlamesPlacementProcedure.execute(world, x, y, z);
							done = true;
						}
					}
					if (isWaterlogged == false) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_WOODEN_SLABS)) {
							SlabsStartProcedure.execute(world, x, y, z);
							done = true;
						}
					}
					if (isWaterlogged == false) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_WOODEN_FENCES)
								&& !(world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BURNT)) {
							FenceStartProcedure.execute(world, x, y, z);
							done = true;
						}
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_FENCE_GATES)) {
						FenceGateStartProcedure.execute(world, x, y, z);
						done = true;
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_WOODEN_DOORS)
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep156
									? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep156).toString()
									: "").equals("lower")) {
						DoorStartProcedure.execute(world, x, y, z);
						done = true;
					}
					if (isWaterlogged == false) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_WOODEN_TRAPDOORS)) {
							TrapdoorStartProcedure.execute(world, x, y, z);
							done = true;
						}
					}
					if (isWaterlogged == false) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.LADDER) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.BURNT_LADDER.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						}
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BOOKSHELF) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = (BurntModBlocks.SOOTY_BOOKSHELF.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp167
									? BurntModBlocks.SOOTY_BOOKSHELF.defaultBlockState().setValue(_withbp167, true)
									: BurntModBlocks.SOOTY_BOOKSHELF.defaultBlockState());
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						ArrowFlamesPlacementProcedure.execute(world, x, y, z);
						done = true;
					}
					if (isWaterlogged == false) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CAMPFIRE) {
							{
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("lit") instanceof BooleanProperty _booleanProp)
									world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
							}
							done = true;
						}
					}
					if (isWaterlogged == false) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SCAFFOLDING) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (blockStateWithInt(BurntModBlocks.BURNT_SCAFFOLDING.defaultBlockState(), "stage", 1));
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								BlockEntity _be = world.getBlockEntity(_bp);
								CompoundTag _bnbt = null;
								if (_be != null) {
									_bnbt = _be.saveWithFullMetadata(world.registryAccess());
									_be.setRemoved();
								}
								world.setBlock(_bp, _bs, 3);
								if (_bnbt != null) {
									_be = world.getBlockEntity(_bp);
									if (_be != null) {
										try {
											_be.loadWithComponents(_bnbt, world.registryAccess());
										} catch (Exception ignored) {
										}
									}
								}
							}
							ArrowFlamesPlacementProcedure.execute(world, x, y, z);
							done = true;
						}
					}
					if (isWaterlogged == false) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_SIGNS)) {
							world.destroyBlock(BlockPos.containing(x, y, z), false);
							done = true;
						}
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_WOODEN_BUTTONS)) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.BURNT_BUTTON.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							BlockEntity _be = world.getBlockEntity(_bp);
							CompoundTag _bnbt = null;
							if (_be != null) {
								_bnbt = _be.saveWithFullMetadata(world.registryAccess());
								_be.setRemoved();
							}
							world.setBlock(_bp, _bs, 3);
							if (_bnbt != null) {
								_be = world.getBlockEntity(_bp);
								if (_be != null) {
									try {
										_be.loadWithComponents(_bnbt, world.registryAccess());
									} catch (Exception ignored) {
									}
								}
							}
						}
						done = true;
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_WOODEN_PRESSURE_PLATES)) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.BURNT_PRESSURE_PLATE.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							BlockEntity _be = world.getBlockEntity(_bp);
							CompoundTag _bnbt = null;
							if (_be != null) {
								_bnbt = _be.saveWithFullMetadata(world.registryAccess());
								_be.setRemoved();
							}
							world.setBlock(_bp, _bs, 3);
							if (_bnbt != null) {
								_be = world.getBlockEntity(_bp);
								if (_be != null) {
									try {
										_be.loadWithComponents(_bnbt, world.registryAccess());
									} catch (Exception ignored) {
									}
								}
							}
						}
						done = true;
					}
					if (BurntBasicConfigConfiguration.WOOL_BURN.get() == true) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_WOOL)) {
							world.setBlock(BlockPos.containing(x, y, z),
									(BurntModBlocks.SMOLDERING_WOOL.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp194
											? BurntModBlocks.SMOLDERING_WOOL.defaultBlockState().setValue(_withbp194, true)
											: BurntModBlocks.SMOLDERING_WOOL.defaultBlockState()),
									3);
							if (Math.random() < 0.25) ArrowFlamesPlacementProcedure.execute(world, x, y, z);
							done = true;
						}
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_WOOL_CARPETS)) {
						world.setBlock(BlockPos.containing(x, y, z),
								(BurntModBlocks.SMOLDERING_CARPET.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp199
										? BurntModBlocks.SMOLDERING_CARPET.defaultBlockState().setValue(_withbp199, true)
										: BurntModBlocks.SMOLDERING_CARPET.defaultBlockState()),
								3);
						done = true;
					}
					if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.HAY_BLOCK) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = (BurntModBlocks.SMOLDERING_HAY.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp204
									? BurntModBlocks.SMOLDERING_HAY.defaultBlockState().setValue(_withbp204, true)
									: BurntModBlocks.SMOLDERING_HAY.defaultBlockState());
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
						ArrowFlamesPlacementProcedure.execute(world, x, y, z);
						done = true;
					}
					if (BurntBasicConfigConfiguration.BURN_BARRELS.get() == true) {
						if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BARREL) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (BurntModBlocks.BURNT_BARREL.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp210
										? BurntModBlocks.BURNT_BARREL.defaultBlockState().setValue(_withbp210, true)
										: BurntModBlocks.BURNT_BARREL.defaultBlockState());
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								BlockEntity _be = world.getBlockEntity(_bp);
								CompoundTag _bnbt = null;
								if (_be != null) {
									_bnbt = _be.saveWithFullMetadata(world.registryAccess());
									_be.setRemoved();
								}
								world.setBlock(_bp, _bs, 3);
								if (_bnbt != null) {
									_be = world.getBlockEntity(_bp);
									if (_be != null) {
										try {
											_be.loadWithComponents(_bnbt, world.registryAccess());
										} catch (Exception ignored) {
										}
									}
								}
							}
							ArrowFlamesPlacementProcedure.execute(world, x, y, z);
							done = true;
						}
					}
				}
			}
		}
		if (done == false) {
			if (isMushroom == true) {
				MushroomStartProcedure.execute(world, x, y, z);
				ArrowFlamesPlacementProcedure.execute(world, x, y, z);
				done = true;
			}
		}
		if (done == false) {
			if (craftedOn == true) {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_BAMBOO_BLOCKS)) {
					BambooStartProcedure.execute(world, x, y, z);
					ArrowFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MOSS_BLOCK) {
				world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
				ArrowFlamesPlacementProcedure.execute(world, x, y, z);
				done = true;
			}
		}
		if (done == false) {
			if (isWaterlogged == false) {
				if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MANGROVE_ROOTS) {
					world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_MANGROVE_ROOTS.defaultBlockState(), "stage", 1)), 3);
					ArrowFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BAMBOO) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_BAMBOO_STALK.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CAVE_VINES_PLANT) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CAVE_VINES) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_CAVE_VINES.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.AZALEA || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.FLOWERING_AZALEA) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_AZALEA.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MOSS_CARPET) {
				world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS_CARPET.defaultBlockState(), "stage", 1)), 3);
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.PUMPKIN) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.BURNT_PUMPKIN.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp251
							? BurntModBlocks.BURNT_PUMPKIN.defaultBlockState().setValue(_withbp251, true)
							: BurntModBlocks.BURNT_PUMPKIN.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CARVED_PUMPKIN) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.BURNT_CARVED_PUMPKIN.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp255
							? BurntModBlocks.BURNT_CARVED_PUMPKIN.defaultBlockState().setValue(_withbp255, true)
							: BurntModBlocks.BURNT_CARVED_PUMPKIN.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.JACK_O_LANTERN) {
				{
					BlockPos _bp = BlockPos.containing(x + rnd, y + rnd, z + rnd);
					BlockState _bs = (BurntModBlocks.BURNT_JACK_O_LANTERN.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp259
							? BurntModBlocks.BURNT_JACK_O_LANTERN.defaultBlockState().setValue(_withbp259, true)
							: BurntModBlocks.BURNT_JACK_O_LANTERN.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.MELON) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.BURNT_MELON.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp263
							? BurntModBlocks.BURNT_MELON.defaultBlockState().setValue(_withbp263, true)
							: BurntModBlocks.BURNT_MELON.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.COBWEB) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.SMOLDERING_COBWEB.defaultBlockState(), 3);
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BurntTags.BLOCK_MC_CROPS)) {
				CropsStartProcedure.execute(world, x, y, z);
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SUGAR_CANE) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_SUGAR_CANE.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CACTUS) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_CACTUS.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.FIRE_BARREL) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.FIRE_BARREL_ACTIVE.defaultBlockState(), 3);
				done = true;
			}
		}
		if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.TNT) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = EntityType.TNT.spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
					}
				}
				done = true;
			}
		}
		// Compat tier tags - flame arrows go through BurnBaby like fire spread does
		if (done == false) {
			var _compatBs = world.getBlockState(BlockPos.containing(x, y, z));
			if (!_compatBs.is(BurntTags.BLOCK_FIRE_RESISTANT) && !_compatBs.is(BurntTags.BLOCK_WILL_NOT_BURN)) {
				if (BurntTags.isCompatBurnable(_compatBs) || BurnMappingRegistry.get(_compatBs.getBlock()) != null) {
					BurnBabyProcedure.execute(world, x, y, z);
					done = true;
				}
			}
		}
				if (done == false) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.COAL_BLOCK) {
				BurntMod.queueServerWork(100, () -> {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_COAL.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				});
				done = true;
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}