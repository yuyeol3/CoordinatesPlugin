package org.yuyeol3.coordinatesPlugin.coordinatesHUD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;
import org.yuyeol3.coordinatesPlugin.commands.ToggleCommand;

public class CoordinatesHUD extends BukkitRunnable {
    private final Set<String> displayToggles;

    public CoordinatesHUD(ToggleCommand toggleCommand) {
        displayToggles = toggleCommand.getDisplayToggles();
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!displayToggles.contains(p.getUniqueId().toString())) {
                double x = p.getLocation().getX();
                double y = p.getLocation().getY();
                double z = p.getLocation().getZ();

                p.sendActionBar(
                        ChatColor.GOLD + "좌표: " +
                                ChatColor.GREEN + String.format("%.0f, %.0f, %.0f ", x, y, z)
                );
            }
        }
    }
}