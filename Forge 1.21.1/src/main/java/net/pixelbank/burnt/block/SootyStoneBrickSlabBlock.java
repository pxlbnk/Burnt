package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class SootyStoneBrickSlabBlock extends SlabBlock {
	public SootyStoneBrickSlabBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 6f));
	}
}