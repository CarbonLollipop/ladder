package com.carbonlollipop.ladder.playermain.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.carbonlollipop.ladder.librarymain.events.Events;

public class PlayerDisable implements Listener {
    @EventHandler
    public static void Ouch(EntityDamageEvent event) {
        if(!InstanceChecker.isPlayer(event)) { return; }

        Player p = (Player) event.getEntity();

        if(Events.causedBy(event, DamageCause.ENTITY_EXPLOSION)) {
            event.setDamage(0);
        }

        if(
        ((Player) event.getEntity()).getPlayer().getInventory().getChestplate().getType() == Material.NETHERITE_CHESTPLATE
        && event.getCause() == DamageCause.ENTITY_ATTACK) {
            event.setCancelled(true);
            ((Player) event.getEntity()).damage(event.getDamage());
        }

        if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }

        if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            event.setDamage(p.getHealth());
        }
    }

    @EventHandler
    public static void be(EntityRegainHealthEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public static void mang(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
