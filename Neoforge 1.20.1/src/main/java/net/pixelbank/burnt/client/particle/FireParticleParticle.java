package net.pixelbank.burnt.client.particle;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
public class FireParticleParticle extends TextureSheetParticle {
	public static FireParticleParticleProvider provider(SpriteSet spriteSet) {
		return new FireParticleParticleProvider(spriteSet);
	}

	public static class FireParticleParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public FireParticleParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new FireParticleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	/** True if this ember was force-sent for long range (spawned >28 blocks from the camera). */
	private boolean distant;

	protected FireParticleParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0.2f, 0.2f);
		// Embers from the server force-send spawn far from the camera, so those get a long life and real size
		// Near embers keep their original values
		boolean distant = false;
		// (assigned to the field below once computed)
		net.minecraft.client.Camera cam0 = net.minecraft.client.Minecraft.getInstance().gameRenderer.getMainCamera();
		if (cam0 != null) {
			net.minecraft.world.phys.Vec3 e0 = cam0.getPosition();
			double ddx = x - e0.x, ddy = y - e0.y, ddz = z - e0.z;
			distant = (ddx * ddx + ddy * ddy + ddz * ddz) > (28.0 * 28.0);
		}
		this.distant = distant;
		if (distant) {
			this.lifetime = 40 + this.random.nextInt(30);   // 2 - 3.5s: quick sparks, not drifting lanterns
			this.quadSize *= 1.8f;
		} else {
			this.lifetime = (int) Math.max(1, 20 + (this.random.nextInt(8) - 4));
		}
		this.gravity = -0.1f;
		this.hasPhysics = false;
		this.xd = vx * 0.3;
		this.yd = vy * 0.3;
		this.zd = vz * 0.3;
		this.setSpriteFromAge(spriteSet);
	}

	@Override
	public int getLightColor(float partialTick) {
		return 15728880;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	// Same perspective compensation as FlameSmokeParticle, no effect within 32 blocks
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

	@Override
	public void tick() {
		super.tick();
		if (!this.removed) {
			// Map the frame sequence once across the lifetime - a long-lived ember would wrap and visibly flicker
			if (this.distant)
				this.setSpriteFromAge(this.spriteSet);
			else
				this.setSprite(this.spriteSet.get((this.age / 1) % 48 + 1, 48));
		}
	}
}