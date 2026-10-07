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
import net.pixelbank.burnt.network.BurntModVariables;
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
import net.pixelbank.burnt.procedures.WindWakerProcedure;
import net.pixelbank.burnt.item.ExtinguisherDispenserBehavior;
import net.pixelbank.burnt.util.PlumeManager;
import net.pixelbank.burnt.BurntLightningRodGuard;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.common.ToolActions;
import net.pixelbank.burnt.compat.BurnMappingLoader;
import net.pixelbank.burnt.network.BurntNetwork;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Mod(BurntMod.MODID)
public class BurntMod {
	public static final String MODID = "burnt";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	// Set by /burnt extinguishAll - every burning block finishes burning, resets after 60 seconds
	public static volatile boolean killFire = false;

	// Set by /burnt extinguishAll for 15 seconds - treats the whole world as raining
	public static volatile boolean forceRain = false;

	// Deferred server-tick work queue
	private static final ConcurrentLinkedQueue<DelayedWork> WORK_QUEUE = new ConcurrentLinkedQueue<>();

	public BurntMod() {
		// Forge: get the mod event bus from FMLJavaModLoadingContext (no constructor injection).
		IEventBus modEventBus = net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus();

		// Config first - must load before any procedure reads a config value
		BurntBasicConfigConfiguration.register();

		// Registries - order: sounds/particles before blocks, blocks before items
		BurntModSounds.register(modEventBus);
		BurntModParticleTypes.register(modEventBus);
		BurntModBlocks.register(modEventBus);
		BurntModItems.register(modEventBus);
		BurntModBlockEntities.register(modEventBus);
		BurntModMenus.register(modEventBus);
		BurntModTabs.register(modEventBus);
		BurntModEntities.register(modEventBus);
		BurntModVariables.register(modEventBus);

		// Common setup: flammability overrides + dispenser behaviour (must run after registries)
		modEventBus.addListener(this::commonSetup);

		// Network channel + message registration
		BurntNetwork.register();

		// Built-in compat datapack (BYG, BiomesAPlenty, etc.)
		modEventBus.addListener(this::addBuiltinDataPacks);

		// Server-side game events
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			// Chunk-claim mods: keep Burnt fire out of claimed land.
			net.pixelbank.burnt.compat.ClaimBridge.init();

			// Dark oak burns slowly and self-contained, so fire dies out after 2-3 trees
			// Vanilla defaults: logs 5/5, leaves 60/30
			FireBlock fire = (FireBlock) Blocks.FIRE;
			fire.setFlammable(Blocks.DARK_OAK_LOG,            2, 5);
			fire.setFlammable(Blocks.DARK_OAK_WOOD,           2, 5);
			fire.setFlammable(Blocks.STRIPPED_DARK_OAK_LOG,   2, 5);
			fire.setFlammable(Blocks.STRIPPED_DARK_OAK_WOOD,  2, 5);
			fire.setFlammable(Blocks.DARK_OAK_LEAVES,         8, 15);

			// Acacia logs + leaves always spread - resinous wood and dry canopy burn aggressively.
			fire.setFlammable(Blocks.ACACIA_LOG,              300, 5);
			fire.setFlammable(Blocks.ACACIA_WOOD,             300, 5);
			fire.setFlammable(Blocks.STRIPPED_ACACIA_LOG,     300, 5);
			fire.setFlammable(Blocks.STRIPPED_ACACIA_WOOD,    300, 5);
			fire.setFlammable(Blocks.ACACIA_LEAVES,           300, 30);

			// Dispenser + extinguisher = water cannon / sprinkler system
			DispenserBlock.registerBehavior(BurntModItems.EXTINGUISHER, new ExtinguisherDispenserBehavior());
		});
	}

	// Datapack reload - burn mappings

	@SubscribeEvent
	public void onAddReloadListeners(AddReloadListenerEvent event) {
		event.addListener(new BurnMappingLoader());
	}

	// Built-in compat datapack - BYG, BiomesAPlenty, etc.

	private void addBuiltinDataPacks(AddPackFindersEvent event) {
		if (event.getPackType() != PackType.SERVER_DATA) return;
		var modFile = ModList.get().getModContainerById(MODID)
				.orElseThrow().getModInfo().getOwningFile().getFile();
		var packPath = modFile.findResource("datapacks", "compat");
		event.addRepositorySource(consumer -> {
			Pack pack = Pack.readMetaAndCreate(
					"burnt_compat",
					Component.literal("Burnt Compat"),
					true,
					(packId) -> new PathPackResources(packId, packPath, true),
					PackType.SERVER_DATA,
					Pack.Position.BOTTOM,
					PackSource.BUILT_IN);
			if (pack != null) consumer.accept(pack);
		});
		// Global user mappings: <configdir>/burnt/mappings applies to every world (no per-save datapacks/).
		java.nio.file.Path mappingsFolder = net.pixelbank.burnt.util.GlobalMappingsPacks.folder(net.minecraftforge.fml.loading.FMLPaths.CONFIGDIR.get());
		net.pixelbank.burnt.util.GlobalMappingsPacks.ensureFolder(mappingsFolder);
		event.addRepositorySource(net.pixelbank.burnt.util.GlobalMappingsPacks.source(mappingsFolder));
	}

	// Server tick - work queue + wind simulation + MapVars dirty sync

	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if (event.phase != TickEvent.Phase.END) return;
		MinecraftServer server = event.getServer();
		BurntMod.tickWorkQueue();
		if ((server.getTickCount() & 63) == 0) {
			BurntLightningRodGuard.cleanupStale(server.overworld() != null ? server.overworld().getGameTime() : 0L);
		}
		if (server.overworld() != null) {
			// Wind simulation - run every 4 ticks to reduce per-tick overhead
			if (server.getTickCount() % 4 == 0) {
				WindWakerProcedure.execute(server.overworld());
			}

			// MapVars dirty sync - Phase 5 will register the payload; send call is wired now
			MapVariables mv = MapVariables.get(server.overworld());
			if (mv._syncDirty) {
				mv._syncDirty = false;
				MapVarsSyncPayload payload = new MapVarsSyncPayload(mv);
				for (ServerPlayer p : server.getPlayerList().getPlayers()) {
					BurntNetwork.sendToPlayer(p, payload);
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
	}

	// Block right-click - flint & steel / fire charge conversions

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		var player = event.getEntity();
		var world = event.getLevel();
		var hand = event.getHand();
		var hitResult = event.getHitVec();

		var stack = player.getItemInHand(hand);

		// Pickaxe on plain terracotta -> crack it in place (like axe-stripping a log).
		if (!world.isClientSide()
				&& net.pixelbank.burnt.util.BurntTerracottaCracking.tryCrack(world, hitResult.getBlockPos(), player, hand, stack)) {
			event.setCanceled(true);
			event.setCancellationResult(net.minecraft.world.InteractionResult.SUCCESS);
			return;
		}
		Level level = world;
		if (!level.isClientSide()
				&& (stack.is(Items.FLINT_AND_STEEL) || stack.is(Items.FIRE_CHARGE))) {
			// When the mod is disabled, skip all mod behaviour and let vanilla handle it.
			if (!BurntBasicConfigConfiguration.BURNT_ON.get()) return;
			BlockPos clickedPos = hitResult.getBlockPos();
			// Convert coal block to smoldering coal
			if (stack.is(Items.FLINT_AND_STEEL) && level.getBlockState(clickedPos).is(Blocks.COAL_BLOCK)) {
				CoalRightClickProcedure.execute(level, clickedPos.getX(), clickedPos.getY(), clickedPos.getZ(), player);
				event.setCanceled(true);
				return;
			}
			// Convert burnt wood blocks to their ember state
			if (stack.is(Items.FLINT_AND_STEEL)) {
				var clickedBlock = level.getBlockState(clickedPos).getBlock();
				if (clickedBlock == BurntModBlocks.BURNT_LOG    || clickedBlock == BurntModBlocks.BURNT_WOOD   ||
					clickedBlock == BurntModBlocks.BURNT_PLANKS || clickedBlock == BurntModBlocks.BURNT_STAIRS ||
					clickedBlock == BurntModBlocks.BURNT_SLAB   || clickedBlock == BurntModBlocks.BURNT_FENCE  ||
					clickedBlock == BurntModBlocks.BURNT_FENCEGATE) {
					EmberRightClickProcedure.execute(level, clickedPos.getX(), clickedPos.getY(), clickedPos.getZ(), player);
					event.setCanceled(true);
					return;
				}
			}
			// Don't ignite barrels when not crouching
			if (!player.isCrouching()) {
				var clickedBlock = level.getBlockState(clickedPos).getBlock();
				if (clickedBlock == Blocks.BARREL || clickedBlock == BurntModBlocks.BURNT_BARREL) {
					return; // PASS - let vanilla open the barrel
				}
			}
			// Doors and trapdoors just open - vanilla's use() also stops fire going on the adjacent face
			{
				var clickedState = level.getBlockState(clickedPos);
				if (clickedState.is(net.pixelbank.burnt.util.BurntTags.BLOCK_MC_WOODEN_DOORS)
						|| clickedState.is(net.pixelbank.burnt.util.BurntTags.BLOCK_MC_WOODEN_TRAPDOORS)) {
					return;
				}
			}
			// Run all block-type conversions for the clicked block
			StartFlintBurnProcedure.execute(level, clickedPos.getX(), clickedPos.getY(), clickedPos.getZ());
			BlockPos firePos = clickedPos.relative(hitResult.getDirection());
			BurntLavaFireBlocker.markEntityPlaced(level, firePos);
			// Directly place FIRESTARTER on non-solid flammable blocks where vanilla fire can't land
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
	}

	// Commands

	@SubscribeEvent
	public void onBlockToolModification(BlockEvent.BlockToolModificationEvent event) {
		if (event.getToolAction() != ToolActions.AXE_STRIP) return;
		var state = event.getState();
		var stripped = net.pixelbank.burnt.util.BurntStripping.getStripped(state);
		if (stripped == null) return;
		event.setFinalState(stripped);
		// Sooty logs/wood: axing scrapes the soot off, dropping one soot item.
		if (!event.isSimulated() && net.pixelbank.burnt.util.BurntStripping.dropsSoot(state)) {
			var ctx = event.getContext();
			net.minecraft.world.level.block.Block.popResource(ctx.getLevel(), ctx.getClickedPos(),
					new net.minecraft.world.item.ItemStack(net.pixelbank.burnt.init.BurntModItems.SOOT));
		}
	}

	@SubscribeEvent
	public void onRegisterCommands(RegisterCommandsEvent event) {
		BurntCommandCommand.register(event);
	}

	// Player login - send MapVars snapshot

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		// Nudge: Dynamic Trees present but Burnt Dynamic Trees missing - its trees won't burn.
		if (event.getEntity() instanceof ServerPlayer _dtPlayer
				&& net.minecraftforge.fml.ModList.get().isLoaded("dynamictrees")
				&& !net.minecraftforge.fml.ModList.get().isLoaded("burnt_dt")) {
			_dtPlayer.sendSystemMessage(net.minecraft.network.chat.Component.literal(
					"You have Dynamic Trees installed, to burn them down you will need to install Burnt Dynamic Trees for the best experience"));
		}
		if (event.getEntity() instanceof ServerPlayer player) {
			var server = player.getServer();
			if (server != null && server.overworld() != null) {
				BurntNetwork.sendToPlayer(player, new MapVarsSyncPayload(MapVariables.get(server.overworld())));
			}
		}
	}

	// Server stopped - clear plume manager

	@SubscribeEvent
	public void onServerStopped(ServerStoppedEvent event) {
		PlumeManager.clear();
	}

	// Entity join - arrow ignition + Villager/Golem AI injection

	@SubscribeEvent
	public void onEntityJoin(EntityJoinLevelEvent event) {
		if (event.getLevel().isClientSide()) return;
		var entity = event.getEntity();

		if (entity instanceof Arrow || entity instanceof SpectralArrow) {
			ApplyBowEffectsToArrowProcedure.execute(event.getLevel(), entity.getX(), entity.getY(), entity.getZ(), entity);
		}
		// Villagers flee from burnt:fire tag blocks within 8 blocks
		if (entity instanceof Villager villager) {
			villager.goalSelector.addGoal(1, new FleeFireGoal(villager, 1.4));
		}
		// All iron golems seek and extinguish burnt:on_fire tag blocks // IRON GOLEM FIRE FIGHTING
		if (entity instanceof IronGolem golem) { // IRON GOLEM FIRE FIGHTING
			golem.goalSelector.addGoal(1, new GolemExtinguishGoal(golem)); // IRON GOLEM FIRE FIGHTING
		} // IRON GOLEM FIRE FIGHTING
	}

	// Living hurt - flame arrows ignite creepers // FLAME ARROW CREEPER

	@SubscribeEvent
	public void onLivingHurt(LivingAttackEvent event) { // FLAME ARROW CREEPER
		if (!BurntBasicConfigConfiguration.FLAME_ARROWS_IGNITE_CREEPERS.get()) return; // FLAME ARROW CREEPER
		if (!(event.getEntity() instanceof Creeper creeper)) return; // FLAME ARROW CREEPER
		if (!(event.getSource().getDirectEntity() instanceof AbstractArrow arrow)) return; // FLAME ARROW CREEPER
		if (!arrow.isOnFire()) return; // FLAME ARROW CREEPER
		creeper.ignite(); // starts swell -> explosion // FLAME ARROW CREEPER
	} // FLAME ARROW CREEPER

	// Deferred tick-work helper (used by procedures)

	public static void queueServerWork(int ticks, Runnable action) {
		WORK_QUEUE.add(new DelayedWork(action, ticks));
	}

	/** Called from the server tick event. */
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
