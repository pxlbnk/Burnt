package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.init.BurntModItems;
import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import net.minecraft.resources.ResourceLocation;

public class SootBrushProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Object event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double bx = 0;
		double by = 0;
		double bz = 0;
		if (!world.isClientSide()) {
			if ((entity instanceof LivingEntity _entUseItem1 ? _entUseItem1.getUseItem() : ItemStack.EMPTY).getItem() == Items.BRUSH) {
				if (Math.random() <= 0.1) {
					bx = entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4.75)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX();
					by = entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4.75)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getY();
					bz = entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4.75)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ();
					if ((world.getBlockState(BlockPos.containing(bx, by, bz))).is(BurntTags.BLOCK_SOOTY)) {
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STONE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STONE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STONE_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STONE_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STONE_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STONE_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ANDESITE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ANDESITE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ANDESITE_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ANDESITE_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ANDESITE_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ANDESITE_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ANDESITE_WALL) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ANDESITE_WALL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DIORITE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DIORITE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DIORITE_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DIORITE_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DIORITE_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DIORITE_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DIORITE_WALL) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DIORITE_WALL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_GRANITE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.GRANITE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_GRANITE_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.GRANITE_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_GRANITE_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.GRANITE_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_GRANITE_WALL) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.GRANITE_WALL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_COBBLESTONE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.COBBLESTONE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_COBBLESTONE_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.COBBLESTONE_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_COBBLESTONE_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.COBBLESTONE_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_COBBLESTONE_WALL) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.COBBLESTONE_WALL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MOSSY_COBBLESTONE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MOSSY_COBBLESTONE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MOSSY_COBBLESTONE_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MOSSY_COBBLESTONE_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MOSSY_COBBLESTONE_WALL) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MOSSY_COBBLESTONE_WALL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STONE_BRICKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STONE_BRICKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_CHISELED_STONE_BRICKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.CHISELED_STONE_BRICKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STONE_BRICK_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STONE_BRICK_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STONE_BRICK_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STONE_BRICK_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STONE_BRICK_WALL) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STONE_BRICK_WALL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MOSSY_STONE_BRICKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MOSSY_STONE_BRICKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MOSSY_STONE_BRICK_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MOSSY_STONE_BRICK_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MOSSY_STONE_BRICK_WALL) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MOSSY_STONE_BRICK_WALL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BRICKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BRICKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BRICK_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BRICK_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BRICK_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BRICK_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BRICK_WALL) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BRICK_WALL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MUD_BRICKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MUD_BRICKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MUD_BRICK_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MUD_BRICK_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MUD_BRICK_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MUD_BRICK_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MUD_BRICK_WALL) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MUD_BRICK_WALL.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_OAK_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.OAK_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_SPRUCE_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.SPRUCE_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BIRCH_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BIRCH_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DARK_OAK_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.JUNGLE_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MANGROVE_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MANGROVE_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ACACIA_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ACACIA_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_CHERRY_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.CHERRY_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_PALE_OAK_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = net.pixelbank.burnt.util.BurntVanilla.stateOf("pale_oak_log");
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_OAK_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.OAK_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_SPRUCE_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.SPRUCE_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BIRCH_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BIRCH_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DARK_OAK_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.JUNGLE_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MANGROVE_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MANGROVE_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ACACIA_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ACACIA_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_CHERRY_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.CHERRY_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_PALE_OAK_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = net.pixelbank.burnt.util.BurntVanilla.stateOf("pale_oak_wood");
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STRIPPED_OAK_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_OAK_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STRIPPED_SPRUCE_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STRIPPED_BIRCH_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_BIRCH_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STRIPPED_DARK_OAK_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_DARK_OAK_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STRIPPED_JUNGLE_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_JUNGLE_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STRIPPED_MANGROVE_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_MANGROVE_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STRIPPED_ACACIA_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_ACACIA_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STRIPPED_CHERRY_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_CHERRY_LOG.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_STRIPPED_PALE_OAK_LOG) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = net.pixelbank.burnt.util.BurntVanilla.stateOf("stripped_pale_oak_log");
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_OAK_STRIPPED_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_OAK_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_SPRUCE_STRIPPED_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_SPRUCE_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BIRCH_STRIPPED_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_BIRCH_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_STRIPPED_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_DARK_OAK_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_STRIPPED_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_JUNGLE_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MANGROVE_STRIPPED_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_MANGROVE_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ACACIA_STRIPPED_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_ACACIA_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_CHERRY_STRIPPED_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.STRIPPED_CHERRY_WOOD.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_PALE_OAK_STRIPPED_WOOD) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = net.pixelbank.burnt.util.BurntVanilla.stateOf("stripped_pale_oak_wood");
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_OAK_PLANKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.OAK_PLANKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_SPRUCE_PLANKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.SPRUCE_PLANKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BIRCH_PLANKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BIRCH_PLANKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_PLANKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DARK_OAK_PLANKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_PLANKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.JUNGLE_PLANKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MANGROVE_PLANKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MANGROVE_PLANKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ACACIA_PLANKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ACACIA_PLANKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_CHERRY_PLANKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.CHERRY_PLANKS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_PALE_OAK_PLANKS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = net.pixelbank.burnt.util.BurntVanilla.stateOf("pale_oak_planks");
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_OAK_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.OAK_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_SPRUCE_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.SPRUCE_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BIRCH_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BIRCH_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DARK_OAK_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.JUNGLE_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MANGROVE_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MANGROVE_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ACACIA_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ACACIA_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_CHERRY_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.CHERRY_STAIRS.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_PALE_OAK_STAIRS) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = net.pixelbank.burnt.util.BurntVanilla.stateOf("pale_oak_stairs");
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_OAK_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.OAK_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_SPRUCE_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.SPRUCE_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BIRCH_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BIRCH_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DARK_OAK_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.JUNGLE_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MANGROVE_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MANGROVE_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ACACIA_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ACACIA_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_CHERRY_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.CHERRY_SLAB.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_PALE_OAK_SLAB) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = net.pixelbank.burnt.util.BurntVanilla.stateOf("pale_oak_slab");
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_OAK_FENCE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.OAK_FENCE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_SPRUCE_FENCE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.SPRUCE_FENCE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BIRCH_FENCE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BIRCH_FENCE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_FENCE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DARK_OAK_FENCE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_FENCE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.JUNGLE_FENCE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_MANGROVE_FENCE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MANGROVE_FENCE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ACACIA_FENCE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ACACIA_FENCE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_CHERRY_FENCE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.CHERRY_FENCE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_PALE_OAK_FENCE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = net.pixelbank.burnt.util.BurntVanilla.stateOf("pale_oak_fence");
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_OAK_FENCE_GATE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.OAK_FENCE_GATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_SPRUCE_FENCE_GATE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.SPRUCE_FENCE_GATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_BIRCH_FENCE_GATE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.BIRCH_FENCE_GATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_DARK_OAK_FENCE_GATE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.DARK_OAK_FENCE_GATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_JUNGLE_FENCE_GATE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.JUNGLE_FENCE_GATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == Blocks.MANGROVE_FENCE_GATE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.MANGROVE_FENCE_GATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_ACACIA_FENCE_GATE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.ACACIA_FENCE_GATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_CHERRY_FENCE_GATE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = Blocks.CHERRY_FENCE_GATE.defaultBlockState();
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						} else if ((world.getBlockState(BlockPos.containing(bx, by, bz))).getBlock() == BurntModBlocks.SOOTY_PALE_OAK_FENCE_GATE) {
							{
								BlockPos _bp = BlockPos.containing(bx, by, bz);
								BlockState _bs = net.pixelbank.burnt.util.BurntVanilla.stateOf("pale_oak_fence_gate");
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(bx, by, bz), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.brush.brushing.sand.complete")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(bx, by, bz, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.brush.brushing.sand.complete")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						world.levelEvent(2001, BlockPos.containing(bx, by, bz), Block.getId(BurntModBlocks.SOOT_BLOCK.defaultBlockState()));
						if (getEntityGameType(entity) == GameType.SURVIVAL) {
							if (world instanceof ServerLevel _level) {
								ItemEntity entityToSpawn = new ItemEntity(_level,
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4.75)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
												.getX()),
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4.75)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
												.getY()),
										(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4.75)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
												.getZ()),
										new ItemStack(BurntModItems.SOOT));
								entityToSpawn.setPickUpDelay(10);
								_level.addFreshEntity(entityToSpawn);
							}
							if (Math.random() == 0.1) {
								if (world instanceof ServerLevel _level) {
									ItemEntity entityToSpawn = new ItemEntity(_level,
											(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4.75)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
													.getX()),
											(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4.75)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
													.getY()),
											(entity.level().clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(4.75)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos()
													.getZ()),
											new ItemStack(BurntModItems.SOOT));
									entityToSpawn.setPickUpDelay(10);
									_level.addFreshEntity(entityToSpawn);
								}
							}
						}
					}
				}
			}
		}
	}

	private static GameType getEntityGameType(Entity entity) {
		if (entity instanceof ServerPlayer serverPlayer) {
			return serverPlayer.gameMode.getGameModeForPlayer();
		} else if (entity instanceof Player player && player.level().isClientSide()) {
			PlayerInfo playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
			if (playerInfo != null)
				return playerInfo.getGameMode();
		}
		return null;
	}
}