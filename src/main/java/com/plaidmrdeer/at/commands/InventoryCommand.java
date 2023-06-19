package com.plaidmrdeer.at.commands;

import com.plaidmrdeer.at.AdminTools;
import com.plaidmrdeer.at.events.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * @author PlaidMrdeer
 */
public class InventoryCommand implements CommandExecutor {
    private final Plugin plugin;
    private static Player tarPlayer;
    public InventoryCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!(sender instanceof Player player)) {
            return true;
        }

        if (!sender.hasPermission("admintools.command.inventory")) {
            ((AdminTools) plugin).sendMessage("no_permission", player);
            return true;
        }

        if (args.length != 2) {
            ((AdminTools) plugin).sendMessage("command_error", player);
            return true;
        }

        tarPlayer = Bukkit.getPlayerExact(args[1]);

        if (tarPlayer == null) {
            ((AdminTools) plugin).sendMessage("cannot_found_player", player);
            return true;
        }

        ((AdminTools) plugin).addPlaceholder("%player%", tarPlayer.getName());
        Inventory inventory = Bukkit.createInventory(new InventoryListener(), (5 * 9), ((AdminTools) plugin).getLanuage("player_inventory"));

        switch (args[0]) {
            case "end" -> {
                inventory = Bukkit.createInventory(new InventoryListener(), InventoryType.ENDER_CHEST, ((AdminTools) plugin).getLanuage("player_end_inventory"));
                ItemStack[] endItemStacks = tarPlayer.getEnderChest().getContents();

                for (int i = 0; i < endItemStacks.length; i++) {
                    inventory.setItem(i, endItemStacks[i]);
                }
            }
            case "inv" -> {
                ItemStack[] itemStacks = tarPlayer.getInventory().getContents();

                for (int i = 0; i < itemStacks.length; i++) {
                    inventory.setItem(i, itemStacks[i]);
                }
            }
            default -> {
                ((AdminTools) plugin).sendMessage("command_error", player);
                return true;
            }
        }

        player.openInventory(inventory);
        ((AdminTools) plugin).removePlaceholder("%player%");

        return true;
    }

    public static void updateInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getSize() - 4; i++) {
            tarPlayer.getInventory().setItem(i, inventory.getItem(i));
        }
    }

    public static void updateEndInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            tarPlayer.getEnderChest().setItem(i, inventory.getItem(i));
        }
    }
}
