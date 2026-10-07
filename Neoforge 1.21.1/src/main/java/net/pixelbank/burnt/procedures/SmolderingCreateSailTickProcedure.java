package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;
import net.neoforged.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class SmolderingCreateSailTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (!world.isClientSide()) {
			if (net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z)) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bsCurrent = world.getBlockState(_bp);
				BlockState _bs = BurntModBlocks.BURNT_SAIL.defaultBlockState();
				if (_bsCurrent.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _fp
						&& _bs.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _nfp) {
					_bs = _bs.setValue(_nfp, _bsCurrent.getValue(_fp));
				}
				world.setBlock(_bp, _bs, 3);
				return;
			}
			// Rain chars a burning sail to burnt_sail, keeping facing - gated on rain_extinguish
			if (IsRainingProcedure.isGloballyRaining(world) && BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bsCurrent = world.getBlockState(_bp);
				BlockState _bs = BurntModBlocks.BURNT_SAIL.defaultBlockState();
				if (_bsCurrent.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _fp
						&& _bs.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _nfp) {
					_bs = _bs.setValue(_nfp, _bsCurrent.getValue(_fp));
				}
				world.setBlock(_bp, _bs, 3);
				return;
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp2 && blockstate.getValue(_getbp2)) == true) {
				if (Math.random() <= 0.02) {
					WillitburnProcedure.execute(world, x, y, z);
					if (Math.random() <= 0.75) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bsCurrent = world.getBlockState(_bp);
						BlockState _bs = BurntModBlocks.BURNT_SAIL.defaultBlockState();
						if (_bsCurrent.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _fp
								&& _bs.getBlock().getStateDefinition().getProperty("facing") instanceof DirectionProperty _nfp) {
							_bs = _bs.setValue(_nfp, _bsCurrent.getValue(_fp));
						}
						world.setBlock(_bp, _bs, 3);
					} else {
						world.destroyBlock(BlockPos.containing(x, y, z), false);
					}
				}
			}
			if (ModList.get().isLoaded("cold_sweat")) {
				if (Math.random() <= 0.25) {
					if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(4 / 2d), e -> true).isEmpty()) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"/temp modify @p addition world 0.1 false");
						BurntMod.queueServerWork((int) Mth.nextDouble(world.getRandom(), 200, 500), () -> {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										"/temp modify @p addition world -0.1 false");
						});
					}
				}
			}
		}
	}
}
