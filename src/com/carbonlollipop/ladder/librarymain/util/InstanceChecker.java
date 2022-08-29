package com.carbonlollipop.ladder.librarymain.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.carbonlollipop.ladder.CompassUI;

public class InstanceChecker {
    public static boolean isPlayer(EntityDamageEvent event) {
        return event.getEntity() instanceof Player;
    }

    public static boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    public static boolean isCompassUI(InventoryClickEvent e) {
        return e.getClickedInventory().getHolder() instanceof CompassUI;
    }
}
