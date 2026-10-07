package net.pixelbank.burnt.block;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

// Orientation of a vertical slab, mirroring Quark's VerticalSlabType
// Serialized names line up 1:1, so the burn dispatch maps a Quark slab's type onto ours by name

public enum VerticalSlabType implements StringRepresentable {
	NORTH(Direction.NORTH),
	SOUTH(Direction.SOUTH),
	WEST(Direction.WEST),
	EAST(Direction.EAST),
	DOUBLE(null);

	private final String name;
	public final Direction direction;
	public final VoxelShape shape;

	VerticalSlabType(Direction direction) {
		this.direction = direction;
		if (direction == null) {
			this.name = "double";
			this.shape = Shapes.block();
		} else {
			this.name = direction.getSerializedName();
			// Quark names type=<dir> for the OPPOSITE half, so the shape must sit on the half the model draws
			// Get it wrong and face-culling leaves a blank gap between two slabs
			boolean high = direction.getAxisDirection() == Direction.AxisDirection.NEGATIVE;
			double min = high ? 8.0 : 0.0;
			double max = high ? 16.0 : 8.0;
			this.shape = switch (direction.getAxis()) {
				case X -> Block.box(min, 0.0, 0.0, max, 16.0, 16.0);
				case Z -> Block.box(0.0, 0.0, min, 16.0, 16.0, max);
				default -> Shapes.block();
			};
		}
	}

	@Override
	public String getSerializedName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean isDouble() {
		return this == DOUBLE;
	}

	public static VerticalSlabType fromDirection(Direction direction) {
		for (VerticalSlabType type : values()) {
			if (type.direction == direction)
				return type;
		}
		return NORTH;
	}
}
