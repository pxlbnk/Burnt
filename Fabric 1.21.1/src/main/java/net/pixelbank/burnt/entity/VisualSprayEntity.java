package net.pixelbank.burnt.entity;

import net.pixelbank.burnt.procedures.SprayWhileProjectileFlyingTickProcedure;
import net.pixelbank.burnt.init.BurntModItems;

import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EntityType;

/** Same as SprayEntity but visual only - no extinguishing, no damage. */
public class VisualSprayEntity extends AbstractArrow implements ItemSupplier {

	public VisualSprayEntity(EntityType<? extends VisualSprayEntity> type, Level world) {
		super(type, world);
	}

	// No gameplay effects on any hit

	@Override
	protected void onHitBlock(BlockHitResult blockHitResult) {
		// Visual only - discard silently, no ExtinguishBlockProcedure
		this.discard();
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		// Visual only - no damage, no ExtinguishEntityProcedure
		this.discard();
	}

	@Override
	public void playerTouch(Player entity) {
		// Visual only - no SprayProjectileHitsPlayerProcedure
	}

	// Same visual tick as the real spray

	@Override
	public void tick() {
		super.tick();
		SprayWhileProjectileFlyingTickProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
		if (this.inGround)
			this.discard();
	}

	// Item representation (reuses extinguisher spray item)

	@Override
	public ItemStack getItem() {
		return new ItemStack(BurntModItems.EXTINGUISHER_SPRAY);
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(BurntModItems.EXTINGUISHER_SPRAY);
	}
}
