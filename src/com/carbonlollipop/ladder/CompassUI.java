package com.carbonlollipop.ladder;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CompassUI implements InventoryHolder {

    private Inventory inv;

    public CompassUI() {
        inv = Bukkit.createInventory(this, 18, "Travel");
        init();
    }

    private void init() {
        inv.addItem(createItem(ChatColor.RESET + "Spawn", Material.BEDROCK));
        inv.addItem(createItem(ChatColor.RESET + "Slime Slap", Material.SLIME_BALL));
        inv.addItem(createItem(ChatColor.RESET + "Maze", Material.OAK_LEAVES));
        inv.addItem(createItem(ChatColor.RESET + "Big Maze", Material.STONE));
        inv.addItem(createItem(ChatColor.RESET + "CarbonCraft", Material.GRASS_BLOCK));
        inv.addItem(createItem(ChatColor.RESET + "Invis Tag", Material.COAL));
        inv.addItem(createItem(ChatColor.RESET + "Insanity Box", Material.GHAST_TEAR));
        inv.addItem(createItem(ChatColor.RESET + "Strider Wars", Material.STRING));
        inv.addItem(createItem(ChatColor.RESET + "Speedrun Parkour", Material.CLOCK));
        inv.addItem(createItem(ChatColor.RESET + "Mini Golf", Material.SNOWBALL));
    }

    private ItemStack createItem(String name, Material material) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
