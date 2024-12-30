package org.yuyeol3.coordinatesPlugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.yuyeol3.coordinatesPlugin.commands.ToggleCommand;

import java.util.Set;

/* Todo  DisplayToggles 클래스 만들기 */

public class ActionBarEvent implements Listener {
    private final ToggleCommand toggleCommand;
    private final Plugin plugin;
    private class EnableDisplay extends BukkitRunnable {
        private final Player player;
        public EnableDisplay(Player player) {
            super();
            this.player = player;
        }

        @Override
        public void run() {
            toggleCommand.getDisplayToggles().remove(player.getUniqueId().toString());
        }
    }


    public ActionBarEvent(ToggleCommand toggleCommand, Plugin plugin) {
        this.toggleCommand = toggleCommand;
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(final PlayerBedEnterEvent event) {
        process(event);
    }

    @EventHandler
    public void onRide(final PlayerInteractEntityEvent event) {
        process(event);
    }

    public void process(final PlayerEvent event) {
        Player player = event.getPlayer();
        Set<String> displayToggles = toggleCommand.getDisplayToggles();
        
        // 이미 off인 경우 처리하지 않음
        if (displayToggles.contains(player.getUniqueId().toString()))
            return;
        

        // 일시적으로 좌표 HUD 비활성화
        displayToggles.add(player.getUniqueId().toString());

        // 1초 후 다시 활성화
        new EnableDisplay(player)
                .runTaskLater(plugin, 40L); // 20 ticks = 1 second
    }

}
