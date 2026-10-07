package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;
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
import net.minecraft.core.BlockPos;

public class BurntLogsStage1Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			// Melt any snow layer sitting on top of a smoldering log
			BlockPos _abovePos = BlockPos.containing(x, y + 1, z);
			if (world.getBlockState(_abovePos).getBlock() == Blocks.SNOW) {
				world.destroyBlock(_abovePos, false);
			}
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.BURNT_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp
						? BurntModBlocks.BURNT_LOG.defaultBlockState().setValue(_withbp, true)
						: BurntModBlocks.BURNT_LOG.defaultBlockState());
				BlockState _bso = world.getBlockState(_bp);
				_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
				world.setBlock(_bp, _bs, 3);
				return;
			}
			if (IsRainingProcedure.execute(world, x, y, z) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = (BurntModBlocks.BURNT_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp
						? BurntModBlocks.BURNT_LOG.defaultBlockState().setValue(_withbp, true)
						: BurntModBlocks.BURNT_LOG.defaultBlockState());
				BlockState _bso = world.getBlockState(_bp);
				_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
				world.setBlock(_bp, _bs, 3);
				return;
			}
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				if (BurntBasicConfigConfiguration.BIOME.get() == false) {
					fireSpeed = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPREAD.get() / 100.0, 1.0), 1.5) * 0.99;
				} else if (BurntBasicConfigConfiguration.BIOME.get() == true) {
					fireSpeed = BiomeSpecificProcedure.execute(world, x, y, z);
				}
				// Structural wood spreads slower so buildings don't flash over
				fireSpeed *= Math.max(0.0, Math.min(1.0, BurntBasicConfigConfiguration.WOOD_SPREAD.get() / 100.0));
				fireSpeed *= net.pixelbank.burnt.util.BurntBlockUtil.darkOakSpreadFactor(world, x, y, z);
				if (Math.random() <= fireSpeed) {
					var _players = world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(((double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get()) / 2d), e -> true);
					if (!_players.isEmpty()) {
						{
							int _value = 2;
							BlockPos _pos = BlockPos.containing(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
								world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
						}
					}
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = (BurntModBlocks.BURNT_LOG.getStateDefinition().getProperty("fragile") instanceof BooleanProperty _withbp10
							? BurntModBlocks.BURNT_LOG.defaultBlockState().setValue(_withbp10, true)
							: BurntModBlocks.BURNT_LOG.defaultBlockState());
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}