package com.carbonlollipop.ladder.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CompassEvent implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null) { return; }
        if(e.getClickedInventory().getHolder() instanceof CompassUI) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            if(e.getCurrentItem() == null) { return; }
            p.closeInventory();
            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            switch (e.getCurrentItem().getType()) {
                case BEDROCK:
                    p.teleport(new Location(Bukkit.getWorld("world"), 0.5, 2, 0.5));
                    break;
                case SLIME_BALL:
                    p.teleport(new Location(Bukkit.getWorld("world"), 55.5, 28, 51.5));
                    break;
                case OAK_LEAVES:
                    p.teleport(new Location(Bukkit.getWorld("world"), 150.5, 19, 53.5));
                    break;
                case STONE:
                    p.teleport(new Location(Bukkit.getWorld("world"), 159.5, 30, -39.5));
                    break;
                case GRASS_BLOCK:
                    p.teleport(new Location(Bukkit.getWorld("world"), -42.5, 41, -114.5));
                    break;
                case COAL:
                    p.teleport(new Location(Bukkit.getWorld("world"), -13.5, 28, 70.5));
                    break;
                case GHAST_TEAR:
                    p.teleport(new Location(Bukkit.getWorld("world"), 72.5, 50, -151.5));
                    break;
                case STRING:
                    p.teleport(new Location(Bukkit.getWorld("world"), 111.5, 38, -42.5));
                    break;
                case CLOCK:
                    p.teleport(new Location(Bukkit.getWorld("world"), -42.5, 25, 53.5));
                    break;
                case SNOWBALL:
                    p.teleport(new Location(Bukkit.getWorld("world"), 61.5, 33, -50.5));
                    break;
                default:
                    break;
            }
        }
    }
}
