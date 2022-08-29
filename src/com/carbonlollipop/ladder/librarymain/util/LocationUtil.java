package com.carbonlollipop.ladder.librarymain.util;

import org.bukkit.Location;

public class LocationUtil {
    public static boolean isInBounds(int x1, int x2, int z1, int z2, Location location) {
        int x = location.getBlockX();
        int z = location.getBlockZ();
        return (x >= x1 && x <= x2 && z >= z1 && z <= z2);
    }
}
