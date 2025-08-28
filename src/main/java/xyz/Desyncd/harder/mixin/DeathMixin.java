package xyz.Desyncd.harder.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(LivingEntity.class)
public class DeathMixin {
    /**
     * @author Desyncfy
     * @reason remove totems
     */
    @Overwrite
    private boolean tryUseDeathProtector(DamageSource source) {
        return false;
    }
}
