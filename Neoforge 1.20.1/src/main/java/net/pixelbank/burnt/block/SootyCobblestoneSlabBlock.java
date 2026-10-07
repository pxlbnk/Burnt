package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class SootyCobblestoneSlabBlock extends SlabBlock {
	public SootyCobblestoneSlabBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 6f));
	}
}