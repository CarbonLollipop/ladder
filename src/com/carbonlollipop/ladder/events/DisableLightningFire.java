package com.carbonlollipop.ladder.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class DisableLightningFire implements Listener {
    @EventHandler
    public static void be(BlockIgniteEvent event) {
        if(event.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING) {
            event.setCancelled(true);
        }
    }
}
