package net.pixelbank.burnt.network;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.core.Direction;

import net.pixelbank.burnt.BurntMod;

import java.util.function.Supplier;

public class BurntModVariables {

	/** Per-player variables, attached via Forge capability. */
	public static final Capability<PlayerVariables> PLAYER_VARIABLES = CapabilityManager.get(new CapabilityToken<>(){});

	public static void register(IEventBus bus) {
		bus.addListener(BurntModVariables::onRegisterCapabilities);
		MinecraftForge.EVENT_BUS.addGenericListener(net.minecraft.world.entity.Entity.class, BurntModVariables::onAttachCapabilities);
	}

	@SubscribeEvent
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<net.minecraft.world.entity.Entity> event) {
		if (event.getObject() instanceof net.minecraft.world.entity.player.Player) {
			event.addCapability(
				ResourceLocation.fromNamespaceAndPath(BurntMod.MODID, "player_variables"),
				new PlayerVariablesProvider());
		}
	}

	public static class PlayerVariablesProvider implements ICapabilitySerializable<CompoundTag> {
		private final PlayerVariables instance = new PlayerVariables();
		private final LazyOptional<PlayerVariables> holder = LazyOptional.of(() -> instance);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return PLAYER_VARIABLES.orEmpty(cap, holder);
		}

		@Override
		public CompoundTag serializeNBT(HolderLookup.Provider provider) {
			return instance.serializeNBT(provider);
		}

		@Override
		public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
			instance.deserializeNBT(provider, nbt);
		}
	}

	// Map Variables - global, Overworld-scoped, synced to all clients (Phase 6)
	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "burnt_mapvars";
		public boolean _syncDirty = false;

		public boolean burntOn = true;
		public boolean rainExtinguish = true;
		public boolean collapseOn = true;
		public boolean flameParticles = true;
		public boolean ashParticles = true;
		public boolean burnDamage = true;
		public boolean smokeParticles = true;
		public boolean sootOn = true;
		public boolean vanillaCatch = true;
		public boolean craftedBlocksBurn = true;
		public boolean woolBurns = true;
		public boolean flameBowOn = true;
		public boolean fireAspectOn = false;
		public boolean lightningCatch = false;
		public boolean smokeFogParticles = true;
		public boolean burnChestContents = true;
		public double playerDistance = 0.0;
		public double grassRecovery = 3.0;
		public double burnSpeed = 50.0;
		public double burnSpread = 50.0;
		public double groundSpread = 50.0;
		public double burning = 0;
		public double chanceCollapse = 100.0;
		public boolean forestRecovery = false;
		public boolean distantSmoke = true;
		// Weather2 fields removed - mod does not exist for Fabric
		public boolean sc_found = false;
		public double sc_tx = 0;
		public double sc_tz = 0;
		public double sc_nextLocate = 0;
		public boolean prevDistantSmoke = false;
		public boolean biomeSpecific = false;
		public double windSpeed = 0;
		public double windAngle = 0;
		public double windTargetSpeed = 0;
		public double windTargetAngle = 0;
		public double tmp_baseChance = 0;
		public double tmp_dx = 0;
		public double tmp_dz = 0;
		public double tmp_windResult = 0;
		public boolean windSpeedLock = false;
		public boolean windAngleLock = false;
		public double windBaseSpeed = 0;
		public double gustStrength = 0;
		public double gustTarget = 0;
		public double gustTimer = 0;
		public double gustMaxAdd = 0;

		public static MapVariables load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
			MapVariables data = new MapVariables();
			data.read(tag, lookupProvider);
			return data;
		}

		public void read(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			burntOn           = nbt.getBoolean("burntOn");
			rainExtinguish    = nbt.getBoolean("rainExtinguish");
			collapseOn        = nbt.getBoolean("collapseOn");
			flameParticles    = nbt.getBoolean("flameParticles");
			ashParticles      = nbt.getBoolean("ashParticles");
			burnDamage        = nbt.getBoolean("burnDamage");
			smokeParticles    = nbt.getBoolean("smokeParticles");
			sootOn            = nbt.getBoolean("sootOn");
			vanillaCatch      = nbt.getBoolean("vanillaCatch");
			craftedBlocksBurn = nbt.getBoolean("craftedBlocksBurn");
			woolBurns         = nbt.getBoolean("woolBurns");
			flameBowOn        = nbt.getBoolean("flameBowOn");
			fireAspectOn      = nbt.getBoolean("fireAspectOn");
			lightningCatch    = nbt.getBoolean("lightningCatch");
			smokeFogParticles = nbt.getBoolean("smokeFogParticles");
			burnChestContents = nbt.getBoolean("burnChestContents");
			playerDistance    = nbt.getDouble("playerDistance");
			grassRecovery     = nbt.getDouble("grassRecovery");
			burnSpeed         = nbt.getDouble("burnSpeed");
			burnSpread        = nbt.getDouble("burnSpread");
			groundSpread      = nbt.getDouble("groundSpread");
			burning           = nbt.getDouble("burning");
			chanceCollapse    = nbt.getDouble("chanceCollapse");
			forestRecovery    = nbt.getBoolean("forestRecovery");
			distantSmoke      = nbt.getBoolean("distantSmoke");
			sc_found          = nbt.getBoolean("sc_found");
			sc_tx             = nbt.getDouble("sc_tx");
			sc_tz             = nbt.getDouble("sc_tz");
			sc_nextLocate     = nbt.getDouble("sc_nextLocate");
			prevDistantSmoke  = nbt.getBoolean("prevDistantSmoke");
			biomeSpecific     = nbt.getBoolean("biomeSpecific");
			windSpeed         = nbt.getDouble("windSpeed");
			windAngle         = nbt.getDouble("windAngle");
			windTargetSpeed   = nbt.getDouble("windTargetSpeed");
			windTargetAngle   = nbt.getDouble("windTargetAngle");
			tmp_baseChance    = nbt.getDouble("tmp_baseChance");
			tmp_dx            = nbt.getDouble("tmp_dx");
			tmp_dz            = nbt.getDouble("tmp_dz");
			tmp_windResult    = nbt.getDouble("tmp_windResult");
			windSpeedLock     = nbt.getBoolean("windSpeedLock");
			windAngleLock     = nbt.getBoolean("windAngleLock");
			windBaseSpeed     = nbt.getDouble("windBaseSpeed");
			gustStrength      = nbt.getDouble("gustStrength");
			gustTarget        = nbt.getDouble("gustTarget");
			gustTimer         = nbt.getDouble("gustTimer");
			gustMaxAdd        = nbt.getDouble("gustMaxAdd");
		}

		@Override
		public CompoundTag save(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			nbt.putBoolean("burntOn",           burntOn);
			nbt.putBoolean("rainExtinguish",    rainExtinguish);
			nbt.putBoolean("collapseOn",        collapseOn);
			nbt.putBoolean("flameParticles",    flameParticles);
			nbt.putBoolean("ashParticles",      ashParticles);
			nbt.putBoolean("burnDamage",        burnDamage);
			nbt.putBoolean("smokeParticles",    smokeParticles);
			nbt.putBoolean("sootOn",            sootOn);
			nbt.putBoolean("vanillaCatch",      vanillaCatch);
			nbt.putBoolean("craftedBlocksBurn", craftedBlocksBurn);
			nbt.putBoolean("woolBurns",         woolBurns);
			nbt.putBoolean("flameBowOn",        flameBowOn);
			nbt.putBoolean("fireAspectOn",      fireAspectOn);
			nbt.putBoolean("lightningCatch",    lightningCatch);
			nbt.putBoolean("smokeFogParticles", smokeFogParticles);
			nbt.putBoolean("burnChestContents", burnChestContents);
			nbt.putDouble("playerDistance",     playerDistance);
			nbt.putDouble("grassRecovery",      grassRecovery);
			nbt.putDouble("burnSpeed",          burnSpeed);
			nbt.putDouble("burnSpread",         burnSpread);
			nbt.putDouble("groundSpread",       groundSpread);
			nbt.putDouble("burning",            burning);
			nbt.putDouble("chanceCollapse",     chanceCollapse);
			nbt.putBoolean("forestRecovery",    forestRecovery);
			nbt.putBoolean("distantSmoke",      distantSmoke);
			nbt.putBoolean("sc_found",          sc_found);
			nbt.putDouble("sc_tx",              sc_tx);
			nbt.putDouble("sc_tz",              sc_tz);
			nbt.putDouble("sc_nextLocate",      sc_nextLocate);
			nbt.putBoolean("prevDistantSmoke",  prevDistantSmoke);
			nbt.putBoolean("biomeSpecific",     biomeSpecific);
			nbt.putDouble("windSpeed",          windSpeed);
			nbt.putDouble("windAngle",          windAngle);
			nbt.putDouble("windTargetSpeed",    windTargetSpeed);
			nbt.putDouble("windTargetAngle",    windTargetAngle);
			nbt.putDouble("tmp_baseChance",     tmp_baseChance);
			nbt.putDouble("tmp_dx",             tmp_dx);
			nbt.putDouble("tmp_dz",             tmp_dz);
			nbt.putDouble("tmp_windResult",     tmp_windResult);
			nbt.putBoolean("windSpeedLock",     windSpeedLock);
			nbt.putBoolean("windAngleLock",     windAngleLock);
			nbt.putDouble("windBaseSpeed",      windBaseSpeed);
			nbt.putDouble("gustStrength",       gustStrength);
			nbt.putDouble("gustTarget",         gustTarget);
			nbt.putDouble("gustTimer",          gustTimer);
			nbt.putDouble("gustMaxAdd",         gustMaxAdd);
			return nbt;
		}

		public void markSyncDirty() {
			this.setDirty();
			_syncDirty = true;
		}

		public static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD)
						.getDataStorage().computeIfAbsent(
								new SavedData.Factory<MapVariables>(MapVariables::new, MapVariables::load, null),
								DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	// World Variables - per-dimension
	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "burnt_worldvars";
		public boolean _syncDirty = false;
		public boolean plumeObjMade = false;
		public boolean flameObjMade = false;
		public double flameCount = 0;
		public double plumeCap = 1000.0;

		public static WorldVariables load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
			WorldVariables data = new WorldVariables();
			data.read(tag, lookupProvider);
			return data;
		}

		public void read(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			plumeObjMade = nbt.getBoolean("plumeObjMade");
			flameObjMade = nbt.getBoolean("flameObjMade");
			flameCount   = nbt.getDouble("flameCount");
			plumeCap     = nbt.getDouble("plumeCap");
		}

		@Override
		public CompoundTag save(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			nbt.putBoolean("plumeObjMade", plumeObjMade);
			nbt.putBoolean("flameObjMade", flameObjMade);
			nbt.putDouble("flameCount",    flameCount);
			nbt.putDouble("plumeCap",      plumeCap);
			return nbt;
		}

		public void markSyncDirty() {
			this.setDirty();
			_syncDirty = true;
		}

		public static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(
						new SavedData.Factory<WorldVariables>(WorldVariables::new, WorldVariables::load, null),
						DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	// Player variables - per-player, NBT-serialisable
	// TODO Phase 6 - attach properly and restore login/respawn/tick sync
	public static class PlayerVariables {

		public static final Codec<PlayerVariables> CODEC = RecordCodecBuilder.create(instance ->
			instance.group(
				Codec.BOOL.fieldOf("arrowFired").orElse(false).forGetter(v -> v.arrowFired),
				Codec.BOOL.fieldOf("extinguisherOn").orElse(false).forGetter(v -> v.extinguisherOn),
				Codec.DOUBLE.fieldOf("PlayerTickDelay").orElse(0.0).forGetter(v -> v.PlayerTickDelay),
				Codec.DOUBLE.fieldOf("smokeAir").orElse(100.0).forGetter(v -> v.smokeAir),
				Codec.DOUBLE.fieldOf("dl_cool").orElse(0.0).forGetter(v -> v.dl_cool),
				Codec.BOOL.fieldOf("dl_pending_clear").orElse(false).forGetter(v -> v.dl_pending_clear),
				Codec.BOOL.fieldOf("dl_active").orElse(false).forGetter(v -> v.dl_active),
				Codec.DOUBLE.fieldOf("ml_cool").orElse(0.0).forGetter(v -> v.ml_cool),
				Codec.BOOL.fieldOf("ml_pending_clear").orElse(false).forGetter(v -> v.ml_pending_clear),
				Codec.BOOL.fieldOf("ml_active").orElse(false).forGetter(v -> v.ml_active),
				Codec.DOUBLE.fieldOf("mol_cool").orElse(0.0).forGetter(v -> v.mol_cool),
				Codec.BOOL.fieldOf("mol_pending_clear").orElse(false).forGetter(v -> v.mol_pending_clear),
				Codec.BOOL.fieldOf("mol_active").orElse(false).forGetter(v -> v.mol_active)
			).apply(instance, (arrowFired, extinguisherOn, playerTickDelay, smokeAir,
					dl_cool, dl_pending_clear, dl_active,
					ml_cool, ml_pending_clear, ml_active,
					mol_cool, mol_pending_clear, mol_active) -> {
				PlayerVariables v = new PlayerVariables();
				v.arrowFired        = arrowFired;
				v.extinguisherOn    = extinguisherOn;
				v.PlayerTickDelay   = playerTickDelay;
				v.smokeAir          = smokeAir;
				v.dl_cool           = dl_cool;
				v.dl_pending_clear  = dl_pending_clear;
				v.dl_active         = dl_active;
				v.ml_cool           = ml_cool;
				v.ml_pending_clear  = ml_pending_clear;
				v.ml_active         = ml_active;
				v.mol_cool          = mol_cool;
				v.mol_pending_clear = mol_pending_clear;
				v.mol_active        = mol_active;
				return v;
			}));

		/** Gets the player variables for this entity, or a transient instance if the capability is absent. */
		public static PlayerVariables get(Entity entity) {
			return entity.getCapability(PLAYER_VARIABLES).orElseGet(PlayerVariables::new);
		}

		public boolean _syncDirty = false;
		public boolean arrowFired = false;
		public boolean extinguisherOn = false;
		public double PlayerTickDelay = 0;
		public double smokeAir = 100.0;
		public double dl_cool = 0;
		public boolean dl_pending_clear = false;
		public boolean dl_active = false;
		public double ml_cool = 0;
		public boolean ml_pending_clear = false;
		public boolean ml_active = false;
		public double mol_cool = 0;
		public boolean mol_pending_clear = false;
		public boolean mol_active = false;

		public CompoundTag serializeNBT(HolderLookup.Provider lookupProvider) {
			CompoundTag nbt = new CompoundTag();
			nbt.putBoolean("arrowFired",        arrowFired);
			nbt.putBoolean("extinguisherOn",    extinguisherOn);
			nbt.putDouble("PlayerTickDelay",    PlayerTickDelay);
			nbt.putDouble("smokeAir",           smokeAir);
			nbt.putDouble("dl_cool",            dl_cool);
			nbt.putBoolean("dl_pending_clear",  dl_pending_clear);
			nbt.putBoolean("dl_active",         dl_active);
			nbt.putDouble("ml_cool",            ml_cool);
			nbt.putBoolean("ml_pending_clear",  ml_pending_clear);
			nbt.putBoolean("ml_active",         ml_active);
			nbt.putDouble("mol_cool",           mol_cool);
			nbt.putBoolean("mol_pending_clear", mol_pending_clear);
			nbt.putBoolean("mol_active",        mol_active);
			return nbt;
		}

		public void deserializeNBT(HolderLookup.Provider lookupProvider, CompoundTag nbt) {
			arrowFired        = nbt.getBoolean("arrowFired");
			extinguisherOn    = nbt.getBoolean("extinguisherOn");
			PlayerTickDelay   = nbt.getDouble("PlayerTickDelay");
			smokeAir          = nbt.getDouble("smokeAir");
			dl_cool           = nbt.getDouble("dl_cool");
			dl_pending_clear  = nbt.getBoolean("dl_pending_clear");
			dl_active         = nbt.getBoolean("dl_active");
			ml_cool           = nbt.getDouble("ml_cool");
			ml_pending_clear  = nbt.getBoolean("ml_pending_clear");
			ml_active         = nbt.getBoolean("ml_active");
			mol_cool          = nbt.getDouble("mol_cool");
			mol_pending_clear = nbt.getBoolean("mol_pending_clear");
			mol_active        = nbt.getBoolean("mol_active");
		}

		public void markSyncDirty() {
			_syncDirty = true;
		}
	}
}
