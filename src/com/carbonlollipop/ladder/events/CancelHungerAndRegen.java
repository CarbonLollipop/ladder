package com.carbonlollipop.ladder.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class CancelHungerAndRegen implements Listener {
    @EventHandler
    public static void be(EntityRegainHealthEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public static void mang(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
