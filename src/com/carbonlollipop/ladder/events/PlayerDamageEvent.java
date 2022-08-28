package com.carbonlollipop.ladder.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageEvent implements Listener {
    @EventHandler
    public static void Ouch(EntityDamageEvent event) {
        if((!(event.getEntity() instanceof Player)))
            return;
        Player p = (Player) event.getEntity();
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            event.setDamage(0);
        }
        if(((Player) event.getEntity()).getPlayer().getInventory().getChestplate() != null && ((Player) event.getEntity()).getPlayer().getInventory().getChestplate().getType() == Material.NETHERITE_CHESTPLATE && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            event.setCancelled(true);
            ((Player) event.getEntity()).damage(event.getDamage());
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            event.setDamage(p.getHealth());
        }
    }
}
