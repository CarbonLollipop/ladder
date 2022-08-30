package com.carbonlollipop.ladder.librarymain.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Events {
    public static boolean causedBy(EntityDamageEvent event, DamageCause cause) {
        return event.getCause() == cause;
    }

    public static boolean causedBy(EntityDamageEvent event, DamageCause[] cause) {
        for(DamageCause damageCause : cause) {
            if(event.getCause() == damageCause) {
                return true;
            }
        }

        return false;
    }

    public static boolean causedBy(EntityDamageByEntityEvent event, DamageCause cause) {
        return event.getCause() == cause;
    }

    public static boolean causedBy(BlockIgniteEvent event, IgniteCause cause) {
        return event.getCause() == cause;
    }

    public static boolean lastDCause(LivingEntity e, DamageCause cause) {
        return e.getLastDamageCause().getCause() == cause;
    }
}
