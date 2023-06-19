package com.plaidmrdeer.at.commands;

import com.plaidmrdeer.at.AdminTools;
import com.plaidmrdeer.at.commands.enums.VisibilityStatus;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PlaidMrdeer
 */
public class InvisibleCommand implements CommandExecutor {
    private final Plugin plugin;

    private final Map<Player, VisibilityStatus> invisibleSwitch = new HashMap<>();

    public InvisibleCommand(Plugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!(sender instanceof Player invisiblePlayer)) {
            return true;
        }

        if (!sender.hasPermission("admintools.command.invisible")) {
            ((AdminTools) plugin).sendMessage("no_permission", invisiblePlayer);
            return true;
        }

        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

        if ((VisibilityStatus.VISIBLE).equals(invisibleSwitch.get(invisiblePlayer))
            || (!invisibleSwitch.containsKey(invisiblePlayer))) {


            for (Player player : onlinePlayers) {
                player.hidePlayer(plugin, invisiblePlayer);
            }
            invisibleSwitch.put(invisiblePlayer, VisibilityStatus.INVISIBLE);

            ((AdminTools) plugin).getTaskManager()
                    .startTask("invisible_" + invisiblePlayer.getName(), new BukkitRunnable() {

                @Override
                public void run() {
                    invisiblePlayer.spigot().sendMessage(

                            ChatMessageType.ACTION_BAR,
                            TextComponent.fromLegacyText(
                                    ((AdminTools) plugin).getLanuage("invisible")
                            )

                    );
                }

            }, 0L, 40L);
            return true;
        }

        for (Player player : onlinePlayers) {
            player.showPlayer(plugin, invisiblePlayer);
        }
        invisibleSwitch.put(invisiblePlayer, VisibilityStatus.VISIBLE);
        ((AdminTools) plugin).getTaskManager().stopTask("invisible_" + invisiblePlayer.getName());

        return true;
    }
}