package net.pixelbank.burnt;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class BurntIgnitionHooks {

    private static final TagKey<Block> REPLACEABLE_GASES =
        TagKey.create(net.minecraft.core.registries.Registries.BLOCK,
            new ResourceLocation("burnt", "replaceable_gases"));

    private static final TagKey<Block> SOUL_BASE =
        TagKey.create(net.minecraft.core.registries.Registries.BLOCK,
            new ResourceLocation("burnt", "soul_fire_base"));

    /** true if the cell can be replaced by fire (real air, vanilla replaceable, or whitelisted gas) */
    public static boolean isReplaceable(LevelAccessor world, BlockPos pos) {
        BlockState s = world.getBlockState(pos);
        return s.isAir() || s.canBeReplaced() || s.is(REPLACEABLE_GASES);
    }

    /** Pick the correct target cell for placing fire */
    public static BlockPos resolveTarget(LevelAccessor world, BlockPos hitPos, Direction face) {
        if (isReplaceable(world, hitPos)) return hitPos;

        BlockPos off = hitPos.relative(face);
        if (isReplaceable(world, off)) return off;

        // Gentle fallback for UP impacts where the hit cell is solid and the air above is gas-like
        if (face == Direction.UP) {
            BlockPos above = hitPos.above();
            if (isReplaceable(world, above)) return above;
        }
        return null;
    }

    /** "can survive" equivalent for our custom ground fire */
    public static boolean canGroundFireSurvive(LevelAccessor world, BlockPos pos) {
        BlockPos below = pos.below();
        BlockState belowState = world.getBlockState(below);
        boolean belowSolidTop = belowState.isFaceSturdy(world, below, Direction.UP);

        boolean anyFlammable =
            isFlammable(world.getBlockState(pos.north())) ||
            isFlammable(world.getBlockState(pos.south())) ||
            isFlammable(world.getBlockState(pos.east()))  ||
            isFlammable(world.getBlockState(pos.west()))  ||
            isFlammable(world.getBlockState(pos.above())) ||
            isFlammable(world.getBlockState(pos.below()));

        return belowSolidTop || anyFlammable;
    }

    private static boolean isFlammable(BlockState state) {
        FlammableBlockRegistry.Entry e = FlammableBlockRegistry.getDefaultInstance().get(state.getBlock());
        return e != null && e.getBurnChance() > 0;
    }

    /** Should be soul fire here? */
    public static boolean isSoulBase(LevelAccessor world, BlockPos pos) {
        BlockState below = world.getBlockState(pos.below());
        return below.is(Blocks.SOUL_SOIL) || below.is(SOUL_BASE);
    }
}
