package net.pixelbank.burnt.mixins;

import net.pixelbank.burnt.ai.GolemExtinguishGoal;
import net.minecraft.world.entity.animal.IronGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IronGolem.class)
public class IronGolemMixin {

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void burnt$addGolemGoals(CallbackInfo ci) {
        IronGolem self = (IronGolem) (Object) this;
        self.goalSelector.addGoal(1, new GolemExtinguishGoal(self));
    }
}
