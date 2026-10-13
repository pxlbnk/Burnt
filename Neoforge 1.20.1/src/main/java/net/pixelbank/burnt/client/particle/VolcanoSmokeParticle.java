package net.pixelbank.burnt.client.particle;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
// Volcano smoke: flame smoke at 1.5x size, for vents that want the column without the fire
public class VolcanoSmokeParticle extends TextureSheetParticle {
	public static VolcanoSmokeParticleProvider provider(SpriteSet spriteSet) {
		return new VolcanoSmokeParticleProvider(spriteSet);
	}

	public static class VolcanoSmokeParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public VolcanoSmokeParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new VolcanoSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;
	// Where the particle was born - smoke spawns inside the burning block, so let it out before the contact check applies
	private final net.minecraft.core.BlockPos originBlock;

	// Shelter and wind factor, re-sampled as the particle climbs so canopy smoke drifts once it clears the leaves
	// On an interval, not every tick - canSeeSky and biome lookups are too costly per particle per tick
	private double windFactor;
	private int windFactorCountdown;

	/* Per-particle wind character. Without these every particle shares one wind vector and one follow
	 * rate, so a plume drifts as a rigid sheet. Each particle instead gets its own susceptibility
	 * (some catch the wind more than others), its own slight angle off the wind, and its own response
	 * rate - so the column frays and stretches the way real smoke does. Kept modest so it still reads
	 * as "the wind is blowing that way". Rolled once at spawn; they never change. */
	private final double windBias;      // speed susceptibility
	private final double windSkewCos;   // angle off the wind, precomputed
	private final double windSkewSin;
	private final double windFollow;    // how fast this particle reacts

	/** Ticks between shelter re-samples. Staggered per particle so they do not all resample together. */
	private static final int WIND_FACTOR_INTERVAL = 20;

	// Wind is the same for every particle in a tick, so resolve it once per client tick and share it.
	private static long windTick = Long.MIN_VALUE;
	private static double windX, windZ;

	/** How hard a particle is pulled toward the wind each tick (0 = ignore wind, 1 = instant). */
	private static final double WIND_FOLLOW = 0.06;

	private static void refreshWind(ClientLevel level) {
		long t = level.getGameTime();
		if (t == windTick)
			return;
		windTick = t;
		if (net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration.WIND.get()) {
			// Same /25 scaling the emitters use, so a particle keeps drifting at the speed it launched at.
			windX = net.pixelbank.burnt.procedures.WindAngleXProcedure.execute(level) / 25.0;
			windZ = net.pixelbank.burnt.procedures.WindAngleZProcedure.execute(level) / 25.0;
		} else {
			windX = 0;
			windZ = 0;
		}
	}

