package net.pixelbank.burnt.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;


public class FleeFireGoal extends Goal {

    private static final TagKey<Block> FIRE_TAG = TagKey.create(
            Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("burnt", "fire"));

    /** Horizontal half-extent of the fire scan box. (2r+1)^2 x 7 blocks per scan. */
    private static final int SCAN_RADIUS = 5;

    // Ticks between fire scans - the box scan is the most expensive thing on the server thread here
    // canUse is polled every tick per idle mob, so scan once a second and reuse the verdict
    private static final int SCAN_INTERVAL = 20;

    private final PathfinderMob mob;
    private final double speed;

    // Target flee coordinates, calculated by the scan.
    private double fleeX, fleeY, fleeZ;

    // Throttle state: last scan verdict, reused between scans.
    private boolean cachedCanUse = false;

    public FleeFireGoal(PathfinderMob mob, double speed) {
        this.mob = mob;
        this.speed = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Throttle the box scan, staggered per mob by entity id so a loaded village doesn't scan all at once
        Level level = mob.level();
        if ((level.getGameTime() + mob.getId()) % SCAN_INTERVAL != 0) {
            return cachedCanUse;
        }
        cachedCanUse = scanForFire(level);
        return cachedCanUse;
    }

    /** Full fire scan + flee-target computation. The expensive path - runs ~1x/sec. */
    private boolean scanForFire(Level level) {
        BlockPos mobPos = mob.blockPosition();

        double sumX = 0, sumZ = 0;
        int count = 0;

        // betweenClosed reuses a single MutableBlockPos internally - no per-block allocation.
        for (BlockPos pos : BlockPos.betweenClosed(
                mobPos.offset(-SCAN_RADIUS, -2, -SCAN_RADIUS),
                mobPos.offset(SCAN_RADIUS,  4,  SCAN_RADIUS))) {
            if (level.getBlockState(pos).is(FIRE_TAG)) {
                sumX += pos.getX();
                sumZ += pos.getZ();
                count++;
            }
        }

        if (count == 0) return false;

        // Direction away from fire centroid (horizontal only)
        double centroidX = sumX / count;
        double centroidZ = sumZ / count;
        Vec3 mobPos3 = mob.position();

        double dx = mobPos3.x - centroidX;
        double dz = mobPos3.z - centroidZ;
        double len = Math.sqrt(dx * dx + dz * dz);

        if (len < 0.001) {
            // Mob is right on top of the centroid - flee in the direction they're facing
            dx = -Math.sin(Math.toRadians(mob.getYRot()));
            dz =  Math.cos(Math.toRadians(mob.getYRot()));
            len = 1.0;
        }

        fleeX = mobPos3.x + (dx / len) * 16.0;
        fleeY = mobPos3.y;
        fleeZ = mobPos3.z + (dz / len) * 16.0;

        // Only activate if a path to the flee point actually exists
        return mob.getNavigation().createPath(fleeX, fleeY, fleeZ, 0) != null;
    }

    @Override
    public boolean canContinueToUse() {
        // Keep fleeing until we arrive; canUse() re-checks fire on its scan interval
        return !mob.getNavigation().isDone();
    }

    @Override
    public void start() {
        mob.getNavigation().moveTo(fleeX, fleeY, fleeZ, speed);
    }

    @Override
    public void stop() {
        mob.getNavigation().stop();
        cachedCanUse = false;
    }
}
