package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class BurningMossCarpetTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
			world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_MOSS_CARPET.defaultBlockState(), 3);
			return;
		}
		if ((blockstate.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _getip1 ? blockstate.getValue(_getip1) : -1) >= 1) {
			if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.MOSS_BLOCK) {
				world.setBlock(BlockPos.containing(x, y - 1, z), (blockStateWithInt(BurntModBlocks.SMOLDERING_MOSS.defaultBlockState(), "stage", 1)), 3);
			}
			if (Math.random() <= 0.3) {
				world.destroyBlock(BlockPos.containing(x, y, z), false);
			} else {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_MOSS_CARPET.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}