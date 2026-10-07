package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.util.BurntProtection;
import net.pixelbank.burnt.compat.AdPotherBridge;
import net.pixelbank.burnt.compat.BurnMappingRegistry;
import net.minecraft.world.level.block.Block;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;
import net.minecraftforge.fml.ModList;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
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

public class BurnBabyProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		// Every ignition path funnels through here, so one claim check covers them all
		if (BurntProtection.isProtected(world, x, y, z)) return;
		// Convert pink petals above first, or they pop off as drops
		if (!world.isClientSide()) {
			BlockPos abovePos = BlockPos.containing(x, y + 1, z);
			if (world.getBlockState(abovePos).getBlock() == Blocks.PINK_PETALS) {
				world.setBlock(abovePos, net.pixelbank.burnt.init.BurntModBlocks.TALL_GRASS_BURNT.defaultBlockState(), 3);
			}
		}
		BlockState bs = Blocks.AIR.defaultBlockState();
		BlockState blk = Blocks.AIR.defaultBlockState();
		double rnd = 0;
		boolean isFireResist = false;
		boolean isPlantsWillBurn = false;
		boolean isSooty = false;
		boolean isGlass = false;
		boolean isLeaves = false;
		boolean isLogsThatBurn = false;
		boolean isMushroom = false;
		boolean craftedOn = false;
		boolean done = false;
		boolean isWaterlogged = false;
		// Cache the state once - 90+ reads below use it
		// Safe because nothing re-tests it after a write: each branch sets done = true
		bs = world.getBlockState(BlockPos.containing(x, y, z));
		blk = bs;
		// Report ignition to AdPother with the original block state, so its Fuels registry matches
		if (AdPotherBridge.isActive()) {
			AdPotherBridge.onBlockIgnited(world, BlockPos.containing(x, y, z), bs);
		}
		if (bs.is(BurntTags.BLOCK_FIRE_RESISTANT)
				|| bs.is(BurntTags.BLOCK_TF_TOWERWOOD)
				|| bs.is(BurntTags.BLOCK_WILL_NOT_BURN)) {
			isFireResist = true;
		}
		if (bs.is(BurntTags.BLOCK_PLANTS_WILL_BURN)) {
			isPlantsWillBurn = true;
		}
		if (bs.is(BurntTags.BLOCK_SOOTY)) {
			isSooty = true;
		}
		if (bs.is(BurntTags.BLOCK_FORGE_GLASS) || bs.is(BurntTags.BLOCK_FORGE_GLASS_PANES)) {
			isGlass = true;
		}
		if (bs.is(BurntTags.BLOCK_MC_LEAVES)) {
			isLeaves = true;
		}
		if (bs.is(BurntTags.BLOCK_MC_LOGS_THAT_BURN)) {
			isLogsThatBurn = true;
		}
		if (bs.is(BurntTags.BLOCK_FORGE_MUSHROOM_BLOCKS)) {
			isMushroom = true;
		}
		if ((bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp21 && bs.getValue(_getbp21)) == true) {
			isWaterlogged = true;
		}
		craftedOn = BurntBasicConfigConfiguration.CRAFTED_BURN.get();
		rnd = Math.random();
		done = false;
		if (done == false) {
			if (ModList.get().isLoaded("the_flesh_that_hates")) {
				if ((BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_coublestone")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_block")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_sand")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_tree")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_plank")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_bricks")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_pile")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:tumor")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:purulent")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:purulent")) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_FLESH_BLOCK.defaultBlockState(), 3);
					done = true;
				}
				if ((BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_terns")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_spike_grass")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_grass")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_small_grass")) {
					world.destroyBlock(BlockPos.containing(x, y, z), false);
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					done = true;
				}
				if ((BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("the_flesh_that_hates:flesh_foliage")) {
					LeavesStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			if (ModList.get().isLoaded("biomesoplenty")) {
				if ((BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("biomesoplenty:high_grass")
						|| (BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("biomesoplenty:high_grass_plant")) {
					if (world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude()) {
						world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.DOUBLE_TALL_GRASS_FIRE.defaultBlockState(), 3);
						done = true;
					}
				}
			}
			if (ModList.get().isLoaded("immersive_weathering")) {
				if ((BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("immersive_weathering:grassy_earthen_clay")) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_GRASSY_EARTHEN_CLAY.defaultBlockState(), 3);
					done = true;
				}
				if ((BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("immersive_weathering:grassy_sandy_dirt")) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_GRASSY_SANDY_D_IRT.defaultBlockState(), 3);
					done = true;
				}
			}
			if (ModList.get().isLoaded("supplementaries")) {
				if ((BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("supplementaries:gunpowder")) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.FIRESTARTER.defaultBlockState(), 3);
					done = true;
				}
			}
			if (ModList.get().isLoaded("regions_unexplored")) {
				if (bs.is(BurntTags.BLOCK_RU_BRANCHES)) {
					world.destroyBlock(BlockPos.containing(x, y, z), false);
					done = true;
				}
			}
			if (ModList.get().isLoaded("regions_unexplored")) {
				if ((BuiltInRegistries.BLOCK.getKey(bs.getBlock()).toString()).equals("regions_unexplored:bamboo_log")) {
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
		if (!(isFireResist == true)) {
			// Tier tag: destroy_in_burn (block simply breaks, no drops)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_DESTROY_IN_BURN)) {
					world.destroyBlock(BlockPos.containing(x, y, z), false);
					done = true;
				}
			}
			// Tier tag: destroy_in_burn_create
			// Same as destroy_in_burn but gated on burn_create_blocks so users can opt out
			if (done == false && BurntBasicConfigConfiguration.BURN_CREATE_BLOCKS.get()) {
				if (bs.is(BurntTags.BLOCK_DESTROY_IN_BURN_CREATE)) {
					world.destroyBlock(BlockPos.containing(x, y, z), false);
					done = true;
				}
			}
			// JSON mapping lookup (highest priority - overrides all tag-based logic)
			if (done == false) {
				net.minecraft.world.level.block.Block _mapped = BurnMappingRegistry.get(bs.getBlock());
				if (_mapped != null) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bso = world.getBlockState(_bp);
					BlockState _bsm = _mapped.defaultBlockState();
					_bsm = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bsm);
					// Set stage 1 - a smoldering block left at stage 0 never ticks or smokes
					_bsm = blockStateWithInt(_bsm, "stage", 1);
					_bsm = net.pixelbank.burnt.util.WoodSpecies.applyTo(_bsm,
							net.pixelbank.burnt.util.WoodSpecies.fromBlock(_bso.getBlock()));
					world.setBlock(_bp, _bsm, 3);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_log_slow (slow-burning modded logs)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_LOG_SLOW)) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bso = world.getBlockState(_bp);
					BlockState _bsm = BurntModBlocks.SMOLDERING_DARK_OAK_LOG.defaultBlockState();
					_bsm = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bsm);
					_bsm = blockStateWithInt(_bsm, "stage", 1);
					_bsm = net.pixelbank.burnt.util.WoodSpecies.applyTo(_bsm,
							net.pixelbank.burnt.util.WoodSpecies.fromBlock(_bso.getBlock()));
					world.setBlock(_bp, _bsm, 3);
					AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_small_log (thin/small non-full-cube modded logs -> small_smoldering_log)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMALL_LOG)) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bso = world.getBlockState(_bp);
					BlockState _bsm = BurntModBlocks.SMALL_SMOLDERING_LOG.defaultBlockState();
					_bsm = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bsm);
					_bsm = blockStateWithInt(_bsm, "stage", 1);
					_bsm = net.pixelbank.burnt.util.WoodSpecies.applyTo(_bsm,
							net.pixelbank.burnt.util.WoodSpecies.fromBlock(_bso.getBlock()));
					_bsm = net.pixelbank.burnt.block.SmallSmolderingLogBlock.withConnections(world, _bp, _bsm);
					world.setBlock(_bp, _bsm, 3);
					AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_log (standard-speed modded logs)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_LOG)) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bso = world.getBlockState(_bp);
					BlockState _bsm = BurntModBlocks.SMOLDERING_LOG.defaultBlockState();
					_bsm = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bsm);
					_bsm = blockStateWithInt(_bsm, "stage", 1);
					_bsm = net.pixelbank.burnt.util.WoodSpecies.applyTo(_bsm,
							net.pixelbank.burnt.util.WoodSpecies.fromBlock(_bso.getBlock()));
					world.setBlock(_bp, _bsm, 3);
					AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_hollow_log (standard-speed modded logs)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_HOLLOW_LOG)) {
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bso = world.getBlockState(_bp);
					BlockState _bsm = BurntModBlocks.SMOLDERING_HOLLOW_LOG.defaultBlockState();
					_bsm = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bsm);
					_bsm = blockStateWithInt(_bsm, "stage", 1);
					_bsm = net.pixelbank.burnt.util.WoodSpecies.applyTo(_bsm,
							net.pixelbank.burnt.util.WoodSpecies.fromBlock(_bso.getBlock()));
					world.setBlock(_bp, _bsm, 3);
					AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Soil slab tiers must run before the full-block ones and keep the slab's type,
			// otherwise a bottom slab burns into a full cube
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_GRASS_SLAB)) {
					GrassStartProcedure.convertPlantsAbove(world, x, y, z);
					setKeepingShape(world, x, y, z, blockStateWithInt(BurntModBlocks.SMOLDERING_GRASS_SLAB.defaultBlockState(), "stage", 1));
					done = true;
				}
			}
			// No AnyFlamesPlacement - see the full-block moss branch below (chain-ignition).
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_MOSS_SLAB)) {
					setKeepingShape(world, x, y, z, blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS_SLAB.defaultBlockState(), "stage", 1));
					done = true;
				}
			}
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_BURNT_DIRT_SLAB)) {
					setKeepingShape(world, x, y, z, BurntModBlocks.BURNT_DIRT_SLAB.defaultBlockState());
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_grass (modded grass blocks)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_GRASS)) {
					GrassStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_burnt_dirt (modded dirt blocks)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_BURNT_DIRT)) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_DIRT.defaultBlockState(), 3);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_planks (modded plank blocks)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_PLANKS)) {
					PlanksStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_slabs (modded wooden slabs)
			if (done == false) {
				if (isWaterlogged == false && bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_SLABS)) {
					SlabsStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_vertical_slab (Quark-style vertical slabs)
			if (done == false) {
				if (isWaterlogged == false && bs.is(BurntTags.BLOCK_BURNS_TO_VERTICAL_SLAB)) {
					VerticalSlabsStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_wall (modded wood walls)
			if (done == false) {
				if (isWaterlogged == false && bs.is(BurntTags.BLOCK_BURNS_TO_WALL)) {
					WallsStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_stairs (modded wooden stairs)
			if (done == false) {
				if (isWaterlogged == false && bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_STAIRS)) {
					StairsStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_fences (modded wooden fences)
			if (done == false) {
				if (isWaterlogged == false && bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_FENCES)) {
					FenceStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_fence_gates (modded wooden fence gates)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_FENCE_GATES)) {
					FenceGateStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_burnt_farmland (modded farmland)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_BURNT_FARMLAND)) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_FARMLAND.defaultBlockState(), 3);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_crops (modded crops)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_CROPS)) {
					CropsStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_fern (modded fern-like plants)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_FERN)) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_FERN.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_tall_grass (modded single-block tall grasses)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_TALL_GRASS)) {
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
			// Tier tag: burns_to_smoldering_wood (modded bark/wood blocks)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_WOOD)) {
					WoodStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_stripped_log (modded stripped logs)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_STRIPPED_LOG)) {
					StrippedLogStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_stripped_wood (modded stripped wood)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_STRIPPED_WOOD)) {
					StrippedWoodStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_large_leaves (broad canopy, e.g. cherry)
			if (done == false) {
				if (isWaterlogged == false && bs.is(BurntTags.BLOCK_BURNS_TO_LARGE_LEAVES)) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_CHERRY_LEAVES.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						_bs = blockStateWithInt(_bs, "stage", 1);
						world.setBlock(_bp, _bs, 3);
					}
					AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_medium_leaves (standard density, e.g. oak)
			if (done == false) {
				if (isWaterlogged == false && bs.is(BurntTags.BLOCK_BURNS_TO_MEDIUM_LEAVES)) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_OAK_LEAVES.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						_bs = blockStateWithInt(_bs, "stage", 1);
						world.setBlock(_bp, _bs, 3);
					}
					AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_fine_leaves (delicate detail, e.g. birch)
			if (done == false) {
				if (isWaterlogged == false && bs.is(BurntTags.BLOCK_BURNS_TO_FINE_LEAVES)) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_BIRCH_LEAVES.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						_bs = blockStateWithInt(_bs, "stage", 1);
						world.setBlock(_bp, _bs, 3);
					}
					AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_moss
			// No flame placement - it made modded moss chain-ignite every neighbour at once
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_MOSS)) {
					world.setBlock(BlockPos.containing(x, y, z), blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1), 3);
				done = true;
				}
			}
			// Tier tag: burns_to_smoldering_moss_carpet
			// No flame placement - see the moss branch above
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_MOSS_CARPET)) {
					world.setBlock(BlockPos.containing(x, y, z), blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS_CARPET.defaultBlockState(), "stage", 1), 3);
				done = true;
				}
			}
			// Tier tag: burns_to_smoldering_wool
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_WOOL)) {
					world.setBlock(BlockPos.containing(x, y, z),
							(BurntModBlocks.SMOLDERING_WOOL.getStateDefinition().getProperty("hot") instanceof BooleanProperty _bpw
									? BurntModBlocks.SMOLDERING_WOOL.defaultBlockState().setValue(_bpw, true)
									: BurntModBlocks.SMOLDERING_WOOL.defaultBlockState()), 3);
						if (Math.random() < 0.25) AnyFlamesPlacementProcedure.execute(world, x, y, z);
				done = true;
				}
			}
			// Tier tag: burns_to_smoldering_envelope
			if (done == false && BurntBasicConfigConfiguration.BURN_CREATE_BLOCKS.get()) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_ENVELOPE)) {
					world.setBlock(BlockPos.containing(x, y, z),
							(BurntModBlocks.SMOLDERING_ENVELOPE.getStateDefinition().getProperty("hot") instanceof BooleanProperty _bpe
									? BurntModBlocks.SMOLDERING_ENVELOPE.defaultBlockState().setValue(_bpe, true)
									: BurntModBlocks.SMOLDERING_ENVELOPE.defaultBlockState()), 3);
					if (Math.random() < 0.25) AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_sail
			// No flame placement - Create block
			if (done == false && BurntBasicConfigConfiguration.BURN_CREATE_BLOCKS.get()) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_SAIL)) {
					BlockState _newSailState = BurntModBlocks.SMOLDERING_SAIL.defaultBlockState();
					if (bs.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _sfp
							&& _newSailState.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _nsfp) {
						_newSailState = _newSailState.setValue(_nsfp, bs.getValue(_sfp));
					}
					if (BurntModBlocks.SMOLDERING_SAIL.getStateDefinition().getProperty("hot") instanceof BooleanProperty _bpSail) {
						_newSailState = _newSailState.setValue(_bpSail, true);
					}
					world.setBlock(BlockPos.containing(x, y, z), _newSailState, 3);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_sail_frame
			// No flame placement - Create block
			if (done == false && BurntBasicConfigConfiguration.BURN_CREATE_BLOCKS.get()) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_SAIL_FRAME)) {
					BlockState _newSFState = BurntModBlocks.SMOLDERING_SAIL_FRAME.defaultBlockState();
					if (bs.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _sffp
							&& _newSFState.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _nsffp) {
						_newSFState = _newSFState.setValue(_nsffp, bs.getValue(_sffp));
					}
					if (BurntModBlocks.SMOLDERING_SAIL_FRAME.getStateDefinition().getProperty("hot") instanceof BooleanProperty _bpSF) {
						_newSFState = _newSFState.setValue(_bpSF, true);
					}
					world.setBlock(BlockPos.containing(x, y, z), _newSFState, 3);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_carpet
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_CARPET)) {
					world.setBlock(BlockPos.containing(x, y, z),
							(BurntModBlocks.SMOLDERING_CARPET.getStateDefinition().getProperty("hot") instanceof BooleanProperty _bpc
									? BurntModBlocks.SMOLDERING_CARPET.defaultBlockState().setValue(_bpc, true)
									: BurntModBlocks.SMOLDERING_CARPET.defaultBlockState()), 3);
						AnyFlamesPlacementProcedure.execute(world, x, y, z);
				done = true;
				}
			}
			// Tier tag: burns_to_smoldering_button
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_BUTTON)) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_BUTTON.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_pressure_plate
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_PRESSURE_PLATE)) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_PRESSURE_PLATE.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					AnyFlamesPlacementProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_door (modded wooden doors)
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_DOOR)) {
					DoorStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			// Tier tag: burns_to_smoldering_trapdoor (modded wooden trapdoors)
			if (done == false) {
				if (isWaterlogged == false && bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_TRAPDOOR)) {
					TrapdoorStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_BURNS_TO_DOUBLE_TALL_GRASS)) {
					// If fire hits the upper half first, redirect to the lower half
					String _halfStr = (bs.getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep102
							? bs.getValue(_getep102).toString()
							: "lower");
					// DoubleBlockHalf.toString() returns "UPPER"/"LOWER" (enum name) - case-insensitive compare.
					double _lowerY = _halfStr.equalsIgnoreCase("upper") ? y - 1 : y;
					{
						BlockPos _lowerPos = BlockPos.containing(x, _lowerY, z);
						BlockPos _upperPos = BlockPos.containing(x, _lowerY + 1, z);
						BlockState _lowerState = world.getBlockState(_lowerPos);
						if (_lowerState.is(BurntTags.BLOCK_BURNS_TO_DOUBLE_TALL_GRASS)) {
							BlockState _bs = BurntModBlocks.DOUBLE_TALL_GRASS_FIRE.defaultBlockState();
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_lowerState, _bs);
							// Clear the upper half without neighbour updates (flag 2), or DoublePlantBlock cascades
							// and destroys the lower half, dropping a tall_grass item and looking like a break
							world.setBlock(_upperPos, Blocks.AIR.defaultBlockState(), 2);
							world.setBlock(_lowerPos, _bs, 3);
							done = true;
						} else {
							// Lower half already converted this tick - just clear the orphan upper half
							world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 2);
							done = true;
						}
					}
				}
			}
			if (done == false) {
				if (bs.getBlock() == Blocks.PINK_PETALS) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.TALL_GRASS_BURNT.defaultBlockState(), 3);
					done = true;
				}
			}
			if (done == false) {
				var _plantBlock = bs.getBlock();
				if (_plantBlock == Blocks.FERN || _plantBlock == Blocks.LARGE_FERN) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_FERN.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					done = true;
				} else if (_plantBlock == Blocks.GRASS || _plantBlock == Blocks.DEAD_BUSH) {
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
				if (bs.getBlock() == Blocks.DIRT) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_DIRT.defaultBlockState(), 3);
					done = true;
				}
			}
			if (done == false) {
				if (isLeaves == true) {
					if (isWaterlogged == false) {
						if (bs.is(BurntTags.BLOCK_BURNING_LEAVES)) {
							done = true;
						} else {
							LeavesStartProcedure.execute(world, x, y, z);
							done = true;
						}
					}
				}
			}
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_GRASS_BLOCKS)
						|| bs.is(BurntTags.BLOCK_IW_GRASS_BLOCKS)
						|| bs.is(BurntTags.BLOCK_RU_GRASS_BLOCKS)
						|| bs.is(BurntTags.BLOCK_BOP_GRASS_BLOCKS)
						|| bs.is(BurntTags.BLOCK_BWG_GRASS_BLOCKS)) {
					GrassStartProcedure.execute(world, x, y, z);
					done = true;
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
					done = true;
				}
			}
			if (done == false) {
				if (craftedOn == true) {
					if (bs.is(BurntTags.BLOCK_FORGE_STRIPPED_WOOD)) {
						StrippedWoodStartProcedure.execute(world, x, y, z);
						done = true;
					}
				}
			}
			if (done == false) {
				if (craftedOn == true) {
					if (bs.is(BurntTags.BLOCK_FORGE_STRIPPED_LOGS)) {
						StrippedLogStartProcedure.execute(world, x, y, z);
						done = true;
					}
				}
			}
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_FORGE_WOOD)) {
					WoodStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			if (done == false) {
				if (isLogsThatBurn == true) {
					LogStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			if (done == false) {
				if (bs.getBlock() == Blocks.MYCELIUM) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.CHARRED_MYCELIUM.defaultBlockState(), 3);
					done = true;
				}
			}
			if (done == false) {
				if (craftedOn == true) {
					if (isFireResist == false) {
						if (bs.is(BurntTags.BLOCK_MC_PLANKS)
								&& !bs.is(BurntTags.BLOCK_FIRE_RESISTANT)) {
							PlanksStartProcedure.execute(world, x, y, z);
							done = true;
						}
						if (isWaterlogged == false) {
							if (bs.is(BurntTags.BLOCK_MC_WOODEN_STAIRS)) {
								StairsStartProcedure.execute(world, x, y, z);
								done = true;
							}
						}
						if (isWaterlogged == false) {
							if (bs.is(BurntTags.BLOCK_MC_WOODEN_SLABS)) {
								SlabsStartProcedure.execute(world, x, y, z);
								done = true;
							}
						}
						if (isWaterlogged == false) {
							if (bs.is(BurntTags.BLOCK_MC_WOODEN_FENCES)
									&& !bs.is(BurntTags.BLOCK_BURNT)) {
								FenceStartProcedure.execute(world, x, y, z);
								done = true;
							}
						}
						if (bs.is(BurntTags.BLOCK_MC_FENCE_GATES)) {
							FenceGateStartProcedure.execute(world, x, y, z);
							done = true;
						}
						if (bs.is(BurntTags.BLOCK_MC_WOODEN_DOORS)
								&& !(bs.is(BurntTags.BLOCK_BURNT_DOORS)
										|| bs.is(BurntTags.BLOCK_BURNING_DOORS))) {
							DoorStartProcedure.execute(world, x, y, z);
							done = true;
						}
						if (isWaterlogged == false) {
							if (bs.is(BurntTags.BLOCK_MC_WOODEN_TRAPDOORS)) {
								TrapdoorStartProcedure.execute(world, x, y, z);
								done = true;
							}
						}
						if (isWaterlogged == false) {
							if (bs.getBlock() == Blocks.LADDER) {
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
						if (bs.getBlock() == Blocks.BOOKSHELF) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (BurntModBlocks.SOOTY_BOOKSHELF.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp184
										? BurntModBlocks.SOOTY_BOOKSHELF.defaultBlockState().setValue(_withbp184, true)
										: BurntModBlocks.SOOTY_BOOKSHELF.defaultBlockState());
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						}
						if (isWaterlogged == false) {
							if (bs.getBlock() == Blocks.CAMPFIRE) {
								{
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockState _bs = BurntModBlocks.SMOLDERING_CAMPFIRE.defaultBlockState();
									BlockState _bso = world.getBlockState(_bp);
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
									world.setBlock(_bp, _bs, 3);
								}
								done = true;
							}
						}
						if (isWaterlogged == false) {
							if (bs.is(BurntTags.BLOCK_MC_SIGNS)) {
								world.destroyBlock(BlockPos.containing(x, y, z), false);
								done = true;
							}
						}
						if (bs.getBlock() == Blocks.BAMBOO_BUTTON) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.BURNT_BAMBOO_BUTTON.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						}
						if (done == false && bs.is(BurntTags.BLOCK_WOODEN_BUTTONS)) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.BURNT_BUTTON.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								BlockEntity _be = world.getBlockEntity(_bp);
								CompoundTag _bnbt = null;
								if (_be != null) {
									_bnbt = _be.saveWithFullMetadata();
									_be.setRemoved();
								}
								world.setBlock(_bp, _bs, 3);
								if (_bnbt != null) {
									_be = world.getBlockEntity(_bp);
									if (_be != null) {
										try {
											_be.load(_bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							done = true;
						}
						if (bs.is(BurntTags.BLOCK_WOODEN_PRESSURE_PLATES)) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.BURNT_PRESSURE_PLATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								BlockEntity _be = world.getBlockEntity(_bp);
								CompoundTag _bnbt = null;
								if (_be != null) {
									_bnbt = _be.saveWithFullMetadata();
									_be.setRemoved();
								}
								world.setBlock(_bp, _bs, 3);
								if (_bnbt != null) {
									_be = world.getBlockEntity(_bp);
									if (_be != null) {
										try {
											_be.load(_bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							done = true;
						}
						if (BurntBasicConfigConfiguration.WOOL_BURN.get() == true) {
							if (bs.is(BurntTags.BLOCK_MC_WOOL)) {
								world.setBlock(BlockPos.containing(x, y, z),
										(BurntModBlocks.SMOLDERING_WOOL.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp208
												? BurntModBlocks.SMOLDERING_WOOL.defaultBlockState().setValue(_withbp208, true)
												: BurntModBlocks.SMOLDERING_WOOL.defaultBlockState()),
										3);
								done = true;
							}
						}
						if (bs.is(BurntTags.BLOCK_MC_WOOL_CARPETS)) {
							world.setBlock(BlockPos.containing(x, y, z),
									(BurntModBlocks.SMOLDERING_CARPET.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp213
											? BurntModBlocks.SMOLDERING_CARPET.defaultBlockState().setValue(_withbp213, true)
											: BurntModBlocks.SMOLDERING_CARPET.defaultBlockState()),
									3);
							done = true;
						}
						if (bs.getBlock() == Blocks.HAY_BLOCK) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (BurntModBlocks.SMOLDERING_HAY.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp218
										? BurntModBlocks.SMOLDERING_HAY.defaultBlockState().setValue(_withbp218, true)
										: BurntModBlocks.SMOLDERING_HAY.defaultBlockState());
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
							done = true;
						}
						if (bs.is(BurntTags.BLOCK_MC_BANNERS)) {
							world.destroyBlock(BlockPos.containing(x, y, z), false);
							done = true;
						}
						if (done == false) {
							if (bs.is(BurntTags.BLOCK_BAMBOO_BLOCKS)) {
								BambooStartProcedure.execute(world, x, y, z);
								done = true;
							}
						}
						if (bs.is(BurntTags.BLOCK_MC_BEDS)) {
							world.destroyBlock(BlockPos.containing(x, y, z), false);
							done = true;
						}
						if (bs.is(BurntTags.BLOCK_FIRE_DESTROYS)) {
							world.destroyBlock(BlockPos.containing(x, y, z), false);
							done = true;
						}
					}
				}
			}
			if (done == false) {
				if (BurntBasicConfigConfiguration.BURN_BARRELS.get() == true) {
					if (bs.getBlock() == Blocks.BARREL) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = (BurntModBlocks.BURNT_BARREL.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp235
									? BurntModBlocks.BURNT_BARREL.defaultBlockState().setValue(_withbp235, true)
									: BurntModBlocks.BURNT_BARREL.defaultBlockState());
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							BlockEntity _be = world.getBlockEntity(_bp);
							CompoundTag _bnbt = null;
							if (_be != null) {
								_bnbt = _be.saveWithFullMetadata();
								_be.setRemoved();
							}
							world.setBlock(_bp, _bs, 3);
							if (_bnbt != null) {
								_be = world.getBlockEntity(_bp);
								if (_be != null) {
									try {
										_be.load(_bnbt);
									} catch (Exception ignored) {
									}
								}
							}
						}
						done = true;
					}
				}
			}
			// Fire spread converts scaffolding like flint and arrows do, gated on crafted_blocks_burn
			if (done == false) {
				if (BurntBasicConfigConfiguration.CRAFTED_BURN.get() == true) {
					if (bs.getBlock() == Blocks.SCAFFOLDING) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (blockStateWithInt(BurntModBlocks.BURNT_SCAFFOLDING.defaultBlockState(), "stage", 1));
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
						AnyFlamesPlacementProcedure.execute(world, x, y, z);
						done = true;
					}
				}
			}
			if (done == false) {
				if (isGlass == true) {
					if (isFireResist == false) {
						if (rnd <= 0.5) {
							world.destroyBlock(BlockPos.containing(x, y, z), false);
							done = true;
						}
					}
				}
			}
			if (done == false) {
				if (isMushroom == true) {
					MushroomStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			if (done == false) {
				if (bs.getBlock() == Blocks.MOSS_BLOCK) {
					world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
					done = true;
				}
			}
			if (done == false) {
				if (isWaterlogged == false) {
					if (bs.getBlock() == Blocks.MANGROVE_ROOTS) {
						world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_MANGROVE_ROOTS.defaultBlockState(), "stage", 1)), 3);
						done = true;
					}
				}
			}
			if (done == false) {
				if (bs.getBlock() == Blocks.BAMBOO) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_BAMBOO_STALK.defaultBlockState(), 3);
					done = true;
				}
			}
			if (done == false) {
				if (bs.getBlock() == Blocks.CAVE_VINES_PLANT) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					done = true;
				}
				if (bs.getBlock() == Blocks.CAVE_VINES) {
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
				if (bs.getBlock() == Blocks.AZALEA || bs.getBlock() == Blocks.FLOWERING_AZALEA) {
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
				if (bs.getBlock() == Blocks.MOSS_CARPET) {
					world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS_CARPET.defaultBlockState(), "stage", 1)), 3);
					done = true;
				}
			}
			if (done == false) {
				if (bs.getBlock() == Blocks.PUMPKIN) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.BURNT_PUMPKIN.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp277
								? BurntModBlocks.BURNT_PUMPKIN.defaultBlockState().setValue(_withbp277, true)
								: BurntModBlocks.BURNT_PUMPKIN.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					done = true;
				}
				if (bs.getBlock() == Blocks.CARVED_PUMPKIN) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.BURNT_CARVED_PUMPKIN.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp281
								? BurntModBlocks.BURNT_CARVED_PUMPKIN.defaultBlockState().setValue(_withbp281, true)
								: BurntModBlocks.BURNT_CARVED_PUMPKIN.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					done = true;
				}
				if (bs.getBlock() == Blocks.JACK_O_LANTERN) {
					{
						BlockPos _bp = BlockPos.containing(x + rnd, y + rnd, z + rnd);
						BlockState _bs = (BurntModBlocks.BURNT_JACK_O_LANTERN.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp285
								? BurntModBlocks.BURNT_JACK_O_LANTERN.defaultBlockState().setValue(_withbp285, true)
								: BurntModBlocks.BURNT_JACK_O_LANTERN.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					done = true;
				}
				if (bs.getBlock() == Blocks.MELON) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BurntModBlocks.BURNT_MELON.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp289
								? BurntModBlocks.BURNT_MELON.defaultBlockState().setValue(_withbp289, true)
								: BurntModBlocks.BURNT_MELON.defaultBlockState());
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					done = true;
				}
			}
			if (done == false) {
				if (bs.is(BurntTags.BLOCK_MC_CROPS)) {
					CropsStartProcedure.execute(world, x, y, z);
					done = true;
				}
			}
			if (done == false) {
				if (bs.getBlock() == Blocks.SUGAR_CANE) {
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
				if (bs.getBlock() == Blocks.ICE) {
					// Ice leaves water behind, except in the Nether where it evaporates
					boolean _iceUltrawarm = world instanceof net.minecraft.world.level.Level _iceLvl
							&& _iceLvl.dimensionType().ultraWarm();
					world.setBlock(BlockPos.containing(x, y, z),
							_iceUltrawarm ? Blocks.AIR.defaultBlockState() : Blocks.WATER.defaultBlockState(), 3);
					done = true;
				}
			}
			if (done == false) {
				// Snow melts to nothing, handled here so molotovs and spread behave the same
				if (bs.getBlock() == Blocks.SNOW || bs.getBlock() == Blocks.SNOW_BLOCK || bs.getBlock() == Blocks.POWDER_SNOW) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					done = true;
				}
			}
			if (done == false) {
				if (bs.getBlock() == Blocks.CACTUS) {
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
				if (bs.getBlock() == Blocks.COBWEB) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_COBWEB.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					done = true;
				}
			}
			if (done == false) {
				if (bs.getBlock() == BurntModBlocks.FIRE_BARREL) {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.FIRE_BARREL_ACTIVE.defaultBlockState(), 3);
					done = true;
				}
			}
			if (done == false) {
				if (bs.getBlock() == Blocks.TNT) {
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
			if (done == false) {
				if (bs.getBlock() == Blocks.COAL_BLOCK) {
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
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}

	// Replaces a block but carries its shared properties across, notably a slab's type
	private static void setKeepingShape(LevelAccessor world, double x, double y, double z, BlockState newState) {
		BlockPos _bp = BlockPos.containing(x, y, z);
		world.setBlock(_bp, net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(world.getBlockState(_bp), newState), 3);
	}
}
