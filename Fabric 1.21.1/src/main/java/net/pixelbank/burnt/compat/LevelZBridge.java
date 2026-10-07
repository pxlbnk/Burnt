package net.pixelbank.burnt.compat;

import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.lang.reflect.Method;
import java.util.List;

// Optional bridge to LevelZ (modid "levelz") for skill-gated flint and steel use
// LevelZ misses the item gate when used on a block, and Burnt registers before it, so both are checked here
// Two incompatible LevelZ generations - 2.x LevelManager, 1.x PlayerStatsManager - resolved by reflection
// Any lookup failure returns false, so without LevelZ nothing is blocked

public final class LevelZBridge {

	private static final boolean PRESENT = FabricLoader.getInstance().isModLoaded("levelz");
	private static volatile boolean resolved = false;

	// 2.x
	private static Method getLevelManager;
	private static Method hasRequiredItemLevel;
	private static Method hasRequiredBlockLevel;

	// 1.x
	private static Method playerLevelisHighEnough;
	private static Method getList;

	// LevelZ names the skill and level in its own refusal, but we refuse first, so rebuild it from the list
	private static String pendingKey;
	private static Object pendingLevel;

	public static boolean isActive() {
		return PRESENT;
	}

	// LevelZ renamed this key between generations, so the wrong one shows the player a raw key
	// Deliberately does NOT trigger resolution - resolving with no Player would latch onto 1.x and break on 1.21.1
	public static String lockedMessageKey() {
		return (playerLevelisHighEnough != null && getLevelManager == null)
				? "item.levelz.locked.tooltip"        // 1.x
				: "restriction.levelz.locked.tooltip"; // 2.x
	}

	// True if LevelZ would deny this player the use of this item (skill level too low).
	public static boolean blocksItem(Player player, Item item) {
		if (!ready(player) || item == null)
			return false;
		if (getLevelManager != null)
			return deniedModern(player, hasRequiredItemLevel, item);
		return deniedLegacy(player, BuiltInRegistries.ITEM.getKey(item).toString(), "minecraft:custom_item");
	}

	// True if LevelZ would deny this player interaction with this block.
	public static boolean blocksBlock(Player player, Block block) {
		if (!ready(player) || block == null)
			return false;
		if (getLevelManager != null)
			return deniedModern(player, hasRequiredBlockLevel, block);
		return deniedLegacy(player, BuiltInRegistries.BLOCK.getKey(block).toString(), "minecraft:custom_block");
	}

	private static boolean ready(Player player) {
		if (!PRESENT || player == null)
			return false;
		if (!resolved)
			resolve(player);
		return getLevelManager != null || playerLevelisHighEnough != null;
	}

	// 2.x: ask the player's LevelManager and invert - false means not allowed.
	private static boolean deniedModern(Player player, Method check, Object subject) {
		if (check == null)
			return false;
		try {
			Object lm = getLevelManager.invoke(player);
			if (lm == null)
				return false;
			Object ok = check.invoke(lm, subject);
			return (ok instanceof Boolean b) && !b;
		} catch (Throwable ignored) {
			return false;
		}
	}

	// 1.x: hand LevelZ its own restriction list and let its static check answer
	// Common items get a dedicated list, not customItemList - reading only the custom list always answered "allowed"
	// A dedicated list IS the restriction so LevelZ passes null, the custom list is id-keyed and wants the id
	private static boolean deniedLegacy(Player player, String id, String customListName) {
		if (playerLevelisHighEnough == null || getList == null || id == null)
			return false;
		try {
			Object raw = getList.invoke(null, id);
			String arg = null;
			if (!(raw instanceof List<?> list) || list.isEmpty()) {
				raw = getList.invoke(null, customListName);
				arg = id;
				if (!(raw instanceof List<?> fallback) || fallback.isEmpty())
					return false;
			}
			Object ok = playerLevelisHighEnough.invoke(null, player, raw, arg, true);
			boolean denied = (ok instanceof Boolean b) && !b;
			if (denied)
				capturePending((List<?>) raw, arg, id);
			return denied;
		} catch (Throwable ignored) {
			return false;
		}
	}

	private static synchronized void resolve(Player player) {
		if (resolved)
			return;
		resolved = true;
		// 2.x first - when it is present it is the better-scoped API.
		if (player != null) {
			try {
				getLevelManager = player.getClass().getMethod("getLevelManager");
				Class<?> lm = getLevelManager.getReturnType();
				// Resolved independently so a rename of one doesn't disable the other.
				try {
					hasRequiredItemLevel = lm.getMethod("hasRequiredItemLevel", Item.class);
				} catch (Throwable ignored) {
					hasRequiredItemLevel = null;
				}
				try {
					hasRequiredBlockLevel = lm.getMethod("hasRequiredBlockLevel", Block.class);
				} catch (Throwable ignored) {
					hasRequiredBlockLevel = null;
				}
				if (hasRequiredItemLevel != null || hasRequiredBlockLevel != null)
					return;
			} catch (Throwable ignored) {
				// not 2.x - fall through to the 1.x lookup
			}
			getLevelManager = null;
		}
		try {
			Class<?> stats = Class.forName("net.levelz.stats.PlayerStatsManager");
			playerLevelisHighEnough = stats.getMethod("playerLevelisHighEnough",
					Player.class, List.class, String.class, boolean.class);
			Class<?> lists = Class.forName("net.levelz.data.LevelLists");
			getList = lists.getMethod("getList", String.class);
		} catch (Throwable ignored) {
			playerLevelisHighEnough = null;
			getList = null;
		}
	}

	// Skill and level sit at 0 and 1 in a dedicated list, or after the id in the custom list
	// Key shape matches LevelZ's own concat: item.levelz.<skill>.tooltip
	private static void capturePending(List<?> list, String arg, String id) {
		pendingKey = null;
		pendingLevel = null;
		try {
			int skillIndex = 0;
			if (arg != null) {
				int at = list.indexOf(id);
				if (at < 0)
					return;
				skillIndex = at + 1;
			}
			if (skillIndex + 1 >= list.size())
				return;
			pendingKey = "item.levelz." + list.get(skillIndex) + ".tooltip";
			pendingLevel = list.get(skillIndex + 1);
		} catch (Throwable ignored) {
			pendingKey = null;
			pendingLevel = null;
		}
	}

	// Message for a refusal, falling back to plain "Locked" when the requirement can't be read
	public static net.minecraft.network.chat.Component lockedMessage() {
		String key = pendingKey;
		Object level = pendingLevel;
		if (key != null && level != null)
			return net.minecraft.network.chat.Component.translatable(key, level)
					.withStyle(net.minecraft.ChatFormatting.RED);
		return net.minecraft.network.chat.Component.translatable(lockedMessageKey())
				.withStyle(net.minecraft.ChatFormatting.RED);
	}

	private LevelZBridge() {}

}
