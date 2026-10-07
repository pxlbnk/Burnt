package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class PlanksStarterTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_PLANKS.defaultBlockState(), 3);
				return;
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp2 && blockstate.getValue(_getbp2)) == true) {
				if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
					if (BurntBasicConfigConfiguration.BIOME.get() == false) {
						fireSpeed = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPREAD.get() / 100.0, 1.0), 1.5) * 0.99;
					} else if (BurntBasicConfigConfiguration.BIOME.get() == true) {
						fireSpeed = BiomeSpecificProcedure.execute(world, x, y, z);
					}
					if (Math.random() <= fireSpeed) {
						world.setBlock(BlockPos.containing(x, y, z), (blockStateWithInt((BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_SMOLDERING_PLANKS).getRandomElement(world.getRandom())
								.orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value()).defaultBlockState(), "stage", 1)), 3);
					}
				} else {
					world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_PLANKS.defaultBlockState(), 3);
				}
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}