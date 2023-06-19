package com.plaidmrdeer.at.events;

import com.plaidmrdeer.at.commands.ProCommand;
import com.plaidmrdeer.at.commands.enums.ProStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

/**
 * @author PlaidMrdeer
 */
public class ProListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (ProStatus.FORBID.equals(ProCommand.getStatus(player))) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (ProStatus.FORBID.equals(ProCommand.getStatus(player))) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (ProStatus.FORBID.equals(ProCommand.getStatus(player))) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlock(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (ProStatus.FORBID.equals(ProCommand.getStatus(player))) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlock(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (ProStatus.FORBID.equals(ProCommand.getStatus(player))) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlock(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (ProStatus.FORBID.equals(ProCommand.getStatus(player))) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlock(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (ProStatus.FORBID.equals(ProCommand.getStatus(player))) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlock(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (ProStatus.FORBID.equals(ProCommand.getStatus(player))) {
            event.setCancelled(true);
        }
    }
}
