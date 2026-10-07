package net.pixelbank.burnt.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;

import net.pixelbank.burnt.BurntLavaFireBlocker;
import net.pixelbank.burnt.BurntLightningRodGuard;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.init.BurntModBlocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// When vanilla fire is placed, swap it for our fire block
// Or remove it near lava when lava burn is off
@Mixin(FireBlock.class)
public class FireBlockOnPlaceMixin {

    @Inject(method = "onPlace", at = @At("TAIL"))
    private void burnt$onFirePlaced(BlockState newState, Level level, BlockPos pos,
                                    BlockState oldState, boolean movedByPiston, CallbackInfo ci) {
        if (level.isClientSide()) return;
        if (net.pixelbank.burnt.util.BurntDimensions.isDisabled(level)) return; // per-dimension kill switch
        if (oldState.is(newState.getBlock())) return; // age/state change, not a new placement

        // Re-read: another system may have already modified the block
        if (!level.getBlockState(pos).is(Blocks.FIRE)) return;

        // LAVA_BURN off: delete lava-adjacent fire unless a player placed it deliberately
        // Must stay ABOVE the netherrack, vanilla_fire_burns_on and obsidian returns below
        // A lava pool sits in exactly those blocks, so running after them let fire beside a pool survive and spread
        if (!BurntBasicConfigConfiguration.LAVA_BURN.get() && BurntLavaFireBlocker.isNearLava(level, pos)) {
            if (!BurntLavaFireBlocker.isEntityPlaced(level, pos)) {
                level.removeBlock(pos, false);
                if ((level.getGameTime() & 63L) == 0L) {
                    BurntLavaFireBlocker.cleanupStale(level.getGameTime());
                }
                return;
            }
            // entity-placed near lava: fall through to VANILLA_BURN swap below
        }

        if (level.getBlockState(pos.below()).is(Blocks.NETHERRACK)) return;

        // Non-burning bases keep vanilla fire, so mods watching for it expiring still work
        // Extend #burnt:vanilla_fire_burns_on by datapack to add more
        if (level.getBlockState(pos.below()).is(net.pixelbank.burnt.util.BurntTags.BLOCK_VANILLA_FIRE_BURNS_ON)) return;

        // Leave end-crystal fire as vanilla - the crystal keeps re-creating it and the swap flickers
        if (!level.getEntitiesOfClass(net.minecraft.world.entity.boss.enderdragon.EndCrystal.class,
                new net.minecraft.world.phys.AABB(pos).inflate(0.5)).isEmpty()) return;

        // Leave fire beside obsidian alone, or smaller-portal mods never detect it and the portal won't light
        for (net.minecraft.core.Direction dir : net.minecraft.core.Direction.values()) {
            net.minecraft.world.level.block.Block n = level.getBlockState(pos.relative(dir)).getBlock();
            if (n == Blocks.OBSIDIAN || n == Blocks.CRYING_OBSIDIAN) return;
        }

        // Swap vanilla FIRE to the mod's fire block (only when the mod is enabled)
        if (!BurntBasicConfigConfiguration.BURNT_ON.get()) return;
        if (!BurntBasicConfigConfiguration.VANILLA_BURN.get()) return;
        // Claimed ground keeps vanilla fire - the claim mod polices that, and FIRESTARTER escaped it
        if (net.pixelbank.burnt.util.BurntProtection.isProtected(level, pos)) return;

        // Lightning ignition is off - leave the bolt fire as ordinary vanilla fire.
        if (BurntLightningRodGuard.isNearNoBurnStrike(level.getGameTime(), pos)) return;

        level.setBlock(pos, BurntModBlocks.FIRESTARTER.defaultBlockState(), 11);
    }
}
