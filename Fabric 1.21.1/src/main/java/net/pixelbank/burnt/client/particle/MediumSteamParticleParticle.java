package net.pixelbank.burnt.client.particle;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

@Environment(EnvType.CLIENT)
public class MediumSteamParticleParticle extends TextureSheetParticle {
	public static MediumSteamParticleParticleProvider provider(SpriteSet spriteSet) {
		return new MediumSteamParticleParticleProvider(spriteSet);
	}

	public static class MediumSteamParticleParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public MediumSteamParticleParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new MediumSteamParticleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected MediumSteamParticleParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(2f, 2f);
		this.quadSize *= 2f;
		this.lifetime = 72;
		this.gravity = -0.1f;
		this.hasPhysics = false;
		this.xd = vx * 0.2;
		this.yd = vy * 0.2;
		this.zd = vz * 0.2;
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
			this.setSprite(this.spriteSet.get((this.age / 3) % 24 + 1, 24));
		}
	}
}