package xyz.Desyncd.harder;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class Harder implements ModInitializer {
    //
    private static final EntityAttributeModifier ATTACK_DAMAGE = new EntityAttributeModifier(
            Identifier.of("harder", "zombie_extra_attack_damage"),
            3.0,
            EntityAttributeModifier.Operation.ADD_VALUE
    );
    // Ender Dragon Max Health Increase
    private static final EntityAttributeModifier MAX_HEALTH = new EntityAttributeModifier(
            Identifier.of("harder", "ender_dragon_extra_max_health"),
            200.0,
            EntityAttributeModifier.Operation.ADD_VALUE
    );
    @Override
    public void onInitialize() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, serverWorld) -> {
            if (entity instanceof ZombieEntity zombie) {
                AttributeContainer attributes = zombie.getAttributes();
                Objects.requireNonNull(attributes.getCustomInstance(EntityAttributes.ATTACK_DAMAGE)).addPersistentModifier(ATTACK_DAMAGE);
                zombie.setHealth(zombie.getMaxHealth());
            }
            if (entity instanceof EnderDragonEntity enderDragon) {
                AttributeContainer attributes = enderDragon.getAttributes();
                Objects.requireNonNull(attributes.getCustomInstance(EntityAttributes.MAX_HEALTH)).addPersistentModifier(MAX_HEALTH);
                enderDragon.setHealth(enderDragon.getMaxHealth());

            }
        });
        EntitySleepEvents.START_SLEEPING.register(new isSleeping());
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> newPlayer.setHealth(12));
    }
}
