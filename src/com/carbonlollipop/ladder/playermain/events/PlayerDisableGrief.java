package com.carbonlollipop.ladder.playermain.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.carbonlollipop.ladder.librarymain.util.LocationUtil;
import com.carbonlollipop.ladder.playermain.info.Gamemode;

public class PlayerDisableGrief implements Listener {
    @EventHandler
    public static void move(BlockBreakEvent event) {
        if(!Gamemode.isGamemode(event, GameMode.CREATIVE)) {
            event.setCancelled(true);
        }

        if(LocationUtil.isInBounds(-85, 2, -182, -116, event.getBlock().getLocation())) {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public static void move(BlockPlaceEvent event) {
        if(!Gamemode.isGamemode(event, GameMode.CREATIVE)) {
            event.setCancelled(true);
        }

        if(LocationUtil.isInBounds(-85, 2, -182, -116, event.getBlock().getLocation())) {
            event.setCancelled(false);
        }
    }
}
