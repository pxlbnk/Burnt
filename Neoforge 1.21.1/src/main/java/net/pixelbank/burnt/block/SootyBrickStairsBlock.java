package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class SootyBrickStairsBlock extends StairBlock {
	public SootyBrickStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().strength(2f, 6f));
	}

	@Override
	public float getExplosionResistance() {
		return 6f;
	}
}