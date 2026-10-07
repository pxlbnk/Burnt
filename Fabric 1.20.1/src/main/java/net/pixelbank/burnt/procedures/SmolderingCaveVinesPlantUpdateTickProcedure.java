package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModItems;
import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class SmolderingCaveVinesPlantUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!world.isClientSide()) {
			if (!(world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BurntTags.BLOCK_MC_CAVE_VINES) || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.AIR) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.SMOLDERING_CAVE_VINES.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.CAVE_VINES) {
				{
					BlockPos _bp = BlockPos.containing(x, y + 1, z);
					BlockState _bs = Blocks.CAVE_VINES_PLANT.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if (Math.random() <= 0.2) {
				if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.CAVE_VINES_PLANT) {
					if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == (Blocks.CAVE_VINES_PLANT.getStateDefinition().getProperty("berries") instanceof BooleanProperty _withbp12
							? Blocks.CAVE_VINES_PLANT.defaultBlockState().setValue(_withbp12, true)
							: Blocks.CAVE_VINES_PLANT.defaultBlockState()).getBlock()) {
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, (y + 1), z, new ItemStack(BurntModItems.EMBER_BERRIES));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
					}
					{
						BlockPos _bp = BlockPos.containing(x, y + 1, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
			if (Math.random() <= 0.06) {
				if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.CAVE_VINES) {
					{
						BlockPos _bp = BlockPos.containing(x, y - 1, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_CAVE_VINES.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				} else if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.CAVE_VINES_PLANT) {
					if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == (Blocks.CAVE_VINES_PLANT.getStateDefinition().getProperty("berries") instanceof BooleanProperty _withbp22
							? Blocks.CAVE_VINES_PLANT.defaultBlockState().setValue(_withbp22, true)
							: Blocks.CAVE_VINES_PLANT.defaultBlockState()).getBlock()) {
						if (world instanceof ServerLevel _level) {
							ItemEntity entityToSpawn = new ItemEntity(_level, x, (y - 1), z, new ItemStack(BurntModItems.EMBER_BERRIES));
							entityToSpawn.setPickUpDelay(10);
							_level.addFreshEntity(entityToSpawn);
						}
					}
					{
						BlockPos _bp = BlockPos.containing(x, y - 1, z);
						BlockState _bs = BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						world.setBlock(_bp, _bs, 3);
					}
				}
			}
			// Horizontal spread to adjacent vine columns
			if (Math.random() <= 0.03) {
				if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.CAVE_VINES_PLANT) {
					world.setBlock(BlockPos.containing(x + 1, y, z), copyProps(world.getBlockState(BlockPos.containing(x + 1, y, z)), BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT.defaultBlockState()), 3);
				} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.CAVE_VINES) {
					world.setBlock(BlockPos.containing(x + 1, y, z), copyProps(world.getBlockState(BlockPos.containing(x + 1, y, z)), BurntModBlocks.SMOLDERING_CAVE_VINES.defaultBlockState()), 3);
				}
			}
			if (Math.random() <= 0.03) {
				if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.CAVE_VINES_PLANT) {
					world.setBlock(BlockPos.containing(x - 1, y, z), copyProps(world.getBlockState(BlockPos.containing(x - 1, y, z)), BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT.defaultBlockState()), 3);
				} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.CAVE_VINES) {
					world.setBlock(BlockPos.containing(x - 1, y, z), copyProps(world.getBlockState(BlockPos.containing(x - 1, y, z)), BurntModBlocks.SMOLDERING_CAVE_VINES.defaultBlockState()), 3);
				}
			}
			if (Math.random() <= 0.03) {
				if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.CAVE_VINES_PLANT) {
					world.setBlock(BlockPos.containing(x, y, z + 1), copyProps(world.getBlockState(BlockPos.containing(x, y, z + 1)), BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT.defaultBlockState()), 3);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.CAVE_VINES) {
					world.setBlock(BlockPos.containing(x, y, z + 1), copyProps(world.getBlockState(BlockPos.containing(x, y, z + 1)), BurntModBlocks.SMOLDERING_CAVE_VINES.defaultBlockState()), 3);
				}
			}
			if (Math.random() <= 0.03) {
				if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.CAVE_VINES_PLANT) {
					world.setBlock(BlockPos.containing(x, y, z - 1), copyProps(world.getBlockState(BlockPos.containing(x, y, z - 1)), BurntModBlocks.SMOLDERING_CAVE_VINES_PLANT.defaultBlockState()), 3);
				} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.CAVE_VINES) {
					world.setBlock(BlockPos.containing(x, y, z - 1), copyProps(world.getBlockState(BlockPos.containing(x, y, z - 1)), BurntModBlocks.SMOLDERING_CAVE_VINES.defaultBlockState()), 3);
				}
			}
			if (Math.random() <= 0.006) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNING_CAVE_VINES_PLANT.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}

	private static BlockState copyProps(BlockState src, BlockState dest) {
		for (Property<?> prop : src.getProperties()) {
			Property destProp = dest.getBlock().getStateDefinition().getProperty(prop.getName());
			if (destProp != null && dest.getValue(destProp) != null)
				try { dest = dest.setValue(destProp, src.getValue(prop)); } catch (Exception ignored) {}
		}
		return dest;
	}
}