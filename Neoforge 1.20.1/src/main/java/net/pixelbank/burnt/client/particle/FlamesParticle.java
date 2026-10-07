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
public class FlamesParticle extends TextureSheetParticle {
	public static FlamesParticleProvider provider(SpriteSet spriteSet) {
		return new FlamesParticleProvider(spriteSet);
	}

	public static class FlamesParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public FlamesParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new FlamesParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected FlamesParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0.2f, 0.2f);
		this.quadSize *= 3f;
		// Flames from the server force-send only spawn far away, so distance tells the two apart
		boolean far = false;
		net.minecraft.client.Camera cam0 = net.minecraft.client.Minecraft.getInstance().gameRenderer.getMainCamera();
		if (cam0 != null) {
			net.minecraft.world.phys.Vec3 e0 = cam0.getPosition();
			double ddx = x - e0.x, ddy = y - e0.y, ddz = z - e0.z;
			far = (ddx * ddx + ddy * ddy + ddz * ddz) > (28.0 * 28.0);
		}
		if (far) {
			// From a long way off these need to read as a flicker at the base of the plume
			this.lifetime = 17 + this.random.nextInt(11);
			this.quadSize *= 1.6f;
			this.gravity = -0.04f;
		} else {
			this.lifetime = 16;
			this.gravity = -0.02f;
		}
		this.hasPhysics = false;
		this.xd = vx * 0;
		this.yd = vy * 0;
		this.zd = vz * 0;
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

	// Same perspective compensation as the smoke and ember particles, no effect within 32 blocks
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
			// The sequence loops, so run one frame per tick regardless of how long the flame lives
			this.setSprite(this.spriteSet.get((this.age / 1) % 8 + 1, 8));
		}
	}
}
