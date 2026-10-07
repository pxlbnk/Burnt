package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.network.BurntModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class WindAngleCommandProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		BurntModVariables.MapVariables.get(world).windAngle = DoubleArgumentType.getDouble(arguments, "angle");
		BurntModVariables.MapVariables.get(world).windAngleLock = true;
		BurntModVariables.MapVariables.get(world).markSyncDirty();
	}
}