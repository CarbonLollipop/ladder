package com.carbonlollipop.ladder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

// Chat handler

// todo: CLEAN THIS CLEAN THIS CLEAN THIS

public class Chat implements Listener {

    // you know what
    // if the connection fails just let everything burn
    public static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ladder", "root", "");
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    @EventHandler
    public static void chat(AsyncPlayerChatEvent e) throws SQLException {
        e.setCancelled(true);
        Statement stmt = conn.createStatement();
        ResultSet row = stmt.executeQuery("SELECT * FROM player WHERE uuid = '" + e.getPlayer().getUniqueId() + "'");
        row.next();

        ChatColor color;

        String rank = row.getString("rank");
        if("OWNER".equals(rank)) {
            color = ChatColor.DARK_RED;
        } else if("BUILDER".equals(rank)) {
            color = ChatColor.LIGHT_PURPLE;
        } else if("ADMIN".equals(rank)) {
            color = ChatColor.AQUA;
        } else if("L".equals(rank)) {
            color = ChatColor.GOLD;
        } else if("DEV".equals(rank)) {
            color = ChatColor.RED;
        } else {
            color = null;
        }

        if(row.getString("rank").equals("NON")) {
            Bukkit.broadcastMessage(ChatColor.GRAY + e.getPlayer().getName() + ": " + e.getMessage());
        } else {
            Bukkit.broadcastMessage("[" + color + ChatColor.BOLD + row.getString("rank") + ChatColor.RESET + "] " + color + e.getPlayer().getName() + ChatColor.RESET + ": " + e.getMessage());
        }
    }

    @EventHandler
    public static void join(PlayerJoinEvent e) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT IGNORE INTO player (uuid, rank) VALUES ('" + e.getPlayer().getUniqueId() + "', 'NON');");

        ResultSet row = stmt.executeQuery("SELECT * FROM player WHERE uuid = '" + e.getPlayer().getUniqueId() + "'");
        row.next();

        ChatColor color;

        String rank = row.getString("rank");
        if("OWNER".equals(rank)) {
            color = ChatColor.DARK_RED;
        } else if("BUILDER".equals(rank)) {
            color = ChatColor.LIGHT_PURPLE;
        } else if("ADMIN".equals(rank)) {
            color = ChatColor.AQUA;
        } else if("L".equals(rank)) {
            color = ChatColor.GOLD;
        } else if("DEV".equals(rank)) {
            color = ChatColor.RED;
        } else {
            color = null;
        }

        if(row.getString("rank").equals("NON")) {
            e.setJoinMessage(ChatColor.GRAY + e.getPlayer().getName() + ChatColor.GREEN + " joined");
        } else {
            e.setJoinMessage("[" + color + ChatColor.BOLD + row.getString("rank") + ChatColor.RESET + "] " + color + e.getPlayer().getName() + ChatColor.GREEN + " joined");
        }
    }

    @EventHandler
    public static void join(PlayerQuitEvent e) throws SQLException {
        Statement stmt = conn.createStatement();

        ResultSet row = stmt.executeQuery("SELECT * FROM player WHERE uuid = '" + e.getPlayer().getUniqueId() + "'");
        row.next();

        ChatColor color;

        String rank = row.getString("rank");
        if("OWNER".equals(rank)) {
            color = ChatColor.DARK_RED;
        } else if("BUILDER".equals(rank)) {
            color = ChatColor.LIGHT_PURPLE;
        } else if("ADMIN".equals(rank)) {
            color = ChatColor.AQUA;
        } else if("L".equals(rank)) {
            color = ChatColor.GOLD;
        } else if("DEV".equals(rank)) {
            color = ChatColor.RED;
        } else {
            color = null;
        }

        if(row.getString("rank").equals("NON")) {
            e.setQuitMessage(ChatColor.GRAY + e.getPlayer().getName() + ChatColor.RED + " left");
        } else {
            e.setQuitMessage("[" + color + ChatColor.BOLD + row.getString("rank") + ChatColor.RESET + "] " + color + e.getPlayer().getName() + ChatColor.RED + " left");
        }
    }
}
