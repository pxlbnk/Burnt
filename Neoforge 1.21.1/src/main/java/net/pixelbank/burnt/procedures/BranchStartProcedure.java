package net.pixelbank.burnt.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.pixelbank.burnt.util.BurntTags;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

// Converts a Dynamic Trees branch into dt_burnt's smoldering branch, keeping DT's radius
// Trunk shells have no radius, so they redirect to the real branch core and recurse
// Adjacent DT leaves all convert in one tick, beating DT's lost-family decay
// Full-radius branches also get fire above, so a thick trunk reads as on fire
// Runtime block lookups only, so no compile-time dependency on the addon
public class BranchStartProcedure {

    private static final ResourceLocation SMOLDERING_BRANCH_ID =
            ResourceLocation.fromNamespaceAndPath("dt_burnt", "smoldering_branch");
    private static final ResourceLocation BURNT_BRANCH_ID =
            ResourceLocation.fromNamespaceAndPath("dt_burnt", "burnt_branch");
    private static final ResourceLocation TRUNK_SHELL_ID =
            ResourceLocation.fromNamespaceAndPath("dynamictrees", "trunk_shell");
    private static final ResourceLocation SMOLDERING_TRUNK_SHELL_ID =
            ResourceLocation.fromNamespaceAndPath("dt_burnt", "smoldering_trunk_shell");

    /** A real branch block is anything that exposes a {@code radius} property. */
    private static int readRadius(BlockState state) {
        Property<?> prop = state.getBlock().getStateDefinition().getProperty("radius");
        return prop instanceof IntegerProperty ip ? state.getValue(ip) : 0;
    }

