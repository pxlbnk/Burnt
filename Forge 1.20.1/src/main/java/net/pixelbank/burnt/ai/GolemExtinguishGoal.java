package net.pixelbank.burnt.ai;

import net.minecraft.core.BlockPos;
import net.pixelbank.burnt.init.BurntModParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.procedures.ExtinguishBlockProcedure;

import java.util.EnumSet;


public class GolemExtinguishGoal extends Goal {

    private static final TagKey<Block> FIRE_TAG = TagKey.create(
            Registries.BLOCK, new ResourceLocation("burnt", "on_fire"));

    /** How far away the golem will notice fire. */
    private static final int SEEK_RADIUS = 6;

    // Ticks between fire scans - canUse is polled every tick, and the cooldown only applies after an extinguish
    // Throttled to about once a second, staggered per golem
    private static final int SCAN_INTERVAL = 20;

    // Throttle state: last scan verdict, reused between scans.
    private boolean cachedCanUse = false;

    /** Close enough to swing at the fire. */
    private static final double SWING_RANGE_SQ = 2.0 * 2.0;

    /** If pathfinding stalls, still extinguish if the fire is this close. */
    private static final double FALLBACK_RANGE_SQ = 4.0 * 4.0;

    /** How long to wait between extinguishes (100 ticks = 5 seconds). */
    private static final long COOLDOWN_TICKS = 100L;

    /** Ticks between swinging and actually putting the fire out. */
    private static final int SWING_DELAY_TICKS = 15;

    private final IronGolem golem;
    private BlockPos targetFire = null;

    // Game-time stamp of the last extinguish, starting far negative so a golem is ready on first load
    private long lastExtinguishTime = Long.MIN_VALUE / 2;

    // Countdown after swing before extinguish fires; -1 = not currently swinging.
    private int swingCountdown = -1;

    public GolemExtinguishGoal(IronGolem golem) {
        this.golem = golem;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    // Lifecycle

    @Override
    public boolean canUse() {
        if (!BurntBasicConfigConfiguration.get().iron_golem_fight_fires) return false;
        long now = golem.level().getGameTime();
        if (now - lastExtinguishTime < COOLDOWN_TICKS) return false;
        // Throttle the box scan, staggered per golem by entity id
        if ((now + golem.getId()) % SCAN_INTERVAL != 0) {
            return cachedCanUse && targetFire != null;
        }
        targetFire = findNearestFire();
        cachedCanUse = targetFire != null;
        return cachedCanUse;
    }

    @Override
    public boolean canContinueToUse() {
        // Keep running during the swing countdown even if the fire has gone out
        if (swingCountdown >= 0) return true;
        if (targetFire == null) return false;
        // Abort if the fire was already extinguished by another means
        return golem.level().getBlockState(targetFire).is(FIRE_TAG);
    }

    @Override
    public void start() {
        if (targetFire != null) {
            golem.getNavigation().moveTo(
                    targetFire.getX() + 0.5,
                    targetFire.getY(),
                    targetFire.getZ() + 0.5,
                    1.0);
        }
    }

    @Override
    public void tick() {
        // Phase 2: swing animation playing, counting down to extinguish
        if (swingCountdown >= 0) {
            // Re-send event 4 every 10 ticks to keep the arm-raise animation looping
            if (swingCountdown % 10 == 0) {
                golem.level().broadcastEntityEvent(golem, (byte) 4);
            }
            if (swingCountdown == 0) {
                if (targetFire != null) {
                    // Campfire-shovel extinguish effect (sizzle sound + large smoke puff)
                    golem.level().levelEvent(1009, targetFire, 0);

                    // Burst of fire-particle embers outward from the block
                    if (golem.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(
                                BurntModParticleTypes.FIRE_PARTICLE,
                                targetFire.getX() + 0.5,
                                targetFire.getY() + 0.5,
                                targetFire.getZ() + 0.5,
                                24,   // count
                                0.4,  // deltaX - spread radius
                                0.4,  // deltaY
                                0.4,  // deltaZ
                                0.15  // speed
                        );
                    }
                    ExtinguishBlockProcedure.execute(
                            golem.level(),
                            targetFire.getX(),
                            targetFire.getY(),
                            targetFire.getZ(),
                            false); // no white spray
                }
                lastExtinguishTime = golem.level().getGameTime();
                targetFire = null;
                swingCountdown = -1;
            } else {
                swingCountdown--;
            }
            return;
        }

        // Phase 1: navigating toward fire
        if (targetFire == null) return;

        // Face the fire while approaching
        golem.getLookControl().setLookAt(
                targetFire.getX() + 0.5,
                targetFire.getY() + 0.5,
                targetFire.getZ() + 0.5,
                30.0f, 30.0f);

        double distSq = golem.distanceToSqr(
                targetFire.getX() + 0.5,
                targetFire.getY() + 0.5,
                targetFire.getZ() + 0.5);

        boolean idealRange   = distSq <= SWING_RANGE_SQ;
        boolean navStuck     = golem.getNavigation().isDone() && distSq <= FALLBACK_RANGE_SQ;

        if (idealRange || navStuck) {
            // In range (or as close as pathfinding allows) - start swing countdown
            golem.getNavigation().stop();
            swingCountdown = SWING_DELAY_TICKS;
        }
    }

    @Override
    public void stop() {
        targetFire = null;
        swingCountdown = -1;
        cachedCanUse = false;
        golem.getNavigation().stop();
    }

    // Helpers

    /** Finds the nearest fire block within range, or null if there isn't one. */
    private BlockPos findNearestFire() {
        Level level = golem.level();
        BlockPos golemPos = golem.blockPosition();
        BlockPos nearest = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.betweenClosed(
                golemPos.offset(-SEEK_RADIUS, -2, -SEEK_RADIUS),
                golemPos.offset( SEEK_RADIUS,  3,  SEEK_RADIUS))) {
            BlockState state = level.getBlockState(pos);
            if (!state.is(FIRE_TAG)) continue;
            // Lava: golem would walk into it
            if (state.is(Blocks.LAVA)) continue;
            // Smoldering coal: intentional furnace-type use, don't disturb
            if (state.getBlock() == BurntModBlocks.SMOLDERING_COAL) continue;
            // Campfires are deliberate player builds, so golems leave them alone
            if (state.getBlock() == Blocks.CAMPFIRE
                    || state.getBlock() == Blocks.SOUL_CAMPFIRE
                    || state.getBlock() == BurntModBlocks.SMOLDERING_CAMPFIRE) continue;
            // Fire on netherrack is eternal, so don't target it and pathfind there forever
            if (state.is(Blocks.FIRE)
                    && level.getBlockState(pos.below()).is(Blocks.NETHERRACK)) continue;
            double distSq = golemPos.distSqr(pos);
            if (distSq < nearestDistSq) {
                nearestDistSq = distSq;
                nearest = pos.immutable();
            }
        }
        // Discard the candidate if the golem can't actually path to it
        if (nearest != null && golem.getNavigation().createPath(nearest, 2) == null) {
            return null;
        }
        return nearest;
    }
}
