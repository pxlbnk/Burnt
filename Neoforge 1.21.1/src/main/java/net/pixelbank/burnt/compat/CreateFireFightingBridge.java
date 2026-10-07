package net.pixelbank.burnt.compat;

import net.neoforged.fml.ModList;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.procedures.ExtinguishBlockProcedure;
import net.pixelbank.burnt.util.BurntTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// Optional bridge to Create: FireFightingAdd (modid "createfirefightingadd")
// Registers a nozzle-spray interaction so a water-like spray hitting Burnt fire or a smoldering
// block runs ExtinguishBlockProcedure there - the same result as Burnt's own extinguisher
// FIRESTARTER is noCollission, so a spray ray reports the block below as the hit - we test above too
// All reflection and a dynamic proxy, so it no-ops without the addon. NeoForge 1.21.1 only
public final class CreateFireFightingBridge {

	private static final boolean PRESENT = ModList.get().isLoaded("createfirefightingadd");

	public static boolean isActive() {
		return PRESENT;
	}

	public static void init() {
		if (!PRESENT)
			return;
		try {
			final String pkg = "com.mikoalopex.createfirefightingadd.api.nozzle.";
			Class<?> iface = Class.forName(pkg + "NozzleSprayBlockInteraction");
			Class<?> registry = Class.forName(pkg + "NozzleSprayInteractionRegistry");
			Class<?> ctxClass = Class.forName(pkg + "NozzleSprayHitContext");

			final Method mLevel = ctxClass.getMethod("level");
			final Method mPos = ctxClass.getMethod("pos");
			final Method mState = ctxClass.getMethod("state");
			final Method mWaterLike = ctxClass.getMethod("isWaterLike");

			InvocationHandler handler = (proxy, method, args) -> {
				switch (method.getName()) {
					case "shouldReceive": {
						Object ctx = args[0];
						if (!((Boolean) mWaterLike.invoke(ctx)))
							return Boolean.FALSE;
						Level level = (Level) mLevel.invoke(ctx);
						BlockPos pos = (BlockPos) mPos.invoke(ctx);
						BlockState state = (BlockState) mState.invoke(ctx);
						return Boolean.valueOf(isBurntFire(state) || isBurntFire(level.getBlockState(pos.above())));
					}
					case "onHit": {
						Object ctx = args[0];
						Level level = (Level) mLevel.invoke(ctx);
						BlockPos pos = (BlockPos) mPos.invoke(ctx);
						if (level != null && !level.isClientSide())
							ExtinguishBlockProcedure.execute(level, pos.getX(), pos.getY(), pos.getZ(), false);
						return null;
					}
					case "toString":
						return "BurntNozzleSprayInteraction";
					case "hashCode":
						return System.identityHashCode(proxy);
					case "equals":
						return proxy == args[0];
					default:
						return null;
				}
			};

			Object interaction = Proxy.newProxyInstance(iface.getClassLoader(), new Class[] { iface }, handler);
			registry.getMethod("register", iface).invoke(null, interaction);
			BurntMod.LOGGER.info("[Burnt] Create: FireFightingAdd detected - nozzle spray now extinguishes Burnt fire.");
		} catch (Throwable t) {
			BurntMod.LOGGER.warn("[Burnt] Create: FireFightingAdd present but spray integration failed (API changed?): {}", t.toString());
		}
	}

	private static boolean isBurntFire(BlockState state) {
		return state.is(BurntTags.BLOCK_FIRE) || state.is(BurntTags.BLOCK_ON_FIRE);
	}

	private CreateFireFightingBridge() {}
}
