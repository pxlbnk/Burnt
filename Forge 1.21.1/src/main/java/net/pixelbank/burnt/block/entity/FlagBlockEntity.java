package net.pixelbank.burnt.block.entity;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.init.BurntModBlockEntities;
import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.procedures.ParticleWindMultiplyerProcedure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

// Block entity on every flag block, holding the wind state the renderer reads
// Rides Create contraptions and Sable sub-levels, since both tick BEs at the carried position
// Server-side only, pushing an update packet when the visible state changes enough to matter
public class FlagBlockEntity extends BlockEntity {

	/** Wind direction in degrees (0-360), applied as the cloth's Y-rotation. */
	private float windAngle = 0f;
	/** Wind strength on the mod's 0-100 scale, after the local biome/height multiplier. */
	private int windSpeed = 0;
	// Which of the 6 wind animation buckets is playing: 0 none, 1 low, up to 5 full
	private int animationStage = 0;

	// Last angle pushed to clients, so only meaningful changes cost a chunk update
	private float lastSyncedAngle = 0f;

	/** Minimum angle delta (degrees) that triggers a client sync. */
	private static final float SYNC_ANGLE_THRESHOLD = 5f;

	/** Client-side animation playback (only ticked / read by the BER). */
	public final AnimationState animationState0 = new AnimationState();
	public final AnimationState animationState1 = new AnimationState();
	public final AnimationState animationState2 = new AnimationState();
	public final AnimationState animationState3 = new AnimationState();
	public final AnimationState animationState4 = new AnimationState();
	public final AnimationState animationState5 = new AnimationState();

	public FlagBlockEntity(BlockPos pos, BlockState state) {
		super(BurntModBlockEntities.FLAG, pos, state);
	}

	public float getWindAngle()      { return windAngle; }
	public int   getWindSpeed()      { return windSpeed; }
	public int   getAnimationStage() { return animationStage; }

	// Persisted so a flag reloads with the right rotation on its first frame

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookup) {
		super.saveAdditional(tag, lookup);
		tag.putFloat("WindAngle", this.windAngle);
		tag.putInt  ("WindSpeed", this.windSpeed);
		tag.putInt  ("AnimationStage", this.animationStage);
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider lookup) {
		super.loadAdditional(tag, lookup);
		if (tag.contains("WindAngle"))      this.windAngle      = tag.getFloat("WindAngle");
		if (tag.contains("WindSpeed"))      this.windSpeed      = tag.getInt("WindSpeed");
		if (tag.contains("AnimationStage")) this.animationStage = tag.getInt("AnimationStage");
	}

	// Make the standard BE update packet carry our fields, so the renderer sees new wind state

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
		CompoundTag tag = super.getUpdateTag(provider);
		saveAdditional(tag, provider);
		return tag;
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	// Per-tick wind read, server-only - syncs when the visible state changes enough to matter

	public static void tick(Level level, BlockPos pos, BlockState state, FlagBlockEntity be) {
		if (level.isClientSide()) return; // server only - client state is fed by getUpdatePacket
		if (!BurntBasicConfigConfiguration.WIND.get()) return;

		BurntModVariables.MapVariables mapVars = BurntModVariables.MapVariables.get(level);
		if (mapVars == null) return;

		float angle = (float) mapVars.windAngle;
		double multiplier = ParticleWindMultiplyerProcedure.execute(level, pos.getX(), pos.getY(), pos.getZ());
		// Living Wind spreads its own 0-2.5 range here rather than the clamped fire-spread value
		double rawSpeed = net.pixelbank.burnt.compat.LivingWindBridge.flagSpeed(level, mapVars.windSpeed);
		double sensitivity = BurntBasicConfigConfiguration.FLAG_SENSITIVITY.get() / 100.0;
		int speed = (int) Math.round(rawSpeed * multiplier * sensitivity);
		int newStage = stageForSpeed(speed);

		boolean stageChanged = newStage != be.animationStage;
		boolean angleChanged = angleDelta(angle, be.lastSyncedAngle) >= SYNC_ANGLE_THRESHOLD;

		be.windAngle = angle;
		be.windSpeed = speed;
		be.animationStage = newStage;

		if (stageChanged || angleChanged) {
			be.lastSyncedAngle = angle;
			// Push to clients tracking this chunk. Flag 3 - notify clients, not neighbours; this is visual only
			level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
		}
	}

	/** Shortest angular distance between two angles in degrees (0-180). */
	private static float angleDelta(float a, float b) {
		float d = Math.abs(a - b) % 360f;
		return d > 180f ? 360f - d : d;
	}

	// Wind speed to animation bucket. Thresholds match the old playback-condition procedures exactly
	private static int stageForSpeed(int speed) {
		if (speed < 1)  return 0; // none
		if (speed < 25) return 1; // low
		if (speed < 45) return 2; // medium
		if (speed < 65) return 3; // high
		if (speed < 85) return 4; // strong
		return                5; // full
	}

	// Drive the 6 animation states from animationStage. Idempotent - the renderer calls it every frame
	public void updateAnimationStates(int tickCount) {
		animationState0.animateWhen(animationStage == 0, tickCount);
		animationState1.animateWhen(animationStage == 1, tickCount);
		animationState2.animateWhen(animationStage == 2, tickCount);
		animationState3.animateWhen(animationStage == 3, tickCount);
		animationState4.animateWhen(animationStage == 4, tickCount);
		animationState5.animateWhen(animationStage == 5, tickCount);
	}
}
