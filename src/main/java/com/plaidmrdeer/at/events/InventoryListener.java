package com.plaidmrdeer.at.events;

import com.plaidmrdeer.at.commands.InventoryCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * @author PlaidMrdeer
 */
public class InventoryListener implements Listener, InventoryHolder {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCloseInventory(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        if (!(inventory.getHolder() instanceof InventoryListener)) {
            return;
        }

        if (InventoryType.CHEST.equals(inventory.getType())) {
            InventoryCommand.updateInventory(inventory);
            return;
        }

        InventoryCommand.updateEndInventory(inventory);
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
