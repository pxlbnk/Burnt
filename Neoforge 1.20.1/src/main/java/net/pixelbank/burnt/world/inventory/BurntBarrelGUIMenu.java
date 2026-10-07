package net.pixelbank.burnt.world.inventory;

import net.pixelbank.burnt.procedures.BurntBarrelGUIClosedProcedure;
import net.pixelbank.burnt.init.BurntModMenus;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.block.entity.BurntBarrelBlockEntity;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BurntBarrelGUIMenu extends AbstractContainerMenu implements BurntModMenus.MenuAccessor {

	public final Map<String, Object> menuState = new HashMap<>() {
		@Override
		public Object put(String key, Object value) {
			if (!this.containsKey(key) && this.size() >= 28)
				return null;
			return super.put(key, value);
		}
	};

	public final Level world;
	public final Player entity;
	public int x, y, z;
	private final ContainerLevelAccess access;
	private final Container internal;
	private final Map<Integer, Slot> customSlots = new HashMap<>();

	/** Client-side constructor - used by the MenuType factory. */
	public BurntBarrelGUIMenu(int id, Inventory inv) {
		super(BurntModMenus.BURNT_BARREL_GUI, id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.internal = new SimpleContainer(27);
		this.access = ContainerLevelAccess.NULL;
		initSlots(inv);
	}

	/** Server-side constructor - called directly from BurntBarrelBlockEntity.createMenu(). */
	public BurntBarrelGUIMenu(int id, Inventory inv, BurntBarrelBlockEntity be) {
		super(BurntModMenus.BURNT_BARREL_GUI, id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.internal = be;
		this.x = be.getBlockPos().getX();
		this.y = be.getBlockPos().getY();
		this.z = be.getBlockPos().getZ();
		this.access = ContainerLevelAccess.create(be.getLevel(), be.getBlockPos());
		initSlots(inv);
	}

	private static final int[][] SLOT_GRID = {
		{8,17},{26,17},{44,17},{62,17},{80,17},{98,17},{116,17},{134,17},{152,17},
		{8,35},{26,35},{44,35},{62,35},{80,35},{98,35},{116,35},{134,35},{152,35},
		{8,53},{26,53},{44,53},{62,53},{80,53},{98,53},{116,53},{134,53},{152,53}
	};

	private void initSlots(Inventory inv) {
		for (int i = 0; i < 27; i++) {
			customSlots.put(i, this.addSlot(new Slot(internal, i, SLOT_GRID[i][0], SLOT_GRID[i][1])));
		}
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 8 + sj * 18, 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 8 + si * 18, 142));
	}

	@Override
	public boolean stillValid(Player player) {
		return AbstractContainerMenu.stillValid(this.access, player, BurntModBlocks.BURNT_BARREL);
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 27) {
				if (!this.moveItemStackTo(itemstack1, 27, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 27, false)) {
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		BurntBarrelGUIClosedProcedure.execute(world, x, y, z);
	}

	@Override
	public Map<Integer, Slot> getSlots() {
		return Collections.unmodifiableMap(customSlots);
	}

	@Override
	public Map<String, Object> getMenuState() {
		return menuState;
	}
}
