package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class SmolderingCampfireUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				if (BurntBasicConfigConfiguration.BIOME.get() == false) {
					fireSpeed = Math.pow(Math.min(BurntBasicConfigConfiguration.BURN_SPREAD.get() / 100.0, 1.0), 1.5) * 0.99;
				} else if (BurntBasicConfigConfiguration.BIOME.get() == true) {
					if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_MC_IS_HOT)
							&& world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_MC_IS_DRY)) {
						fireSpeed = 0.7;
					} else if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_MC_IS_HOT)
							&& world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_MC_IS_WET)) {
						fireSpeed = 0.5;
					} else if (world.getBiome(BlockPos.containing(x, y, z)).is(BurntTags.BIOME_MC_IS_COLD)) {
						fireSpeed = 0.05;
					} else {
						fireSpeed = 0.1;
					}
				}
				if (Math.random() <= fireSpeed) {
					if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(((double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get()) / 2d), e -> true).isEmpty()) {
						WillitburnProcedure.execute(world, x, y, z);
					}
				}
				if (Math.random() <= 0.1) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = (blockStateWithInt(BurntModBlocks.EMBER_CAMPFIRE.defaultBlockState(), "stage", 1));
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_CAMPFIRE.defaultBlockState();
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