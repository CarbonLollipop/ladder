package com.carbonlollipop.ladder.playermain.info;

import org.bukkit.GameMode;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Gamemode {
    public static boolean isGamemode(BlockBreakEvent event, GameMode mode) {
        return event.getPlayer().getGameMode() == mode;
    }

    public static boolean isGamemode(BlockPlaceEvent event, GameMode mode) {
        return event.getPlayer().getGameMode() == mode;
    }
}
