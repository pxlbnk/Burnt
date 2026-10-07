package net.pixelbank.burnt.command;

import net.pixelbank.burnt.procedures.WindSpeedCommandProcedure;
import net.pixelbank.burnt.procedures.WindResetCommandProcedure;
import net.pixelbank.burnt.procedures.WindAngleCommandProcedure;
import net.pixelbank.burnt.procedures.FireOutCommandProcedure;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.world.level.Level;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.pixelbank.burnt.util.BurntNoBurnRegions;

public class BurntCommandCommand {

	public static void register() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
			dispatcher.register(Commands.literal("burnt").requires(s -> s.hasPermission(1))
				.then(Commands.literal("killFire").executes(arguments -> {
					Level world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					FireOutCommandProcedure.execute(world, x, y, z);
					return 1;
				}))
				.then(Commands.literal("noburn").requires(s -> s.hasPermission(2))
					.then(Commands.literal("add")
						.then(Commands.argument("from", BlockPosArgument.blockPos())
							.then(Commands.argument("to", BlockPosArgument.blockPos()).executes(arguments -> {
								BlockPos a = BlockPosArgument.getLoadedBlockPos(arguments, "from");
								BlockPos b = BlockPosArgument.getLoadedBlockPos(arguments, "to");
								int n = BurntNoBurnRegions.add(arguments.getSource().getLevel(), a, b);
								arguments.getSource().sendSuccess(() -> Component.literal(
										"Burnt fire is now blocked in that box (" + n + " region(s) total)."), true);
								return 1;
							}))))
					.then(Commands.literal("here").executes(arguments -> {
						// Whole current chunk, full world height - the common case for "protect my base".
						var level = arguments.getSource().getLevel();
						BlockPos p = BlockPos.containing(arguments.getSource().getPosition());
						int cx = p.getX() >> 4, cz = p.getZ() >> 4;
						BlockPos a = new BlockPos(cx << 4, level.getMinBuildHeight(), cz << 4);
						BlockPos b = new BlockPos((cx << 4) + 15, level.getMaxBuildHeight(), (cz << 4) + 15);
						int n = BurntNoBurnRegions.add(level, a, b);
						arguments.getSource().sendSuccess(() -> Component.literal(
								"Burnt fire is now blocked in this chunk (" + n + " region(s) total)."), true);
						return 1;
					}))
					.then(Commands.literal("list").executes(arguments -> {
						var lines = BurntNoBurnRegions.list(arguments.getSource().getServer());
						if (lines.isEmpty()) {
							arguments.getSource().sendSuccess(() -> Component.literal("No no-burn regions defined."), false);
						} else {
							arguments.getSource().sendSuccess(() -> Component.literal(
									"No-burn regions:\n" + String.join("\n", lines)), false);
						}
						return lines.size();
					}))
					.then(Commands.literal("remove")
						.then(Commands.argument("index", IntegerArgumentType.integer(1)).executes(arguments -> {
							int i = IntegerArgumentType.getInteger(arguments, "index");
							boolean ok = BurntNoBurnRegions.remove(arguments.getSource().getServer(), i);
							arguments.getSource().sendSuccess(() -> Component.literal(ok
									? "Removed no-burn region " + i + "."
									: "No no-burn region with index " + i + "."), true);
							return ok ? 1 : 0;
						})))
					.then(Commands.literal("clear").executes(arguments -> {
						int n = BurntNoBurnRegions.clear(arguments.getSource().getServer());
						arguments.getSource().sendSuccess(() -> Component.literal(
								"Cleared " + n + " no-burn region(s)."), true);
						return n;
					})))
				.then(Commands.literal("wind")
					.then(Commands.literal("reset").executes(arguments -> {
						Level world = arguments.getSource().getLevel();
						WindResetCommandProcedure.execute(world);
						return 0;
					}))
					.then(Commands.literal("set")
						.then(Commands.literal("strength")
							.then(Commands.argument("speed", DoubleArgumentType.doubleArg(0, 100)).executes(arguments -> {
								Level world = arguments.getSource().getLevel();
								WindSpeedCommandProcedure.execute(world, arguments);
								return 0;
							})))
						.then(Commands.literal("angle")
							.then(Commands.argument("angle", DoubleArgumentType.doubleArg(1, 360)).executes(arguments -> {
								Level world = arguments.getSource().getLevel();
								WindAngleCommandProcedure.execute(world, arguments);
								return 0;
							}))))))
		);
	}
}
