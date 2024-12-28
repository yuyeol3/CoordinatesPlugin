package org.yuyeol3.coordinatesPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.bukkit.command.CommandExecutor;

import java.util.HashSet;
import java.util.Set;


public class ToggleCommand implements CommandExecutor {
    private final Set<String> displayToggles;

    public ToggleCommand() {
        this.displayToggles = new HashSet<>();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("플레이어만 입력이 가능합니다.");
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage("/displayCoordinate true | false");
            return false;
        }

        Player player = (Player) sender;
        String toggle = args[0];

        if (toggle.equalsIgnoreCase("true")) {
            if (displayToggles.contains(player.getUniqueId().toString()))
                displayToggles.remove(player.getUniqueId().toString());

            sender.sendMessage("설정이 true로 변경되었습니다.");
            return true;
        }
        else if (toggle.equalsIgnoreCase("false")) {
            displayToggles.add(player.getUniqueId().toString());
            sender.sendMessage("설정이 false로 변경되었습니다.");
            return true;
        }
        else {
            sender.sendMessage("명령 구문이 잘못되었습니다.");
            return false;
        }

    }

    public Set<String> getDisplayToggles() {
        return displayToggles;
    }
}
