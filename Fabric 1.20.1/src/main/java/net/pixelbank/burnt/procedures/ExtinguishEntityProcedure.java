package net.pixelbank.burnt.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.pixelbank.burnt.init.BurntModBlocks;

public class ExtinguishEntityProcedure {
	private static final TagKey<EntityType<?>> FIRE_ENTITY_TAG = TagKey.create(
			Registries.ENTITY_TYPE, new ResourceLocation("burnt", "fire_entity"));

	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world.isClientSide())
			return;

		// 1. If entity is on fire, extinguish it
		if (entity.isOnFire()) {
			entity.clearFire();
		}

		// 2. If entity is tagged as burnt:fire_entity
		if (entity.getType().is(FIRE_ENTITY_TAG)) {
			// 5% chance to play block break particles
			if (world instanceof Level level && level.getRandom().nextFloat() <= 0.05f) {
				BlockPos pos = BlockPos.containing(entity.getX(), entity.getY(), entity.getZ());
				level.levelEvent(2001, pos, Block.getId(BurntModBlocks.SPLASH.defaultBlockState()));
			}
			// Deal 2 drown damage
			if (world instanceof Level level) {
				entity.hurt(level.damageSources().drown(), 2);
			}
		}
	}
}