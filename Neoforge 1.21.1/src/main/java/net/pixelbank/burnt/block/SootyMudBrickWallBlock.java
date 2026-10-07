package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.SoundType;

public class SootyMudBrickWallBlock extends WallBlock {
	public SootyMudBrickWallBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.MUD_BRICKS).strength(1.5f, 3f).requiresCorrectToolForDrops().forceSolidOn());
	}
}