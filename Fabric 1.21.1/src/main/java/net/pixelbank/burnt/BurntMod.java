package net.pixelbank.burnt;

import net.pixelbank.burnt.command.BurntCommandCommand;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.init.BurntModItems;
import net.pixelbank.burnt.init.BurntModSounds;
import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.pixelbank.burnt.init.BurntModBlockEntities;
import net.pixelbank.burnt.init.BurntModMenus;
import net.pixelbank.burnt.init.BurntModTabs;
import net.pixelbank.burnt.init.BurntModEntities;
import net.pixelbank.burnt.network.BurntModVariables.MapVariables;
import net.pixelbank.burnt.network.FireSkyPayload;
import net.pixelbank.burnt.network.SmokeFogPayload;
import net.pixelbank.burnt.network.MapVarsSyncPayload;
import net.pixelbank.burnt.network.MenuStateUpdateMessage;
import net.pixelbank.burnt.ai.FleeFireGoal;
import net.pixelbank.burnt.ai.GolemExtinguishGoal; // IRON GOLEM FIRE FIGHTING
import net.pixelbank.burnt.procedures.ApplyBowEffectsToArrowProcedure;
import net.pixelbank.burnt.procedures.CoalRightClickProcedure;
import net.pixelbank.burnt.procedures.EmberRightClickProcedure;
import net.pixelbank.burnt.procedures.StartFlintBurnProcedure;
import net.pixelbank.burnt.procedures.FlameCountResetProcedure;
import net.pixelbank.burnt.procedures.PlumeAgeCleanupProcedure;
import net.pixelbank.burnt.procedures.PlumeConfigWatcherServerProcedure;
import net.pixelbank.burnt.procedures.PlumeRiseTickProcedure;
import net.pixelbank.burnt.procedures.SootBrushProcedure;
import net.pixelbank.burnt.BurntLightningRodGuard;
import net.pixelbank.burnt.procedures.WindWakerProcedure;
import net.pixelbank.burnt.util.PlumeManager;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.pixelbank.burnt.compat.BurnMappingLoader;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents; // FLAME ARROW CREEPER
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.pixelbank.burnt.item.ExtinguisherDispenserBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.ArrayList;
import java.util.List;

public class BurntMod implements ModInitializer {
	public static final String MODID = "burnt";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	// Set by /burnt extinguishAll - every burning block finishes burning, resets after 60 seconds
	public static volatile boolean killFire = false;

	// Set by /burnt extinguishAll for 15 seconds - treats the whole world as raining
	public static volatile boolean forceRain = false;

	// Deferred server-tick work queue - wired into ServerTickEvents.END_SERVER_TICK in Phase 5
	private static final ConcurrentLinkedQueue<DelayedWork> WORK_QUEUE = new ConcurrentLinkedQueue<>();

	@Override
	public void onInitialize() {
		// Chunk-claim mods: keep Burnt fire out of claimed land.
		net.pixelbank.burnt.compat.ClaimBridge.init();

		// Phase 4: config - must load before any procedure reads a config value
		BurntBasicConfigConfiguration.register();

		// Dark oak burns slowly and self-contained, so fire dies out after 2-3 trees
		// Vanilla defaults: logs 5/5, leaves 60/30
		FlammableBlockRegistry reg = FlammableBlockRegistry.getDefaultInstance();
		reg.add(Blocks.DARK_OAK_LOG,            2, 5);
		reg.add(Blocks.DARK_OAK_WOOD,           2, 5);
		reg.add(Blocks.STRIPPED_DARK_OAK_LOG,   2, 5);
		reg.add(Blocks.STRIPPED_DARK_OAK_WOOD,  2, 5);
		reg.add(Blocks.DARK_OAK_LEAVES,         8, 15);

		// Acacia always spreads - resinous wood and dry canopy burn hard in savanna
		// Max is 300, which guarantees spread on every check
		reg.add(Blocks.ACACIA_LOG,              300, 5);
		reg.add(Blocks.ACACIA_WOOD,             300, 5);
		reg.add(Blocks.STRIPPED_ACACIA_LOG,     300, 5);
		reg.add(Blocks.STRIPPED_ACACIA_WOOD,    300, 5);
		reg.add(Blocks.ACACIA_LEAVES,           300, 30);

		// Phase 2: registries - order matters (blocks before items, items before tabs)
		BurntModSounds.register();
		BurntModParticleTypes.register();
		BurntModBlocks.register();
		// Wire axe-stripping for burnt logs/wood (Fabric StrippableBlockRegistry).
		net.pixelbank.burnt.util.BurntStripping.registerFabric();
		BurntModItems.register();

		// Dispenser + extinguisher = water cannon / sprinkler system
		DispenserBlock.registerBehavior(BurntModItems.EXTINGUISHER, new ExtinguisherDispenserBehavior());

		BurntModBlockEntities.register();
		BurntModMenus.register();
		BurntModTabs.register();
		BurntModEntities.register();

		// Datapack-driven burn mappings - reload on each /reload or world load
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new BurnMappingLoader());

