package com.carbonlollipop.ladder.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class DisablePlaceAndBreak implements Listener {
    @EventHandler
    public static void move(BlockBreakEvent event) {
        if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
        if(event.getBlock().getLocation().getBlockX() >= -85 && event.getBlock().getLocation().getBlockX() <= 2 && event.getBlock().getLocation().getBlockZ() >= -182 && event.getBlock().getLocation().getBlockZ() <= -116) {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public static void move(BlockPlaceEvent event) {
        if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
        if(event.getBlock().getLocation().getBlockX() >= -85 && event.getBlock().getLocation().getBlockX() <= 2 && event.getBlock().getLocation().getBlockZ() >= -182 && event.getBlock().getLocation().getBlockZ() <= -116) {
            event.setCancelled(false);
        }
    }
}
