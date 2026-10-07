package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import java.util.Comparator;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;

import net.minecraft.core.registries.Registries;

public class ApplyBowEffectsToArrowProcedure {
	// Fabric replacement for NeoForge entity.getPersistentData() - track arrow state via UUID sets
	private static final Set<UUID> PROCESSED_ARROWS = Collections.synchronizedSet(
			Collections.newSetFromMap(new WeakHashMap<>()));
	public static final Set<UUID> SOUL_ARROWS = Collections.synchronizedSet(
			Collections.newSetFromMap(new WeakHashMap<>()));

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Object event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean done = false;
		ItemStack mh = ItemStack.EMPTY;
		ItemStack oh = ItemStack.EMPTY;
		if (!world.isClientSide()) {
			if (entity instanceof Arrow || entity instanceof SpectralArrow) {
				if (!PROCESSED_ARROWS.contains(entity.getUUID())) {
					done = false;
					{
						final Vec3 _center = new Vec3(x, y, z);
						for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
							if (done == true) {
								break;
							} else {
								if (entityiterator instanceof Player) {
									if (BurntBasicConfigConfiguration.FLAME_BOW.get() == true) {
										SOUL_ARROWS.add(entity.getUUID());
										// burntSoulUntil reset - tracked via PlayerVariables if needed
										PROCESSED_ARROWS.add(entity.getUUID());
										done = true;
										break;
									}
								}
								mh = (entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
								oh = (entityiterator instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
								if (mh.is(BurntTags.ITEM_BOWS) && EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FLAME), mh) > 0
										|| oh.is(BurntTags.ITEM_BOWS) && EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FLAME), oh) > 0) {
									entity.igniteForSeconds(5);
									PROCESSED_ARROWS.add(entity.getUUID());
								}
							}
						}
					}
				}
			}
		}
	}
}
