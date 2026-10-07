package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.pixelbank.burnt.util.BurntBlockUtil;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

public class BurningLeavesTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double fireSpeed = 0;
		double particleRadius = 0;
		double particleAmount = 0;
		boolean rainingNow = false;
		boolean useW2 = false;
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_LEAVES.defaultBlockState(), 3);
				return;
			}
			// Sky-exposed burning leaves go out in rain
			// Uses rainCanReach, not the vanilla sky check - vanilla treats leaves as opaque
			if (IsRainingProcedure.isGloballyRaining(world) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true) {
				if (BurntBlockUtil.rainCanReach(world, BlockPos.containing(x, y + 1, z))) {
					BurntMod.queueServerWork((int) Mth.nextDouble(world.getRandom(), 0, 40), () -> {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							if (_bs.hasProperty(BlockStateProperties.WATERLOGGED))
								_bs = _bs.setValue(BlockStateProperties.WATERLOGGED, false);
							world.setBlock(_bp, _bs, 3);
							BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount - 1;
							BurntModVariables.WorldVariables.get(world).markSyncDirty();
						}
					});
					return;
				}
			}
			// Embers only spread from actively-burning blocks (stage >= 1), with sufficient wind
			if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip0 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip0) : -1) >= 1) {
				if (BurntBasicConfigConfiguration.EMBER_SPREAD.get() && BurntModVariables.MapVariables.get(world).windSpeed > 30) {
					if (Math.random() <= BiomeChanceProcedure.execute(world, x, y, z) / 10000) {
						EmberSpreadProcedure.execute(world, x, y, z);
					}
				}
			}
			if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip2 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip2) : -1) >= 1) {
				if (world.isEmptyBlock(BlockPos.containing(x + 1, y, z)) && world.isEmptyBlock(BlockPos.containing(x - 1, y, z)) && world.isEmptyBlock(BlockPos.containing(x, y + 1, z)) && world.isEmptyBlock(BlockPos.containing(x, y - 1, z))
						&& world.isEmptyBlock(BlockPos.containing(x, y, z + 1)) && world.isEmptyBlock(BlockPos.containing(x, y, z - 1))) {
					if (Math.random() <= 0.1) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount - 1;
						BurntModVariables.WorldVariables.get(world).markSyncDirty();
					}
				}
				if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
					if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.SNOW) {
						world.destroyBlock(BlockPos.containing(x, y + 1, z), false);
					}
					if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							if (_bs.hasProperty(BlockStateProperties.WATERLOGGED))
								_bs = _bs.setValue(BlockStateProperties.WATERLOGGED, false);
							world.setBlock(_bp, _bs, 3);
						}
						BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount - 1;
						BurntModVariables.WorldVariables.get(world).markSyncDirty();
					} else {
						if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip33 ? blockstate.getValue(_getip33) : -1) == 1) {
							if (Math.random() <= 0.5) {
								BurntLeavesStage2Procedure.execute(world, x, y, z);
							}
						} else if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip35 ? blockstate.getValue(_getip35) : -1) == 2) {
							BurntLeavesStage3Procedure.execute(world, x, y, z);
						}
					}
				} else {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						if (_bs.hasProperty(BlockStateProperties.WATERLOGGED))
							_bs = _bs.setValue(BlockStateProperties.WATERLOGGED, false);
						world.setBlock(_bp, _bs, 3);
					}
					BurntModVariables.WorldVariables.get(world).flameCount = BurntModVariables.WorldVariables.get(world).flameCount - 1;
					BurntModVariables.WorldVariables.get(world).markSyncDirty();
				}
				{ // NOT gated on distant_plumes: long-range smoke must run with plumes off. Plumes gated inside.
					if (Math.random() <= 0.25) {
						SpawnSignalPlumeXYZProcedure.execute(world, x, y, z);
					}
				}
			}
		}
	}
}