	protected VolcanoSmokeParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.originBlock = BlockPos.containing(x, y, z);
		net.pixelbank.burnt.util.BurntSmokeBudget.acquire(world);
		// Vary brightness per particle so a plume reads as depth rather than one flat mass
		// setColor only ever darkens - the sheet is authored at its lightest
		float _shade = 0.65f + this.random.nextFloat() * 0.35f;
		this.setColor(_shade, _shade, _shade);
		this.spriteSet = spriteSet;
		this.setSize(1f, 1f);
		// Base size, tuned against the sheet's average apparent size of about 79% of the tile
		// Re-measure if the sheet is redrawn - a fuller sheet at the same multiplier renders visibly bigger
		// 50% larger than the 7f flame smoke uses - a volcano plume needs to read as bigger
		this.quadSize *= 10.5f;
		// Per-particle size spread, 0.6x to 1.8x
		// Capped, because the distance ramp multiplies on top and 3.0x read as a few giant blobs
		this.quadSize *= 0.6f + this.random.nextFloat() * 1.2f;
		// Long life so distant smoke climbs into a real column, roughly 30-50 blocks of travel
		// Randomised per particle - a fixed lifetime put a hard flat ceiling across the top of the plume
		this.lifetime = 60 + this.random.nextInt(391);           // 60 - 450 ticks
		// Negative gravity makes it rise, varied per particle so they climb at different rates
		// Kept firmly negative so none of them ever sink
		this.gravity = -0.07f - this.random.nextFloat() * 0.06f;  // -0.07 .. -0.13
		this.hasPhysics = false;
		this.xd = vx * 1;
		this.yd = vy * 1;
		this.zd = vz * 1;
		this.windFactor = net.pixelbank.burnt.procedures.ParticleWindMultiplyerProcedure.execute(world, x, y, z);
		this.windFactorCountdown = 1 + this.random.nextInt(WIND_FACTOR_INTERVAL);
		this.windBias = 0.75 + this.random.nextDouble() * 0.5;                  // 0.75 - 1.25x wind speed
		double skew = (this.random.nextDouble() - 0.5) * 0.80;                  // +/- ~23 degrees off wind
		this.windSkewCos = Math.cos(skew);
		this.windSkewSin = Math.sin(skew);
		this.windFollow = WIND_FOLLOW * (0.6 + this.random.nextDouble() * 0.8); // 0.6 - 1.4x response rate
		this.setSpriteFromAge(spriteSet);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.removed) {
			// Die on contact with something solid, so smoke vanishes at a ceiling instead of bunching into a mat
			// isSolidRender is an opacity test, not collision - leaves have full collision and would pool smoke under every canopy
			// Two exemptions or it kills itself immediately: its own spawn block, and any block that is itself on fire
			BlockPos at = BlockPos.containing(this.x, this.y, this.z);
			if (!at.equals(this.originBlock)) {
				BlockState hit = this.level.getBlockState(at);
				if ((hit.isSolidRender(this.level, at) || hit.is(BlockTags.IMPERMEABLE))
						&& !hit.is(net.pixelbank.burnt.util.BurntTags.BLOCK_ON_FIRE)) {
					remove();
					return;
				}
			}
			// Play the frames once across the whole lifetime, so it reads as one dissipation at any length
			this.setSpriteFromAge(this.spriteSet);

			// Ease the horizontal velocity toward the current wind every tick, so friction only damps the difference
			// A one-off launch velocity is ~87% gone after 100 ticks, very visible at a 200-tick lifetime
			// Re-sample shelter as it climbs - this is what lets canopy smoke drift once clear of the leaves
			if (--this.windFactorCountdown <= 0) {
				this.windFactorCountdown = WIND_FACTOR_INTERVAL;
				this.windFactor = net.pixelbank.burnt.procedures.ParticleWindMultiplyerProcedure.execute(this.level, this.x, this.y, this.z);
			}
			refreshWind(this.level);
			// Rotate the shared wind by this particle.s own skew, scale by its own susceptibility.
			double wx = windX * this.windSkewCos - windZ * this.windSkewSin;
			double wz = windX * this.windSkewSin + windZ * this.windSkewCos;
			double gust = this.windFactor * this.windBias;
			this.xd += (wx * gust - this.xd) * this.windFollow;
			this.zd += (wz * gust - this.zd) * this.windFollow;
		}
	}

	// Grow the quad with camera distance - perspective shrinks smoke to specks exactly where it matters most
	private static final double NEAR_DISTANCE = 32.0;
	private static final double FAR_DISTANCE = 160.0;
	private static final double MAX_DISTANCE_SCALE = 3.0;

	@Override
	public float getQuadSize(float partialTicks) {
		float base = super.getQuadSize(partialTicks);
		net.minecraft.client.Camera cam = net.minecraft.client.Minecraft.getInstance().gameRenderer.getMainCamera();
		if (cam == null)
			return base;
		net.minecraft.world.phys.Vec3 eye = cam.getPosition();
		double dx = this.x - eye.x, dy = this.y - eye.y, dz = this.z - eye.z;
		double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
		if (d <= NEAR_DISTANCE)
			return base;
		double t = Math.min(1.0, (d - NEAR_DISTANCE) / (FAR_DISTANCE - NEAR_DISTANCE));
		return (float) (base * (1.0 + t * (MAX_DISTANCE_SCALE - 1.0)));
	}

	// Every vanilla death path goes through remove(), so this fires exactly once per particle
	private boolean budgetReleased = false;

	@Override
	public void remove() {
		if (!this.budgetReleased) {
			this.budgetReleased = true;
			net.pixelbank.burnt.util.BurntSmokeBudget.release();
		}
		super.remove();
	}
}