    public static void execute(LevelAccessor world, double x, double y, double z) {
        Block smoldering = BuiltInRegistries.BLOCK.get(SMOLDERING_BRANCH_ID);
        if (smoldering == null || smoldering == Blocks.AIR) {
            // dt_burnt addon not loaded - nothing to do.
            return;
        }

        BlockPos _bp = BlockPos.containing(x, y, z);
        BlockState _bso = world.getBlockState(_bp);

        // Anything without a radius - a trunk shell, or one we already converted - would become a
        // 1-radius branch, so redirect to the real core in the surrounding 3x3 or bail
        if (readRadius(_bso) <= 0) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dz == 0) continue;
                    BlockPos coreCandidate = _bp.offset(dx, 0, dz);
                    BlockState candState = world.getBlockState(coreCandidate);
                    if (candState.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_BRANCH) && readRadius(candState) > 0) {
                        execute(world, coreCandidate.getX(), coreCandidate.getY(), coreCandidate.getZ());
                        return;
                    }
                }
            }
            return; // not a real branch and no core nearby - bail
        }

        // smoldering_branch is a ThickBranchBlock too, so radius and waterlogged copy straight across
        BlockState _bsm = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, smoldering.defaultBlockState());
        // Flip every leaf on the tree to PERSISTENT before converting, so they don't decay-cascade when
        // DT's family check sees the new NULL_FAMILY block
        // Later conversions short-circuit on hitting an already-converted branch
        persistTreeLeaves(world, _bp);

        // Flag 2, client sync only - a neighbour notify makes adjacent DT leaves see a lost family
        // branch and wipe an entire canopy in one tick
        world.setBlock(_bp, _bsm, 2);

        convertAdjacentShells(world, _bp);
        convertCanopyLeaves(world, _bp);

        // Radius 7+ gets a flame on top as a visible "tree on fire" signal
        // Thinner branches don't - placed fire would float over the geometry
        if (readRadius(_bsm) >= 7) {
            AnyFlamesPlacementProcedure.execute(world, x, y, z);
        }

        // Bottom branch: route the rooty block below through the burn pipeline to sever the family link
        // Once its BlockEntity is gone, DT can't auto-decay the tree mid-burn
        BlockPos below = _bp.below();
        if (world.getBlockState(below).is(BurntTags.BLOCK_BURNS_TO_BURNT_DIRT)) {
            BurnBabyProcedure.execute(world, below.getX(), below.getY(), below.getZ());
        }
    }

    // BFS the connected DT branch graph, flipping adjacent leaves to PERSISTENT so they don't
    // random-tick-decay once DT sees the new NULL_FAMILY core
    // Stops at an already-converted branch - that tree's leaves are already persistent
    // Bounded by PERSIST_BFS_BUDGET so a pathologically large tree stays predictable
    private static final int PERSIST_BFS_BUDGET = 1024;

    private static void persistTreeLeaves(LevelAccessor world, BlockPos start) {
        Queue<BlockPos> q = new ArrayDeque<>();
        Set<Long> seenBranches = new HashSet<>();
        Set<Long> seenLeaves = new HashSet<>();
        q.add(start.immutable());
        seenBranches.add(start.asLong());

        int budget = PERSIST_BFS_BUDGET;
        while (!q.isEmpty() && budget-- > 0) {
            BlockPos b = q.poll();
            for (Direction dir : Direction.values()) {
                BlockPos n = b.relative(dir);
                long nKey = n.asLong();
                BlockState ns = world.getBlockState(n);

                ResourceLocation id = BuiltInRegistries.BLOCK.getKey(ns.getBlock());
                if (SMOLDERING_BRANCH_ID.equals(id) || BURNT_BRANCH_ID.equals(id)) {
                    // Tree already claimed by a prior conversion - leaves were persisted then.
                    return;
                }

                // Vanilla DT branch (or trunk_shell): keep walking the tree.
                if (ns.is(BurntTags.BLOCK_BURNS_TO_SMOLDERING_BRANCH)) {
                    if (seenBranches.add(nKey)) q.add(n.immutable());
                    continue;
                }

                // Leaf: persist if it has the property and isn't already persistent.
                if (ns.getBlock() instanceof LeavesBlock
                        && ns.hasProperty(LeavesBlock.PERSISTENT)
                        && !ns.getValue(LeavesBlock.PERSISTENT)
                        && seenLeaves.add(nKey)) {
                    // Flag 2, client sync only - no neighbour updates to cascade hydration checks onto leaves
                    world.setBlock(n, ns.setValue(LeavesBlock.PERSISTENT, true), 2);
                }
            }
        }
    }

    /**
     * Swap the dynamictrees:trunk_shell blocks around a converted core for our smoldering ones,
     * copying coredir and waterlogged, so they render as overflow for the core's model.
     */
    private static void convertAdjacentShells(LevelAccessor world, BlockPos core) {
        Block oldShell = BuiltInRegistries.BLOCK.get(TRUNK_SHELL_ID);
        Block newShell = BuiltInRegistries.BLOCK.get(SMOLDERING_TRUNK_SHELL_ID);
        if (oldShell == null || oldShell == Blocks.AIR || newShell == null || newShell == Blocks.AIR) {
            return;
        }
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (dx == 0 && dz == 0) continue;
                BlockPos shellPos = core.offset(dx, 0, dz);
                BlockState shellState = world.getBlockState(shellPos);
                if (!shellState.is(oldShell)) continue;

                BlockState target = newShell.defaultBlockState();
                for (Property<?> prop : shellState.getProperties()) {
                    @SuppressWarnings("rawtypes")
                    Property matching = target.getBlock().getStateDefinition().getProperty(prop.getName());
                    if (matching != null) {
                        try {
                            target = target.setValue(matching, shellState.getValue(prop));
                        } catch (Exception ignored) {
                        }
                    }
                }
                world.setBlock(shellPos, target, 3);
            }
        }
    }

    /**
     * Convert every DT leaf in a 3x3x3 around the core in one sweep, routed through BurnBaby so the
     * leaf tier tags pick the right variant. Doing them all before any decay tick suppresses the
     * "big chunks vanishing" effect.
     */
    private static void convertCanopyLeaves(LevelAccessor world, BlockPos core) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) continue;
                    BlockPos leafPos = core.offset(dx, dy, dz);
                    BlockState leafState = world.getBlockState(leafPos);
                    if (!leafState.is(BurntTags.BLOCK_BURNS_TO_LARGE_LEAVES)
                            && !leafState.is(BurntTags.BLOCK_BURNS_TO_MEDIUM_LEAVES)
                            && !leafState.is(BurntTags.BLOCK_BURNS_TO_FINE_LEAVES)) {
                        continue;
                    }
                    BurnBabyProcedure.execute(world, leafPos.getX(), leafPos.getY(), leafPos.getZ());
                }
            }
        }
    }
}
