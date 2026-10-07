package net.pixelbank.burnt.network;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

import net.minecraftforge.eventbus.api.IEventBus;

import net.pixelbank.burnt.BurntMod;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BurntModVariables {

	/** No-op: AttachmentType system not available in 1.20.1. PlayerVariables use a HashMap instead. */
	public static void register(IEventBus bus) {}

	// Map Variables - global, Overworld-scoped, synced to all clients
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

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
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
		public CompoundTag save(CompoundTag nbt) {
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
						.getDataStorage().computeIfAbsent(MapVariables::load, MapVariables::new, DATA_NAME);
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

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			plumeObjMade = nbt.getBoolean("plumeObjMade");
			flameObjMade = nbt.getBoolean("flameObjMade");
			flameCount   = nbt.getDouble("flameCount");
			plumeCap     = nbt.getDouble("plumeCap");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
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
				return level.getDataStorage().computeIfAbsent(WorldVariables::load, WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	// Player variables - per-player, in a server-side map
	// No AttachmentType API in 1.20.1, so a ConcurrentHashMap keyed by UUID
	public static class PlayerVariables {

		private static final Map<UUID, PlayerVariables> SERVER_MAP = new ConcurrentHashMap<>();

		/** Gets (or creates) the PlayerVariables for this entity on the server. */
		public static PlayerVariables get(Entity entity) {
			return SERVER_MAP.computeIfAbsent(entity.getUUID(), id -> new PlayerVariables());
		}

		/** Call on server-stop / player-quit to prevent memory leak. */
		public static void remove(UUID uuid) {
			SERVER_MAP.remove(uuid);
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

		public CompoundTag serializeNBT() {
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

		public void deserializeNBT(CompoundTag nbt) {
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
