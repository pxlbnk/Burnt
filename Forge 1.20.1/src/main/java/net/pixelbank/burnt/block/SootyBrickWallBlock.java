package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class SootyBrickWallBlock extends WallBlock {
	public SootyBrickWallBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 6f).forceSolidOn());
	}
}