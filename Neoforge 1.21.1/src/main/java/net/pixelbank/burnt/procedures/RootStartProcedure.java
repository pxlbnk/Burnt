package net.pixelbank.burnt.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

/**
 * Convert a Dynamic Trees root block to dt_burnt:smoldering_root, keeping radius, grounded and
 * waterlogged. Runtime lookup, so no compile-time dependency on the addon.
 */
public class RootStartProcedure {

    private static final ResourceLocation SMOLDERING_ROOT_ID =
            ResourceLocation.fromNamespaceAndPath("dt_burnt", "smoldering_root");

    public static void execute(LevelAccessor world, double x, double y, double z) {
        Block target = BuiltInRegistries.BLOCK.get(SMOLDERING_ROOT_ID);
        if (target == null || target == Blocks.AIR) {
            return;
        }

        BlockPos _bp = BlockPos.containing(x, y, z);
        BlockState _bso = world.getBlockState(_bp);
        BlockState _bsm = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, target.defaultBlockState());
        world.setBlock(_bp, _bsm, 3);
    }
}
