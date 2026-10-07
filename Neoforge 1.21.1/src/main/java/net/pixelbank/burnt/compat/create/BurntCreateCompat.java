package net.pixelbank.burnt.compat.create;

import com.simibubi.create.api.behaviour.movement.MovementBehaviour;

import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;

import net.pixelbank.burnt.BurntMod;
import net.pixelbank.burnt.init.BurntModBlocks;

/**
 * Optional bridge to Create (modid "create").
 *
 * Registers a MovementBehaviour for the smoldering Create blocks so they stay alive on an active
 * contraption - spawning particles and spreading fire as it moves.
 *
 * Safe without Create - the isActive() guard keeps its classes untouched. Targets Create 6.0.x;
 * an API change fails registration with a warning rather than taking the mod down.
 */
public final class BurntCreateCompat {

    private static final boolean MOD_PRESENT = ModList.get().isLoaded("create");

    private BurntCreateCompat() {}

    /** Returns true if Create is installed and available. */
    public static boolean isActive() {
        return MOD_PRESENT;
    }

    /** Called once from BurntMod's constructor. No-op without Create, and broadly try/caught. */
    public static void register() {
        if (!MOD_PRESENT) return;

        try {
            SmolderingMovementBehaviour behaviour = new SmolderingMovementBehaviour();
            registerOne(BurntModBlocks.SMOLDERING_SAIL,           behaviour);
            registerOne(BurntModBlocks.SMOLDERING_SAIL_FRAME,     behaviour);
            registerOne(BurntModBlocks.SMOLDERING_SYMMETRIC_SAIL, behaviour);
            registerOne(BurntModBlocks.SMOLDERING_ENVELOPE,       behaviour);
            // Vanilla fire is deliberately not registered - only our own smoldering blocks drive spread
            // from a moving contraption
            BurntMod.LOGGER.info("[Burnt] Create detected - registered contraption behaviour for smoldering blocks.");
        } catch (Throwable t) {
            BurntMod.LOGGER.warn("[Burnt] Failed to register Create MovementBehaviour - Create API may have changed: {}", t.toString());
        }
    }

    private static void registerOne(Block block, SmolderingMovementBehaviour behaviour) {
        if (block == null) return;
        // Create 6.0 API: SimpleRegistry-based registration keyed by Block.
        MovementBehaviour.REGISTRY.register(block, behaviour);
    }
}
