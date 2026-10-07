package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class BurntVinesPlacementProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.VINE) {
				world.setBlock(BlockPos.containing(x + 1, y, z), (blockStateWithDirection(BurntModBlocks.BURNT_VINE.defaultBlockState(), Direction.EAST)), 3);
			}
			if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.VINE) {
				world.setBlock(BlockPos.containing(x - 1, y, z), (blockStateWithDirection(BurntModBlocks.BURNT_VINE.defaultBlockState(), Direction.WEST)), 3);
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.VINE) {
				world.setBlock(BlockPos.containing(x, y, z + 1), (blockStateWithDirection(BurntModBlocks.BURNT_VINE.defaultBlockState(), Direction.SOUTH)), 3);
			}
			if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.VINE) {
				world.setBlock(BlockPos.containing(x, y, z - 1), (blockStateWithDirection(BurntModBlocks.BURNT_VINE.defaultBlockState(), Direction.NORTH)), 3);
			}
			if (Math.random() <= 0.3) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.campfire.crackle")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.campfire.crackle")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			}
		}
	}

	private static BlockState blockStateWithDirection(BlockState blockState, Direction newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (prop instanceof DirectionProperty dp && dp.getPossibleValues().contains(newValue))
			return blockState.setValue(dp, newValue);
		prop = blockState.getBlock().getStateDefinition().getProperty("axis");
		return prop instanceof EnumProperty ep && ep.getPossibleValues().contains(newValue.getAxis()) ? blockState.setValue(ep, newValue.getAxis()) : blockState;
	}
}