		// Built-in compat resourcepack (BYG, BiomesAPlenty) - always enabled
		ResourceManagerHelper.registerBuiltinResourcePack(
				ResourceLocation.parse("burnt:compat"),
				FabricLoader.getInstance().getModContainer("burnt").get(),
				ResourcePackActivationType.ALWAYS_ENABLED
		);

		// Phase 5 + 6: work queue + world-tick procedures + MapVars dirty sync
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			BurntMod.tickWorkQueue();
			if (server.overworld() != null) BurntLightningRodGuard.cleanupStale(server.overworld().getGameTime());
			if (server.overworld() != null) {
				// Wind simulation - run every 4 ticks to reduce per-tick overhead
				if (server.getTickCount() % 4 == 0) {
					WindWakerProcedure.execute(server.overworld());
				}

				// MapVars dirty sync
				MapVariables mv = MapVariables.get(server.overworld());
				if (mv._syncDirty) {
					mv._syncDirty = false;
					MapVarsSyncPayload payload = new MapVarsSyncPayload(mv);
					for (ServerPlayer p : server.getPlayerList().getPlayers()) {
						ServerPlayNetworking.send(p, payload);
					}
				}
			}
			// Per-level procedures (flame count decay, smoke plumes, config watcher)
			for (ServerLevel level : server.getAllLevels()) {
				FlameCountResetProcedure.execute(level);
				PlumeRiseTickProcedure.execute(level);
				PlumeAgeCleanupProcedure.execute(level);
				PlumeConfigWatcherServerProcedure.execute(level);
				// SootBrushProcedure guards internally, so calling it every tick is fine
				for (ServerPlayer player : level.players()) {
					SootBrushProcedure.execute(level, player);
				}
			}
		});

		// Track entity-placed fire for BurntLavaFireBlocker
		// Also handles flint right-click conversions, and a pickaxe cracking plain terracotta
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			// Returning SUCCESS here would consume the interaction before LevelZ can veto it
			// PASS lets LevelZ refuse it in its own words
			if (world instanceof Level pxLevel
					&& !net.pixelbank.burnt.compat.LevelZBridge.blocksBlock(player, pxLevel.getBlockState(hitResult.getBlockPos()).getBlock())
					&& net.pixelbank.burnt.util.BurntTerracottaCracking.tryCrack(pxLevel, hitResult.getBlockPos(), player, hand, player.getItemInHand(hand)))
				return InteractionResult.SUCCESS;
			return InteractionResult.PASS;
		});

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			var stack = player.getItemInHand(hand);
			if (world instanceof Level level
					&& (stack.is(Items.FLINT_AND_STEEL) || stack.is(Items.FIRE_CHARGE))) {
				// When the mod is disabled, skip all mod behavior and let vanilla handle it.
				if (!BurntBasicConfigConfiguration.BURNT_ON.get()) return InteractionResult.PASS;
				// Honour both LevelZ skill gates before anything is lit
				// LevelZ misses the item gate when used on a block, and it registers after us, so check both here
				if (net.pixelbank.burnt.compat.LevelZBridge.blocksItem(player, stack.getItem())
						|| net.pixelbank.burnt.compat.LevelZBridge.blocksBlock(player, level.getBlockState(hitResult.getBlockPos()).getBlock())) {
					// Shown on whichever side refuses - FAIL on the client stops the packet ever
					// reaching the server, so a server-only message would never be seen
					player.displayClientMessage(net.pixelbank.burnt.compat.LevelZBridge.lockedMessage(), true);
					return InteractionResult.FAIL;
				}

				// Server-only below this point
				// The gate itself runs on both sides, or the client predicts a fire the server then refuses
				if (level.isClientSide())
					return InteractionResult.PASS;
				BlockPos clickedPos = hitResult.getBlockPos();
				// Coal block to smoldering coal - consumes the interaction so vanilla fire isn't also placed
				if (stack.is(Items.FLINT_AND_STEEL) && level.getBlockState(clickedPos).is(Blocks.COAL_BLOCK)) {
					CoalRightClickProcedure.execute(level, clickedPos.getX(), clickedPos.getY(), clickedPos.getZ(), player);
					return InteractionResult.SUCCESS;
				}
				// Burnt wood to ember - SUCCESS stops vanilla fire going on the adjacent face
				if (stack.is(Items.FLINT_AND_STEEL)) {
					var clickedBlock = level.getBlockState(clickedPos).getBlock();
					if (clickedBlock == BurntModBlocks.BURNT_LOG    || clickedBlock == BurntModBlocks.BURNT_WOOD   ||
						clickedBlock == BurntModBlocks.BURNT_PLANKS || clickedBlock == BurntModBlocks.BURNT_STAIRS ||
						clickedBlock == BurntModBlocks.BURNT_SLAB   || clickedBlock == BurntModBlocks.BURNT_FENCE  ||
						clickedBlock == BurntModBlocks.BURNT_FENCEGATE) {
						EmberRightClickProcedure.execute(level, clickedPos.getX(), clickedPos.getY(), clickedPos.getZ(), player);
						return InteractionResult.SUCCESS;
					}
				}
				// Don't ignite barrels unless crouching - the player means to open them
				if (!player.isCrouching()) {
					var clickedBlock = level.getBlockState(clickedPos).getBlock();
					if (clickedBlock == Blocks.BARREL || clickedBlock == BurntModBlocks.BURNT_BARREL) {
						return InteractionResult.PASS;
					}
				}
				// Doors and trapdoors just open - vanilla's use() also stops fire going on the adjacent face
				{
					var clickedState = level.getBlockState(clickedPos);
					if (clickedState.is(net.pixelbank.burnt.util.BurntTags.BLOCK_MC_WOODEN_DOORS)
							|| clickedState.is(net.pixelbank.burnt.util.BurntTags.BLOCK_MC_WOODEN_TRAPDOORS)) {
						return InteractionResult.PASS;
					}
				}
				// Run the block conversions for the clicked block
				StartFlintBurnProcedure.execute(level, clickedPos.getX(), clickedPos.getY(), clickedPos.getZ());
				BlockPos firePos = clickedPos.relative(hitResult.getDirection());
				BurntLavaFireBlocker.markEntityPlaced(level, firePos);
				// Vanilla fire needs a solid block below, so non-solid flammables like leaves never get it
				// Place FIRESTARTER directly here instead
				BlockState belowFire = level.getBlockState(firePos.below());
				boolean placeOnLeafTop = hitResult.getDirection() == Direction.UP
						&& belowFire.getBlock() instanceof LeavesBlock;
				// Never place fire on or into a fluid - it corrupted Supplementaries lumisene's volume ledger
				// Never build on fire either - clicking the top of one used to stack a tower of them
				if (((!belowFire.isSolid() && !belowFire.isAir() && belowFire.getFluidState().isEmpty()) || placeOnLeafTop)
						&& !(belowFire.getBlock() instanceof net.minecraft.world.level.block.BaseFireBlock)
						&& !belowFire.is(net.pixelbank.burnt.util.BurntTags.BLOCK_FIRE)
						&& level.getBlockState(firePos).canBeReplaced() && level.getFluidState(firePos).isEmpty()) {
					level.setBlock(firePos, BurntModBlocks.FIRESTARTER.defaultBlockState(), 11);
				}
			}
			return InteractionResult.PASS;
		});

		// Fire on USE before the strip resolves, while the block is still sooty, then PASS so vanilla strips it
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (world instanceof Level level && !level.isClientSide()) {
				var stack = player.getItemInHand(hand);
				if (stack.getItem() instanceof net.minecraft.world.item.AxeItem) {
					BlockPos pos = hitResult.getBlockPos();
					if (net.pixelbank.burnt.util.BurntStripping.dropsSoot(level.getBlockState(pos))) {
						net.minecraft.world.level.block.Block.popResource(level, pos,
								new net.minecraft.world.item.ItemStack(BurntModItems.SOOT));
					}
				}
			}
			return InteractionResult.PASS;
		});

		// Phase 5: commands
		BurntCommandCommand.register();

		// Phase 6: register S2C payload types
		PayloadTypeRegistry.playS2C().register(MapVarsSyncPayload.TYPE, MapVarsSyncPayload.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(MenuStateUpdateMessage.TYPE, MenuStateUpdateMessage.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(FireSkyPayload.TYPE, FireSkyPayload.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(SmokeFogPayload.TYPE, SmokeFogPayload.STREAM_CODEC);

		// Phase 6: send MapVars snapshot when player joins
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayer player = handler.player;
			// Nudge: Dynamic Trees present but Burnt Dynamic Trees missing - its trees won't burn.
			if (net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("dynamictrees")
					&& !net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("burnt_dt")) {
				player.sendSystemMessage(net.minecraft.network.chat.Component.literal(
						"You have Dynamic Trees installed, to burn them down you will need to install Burnt Dynamic Trees for the best experience"));
			}
			if (server.overworld() != null) {
				ServerPlayNetworking.send(player, new MapVarsSyncPayload(MapVariables.get(server.overworld())));
			}
		});

		// Clear plume counters on server stop so stale UUIDs don't leak across world loads
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> PlumeManager.clear());

		// Ignite arrows from Burnt bows with Flame when they spawn
		ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if (entity instanceof Arrow || entity instanceof SpectralArrow) {
				ApplyBowEffectsToArrowProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
			}
			// Villagers flee from burnt:fire tag blocks within 8 blocks (speed 1.4, priority 1)
			if (entity instanceof Villager villager) {
				villager.goalSelector.addGoal(1, new FleeFireGoal(villager, 1.4));
			}
			// GolemExtinguishGoal is added via IronGolemMixin.registerGoals() - not here
		});

		// Flame arrows that hit a creeper ignite its fuse // FLAME ARROW CREEPER
		ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamage, damage, blocked) -> { // FLAME ARROW CREEPER
			if (!BurntBasicConfigConfiguration.FLAME_ARROWS_IGNITE_CREEPERS.get()) return; // FLAME ARROW CREEPER
			if (!(entity instanceof Creeper creeper)) return; // FLAME ARROW CREEPER
			if (!(source.getDirectEntity() instanceof AbstractArrow arrow)) return; // FLAME ARROW CREEPER
			if (!arrow.isOnFire()) return; // FLAME ARROW CREEPER
			creeper.ignite(); // starts swell -> explosion // FLAME ARROW CREEPER
		}); // FLAME ARROW CREEPER

		LOGGER.info("Burnt Basic initialized.");
	}

	// Deferred tick-work helper (used by procedures)

	public static void queueServerWork(int ticks, Runnable action) {
		WORK_QUEUE.add(new DelayedWork(action, ticks));
	}

	/** Called from the server tick callback (registered in Phase 5). */
	public static void tickWorkQueue() {
		List<DelayedWork> ready = new ArrayList<>();
		for (DelayedWork w : WORK_QUEUE) {
			w.ticksLeft--;
			if (w.ticksLeft <= 0) ready.add(w);
		}
		ready.forEach(w -> w.action.run());
		WORK_QUEUE.removeAll(ready);
	}

	private static final class DelayedWork {
		final Runnable action;
		int ticksLeft;

		DelayedWork(Runnable action, int ticks) {
			this.action = action;
			this.ticksLeft = ticks;
		}
	}
}
