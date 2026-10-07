package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.util.WoodSpecies;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

// Breakdown log on random tick
// Destroy or replant if at ground level
// Based on replant chance

public class BurntLogOnTickUpdateProcedure {

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (world.isClientSide()) return;
		if (!BurntBasicConfigConfiguration.FOREST_RECOVERY.get()) return;
		double speed = BurntBasicConfigConfiguration.FOREST_RECOVER_SPEED.get();
		if (speed <= 0.0) return;

		// Only fragile logs participate in breakdown.
		if (!(blockstate.getBlock().getStateDefinition().getProperty("fragile") instanceof BooleanProperty fragileProp
				&& blockstate.getValue(fragileProp))) {
			return;
		}

		BlockPos pos = BlockPos.containing(x, y, z);
		BlockPos above = pos.above();
		BlockPos below = pos.below();

		// Cleanup pass - burnt leaves directly above tumble first.
		if (world.getBlockState(above).getBlock() == BurntModBlocks.BURNT_LEAVES) {
			world.destroyBlock(above, false);
		}

		BlockState aboveState = world.getBlockState(above);
		BlockState belowState = world.getBlockState(below);
		boolean airAbove = aboveState.is(BurntTags.BLOCK_AIR);
		boolean airBelow = belowState.is(BurntTags.BLOCK_AIR);

		// Need some air around - buried logs (e.g. inside a player build) stay put.
		if (!airAbove && !airBelow) return;

		boolean dirtBelow = belowState.getBlock() == BurntModBlocks.BURNT_DIRT
				|| belowState.getBlock() == Blocks.DIRT;

		// A crafted block on any side means this was a build, not a tree - decay but don't replant
		boolean nextToBuildBlock =
				   world.getBlockState(pos.north()).is(BurntTags.BLOCK_CRAFTABLES)
				|| world.getBlockState(pos.east() ).is(BurntTags.BLOCK_CRAFTABLES)
				|| world.getBlockState(pos.south()).is(BurntTags.BLOCK_CRAFTABLES)
				|| world.getBlockState(pos.west() ).is(BurntTags.BLOCK_CRAFTABLES)
				|| belowState.is(BurntTags.BLOCK_CRAFTABLES);

		double replantChance = BurntBasicConfigConfiguration.FOREST_REPLANT_CHANCE.get() / 100.0;
		boolean shouldReplant = airAbove && dirtBelow && !nextToBuildBlock
				&& Math.random() < replantChance;

		if (shouldReplant) {
			// Use the species carried from the source log, or guess from the biome
			WoodSpecies species = WoodSpecies.readFrom(blockstate);
			BlockState sapling = species != WoodSpecies.UNKNOWN
					? species.getSaplingState()
					: saplingForBiome(world, pos);
			world.destroyBlock(pos, false);
			world.setBlock(below, copyMatchingProps(belowState, Blocks.DIRT.defaultBlockState()), 3);
			if (!sapling.isAir()) {
				world.setBlock(pos, sapling, 3);
			}
		} else {
			// Just decay away - no sapling.
			world.destroyBlock(pos, false);
		}
	}

	// Replant the sapling that suits the biome
	private static BlockState saplingForBiome(LevelAccessor world, BlockPos pos) {
		var biome = world.getBiome(pos);
		if (biome.is(ResourceLocation.parse("jungle"))
				|| biome.is(ResourceLocation.parse("bamboo_jungle"))
				|| biome.is(ResourceLocation.parse("sparse_jungle"))) {
			return Blocks.JUNGLE_SAPLING.defaultBlockState();
		}
		if (biome.is(ResourceLocation.parse("dark_forest"))) {
			// Dark forest regrows sparsely - jungle sapling only half the time
			return Math.random() < 0.5
					? Blocks.JUNGLE_SAPLING.defaultBlockState()
					: Blocks.AIR.defaultBlockState();
		}
		if (biome.is(ResourceLocation.parse("savanna"))
				|| biome.is(ResourceLocation.parse("savanna_plateau"))
				|| biome.is(ResourceLocation.parse("windswept_savanna"))) {
			return Blocks.ACACIA_SAPLING.defaultBlockState();
		}
		if (biome.is(ResourceLocation.parse("taiga"))
				|| biome.is(ResourceLocation.parse("old_growth_spruce_taiga"))
				|| biome.is(ResourceLocation.parse("old_growth_pine_taiga"))) {
			return Blocks.SPRUCE_SAPLING.defaultBlockState();
		}
		// Fallback: 50/50 oak / birch.
		return Math.random() < 0.5
				? Blocks.OAK_SAPLING.defaultBlockState()
				: Blocks.BIRCH_SAPLING.defaultBlockState();
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private static BlockState copyMatchingProps(BlockState source, BlockState target) {
		for (Property<?> propOld : source.getProperties()) {
			Property propNew = target.getBlock().getStateDefinition().getProperty(propOld.getName());
			if (propNew != null && target.getValue(propNew) != null) {
				try { target = target.setValue(propNew, source.getValue(propOld)); } catch (Exception ignored) {}
			}
		}
		return target;
	}
}
