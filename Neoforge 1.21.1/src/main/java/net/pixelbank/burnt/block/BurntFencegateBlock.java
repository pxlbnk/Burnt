package net.pixelbank.burnt.block;


import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class BurntFencegateBlock extends FenceGateBlock {
	public BurntFencegateBlock() {
		super(WoodType.OAK, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).sound(SoundType.WOOD).strength(1.5f, 2f).ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}


	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}


	@Override
	public net.minecraft.world.level.block.state.BlockState updateShape(net.minecraft.world.level.block.state.BlockState state, net.minecraft.core.Direction facing, net.minecraft.world.level.block.state.BlockState facingState, net.minecraft.world.level.LevelAccessor world, net.minecraft.core.BlockPos currentPos, net.minecraft.core.BlockPos facingPos) {
		return net.pixelbank.burnt.util.BurntSableGuard.keepInSubLevel(
				super.updateShape(state, facing, facingState, world, currentPos, facingPos),
				state, world, currentPos);
	}

	// Can crumble to charcoal instead of dropping itself - see BurntAshDrops
	// Lives in getDrops so /loot and tree-felling mods get the same result
	@Override
	public java.util.List<net.minecraft.world.item.ItemStack> getDrops(BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder builder) {
		java.util.List<net.minecraft.world.item.ItemStack> drops = super.getDrops(state, builder);
		net.pixelbank.burnt.util.BurntAshDrops.applyBurntDrops(drops, builder);
		return drops;
	}
}
