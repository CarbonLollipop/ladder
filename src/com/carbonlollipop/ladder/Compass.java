package com.carbonlollipop.ladder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Compass implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDropItemEvent(final PlayerDropItemEvent e) {
        if(e.getItemDrop().getItemStack().getType() == Material.COMPASS) {
            e.getPlayer().getInventory().setItem(8, new ItemStack(Material.COMPASS));
            new BukkitRunnable() {
                public void run() {
                    e.getItemDrop().remove();
                }
            }.runTaskLater(Bukkit.getPluginManager().getPlugin("Ladder"), 1);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDropItemEvent(final PlayerInteractEvent e) {
        if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS) {
            CompassUI ui = new CompassUI();
            e.getPlayer().openInventory(ui.getInventory());
        }
    }
}
