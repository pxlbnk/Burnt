package net.pixelbank.burnt.compat.sable;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.fml.ModList;

// Facade for the Sable physics-airship integration
// Deliberately free of any dev.ryanhcode.sable.* reference - imports, fields, signatures - so
// the JVM can load this class without Sable installed
// All Sable-touching code lives in BurntSableCompatImpl, behind the MOD_PRESENT guard, because
// the verifier resolves referenced classes at load time: an earlier version imported Sable types
// here and crashed on first tick for anyone without the main jar
// New entry points go here as thin guarded delegates, with the body in Impl
public final class BurntSableCompat {

	private static final boolean MOD_PRESENT = ModList.get().isLoaded("sable");

	private BurntSableCompat() {}

	/** Whether the main Sable mod is installed. */
	public static boolean isActive() {
		return MOD_PRESENT;
	}

	// Called every server tick. Does nothing without Sable - Impl isn't even resolved then
	public static void serverTick(ServerLevel level) {
		if (!MOD_PRESENT) return;
		BurntSableCompatImpl.serverTick(level);
	}

	// Maps a Sable sub-level storage position to the visible world position via its pose transform,
	// or null for ordinary world space
	// Plumes are world-space entities that always drift +Y, so spawned at a storage coord they get
	// rendered through the ship's pose and drift sideways when it tilts
	public static double[] subLevelWorldPos(ServerLevel level, double x, double y, double z) {
		if (!MOD_PRESENT) return null;
		return BurntSableCompatImpl.subLevelWorldPos(level, x, y, z);
	}

	// Ignites burnable world blocks under a burning sub-level block's visible position
	// This is how airship fire reaches the world - every burning hull block calls it on its tick,
	// rate-limited internally. No-op off a sub-level
	public static void igniteGroundUnder(ServerLevel level, double x, double y, double z) {
		if (!MOD_PRESENT) return;
		BurntSableCompatImpl.igniteGroundUnder(level, x, y, z);
	}

	/**
	 * True if the position is on a Sable creation. Burn procedures use it to apply structural
	 * break-away to ships without making world builds any more fragile.
	 */
	public static boolean isInSubLevel(ServerLevel level, double x, double y, double z) {
		if (!MOD_PRESENT) return false;
		return BurntSableCompatImpl.isInSubLevel(level, x, y, z);
	}
}
