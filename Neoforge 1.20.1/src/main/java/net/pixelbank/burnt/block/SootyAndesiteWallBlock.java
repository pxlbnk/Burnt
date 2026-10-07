package net.pixelbank.burnt.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;

public class SootyAndesiteWallBlock extends WallBlock {
	public SootyAndesiteWallBlock() {
		super(BlockBehaviour.Properties.of().strength(1.5f, 6f).forceSolidOn());
	}
}