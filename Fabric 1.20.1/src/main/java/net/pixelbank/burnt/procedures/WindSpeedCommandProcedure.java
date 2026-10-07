package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class WindSpeedCommandProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		BurntModVariables.MapVariables.get(world).windSpeed = DoubleArgumentType.getDouble(arguments, "speed");
		BurntModVariables.MapVariables.get(world).windSpeedLock = true;
		BurntModVariables.MapVariables.get(world).markSyncDirty();
	}
}