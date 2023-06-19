package com.plaidmrdeer.at.commands;

import com.plaidmrdeer.at.AdminTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * @author PlaidMrdeer
 */
public class ReloadCommand implements CommandExecutor {
    private final Plugin plugin;
    public ReloadCommand(Plugin plugin) {
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

        ((AdminTools) plugin).reload();

        ((AdminTools) plugin).sendMessage("reload_complete", sender);
        return true;
    }
}
