package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.core.BlockPos;

public class BurntBarrelOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double slot = 0;
		if (!world.isClientSide()) {
			if ((blockstate.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp2 && blockstate.getValue(_getbp2)) == true) {
				{
					int _value = 1;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			} else if ((blockstate.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp5 && blockstate.getValue(_getbp5)) == false) {
				{
					int _value = 0;
					BlockPos _pos = BlockPos.containing(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
			}
			if ((blockstate.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _getbp8 && blockstate.getValue(_getbp8)) == true) {
				if (BurntBasicConfigConfiguration.BURN_BARRELS.get() == true) {
					slot = 0;
					for (int index0 = 0; index0 < 27; index0++) {
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_LOGS_THAT_BURN)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_LOG).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_BOOKSHELVES)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_BOOKSHELF).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_FENCE_GATES_WOODEN)
								&& !(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_NON_FLAMMABLE_WOOD)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_FENCEGATE).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_WOODEN_FENCES)
								&& !(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_NON_FLAMMABLE_WOOD)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_FENCE).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_LEAVES)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_LEAVES).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_PLANKS)
								&& !(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_NON_FLAMMABLE_WOOD)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_PLANKS).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_WOODEN_BUTTONS)
								&& !(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_NON_FLAMMABLE_WOOD)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_LEAVES).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_WOODEN_DOORS)
								&& !(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_NON_FLAMMABLE_WOOD)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_DOOR).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_WOODEN_PRESSURE_PLATES)
								&& !(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_NON_FLAMMABLE_WOOD)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_PRESSURE_PLATE).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_WOODEN_SLABS)
								&& !(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_NON_FLAMMABLE_WOOD)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_SLAB).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_WOODEN_STAIRS)
								&& !(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_NON_FLAMMABLE_WOOD)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_STAIRS).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_WOODEN_TRAPDOORS)
								&& !(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_NON_FLAMMABLE_WOOD)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_TRAPDOOR).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_WOOL)
								&& !(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).copy()).is(BurntTags.ITEM_MC_NON_FLAMMABLE_WOOD)) {
							if (world.getBlockEntity(BlockPos.containing(x, y, z)) instanceof Container _container) {
								ItemStack _setstack = new ItemStack(BurntModBlocks.BURNT_WOOL).copy();
								_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) slot).getCount());
								_container.setItem((int) slot, _setstack);
							}
						}
						slot = slot + 1;
					}
					{
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("hot") instanceof BooleanProperty _booleanProp)
							world.setBlock(_pos, _bs.setValue(_booleanProp, false), 3);
					}
				}
			}
		}
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world.getBlockEntity(pos) instanceof Container container) {
			return container.getItem(slot);
		}
		return ItemStack.EMPTY;
	}
}
