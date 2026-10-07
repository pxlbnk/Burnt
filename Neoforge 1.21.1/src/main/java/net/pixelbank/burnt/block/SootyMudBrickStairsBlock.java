package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Blocks;

public class SootyMudBrickStairsBlock extends StairBlock {
	public SootyMudBrickStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().sound(SoundType.MUD_BRICKS).strength(1.5f, 3f).requiresCorrectToolForDrops());
	}

	@Override
	public float getExplosionResistance() {
		return 3f;
	}
}