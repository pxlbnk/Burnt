package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.block.VerticalSlabBlock;
import net.pixelbank.burnt.block.VerticalSlabType;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.util.BurntBlockUtil;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import java.util.Locale;

// Quark-style vertical slab to smoldering vertical slab, stage 1
// No sooty stage, it goes straight to smoldering
// The type enum is a different class per mod, so it's mapped across by serialized name

public class VerticalSlabsStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isClientSide())
			return;
		BlockPos _bp = BlockPos.containing(x, y, z);
		BlockState _bso = world.getBlockState(_bp);
		BlockState _bs = BurntModBlocks.SMOLDERING_VERTICAL_SLAB.defaultBlockState();
		_bs = BurntBlockUtil.copyProperties(_bso, _bs);
		_bs = mapType(_bso, _bs);
		if (_bs.getBlock().getStateDefinition().getProperty("stage") instanceof IntegerProperty _stage && _stage.getPossibleValues().contains(1))
			_bs = _bs.setValue(_stage, 1);
		world.setBlock(_bp, _bs, 3);
	}

	private static BlockState mapType(BlockState src, BlockState dst) {
		Property<?> srcType = src.getBlock().getStateDefinition().getProperty("type");
		if (srcType == null)
			return dst;
		Comparable<?> value = src.getValue(srcType);
		String name = (value instanceof StringRepresentable sr) ? sr.getSerializedName() : String.valueOf(value).toLowerCase(Locale.ROOT);
		for (VerticalSlabType type : VerticalSlabType.values()) {
			if (type.getSerializedName().equals(name))
				return dst.setValue(VerticalSlabBlock.TYPE, type);
		}
		return dst;
	}
}
