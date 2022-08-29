package com.carbonlollipop.ladder.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PlayerKill implements Listener {

    @EventHandler
    public static void Death(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        if (!(killer instanceof Player) || victim == killer)
            return;
        if (event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID) {
            event.setDeathMessage(victim.getName() + " was bopped by " + killer.getName());
            victim.teleport(new Location(victim.getWorld(), 0, 1, 0));
        }
        if (event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            event.setDeathMessage(victim.getName() + " was chopped up by " + killer.getName());
            victim.teleport(new Location(victim.getWorld(), 0, 1, 0));
        }
        ItemStack stack = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) stack.getItemMeta();

        meta.setBasePotionData(new PotionData(PotionType.STRENGTH, false, true));
        stack.setItemMeta(meta);

        killer.getInventory().setItem(8, stack);
    }

    @EventHandler
    public static void muerta(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;
        if (event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING
                && ((Player) event.getEntity()).getInventory().getItemInMainHand().getType() == Material.DIAMOND_AXE) {
            event.setCancelled(true);
        }
        if (new Location(event.getEntity().getWorld(), event.getEntity().getLocation().getBlockX(),
                event.getEntity().getLocation().getBlockY() - 1, event.getEntity().getLocation().getBlockZ()).getBlock()
                        .getType() == Material.LIME_WOOL) {
            event.setCancelled(true);
        }
        if (!(event.getDamager() instanceof Player))
            return;
        if (((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.CARROT_ON_A_STICK) {
            ((Player) event.getEntity()).addPotionEffect(PotionEffectType.LEVITATION.createEffect(20, 19));
        }
        if (((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.DIAMOND_AXE) {
            event.getEntity().getLocation().getWorld().spawnEntity(event.getEntity().getLocation(),
                    EntityType.LIGHTNING);
            event.setDamage(event.getDamage() / 3);
        }
        if (((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            event.getEntity().setFireTicks(40);
        }
        if (((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.STICK) {
            event.setDamage(0);
        }
        if (((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.IRON_AXE
                && event.getDamager().getUniqueId().toString().equals("531cbd17-1f75-4d6e-b560-833a9d5ed9d7")) {
            event.getEntity().getLocation().getWorld().spawnEntity(event.getEntity().getLocation(),
                    EntityType.LIGHTNING);
            ((Player) event.getEntity()).kickPlayer("banhammerd xd");
        }
        if (((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.IRON_HOE) {
            event.setDamage(5);
            ((Player) event.getEntity()).addPotionEffect(PotionEffectType.DARKNESS.createEffect(200, 50));
            ((Player) event.getEntity()).addPotionEffect(PotionEffectType.SLOW.createEffect(200, 2));
        }
        if (((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.SLIME_BALL) {
            ((Player) event.getEntity()).addPotionEffect(PotionEffectType.POISON.createEffect(100, 2));
        }
        if (((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.ANVIL) {
            event.setCancelled(true);
            ((Player) event.getEntity()).setWalkSpeed(0.2f);
            new Location(event.getEntity().getLocation().getWorld(), event.getEntity().getLocation().getBlockX(),
                    event.getEntity().getLocation().getBlockY() + 50, event.getEntity().getLocation().getBlockZ())
                            .getBlock().setType(Material.ANVIL);
        }
        if (((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.HONEYCOMB) {
            event.setDamage(0);
            Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("Ladder");
            for (int i = 0; i < 20; i++) {
                new BukkitRunnable() {
                    public void run() {
                        Bee bee = (Bee) event.getEntity().getLocation().getWorld()
                                .spawnEntity(event.getEntity().getLocation(), EntityType.BEE);
                        bee.setTarget((LivingEntity) event.getEntity());
                        bee.setAnger(100000);
                        bee.setHealth(1);
                        new BukkitRunnable() {
                            public void run() {
                                bee.setHealth(0);
                            }
                        }.runTaskLater(pl, 200);
                    }
                }.runTaskLater(pl, i);
            }
            Player p = (Player) event.getDamager();
            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
        }
    }

    @EventHandler
    public static void move(PlayerMoveEvent event) {
        if (event.getPlayer().getLocation().getBlockY() < -30) {
            event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 1, 0.5));
        }
        if (event.getPlayer().getInventory().getBoots() != null
                && event.getPlayer().getInventory().getBoots().getType() == Material.DIAMOND_BOOTS) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 4));
        }
        Location loc = event.getPlayer().getLocation().clone().subtract(0, 1, 0);
        Block b = loc.getBlock();
        if (b.getType() == Material.BLACK_WOOL && !event.getPlayer().isSneaking()) {
            event.getPlayer().setVelocity(new Vector(0, 1, 0));
        }
        if (b.getLocation().equals(new Location(Bukkit.getWorld("world"), 41, 21, 16))) {
            event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 1000.5, 21, 995.5));
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        }
        if (b.getLocation().equals(new Location(Bukkit.getWorld("world"), 1000, 20, 999))) {
            event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 40.5, 22.5, 16.5));
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event) {

        Player p = event.getPlayer();

        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.WARPED_BUTTON) {
            Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("Ladder");
            String[] ITEMS = { "Fire Wand", "Bouncy Ball", "Thwacker 9001", "The Swarm", "Lightning Axe",
                    "Death Scythe", "Basic Kit", "Sexy Pants", "Jump Boots", "Steak", "Cake", "Anti-KB Chestplate",
                    "Magic Stew" };
            Player player = p;

            for (int j = 0; j < 6; j++) {
                for (int i = 0; i < ITEMS.length; i++) {
                    int finalI = i;
                    new BukkitRunnable() {
                        public void run() {
                            player.sendTitle("> " + ChatColor.GREEN + ITEMS[finalI] + ChatColor.WHITE + " <",
                                    ChatColor.RED + "", 0, 10, 0);
                            player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, 1.0F,
                                    2);
                        }
                    }.runTaskLater(pl, i + j * ITEMS.length);
                }

            }

            int rnd = new Random().nextInt(ITEMS.length);

            ItemStack stack;

            switch (rnd) {
            case 0 -> stack = new ItemStack(Material.BLAZE_ROD);
            case 1 -> stack = new ItemStack(Material.SLIME_BALL);
            case 2 -> stack = new ItemStack(Material.CARROT_ON_A_STICK);
            case 3 -> stack = new ItemStack(Material.HONEYCOMB);
            case 4 -> stack = new ItemStack(Material.DIAMOND_AXE);
            case 5 -> stack = new ItemStack(Material.IRON_HOE);
            case 6 -> stack = new ItemStack(Material.STONE_SWORD);
            case 7 -> stack = new ItemStack(Material.LEATHER_LEGGINGS);
            case 8 -> stack = new ItemStack(Material.DIAMOND_BOOTS);
            case 9 -> stack = new ItemStack(Material.COOKED_BEEF);
            case 10 -> stack = new ItemStack(Material.CAKE);
            case 11 -> stack = new ItemStack(Material.NETHERITE_CHESTPLATE);
            case 12 -> stack = new ItemStack(Material.MUSHROOM_STEW);
            default -> throw new IllegalStateException("???");
            }

            ItemMeta m = stack.getItemMeta();

            if (rnd == 2) {
                m.addEnchant(Enchantment.KNOCKBACK, 20, true);
            } else if (rnd == 7) {
                m.addEnchant(Enchantment.THORNS, 10, true);
                m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            } else if (rnd == 8) {
                m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            } else if (rnd == 6) {
                m.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
            } else if (rnd == 11) {
                m.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 10, true);
            } else if (rnd == 9) {
                stack.setAmount(16);
            } else {
                m.addEnchant(Enchantment.DURABILITY, 1, true);
            }

            m.setDisplayName(ChatColor.RESET + ITEMS[rnd]);
            m.setUnbreakable(true);
            stack.setItemMeta(m);

            new BukkitRunnable() {
                public void run() {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2);
                    player.sendTitle("> " + ChatColor.GREEN + ChatColor.BOLD + ITEMS[rnd] + ChatColor.WHITE
                            + ChatColor.RESET + " <", ChatColor.RED + "RANDOM ITEM SELECTED", 0, 20, 20);
                    player.getInventory().addItem(stack);
                    if (rnd == 6) {
                        player.getInventory().addItem(new ItemStack(Material.LEATHER_HELMET, 1));
                        player.getInventory().addItem(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                        player.getInventory().addItem(new ItemStack(Material.LEATHER_LEGGINGS, 1));
                        player.getInventory().addItem(new ItemStack(Material.LEATHER_BOOTS, 1));
                    }
                }
            }.runTaskLater(pl, 78);
            new BukkitRunnable() {
                public void run() {
                    event.getClickedBlock().setType(Material.WARPED_BUTTON);
                }
            }.runTaskLater(pl, 20);
        }

        if (p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD
                && event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            Fireball fire = p.getWorld()
                    .spawn(new Location(event.getPlayer().getWorld(), event.getPlayer().getLocation().getBlockX(),
                            event.getPlayer().getLocation().getBlockY() + 1,
                            event.getPlayer().getLocation().getBlockZ()), Fireball.class);
            p.playSound(p.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0F, 1);
            fire.setDirection(player.getLocation().getDirection());
            fire.setInvulnerable(true);
            fire.setVisualFire(false);
            fire.setShooter(p);
            fire.setIsIncendiary(false);
            fire.setYield(0);
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.POTION) {
            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
            p.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(20 * 5, 1));
            p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0F, 1);
        }
        if ((event.getPlayer()).getInventory().getItemInMainHand().getType() == Material.FEATHER) {
            (event.getPlayer()).playSound(event.getPlayer(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1, 0.5f);
            Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("Ladder");
            for (int i = 0; i < 3; i++) {
                new BukkitRunnable() {
                    public void run() {
                        event.getPlayer().setVelocity(new Vector(0, 0.5, 0));
                    }
                }.runTaskLater(pl, i * 5);
            }
            new BukkitRunnable() {
                public void run() {
                    p.setVelocity(p.getLocation().getDirection().multiply(1.5));
                }
            }.runTaskLater(pl, 15);
            p.setVelocity(p.getLocation().getDirection().multiply(1.5));
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.SLIME_BALL) {
            if (p.getLocation().getBlockY() < -5 || p.getLocation().getBlockY() > 50) {
                p.sendMessage(ChatColor.RED + "You can't use this here!");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1);
                return;
            }
            p.setVelocity(p.getLocation().getDirection().multiply(1.5));
            p.playSound(p.getLocation(), Sound.ENTITY_SLIME_SQUISH, 1.0F, 1);
            p.addPotionEffect(PotionEffectType.CONFUSION.createEffect(80, 2));
            p.spawnParticle(Particle.SLIME, p.getLocation().getBlockX(), p.getLocation().getBlockY(),
                    p.getLocation().getBlockZ(), 40);
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.LADDER
                && p.getUniqueId().toString().equals("531cbd17-1f75-4d6e-b560-833a9d5ed9d7")) {
            Bukkit.broadcastMessage(ChatColor.YELLOW + "Reloading server...");
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1.0F, 1);
            }
            Bukkit.reload();
            Bukkit.broadcastMessage(ChatColor.GREEN + "Done!");
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1.0F, 5);
            }
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.COOKED_BEEF) {
            if (p.getHealth() < p.getHealthScale()) {
                p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                event.getPlayer().setHealth(event.getPlayer().getHealthScale());
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1.0F, 1);
            }
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.CAKE) {
            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
            p.addPotionEffect(PotionEffectType.ABSORPTION.createEffect(1200, 4));
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1.0F, 1);
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.MUSHROOM_STEW) {
            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
            p.setAllowFlight(true);
            p.setFlying(true);
            p.sendMessage(ChatColor.GREEN + "You may fly for 15 seconds!");
            p.addPotionEffect(PotionEffectType.CONFUSION.createEffect(60, 4));
            p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0F, 1);
            new BukkitRunnable() {
                public void run() {
                    event.getPlayer().setAllowFlight(false);
                }
            }.runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("Ladder"), 300);
            new BukkitRunnable() {
                public void run() {
                    p.sendMessage(ChatColor.YELLOW + "Your flight will run out in 5 seconds!");
                }
            }.runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("Ladder"), 200);
        }
    }

}
