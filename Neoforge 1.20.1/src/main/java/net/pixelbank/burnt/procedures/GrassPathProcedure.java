package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class GrassPathProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Object event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.BURNT_GRASS || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_GRASS
					|| (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.RECOVERING_GRASS
					|| (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.BURNT_DIRT)
					&& world.getBlockState(BlockPos.containing(x, y + 1, z)).isAir()
					&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof ShovelItem) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.shovel.flatten")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.shovel.flatten")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				world.setBlock(BlockPos.containing(x, y, z), BurntModBlocks.BURNT_PATH.defaultBlockState(), 3);
			}
		}
	}
}