package xyz.Desyncd.harder;

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class isSleeping implements EntitySleepEvents.StartSleeping {
    /**
     * Called when an entity starts to sleep.
     *
     * @param entity      the sleeping entity
     * @param sleepingPos the {@linkplain LivingEntity#getSleepingPosition() sleeping position} of the entity
     */
    // Cancel Sleeping
    @Override
    public void onStartSleeping(LivingEntity entity, BlockPos sleepingPos) {
        if (entity.isPlayer()) {
            entity.teleport(Objects.requireNonNull(entity.getServer()).getOverworld(), entity.getX(), entity.getY(), entity.getZ(), PositionFlag.getFlags(0), entity.getYaw(), entity.getYaw(), false);
        }
    }
}
