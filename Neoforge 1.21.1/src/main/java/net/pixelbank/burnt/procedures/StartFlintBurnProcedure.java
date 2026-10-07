package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.compat.BurnMappingRegistry;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameRules;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class StartFlintBurnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		// Claimed ground: flint lights blocks by calling StartProcedures directly, bypassing BurnBaby.
		if (net.pixelbank.burnt.util.BurntProtection.isProtected(world, x, y, z))
			return;
		if (world.getLevelData().getGameRules().getBoolean(GameRules.RULE_DOFIRETICK) == true) {
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				// Cache position, block state, registry key, and config flags once
				BlockPos pos = BlockPos.containing(x, y, z);
				var bs = world.getBlockState(pos);
				var blockKey = BuiltInRegistries.BLOCK.getKey(bs.getBlock());
				boolean rainExtinguish = BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get();
				boolean crafted = BurntBasicConfigConfiguration.CRAFTED_BURN.get();
				boolean woolBurn = BurntBasicConfigConfiguration.WOOL_BURN.get();
				boolean burnBarrels = BurntBasicConfigConfiguration.BURN_BARRELS.get();
				// Rain gate - must stay a per-position check, the global flag blocks flint everywhere
				boolean rainingHere = IsRainingProcedure.isGloballyRaining(world)
						&& IsRainingProcedure.execute(world, x, y, z);
				if (!rainExtinguish || !rainingHere
						|| world.getBiome(pos).is(BurntTags.BIOME_MC_IS_CAVE)) {
					// Cache water-check block states; derive fluid state from them to avoid double lookups
					var bsW1 = world.getBlockState(BlockPos.containing(x + 1, y - 1, z));
					var bsW2 = world.getBlockState(BlockPos.containing(x - 1, y - 1, z));
					var bsW3 = world.getBlockState(BlockPos.containing(x, y - 1, z + 1));
					var bsW4 = world.getBlockState(BlockPos.containing(x, y - 1, z - 1));
					var bsW5 = world.getBlockState(BlockPos.containing(x, y - 1, z));
					if (!(bsW1.getFluidState().createLegacyBlock().getBlock() == Blocks.WATER
							|| bsW2.getFluidState().createLegacyBlock().getBlock() == Blocks.WATER
							|| bsW3.getFluidState().createLegacyBlock().getBlock() == Blocks.WATER
							|| bsW4.getFluidState().createLegacyBlock().getBlock() == Blocks.WATER
							|| bsW5.getFluidState().createLegacyBlock().getBlock() == Blocks.WATER)) {
						// BOP high grass
						if (blockKey.equals(ResourceLocation.parse("biomesoplenty:high_grass"))
								|| blockKey.equals(ResourceLocation.parse("biomesoplenty:high_grass_plant"))) {
							if (world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude()) {
								world.setBlock(pos, BurntModBlocks.DOUBLE_TALL_GRASS_FIRE.defaultBlockState(), 3);
							}
						}
						// Immersive Weathering grassy earthen clay
						if (blockKey.equals(ResourceLocation.parse("immersive_weathering:grassy_earthen_clay"))) {
							world.setBlock(pos, BurntModBlocks.BURNT_GRASSY_EARTHEN_CLAY.defaultBlockState(), 3);
						}
						// Immersive Weathering grassy sandy dirt
						if (blockKey.equals(ResourceLocation.parse("immersive_weathering:grassy_sandy_dirt"))) {
							world.setBlock(pos, BurntModBlocks.BURNT_GRASSY_SANDY_D_IRT.defaultBlockState(), 3);
						}
						// Supplementaries gunpowder
						if (blockKey.equals(ResourceLocation.parse("supplementaries:gunpowder"))) {
							world.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
						}
						// Regions Unexplored small oak log / stripped small oak log
						if (blockKey.equals(ResourceLocation.parse("regions_unexplored:small_oak_log"))
								|| blockKey.equals(ResourceLocation.parse("regions_unexplored:stripped_small_oak_log"))) {
							{
								BlockState _bs = (BurntModBlocks.SOOTY_SMALL_LOG.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp38
										? BurntModBlocks.SOOTY_SMALL_LOG.defaultBlockState().setValue(_withbp38, true)
										: BurntModBlocks.SOOTY_SMALL_LOG.defaultBlockState());
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Regions Unexplored bamboo log
						if (blockKey.equals(ResourceLocation.parse("regions_unexplored:bamboo_log"))) {
							{
								BlockState _bs = BurntModBlocks.BURNT_BAMBOO_LOG.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Sooty blocks - make hot
						if (bs.is(BurntTags.BLOCK_SOOTY)) {
							if (bs.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _booleanProp)
								world.setBlock(pos, bs.setValue(_booleanProp, true), 3);
						}
						// Glass shatter
						if (Math.random() <= 0.5) {
							if ((bs.is(BurntTags.BLOCK_FORGE_GLASS) || bs.is(BurntTags.BLOCK_FORGE_GLASS_PANES))
									&& !bs.is(BurntTags.BLOCK_FIRE_RESISTANT)) {
								world.destroyBlock(pos, false);
							}
						}
						// Fern / large fern -> SmolderingFern
						if (bs.getBlock() == Blocks.FERN || bs.getBlock() == Blocks.LARGE_FERN) {
							{
								BlockState _bs = BurntModBlocks.SMOLDERING_FERN.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Short grass / dead bush -> TallGrassFire
						if (bs.getBlock() == Blocks.SHORT_GRASS || bs.getBlock() == Blocks.DEAD_BUSH) {
							{
								BlockState _bs = BurntModBlocks.TALL_GRASS_FIRE.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Tall grass lower half -> DoubleTallGrassFire
						if (bs.is(BurntTags.BLOCK_BURNS_TO_DOUBLE_TALL_GRASS)
								&& (bs.getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep70
										? bs.getValue(_getep70).toString()
										: "").equals("lower")) {
							world.setBlock(BlockPos.containing(x, y + 1, z), Blocks.AIR.defaultBlockState(), 3);
							{
								BlockState _bs = BurntModBlocks.DOUBLE_TALL_GRASS_FIRE.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Grass blocks -> GrassStartProcedure
						if (bs.is(BurntTags.BLOCK_GRASS_BLOCKS)
								|| bs.is(BurntTags.BLOCK_IW_GRASS_BLOCKS)
								|| bs.is(BurntTags.BLOCK_RU_GRASS_BLOCKS)
								|| bs.is(BurntTags.BLOCK_BOP_GRASS_BLOCKS)
								|| bs.is(BurntTags.BLOCK_BWG_GRASS_BLOCKS)) {
							GrassStartProcedure.execute(world, x, y, z);
						}
						// Dirt
						if (bs.getBlock() == Blocks.DIRT) {
							world.setBlock(pos, BurntModBlocks.BURNT_DIRT.defaultBlockState(), 3);
						}
						// Mushroom blocks
						if (bs.is(BurntTags.BLOCK_FORGE_MUSHROOM_BLOCKS)) {
							MushroomStartProcedure.execute(world, x, y, z);
						}
						// Bamboo blocks (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_BAMBOO_BLOCKS)) {
								BambooStartProcedure.execute(world, x, y, z);
							}
						}
						// Leaves
						if (bs.is(BurntTags.BLOCK_MC_LEAVES)
								&& !bs.is(BurntTags.BLOCK_FIRE_RESISTANT)
								&& (bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp101
										&& bs.getValue(_getbp101)) == false) {
							LeavesStartProcedure.execute(world, x, y, z);
						}
						// Stripped wood (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_FORGE_STRIPPED_WOOD)) {
								StrippedWoodStartProcedure.execute(world, x, y, z);
							}
						}
						// Stripped logs (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_FORGE_STRIPPED_LOGS)) {
								StrippedLogStartProcedure.execute(world, x, y, z);
							}
						}
						// Wood
						if (bs.is(BurntTags.BLOCK_FORGE_WOOD)) {
							WoodStartProcedure.execute(world, x, y, z);
						}
						// DT branches must come before the logs check - they're in #minecraft:logs_that_burn and would
						// otherwise become a smoldering_log
						if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_BRANCH)) {
							BranchStartProcedure.execute(world, x, y, z);
						}
						// DT roots - must come before MC_LOGS_THAT_BURN for the same reason.
						else if (bs.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_ROOT)) {
							RootStartProcedure.execute(world, x, y, z);
						}
						// Logs
						else if (bs.is(BurntTags.BLOCK_MC_LOGS_THAT_BURN)) {
							LogStartProcedure.execute(world, x, y, z);
						}
						// Planks (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_MC_PLANKS)
									&& !bs.is(BurntTags.BLOCK_FIRE_RESISTANT)) {
								PlanksStartProcedure.execute(world, x, y, z);
							}
						}
						// Stairs (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_MC_WOODEN_STAIRS)
									&& !bs.is(BurntTags.BLOCK_FIRE_RESISTANT)
									&& (bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp129
											&& bs.getValue(_getbp129)) == false) {
								StairsStartProcedure.execute(world, x, y, z);
							}
						}
						// Slabs (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_MC_WOODEN_SLABS)
									&& !bs.is(BurntTags.BLOCK_FIRE_RESISTANT)
									&& (bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp137
											&& bs.getValue(_getbp137)) == false) {
								SlabsStartProcedure.execute(world, x, y, z);
							}
						}
						// Fences (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_MC_WOODEN_FENCES)
									&& !bs.is(BurntTags.BLOCK_FIRE_RESISTANT)
									&& !bs.is(BurntTags.BLOCK_BURNT)) {
								FenceStartProcedure.execute(world, x, y, z);
							}
						}
						// Fence gates (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_MC_FENCE_GATES)
									&& !bs.is(BurntTags.BLOCK_FIRE_RESISTANT)) {
								FenceGateStartProcedure.execute(world, x, y, z);
							}
						}
						// Doors (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_MC_WOODEN_DOORS)
									&& !bs.is(BurntTags.BLOCK_NO_BURN)
									&& (bs.getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep159
											? bs.getValue(_getep159).toString()
											: "").equals("lower")) {
								DoorStartProcedure.execute(world, x, y, z);
							}
						}
						// Trapdoors (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_MC_WOODEN_TRAPDOORS)
									&& !bs.is(BurntTags.BLOCK_NO_BURN)) {
								TrapdoorStartProcedure.execute(world, x, y, z);
							}
						}
						// Ladder (crafted)
						if (crafted) {
							if (bs.getBlock() == Blocks.LADDER) {
								{
									BlockState _bs = BurntModBlocks.BURNT_LADDER.defaultBlockState();
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
									BlockEntity _be = world.getBlockEntity(pos);
									CompoundTag _bnbt = null;
									if (_be != null) {
										_bnbt = _be.saveWithFullMetadata(world.registryAccess());
										_be.setRemoved();
									}
									world.setBlock(pos, _bs, 3);
									if (_bnbt != null) {
										_be = world.getBlockEntity(pos);
										if (_be != null) {
											try {
												_be.loadWithComponents(_bnbt, world.registryAccess());
											} catch (Exception ignored) {
											}
										}
									}
								}
							}
						}
						// Moss block
						if (bs.getBlock() == Blocks.MOSS_BLOCK) {
							world.setBlock(pos, (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
						}
						// Bookshelf (crafted)
						if (crafted) {
							if (bs.getBlock() == Blocks.BOOKSHELF) {
								{
									BlockState _bs = (BurntModBlocks.SOOTY_BOOKSHELF.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp180
											? BurntModBlocks.SOOTY_BOOKSHELF.defaultBlockState().setValue(_withbp180, true)
											: BurntModBlocks.SOOTY_BOOKSHELF.defaultBlockState());
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
									world.setBlock(pos, _bs, 3);
								}
							}
						}
						// Mangrove roots
						if (bs.getBlock() == Blocks.MANGROVE_ROOTS) {
							world.setBlock(pos, (blockStateWithInt(BurntModBlocks.SMOLDERING_MANGROVE_ROOTS.defaultBlockState(), "stage", 1)), 3);
						}
						// Bamboo stalk
						if (bs.getBlock() == Blocks.BAMBOO) {
							{
								BlockState _bs = BurntModBlocks.BURNT_BAMBOO_STALK.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Wooden buttons (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_WOODEN_BUTTONS)) {
								{
									BlockState _bs = BurntModBlocks.BURNT_BUTTON.defaultBlockState();
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
									BlockEntity _be = world.getBlockEntity(pos);
									CompoundTag _bnbt = null;
									if (_be != null) {
										_bnbt = _be.saveWithFullMetadata(world.registryAccess());
										_be.setRemoved();
									}
									world.setBlock(pos, _bs, 3);
									if (_bnbt != null) {
										_be = world.getBlockEntity(pos);
										if (_be != null) {
											try {
												_be.loadWithComponents(_bnbt, world.registryAccess());
											} catch (Exception ignored) {
											}
										}
									}
								}
							}
						}
						// Wooden pressure plates (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_WOODEN_PRESSURE_PLATES)) {
								{
									BlockState _bs = BurntModBlocks.BURNT_PRESSURE_PLATE.defaultBlockState();
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
									BlockEntity _be = world.getBlockEntity(pos);
									CompoundTag _bnbt = null;
									if (_be != null) {
										_bnbt = _be.saveWithFullMetadata(world.registryAccess());
										_be.setRemoved();
									}
									world.setBlock(pos, _bs, 3);
									if (_bnbt != null) {
										_be = world.getBlockEntity(pos);
										if (_be != null) {
											try {
												_be.loadWithComponents(_bnbt, world.registryAccess());
											} catch (Exception ignored) {
											}
										}
									}
								}
							}
						}
						// Cave vines plant
						if (bs.getBlock() == Blocks.CAVE_VINES_PLANT) {
							{
								BlockState _bs = BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Cave vines
						if (bs.getBlock() == Blocks.CAVE_VINES) {
							{
								BlockState _bs = BurntModBlocks.SMOLDERING_CAVE_VINES.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Azalea / flowering azalea
						if (bs.getBlock() == Blocks.AZALEA || bs.getBlock() == Blocks.FLOWERING_AZALEA) {
							{
								BlockState _bs = BurntModBlocks.SMOLDERING_AZALEA.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Moss carpet
						if (bs.getBlock() == Blocks.MOSS_CARPET) {
							world.setBlock(pos, (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS_CARPET.defaultBlockState(), "stage", 1)), 3);
						}
						// Wool (crafted + wool_burn)
						if (crafted && woolBurn) {
							if (bs.is(BurntTags.BLOCK_MC_WOOL)
									&& !bs.is(BurntTags.BLOCK_FIRE_RESISTANT)) {
								world.setBlock(pos,
										(BurntModBlocks.SMOLDERING_WOOL.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp227
												? BurntModBlocks.SMOLDERING_WOOL.defaultBlockState().setValue(_withbp227, true)
												: BurntModBlocks.SMOLDERING_WOOL.defaultBlockState()),
										3);
							}
						}
						// Wool carpets (crafted)
						if (crafted) {
							if (bs.is(BurntTags.BLOCK_MC_WOOL_CARPETS)) {
								world.setBlock(pos,
										(BurntModBlocks.SMOLDERING_CARPET.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp233
												? BurntModBlocks.SMOLDERING_CARPET.defaultBlockState().setValue(_withbp233, true)
												: BurntModBlocks.SMOLDERING_CARPET.defaultBlockState()),
										3);
							}
						}
						// Hay block
						if (bs.getBlock() == Blocks.HAY_BLOCK) {
							{
								BlockState _bs = (BurntModBlocks.SMOLDERING_HAY.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp238
										? BurntModBlocks.SMOLDERING_HAY.defaultBlockState().setValue(_withbp238, true)
										: BurntModBlocks.SMOLDERING_HAY.defaultBlockState());
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Pumpkin
						if (bs.getBlock() == Blocks.PUMPKIN) {
							{
								BlockState _bs = (BurntModBlocks.BURNT_PUMPKIN.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp243
										? BurntModBlocks.BURNT_PUMPKIN.defaultBlockState().setValue(_withbp243, true)
										: BurntModBlocks.BURNT_PUMPKIN.defaultBlockState());
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Carved pumpkin
						if (bs.getBlock() == Blocks.CARVED_PUMPKIN) {
							{
								BlockState _bs = (BurntModBlocks.BURNT_CARVED_PUMPKIN.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp247
										? BurntModBlocks.BURNT_CARVED_PUMPKIN.defaultBlockState().setValue(_withbp247, true)
										: BurntModBlocks.BURNT_CARVED_PUMPKIN.defaultBlockState());
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Jack-o-lantern (fixed: was using dead sx/sy/sz offsets - always zero)
						if (bs.getBlock() == Blocks.JACK_O_LANTERN) {
							{
								BlockState _bs = (BurntModBlocks.BURNT_JACK_O_LANTERN.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp251
										? BurntModBlocks.BURNT_JACK_O_LANTERN.defaultBlockState().setValue(_withbp251, true)
										: BurntModBlocks.BURNT_JACK_O_LANTERN.defaultBlockState());
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Melon
						if (bs.getBlock() == Blocks.MELON) {
							{
								BlockState _bs = (BurntModBlocks.BURNT_MELON.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp255
										? BurntModBlocks.BURNT_MELON.defaultBlockState().setValue(_withbp255, true)
										: BurntModBlocks.BURNT_MELON.defaultBlockState());
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Crops
						if (bs.is(BurntTags.BLOCK_MC_CROPS)) {
							CropsStartProcedure.execute(world, x, y, z);
						}
						// Sugar cane
						if (bs.getBlock() == Blocks.SUGAR_CANE) {
							{
								BlockState _bs = BurntModBlocks.BURNT_SUGAR_CANE.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Ice melts to water, or to nothing in the Nether
						if (bs.getBlock() == Blocks.ICE) {
							boolean _iceUltrawarm = world instanceof net.minecraft.world.level.Level _iceLvl
									&& _iceLvl.dimensionType().ultraWarm();
							world.setBlock(pos,
									_iceUltrawarm ? Blocks.AIR.defaultBlockState() : Blocks.WATER.defaultBlockState(), 3);
						}
						// Snow melts away to nothing, unlike ice - vanilla snow leaves no water behind.
						if (bs.getBlock() == Blocks.SNOW || bs.getBlock() == Blocks.SNOW_BLOCK || bs.getBlock() == Blocks.POWDER_SNOW) {
							world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
						}
						// Scaffolding (crafted)
						if (crafted) {
							if (bs.getBlock() == Blocks.SCAFFOLDING) {
								{
									BlockState _bs = (blockStateWithInt(BurntModBlocks.BURNT_SCAFFOLDING.defaultBlockState(), "stage", 1));
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
									BlockEntity _be = world.getBlockEntity(pos);
									CompoundTag _bnbt = null;
									if (_be != null) {
										_bnbt = _be.saveWithFullMetadata(world.registryAccess());
										_be.setRemoved();
									}
									world.setBlock(pos, _bs, 3);
									if (_bnbt != null) {
										_be = world.getBlockEntity(pos);
										if (_be != null) {
											try {
												_be.loadWithComponents(_bnbt, world.registryAccess());
											} catch (Exception ignored) {
											}
										}
									}
								}
							}
						}
						// Cactus
						if (bs.getBlock() == Blocks.CACTUS) {
							{
								BlockState _bs = BurntModBlocks.SMOLDERING_CACTUS.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Compat tier tags - delegate to BurnBaby, which has all the tier routing
						// Uses isCompatBurnable rather than the aggregator tag, which can silently fail to resolve
						if (!bs.is(BurntTags.BLOCK_FIRE_RESISTANT) && !bs.is(BurntTags.BLOCK_WILL_NOT_BURN)) {
							if (BurntTags.isCompatBurnable(bs)
									|| BurnMappingRegistry.get(bs.getBlock()) != null) {
								BurnBabyProcedure.execute(world, x, y, z);
							}
						}
						// Cobweb
						if (bs.getBlock() == Blocks.COBWEB) {
							{
								BlockState _bs = BurntModBlocks.SMOLDERING_COBWEB.defaultBlockState();
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
								world.setBlock(pos, _bs, 3);
							}
						}
						// Fire barrel
						if (bs.getBlock() == BurntModBlocks.FIRE_BARREL) {
							world.setBlock(pos, BurntModBlocks.FIRE_BARREL_ACTIVE.defaultBlockState(), 3);
						}
						// TNT - mark unstable
						if (bs.getBlock() == Blocks.TNT) {
							if (bs.getBlock().getStateDefinition().getProperty("unstable") instanceof BooleanProperty _booleanProp)
								world.setBlock(pos, bs.setValue(_booleanProp, true), 3);
						}
						// Barrel (burn_barrels config)
						if (burnBarrels) {
							if (bs.getBlock() == Blocks.BARREL) {
								{
									BlockState _bs = (BurntModBlocks.BURNT_BARREL.getStateDefinition().getProperty("hot") instanceof BooleanProperty _withbp294
											? BurntModBlocks.BURNT_BARREL.defaultBlockState().setValue(_withbp294, true)
											: BurntModBlocks.BURNT_BARREL.defaultBlockState());
									_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(bs, _bs);
									BlockEntity _be = world.getBlockEntity(pos);
									CompoundTag _bnbt = null;
									if (_be != null) {
										_bnbt = _be.saveWithFullMetadata(world.registryAccess());
										_be.setRemoved();
									}
									world.setBlock(pos, _bs, 3);
									if (_bnbt != null) {
										_be = world.getBlockEntity(pos);
										if (_be != null) {
											try {
												_be.loadWithComponents(_bnbt, world.registryAccess());
											} catch (Exception ignored) {
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}
