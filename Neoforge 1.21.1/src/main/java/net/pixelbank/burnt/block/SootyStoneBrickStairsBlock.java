package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.Blocks;

public class SootyStoneBrickStairsBlock extends StairBlock {
	public SootyStoneBrickStairsBlock() {
		super(Blocks.AIR.defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5f, 6f));
	}

	@Override
	public float getExplosionResistance() {
		return 6f;
	}
}