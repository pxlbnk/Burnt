package net.pixelbank.burnt.procedures;


import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class SmolderingCoalOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			{ // NOT gated on distant_plumes: long-range smoke must run with plumes off. Plumes gated inside.
				if (Math.random() <= 0.25) {
					SpawnSignalPlumeXYZProcedure.execute(world, x, y, z);
				}
			}
		}
	}
}