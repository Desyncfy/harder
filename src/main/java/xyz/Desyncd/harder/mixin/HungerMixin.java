package xyz.Desyncd.harder.mixin;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public class HungerMixin {
    @Shadow
    private float saturationLevel;

    @Inject(method = "update", at = @At("TAIL"))
    public void update(ServerPlayerEntity player, CallbackInfo ci) {
        this.saturationLevel = 0.0F; // cancel all saturation
    }
}
