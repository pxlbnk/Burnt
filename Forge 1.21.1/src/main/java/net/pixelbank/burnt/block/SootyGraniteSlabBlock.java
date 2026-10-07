package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class SootyGraniteSlabBlock extends SlabBlock {
	public SootyGraniteSlabBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 6f));
	}
}