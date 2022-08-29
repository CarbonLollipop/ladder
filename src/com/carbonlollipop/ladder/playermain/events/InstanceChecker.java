package com.carbonlollipop.ladder.playermain.events;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class InstanceChecker {
    public static boolean isPlayer(EntityDamageEvent event) {
        return event.getEntity() instanceof Player;
    }

    public static boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }
}
