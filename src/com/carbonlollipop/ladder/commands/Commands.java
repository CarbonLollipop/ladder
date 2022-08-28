package com.carbonlollipop.ladder.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) { return true; }

        //if (cmd.getName().equalsIgnoreCase("command")) {
        //    player.sendMessage(ChatColor.GREEN + "Cool!");
        //}
        return true;
    }
}
