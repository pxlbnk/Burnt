package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;
import net.minecraftforge.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class SmolderingHayTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if ((IsRainingProcedure.isGloballyRaining(world) || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z))) {
				if ((net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true)) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_HAY.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				} else {
					if (Math.random() <= 0.3) {
						WillitburnProcedure.execute(world, x, y, z);
						if (Math.random() <= 0.5) {
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = BurntModBlocks.BURNT_HAY.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else {
							world.destroyBlock(BlockPos.containing(x, y, z), false);
						}
					}
				}
			} else {
				if (Math.random() <= 0.3) {
					WillitburnProcedure.execute(world, x, y, z);
					if (Math.random() <= 0.5) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.BURNT_HAY.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							world.setBlock(_bp, _bs, 3);
						}
					} else {
						world.destroyBlock(BlockPos.containing(x, y, z), false);
					}
				}
			}
			{ // NOT gated on distant_plumes: long-range smoke must run with plumes off. Plumes gated inside.
				if (Math.random() <= 0.5) {
					SpawnSignalPlumeXYZProcedure.execute(world, x, y, z);
				}
			}
			if (ModList.get().isLoaded("cold_sweat")) {
				if (Math.random() <= 0.5) {
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