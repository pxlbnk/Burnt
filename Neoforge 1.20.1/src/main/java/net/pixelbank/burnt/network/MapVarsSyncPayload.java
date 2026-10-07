package net.pixelbank.burnt.network;

import net.pixelbank.burnt.network.BurntModVariables.MapVariables;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/** Syncs all world variables to the client. */
public class MapVarsSyncPayload {

	public final MapVariables data;

	public MapVarsSyncPayload(MapVariables data) {
		this.data = data;
	}

	public static void encode(MapVarsSyncPayload msg, FriendlyByteBuf buf) {
		MapVariables v = msg.data;
		buf.writeBoolean(v.burntOn);
		buf.writeBoolean(v.rainExtinguish);
		buf.writeBoolean(v.collapseOn);
		buf.writeBoolean(v.flameParticles);
		buf.writeBoolean(v.ashParticles);
		buf.writeBoolean(v.burnDamage);
		buf.writeBoolean(v.smokeParticles);
		buf.writeBoolean(v.sootOn);
		buf.writeBoolean(v.vanillaCatch);
		buf.writeBoolean(v.craftedBlocksBurn);
		buf.writeBoolean(v.woolBurns);
		buf.writeBoolean(v.flameBowOn);
		buf.writeBoolean(v.fireAspectOn);
		buf.writeBoolean(v.lightningCatch);
		buf.writeBoolean(v.smokeFogParticles);
		buf.writeBoolean(v.burnChestContents);
		buf.writeDouble(v.playerDistance);
		buf.writeDouble(v.grassRecovery);
		buf.writeDouble(v.burnSpeed);
		buf.writeDouble(v.burnSpread);
		buf.writeDouble(v.groundSpread);
		buf.writeDouble(v.burning);
		buf.writeDouble(v.chanceCollapse);
		buf.writeBoolean(v.forestRecovery);
		buf.writeBoolean(v.distantSmoke);
		buf.writeBoolean(v.sc_found);
		buf.writeDouble(v.sc_tx);
		buf.writeDouble(v.sc_tz);
		buf.writeDouble(v.sc_nextLocate);
		buf.writeBoolean(v.prevDistantSmoke);
		buf.writeBoolean(v.biomeSpecific);
		buf.writeDouble(v.windSpeed);
		buf.writeDouble(v.windAngle);
		buf.writeDouble(v.windTargetSpeed);
		buf.writeDouble(v.windTargetAngle);
		buf.writeDouble(v.tmp_baseChance);
		buf.writeDouble(v.tmp_dx);
		buf.writeDouble(v.tmp_dz);
		buf.writeDouble(v.tmp_windResult);
		buf.writeBoolean(v.windSpeedLock);
		buf.writeBoolean(v.windAngleLock);
		buf.writeDouble(v.windBaseSpeed);
		buf.writeDouble(v.gustStrength);
		buf.writeDouble(v.gustTarget);
		buf.writeDouble(v.gustTimer);
		buf.writeDouble(v.gustMaxAdd);
	}

	public static MapVarsSyncPayload decode(FriendlyByteBuf buf) {
		MapVariables v = new MapVariables();
		v.burntOn           = buf.readBoolean();
		v.rainExtinguish    = buf.readBoolean();
		v.collapseOn        = buf.readBoolean();
		v.flameParticles    = buf.readBoolean();
		v.ashParticles      = buf.readBoolean();
		v.burnDamage        = buf.readBoolean();
		v.smokeParticles    = buf.readBoolean();
		v.sootOn            = buf.readBoolean();
		v.vanillaCatch      = buf.readBoolean();
		v.craftedBlocksBurn = buf.readBoolean();
		v.woolBurns         = buf.readBoolean();
		v.flameBowOn        = buf.readBoolean();
		v.fireAspectOn      = buf.readBoolean();
		v.lightningCatch    = buf.readBoolean();
		v.smokeFogParticles = buf.readBoolean();
		v.burnChestContents = buf.readBoolean();
		v.playerDistance    = buf.readDouble();
		v.grassRecovery     = buf.readDouble();
		v.burnSpeed         = buf.readDouble();
		v.burnSpread        = buf.readDouble();
		v.groundSpread      = buf.readDouble();
		v.burning           = buf.readDouble();
		v.chanceCollapse    = buf.readDouble();
		v.forestRecovery    = buf.readBoolean();
		v.distantSmoke      = buf.readBoolean();
		v.sc_found          = buf.readBoolean();
		v.sc_tx             = buf.readDouble();
		v.sc_tz             = buf.readDouble();
		v.sc_nextLocate     = buf.readDouble();
		v.prevDistantSmoke  = buf.readBoolean();
		v.biomeSpecific     = buf.readBoolean();
		v.windSpeed         = buf.readDouble();
		v.windAngle         = buf.readDouble();
		v.windTargetSpeed   = buf.readDouble();
		v.windTargetAngle   = buf.readDouble();
		v.tmp_baseChance    = buf.readDouble();
		v.tmp_dx            = buf.readDouble();
		v.tmp_dz            = buf.readDouble();
		v.tmp_windResult    = buf.readDouble();
		v.windSpeedLock     = buf.readBoolean();
		v.windAngleLock     = buf.readBoolean();
		v.windBaseSpeed     = buf.readDouble();
		v.gustStrength      = buf.readDouble();
		v.gustTarget        = buf.readDouble();
		v.gustTimer         = buf.readDouble();
		v.gustMaxAdd        = buf.readDouble();
		return new MapVarsSyncPayload(v);
	}

	/** Client-side handler - updates clientSide snapshot. Dist-safe: no client-only class references. */
	public static void handle(MapVarsSyncPayload payload, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> MapVariables.clientSide = payload.data);
		ctx.get().setPacketHandled(true);
	}
}
