package net.pixelbank.burnt.item;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.entity.SprayEntity;
import net.pixelbank.burnt.entity.VisualSprayEntity;
import net.pixelbank.burnt.init.BurntModEntities;
import net.pixelbank.burnt.init.BurntModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.phys.Vec3;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ExtinguisherDispenserBehavior extends DefaultDispenseItemBehavior {

	/** How much to spread the spray left/right when firing sideways. */
	private static final double SPREAD_HORIZONTAL = 0.4;

	/** Spread for ceiling-mounted dispensers. */
	private static final double SPREAD_UP = 0.6;

	/** Spread for floor dispensers - wider cone downward. */
	private static final double SPREAD_DOWN = 2.0;

	/** Speed for horizontal spray shots. */
	private static final float SPEED = 1.5f;

	/** Slower speed for vertical dispensers so the spray doesn't fly too far. */
	private static final float SPEED_VERTICAL = 1.0f;

	/** Drops the spawn point a bit so spray exits from the mouth, not the middle. */
	private static final double HORIZONTAL_Y_OFFSET = -0.2;

	/** How often to fire a new spray projectile (2 ticks = 10 per second). */
	private static final int SPRAY_INTERVAL_TICKS = 2;

	/** Tracks which dispensers are currently spraying so we don't start duplicate loops. */
	private static final Set<Long> ACTIVE = ConcurrentHashMap.newKeySet();

	// Dispenser entry point - called once per rising redstone edge

	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		ServerLevel world = source.level();
		Direction facing = source.state().getValue(DispenserBlock.FACING);
		long key = source.pos().asLong();

		// One continuous spray loop per dispenser - ignore a re-trigger while already spraying
		if (ACTIVE.add(key)) {
			scheduleSpray(world, source.pos(), facing, key);
		}

		// Durability drains inside the loop, one per projectile, so it empties at the spray rate
		return stack;
	}

	// Self-rescheduling spray loop

	/** Fires one spray shot, then re-queues itself. Stops when the signal drops or the extinguisher runs out. */
	private static void scheduleSpray(ServerLevel world, BlockPos pos, Direction facing, long key) {
		BurntMod.queueServerWork(SPRAY_INTERVAL_TICKS, () -> {

			// Stop condition 1: signal dropped
			if (!world.hasNeighborSignal(pos)) {
				ACTIVE.remove(key);
				return;
			}

			// Stop condition 2: block entity gone (broken/unloaded)
			if (!(world.getBlockEntity(pos) instanceof DispenserBlockEntity be)) {
				ACTIVE.remove(key);
				return;
			}

			// Stop condition 3: no extinguisher in inventory
			ItemStack extStack = ItemStack.EMPTY;
			int extSlot = -1;
			for (int i = 0; i < be.getContainerSize(); i++) {
				if (be.getItem(i).is(BurntModItems.EXTINGUISHER)) {
					extStack = be.getItem(i);
					extSlot = i;
					break;
				}
			}
			if (extSlot < 0) {
				ACTIVE.remove(key);
				return;
			}

			// Fire one projectile
			spawnSpray(world, pos, facing);

			// Drain durability; clear slot when the extinguisher breaks
			final DispenserBlockEntity beRef = be;
			final int slot = extSlot;
			extStack.hurtAndBreak(1, world, null,
					item -> beRef.setItem(slot, ItemStack.EMPTY));

			// Water hiss (pitch randomised so rapid shots don't sound flat)
			world.playSound(null, pos, SoundEvents.WATER_AMBIENT,
					SoundSource.BLOCKS, 1.0f, 0.8f + world.getRandom().nextFloat() * 0.4f);

			// Loop: re-queue for the next spray
			scheduleSpray(world, pos, facing, key);
		});
	}

	// Projectile spawning

	/** Spawns one spray projectile in the given direction with a bit of random spread. */
	private static void spawnSpray(ServerLevel world, BlockPos dispenserPos, Direction facing) {
		BlockPos frontPos = dispenserPos.relative(facing, 1);

		// Don't try to spawn inside solid geometry (blocks with empty collision - e.g. smoke detectors - are fine)
		if (!world.getBlockState(frontPos).getCollisionShape(world, frontPos, net.minecraft.world.phys.shapes.CollisionContext.empty()).isEmpty()) return;

		// Centre of the front block, shifted down for horizontal dispensers so the spray exits the mouth
		Vec3 spawnPos = Vec3.atCenterOf(frontPos);
		if (facing.getAxis() != Direction.Axis.Y) {
			spawnPos = spawnPos.add(0, HORIZONTAL_Y_OFFSET, 0);
		}

		SprayEntity spray = new SprayEntity(BurntModEntities.SPRAY, world);
		spray.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

		double spread = switch (facing) {
			case DOWN -> SPREAD_DOWN;
			case UP -> SPREAD_UP;
			default -> SPREAD_HORIZONTAL;
		};
		double dx = facing.getStepX() + (world.getRandom().nextDouble() - 0.5) * spread;
		double dy = facing.getStepY() + (world.getRandom().nextDouble() - 0.5) * spread;
		double dz = facing.getStepZ() + (world.getRandom().nextDouble() - 0.5) * spread;
		float speed = (facing.getAxis() == Direction.Axis.Y) ? SPEED_VERTICAL : SPEED;
		spray.shoot(dx, dy, dz, speed, 0);

		spray.setSilent(true);
		spray.pickup = AbstractArrow.Pickup.DISALLOWED;
		spray.setBaseDamage(0);

		world.addFreshEntity(spray);

		// Visual-only companion - same spread but independent random offset
		double vdx = facing.getStepX() + (world.getRandom().nextDouble() - 0.5) * spread;
		double vdy = facing.getStepY() + (world.getRandom().nextDouble() - 0.5) * spread;
		double vdz = facing.getStepZ() + (world.getRandom().nextDouble() - 0.5) * spread;
		VisualSprayEntity visual = new VisualSprayEntity(BurntModEntities.VISUAL_SPRAY, world);
		visual.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
		visual.shoot(vdx, vdy, vdz, speed, 0);
		visual.setSilent(true);
		visual.pickup = AbstractArrow.Pickup.DISALLOWED;
		world.addFreshEntity(visual);
	}
}
