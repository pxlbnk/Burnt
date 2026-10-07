package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class SootyMossyCobblestoneWallBlock extends WallBlock {
	public SootyMossyCobblestoneWallBlock() {
		super(BlockBehaviour.Properties.of().strength(2f, 6f).forceSolidOn());
	}
}