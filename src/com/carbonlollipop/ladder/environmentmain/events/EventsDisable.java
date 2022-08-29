package com.carbonlollipop.ladder.environmentmain.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;

import com.carbonlollipop.ladder.librarymain.events.Events;

public class EventsDisable implements Listener {
    @EventHandler
    public static void BlockIgniteEvent(BlockIgniteEvent event) {
        if(Events.causedBy(event, IgniteCause.LIGHTNING)) {
            event.setCancelled(true);
        }
    }
}
