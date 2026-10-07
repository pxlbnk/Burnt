package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;

public class SootyMudBrickSlabBlock extends SlabBlock {
	public SootyMudBrickSlabBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.MUD_BRICKS).strength(1.5f, 3f).requiresCorrectToolForDrops());
	}
}