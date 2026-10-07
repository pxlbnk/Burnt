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
public class LargeFlameSmokeParticle extends TextureSheetParticle {
	public static LargeFlameSmokeParticleProvider provider(SpriteSet spriteSet) {
		return new LargeFlameSmokeParticleProvider(spriteSet);
	}

	public static class LargeFlameSmokeParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public LargeFlameSmokeParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new LargeFlameSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;
	// Where the particle was born - smoke spawns inside the burning block, so let it out before the contact check applies
	private final net.minecraft.core.BlockPos originBlock;

	protected LargeFlameSmokeParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.originBlock = BlockPos.containing(x, y, z);
		net.pixelbank.burnt.util.BurntSmokeBudget.acquire(world);
		// Vary brightness per particle so a plume reads as depth rather than one flat mass
		// setColor only ever darkens - the sheet is authored at its lightest
		float _shade = 0.65f + this.random.nextFloat() * 0.35f;
		this.setColor(_shade, _shade, _shade);
		this.spriteSet = spriteSet;
		this.setSize(1f, 1f);
		this.quadSize *= 6f;
		this.lifetime = 51;
		this.gravity = -0.05f;
		this.hasPhysics = false;
		this.xd = vx * 1;
		this.yd = vy * 1;
		this.zd = vz * 1;
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
			this.setSprite(this.spriteSet.get((this.age / 3) % 18 + 1, 18));
		}
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
