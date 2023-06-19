package com.plaidmrdeer.at.commands;

import com.plaidmrdeer.at.AdminTools;
import com.plaidmrdeer.at.commands.enums.ProStatus;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author PlaidMrdeer
 */
public class ProCommand implements CommandExecutor {
    private final Plugin plugin;

    private static final Map<Player, ProStatus> proSwitch = new HashMap<>();

    public ProCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!sender.hasPermission("admintools.command.atreload")) {
            ((AdminTools) plugin).sendMessage("no_permission", sender);
            return true;
        }

        if (args.length != 1) {
            ((AdminTools) plugin).sendMessage("command_error", sender);
            return true;
        }

        Player player = Bukkit.getPlayerExact(args[0]);

        if (player == null) {
            ((AdminTools) plugin).sendMessage("cannot_found_player", sender);
            return true;
        }

        ((AdminTools) plugin).sendMessage("command_succeed", sender);

        if ((ProStatus.LIFTED).equals(proSwitch.get(player))
                || (!proSwitch.containsKey(player))) {

            proSwitch.put(player, ProStatus.FORBID);
            return true;
        }

        proSwitch.put(player, ProStatus.LIFTED);

        return true;
    }

    public static ProStatus getStatus(Player player) {
        return proSwitch.get(player);
    }
}

