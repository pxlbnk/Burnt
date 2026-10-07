package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;
import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class BurntFenceGateStage1Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || (IsRainingProcedure.execute(world, x, y, z) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get())) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_BURNT_FENCE_GATES).getRandomElement(world.getRandom())
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
			if (BurntBasicConfigConfiguration.BIOME.get() == false) {
				fireSpeed = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPREAD.get() / 100.0, 1.0), 1.5) * 0.99;
			} else if (BurntBasicConfigConfiguration.BIOME.get() == true) {
				fireSpeed = BiomeSpecificProcedure.execute(world, x, y, z);
			}
			// Structural wood spreads slower so buildings don't flash over
			fireSpeed *= Math.max(0.0, Math.min(1.0, BurntBasicConfigConfiguration.WOOD_SPREAD.get() / 100.0));
			fireSpeed *= net.pixelbank.burnt.util.BurntBlockUtil.darkOakSpreadFactor(world, x, y, z);
			if (Math.random() <= fireSpeed) {
				if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(((double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get()) / 2d), e -> true).isEmpty()) {
					{
						int _value = 2;
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.SNOW || (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BurntTags.BLOCK_FIRE_DESTROYS)) {
				world.destroyBlock(BlockPos.containing(x, y + 1, z), false);
			}
		}
	}
}
