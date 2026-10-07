package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class SootyMossyStoneBrickWallBlock extends WallBlock {
	public SootyMossyStoneBrickWallBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 6f).forceSolidOn());
	}
}