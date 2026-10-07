package net.pixelbank.burnt.compat.create;

import com.simibubi.create.api.behaviour.movement.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.procedures.BurnBabyProcedure;
import net.pixelbank.burnt.procedures.SmolderingPlanksParticlesProcedure;
import net.pixelbank.burnt.util.BurntTags;

import java.util.Random;

/**
 * Create MovementBehaviour for the smoldering Create blocks.
 *
 * Create takes a block off the world and onto the contraption entity, so normal block ticks stop
 * firing and the smoldering tick procedure can't run. This bridges that: particles at the animated
 * world position each tick, and on the server a small chance to ignite a nearby world block.
 * Respects mobGriefing for ignition.
 */
public class SmolderingMovementBehaviour implements MovementBehaviour {

    /** Per-tick probability of attempting one fire-spread roll. */
    private static final float IGNITE_CHANCE = 0.05f;

    private static final Random RNG = new Random();

    @Override
    public void tick(MovementContext context) {
        // Try/catch so one bad context - a NaN from a physics mod, an unloaded chunk - can't crash the
        // host mod or take down the JVM through native renderer code
        try {
            tickGuarded(context);
        } catch (Throwable t) {
            BurntMod.LOGGER.debug("[Burnt] SmolderingMovementBehaviour tick suppressed: {}", t.toString());
        }
    }

    private void tickGuarded(MovementContext context) {
        if (context == null || context.world == null) return;
        Vec3 pos = context.position;
        if (pos == null) return;

        // Physics extensions can briefly write NaN to a contraption position during state recovery
        // NaN reaching the GL matrices crashes the native renderer, so bail before that
        if (Double.isNaN(pos.x) || Double.isNaN(pos.y) || Double.isNaN(pos.z)
                || Double.isInfinite(pos.x) || Double.isInfinite(pos.y) || Double.isInfinite(pos.z)) {
            return;
        }

        Level level = context.world;

        // Particles on the client at the animated world position - the procedure handles the chance
        if (level.isClientSide) {
            SmolderingPlanksParticlesProcedure.execute(level,
                    pos.x, pos.y, pos.z, context.state);
            return;
        }

        // Server-side: rate-limited fire spread.
        if (!level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) return;
        if (RNG.nextFloat() > IGNITE_CHANCE) return;

        // Pick a random neighbour in a 3x3x3 area around the block.
        int dx = RNG.nextInt(3) - 1;
        int dy = RNG.nextInt(3) - 1;
        int dz = RNG.nextInt(3) - 1;
        if (dx == 0 && dy == 0 && dz == 0) return;

        BlockPos target = BlockPos.containing(pos.x + dx, pos.y + dy, pos.z + dz);
        BlockState targetState = level.getBlockState(target);

        // A neighbour in any burns_to_* tag goes through BurnBaby, which picks the right variant
        // This is what makes an airship dragging fire over a meadow leave scorched ground
        if (BurntTags.isCompatBurnable(targetState)) {
            BurnBabyProcedure.execute(level, target.getX(), target.getY(), target.getZ());
            return;
        }

        // Otherwise place fire if it's air and vanilla's canSurvive says fire can legally exist there
        if (targetState.isAir()) {
            BlockState fire = Blocks.FIRE.defaultBlockState();
            if (fire.canSurvive(level, target)) {
                level.setBlock(target, fire, 11);
            }
        }
    }
}
