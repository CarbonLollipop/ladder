package com.carbonlollipop.ladder;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.carbonlollipop.ladder.environmentmain.events.EventsDisable;
import com.carbonlollipop.ladder.playermain.events.PlayerDeath;
import com.carbonlollipop.ladder.playermain.events.PlayerDisableGrief;

//import java.util.Arrays;
//import java.util.Objects;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new EventsDisable(), this);
        getServer().getPluginManager().registerEvents(new PlayerDisableGrief(), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new Compass(), this);
        getServer().getPluginManager().registerEvents(new CompassEvent(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Ladder enabled!");
        //    for (String s : Arrays.asList("commands", "will", "go", "here")) {
        //        Objects.requireNonNull(getCommand(s)).setExecutor(new ShoutCmd());
        //    }
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Ladder disabled!");
    }

}
