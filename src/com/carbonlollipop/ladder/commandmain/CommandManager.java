package com.carbonlollipop.ladder.commandmain;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.carbonlollipop.ladder.playermain.events.InstanceChecker;

public class CommandManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!InstanceChecker.isPlayer(sender)) { return true; }

        return true;
    }
}
