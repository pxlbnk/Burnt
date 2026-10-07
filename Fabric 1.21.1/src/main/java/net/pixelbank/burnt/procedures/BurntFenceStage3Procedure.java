package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class BurntFenceStage3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double burnTime = 0;
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || (IsRainingProcedure.execute(world, x, y, z) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get())) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_BURNT_FENCES).getRandomElement(world.getRandom())
						.orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value()).defaultBlockState();
				BlockState _bso = world.getBlockState(_bp);
				_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
				world.setBlock(_bp, _bs, 3);
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bsNew = world.getBlockState(_pos);
				if (_bsNew.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
					world.setBlock(_pos, _bsNew.setValue(_booleanProp, false), 3);
				return;
			}
			burnTime = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPEED.get() / 100.0, 1.0), 1.5) * 100.0;
			if (Mth.nextInt(world.getRandom(), 0, 250) <= burnTime) { // charred-out roll halved 500->250 (smoldering fences lingered; matches planks)
				if (Math.random() <= 0.05) {
					world.destroyBlock(BlockPos.containing(x, y, z), false);
				} else {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_BURNT_FENCES).getRandomElement(world.getRandom()).orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR))
								.value()).defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
					}
				}
			}
		}
	}
}