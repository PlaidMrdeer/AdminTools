package com.plaidmrdeer.at.commands.tabs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

/**
 * @author PlaidMrdeer
 */
public class InventoryCommandTab extends Tabs implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender,
                                      Command command,
                                      String label,
                                      String[] args) {

        return setTabs(args, new String[] {"inv", "end"});
    }
}
