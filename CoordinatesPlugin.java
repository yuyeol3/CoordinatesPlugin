package org.yuyeol3.coordinatesPlugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.yuyeol3.coordinatesPlugin.commands.ToggleCommand;
import org.yuyeol3.coordinatesPlugin.coordinatesHUD.CoordinatesHUD;


public final class CoordinatesPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("CoordinatesPlugin enabled");
        try {
            ToggleCommand toggleCommand = new ToggleCommand();
            CoordinatesHUD coordinatesHUD = new CoordinatesHUD(toggleCommand);
            getCommand("displayCoordinate").setExecutor(toggleCommand);
            coordinatesHUD.runTaskTimerAsynchronously(this, 0L, 1L);
        } catch (Exception e) {
            getLogger().warning(e.getMessage());
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("CoordinatesPlugin disabled");
    }

}
