package net.pixelbank.burnt.util;

import net.pixelbank.burnt.init.BurntModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.IdentityHashMap;
import java.util.Map;

import javax.annotation.Nullable;

// Right-click plain terracotta with a pickaxe to crack it, like axe-stripping a log
// Uses the same terracotta-to-cracked map as the fire pass in SootyProcedure

public final class BurntTerracottaCracking {

	private static Map<Block, Block> map;

	private static Map<Block, Block> map() {
		Map<Block, Block> m = map;
		if (m == null) {
			m = new IdentityHashMap<>();
			m.put(Blocks.TERRACOTTA, BurntModBlocks.CRACKED_TERRACOTTA);
			m.put(Blocks.WHITE_TERRACOTTA, BurntModBlocks.CRACKED_WHITE_TERRACOTTA);
			m.put(Blocks.ORANGE_TERRACOTTA, BurntModBlocks.CRACKED_ORANGE_TERRACOTTA);
			m.put(Blocks.MAGENTA_TERRACOTTA, BurntModBlocks.CRACKED_MAGENTA_TERRACOTTA);
			m.put(Blocks.LIGHT_BLUE_TERRACOTTA, BurntModBlocks.CRACKED_LIGHT_BLUE_TERRACOTTA);
			m.put(Blocks.YELLOW_TERRACOTTA, BurntModBlocks.CRACKED_YELLOW_TERRACOTTA);
			m.put(Blocks.LIME_TERRACOTTA, BurntModBlocks.CRACKED_LIME_TERRACOTTA);
			m.put(Blocks.PINK_TERRACOTTA, BurntModBlocks.CRACKED_PINK_TERRACOTTA);
			m.put(Blocks.GRAY_TERRACOTTA, BurntModBlocks.CRACKED_GREY_TERRACOTTA);
			m.put(Blocks.LIGHT_GRAY_TERRACOTTA, BurntModBlocks.CRACKED_LIGHT_GREY_TERRACOTTA);
			m.put(Blocks.CYAN_TERRACOTTA, BurntModBlocks.CRACKED_CYAN_TERRACOTTA);
			m.put(Blocks.PURPLE_TERRACOTTA, BurntModBlocks.CRACKED_PURPLE_TERRACOTTA);
			m.put(Blocks.BLUE_TERRACOTTA, BurntModBlocks.CRACKED_BLUE_TERRACOTTA);
			m.put(Blocks.BROWN_TERRACOTTA, BurntModBlocks.CRACKED_BROWN_TERRACOTTA);
			m.put(Blocks.GREEN_TERRACOTTA, BurntModBlocks.CRACKED_GREEN_TERRACOTTA);
			m.put(Blocks.RED_TERRACOTTA, BurntModBlocks.CRACKED_RED_TERRACOTTA);
			m.put(Blocks.BLACK_TERRACOTTA, BurntModBlocks.CRACKED_BLACK_TERRACOTTA);
			map = m;
		}
		return m;
	}

	// Cracked state for a plain terracotta block, or null if it isn't one.
	@Nullable
	public static BlockState getCracked(BlockState state) {
		Block to = map().get(state.getBlock());
		return to == null ? null : to.defaultBlockState();
	}

	// True for a pickaxe on plain terracotta, so the caller can consume the interaction
	// Each one gets a random facing as it's made, so a wall reads as hand-worn rather than tiled
	private static BlockState randomFacing(BlockState state, net.minecraft.util.RandomSource random) {
		if (!state.hasProperty(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING)) return state;
		return state.setValue(net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING, net.minecraft.core.Direction.from2DDataValue(random.nextInt(4)));
	}

	public static boolean tryCrack(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
		if (!(stack.getItem() instanceof PickaxeItem)) return false;
		if (!player.mayBuild()) return false;
		BlockState cracked = getCracked(level.getBlockState(pos));
		if (cracked == null) return false;
		if (!level.isClientSide()) {
			level.setBlock(pos, randomFacing(cracked, level.getRandom()), 11);
			level.playSound(null, pos, SoundEvents.STONE_HIT, SoundSource.BLOCKS, 1.0F, 0.85F);
			stack.hurtAndBreak(1, player, _p -> _p.broadcastBreakEvent(hand));
		}
		return true;
	}

	private BurntTerracottaCracking() {}
}
