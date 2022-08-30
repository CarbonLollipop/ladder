package com.carbonlollipop.ladder.playermain.info.inventory;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {
    public static ItemStack GetMainHand(Entity entity) {
        return ((Player) entity).getInventory().getItemInMainHand();
    }
}
