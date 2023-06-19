package com.plaidmrdeer.at;

import com.plaidmrdeer.at.commands.InventoryCommand;
import com.plaidmrdeer.at.commands.InvisibleCommand;
import com.plaidmrdeer.at.commands.ProCommand;
import com.plaidmrdeer.at.commands.ReloadCommand;
import com.plaidmrdeer.at.commands.tabs.InventoryCommandTab;
import com.plaidmrdeer.at.config.language_config.LanguageManager;
import com.plaidmrdeer.at.events.InventoryListener;
import com.plaidmrdeer.at.events.ProListener;
import com.plaidmrdeer.at.task.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author PlaidMrdeer
 */
public final class AdminTools extends JavaPlugin {
    private TaskManager taskManager;

    private LanguageManager languageManager;

    private final Map<String, String> placeholder = new HashMap<>();

    private Map<String, String> language;

    public void addPlaceholder(String key, String value) {
        placeholder.put(key, value);
    }

    public void removePlaceholder(String key) {
        placeholder.remove(key);
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    private void registerCommands() {
        getCommand("invisible").setExecutor(new InvisibleCommand(this));
        getCommand("inventory").setExecutor(new InventoryCommand(this));
        getCommand("atreload").setExecutor(new ReloadCommand(this));
        getCommand("pro").setExecutor(new ProCommand(this));

        getCommand("inventory").setTabCompleter(new InventoryCommandTab());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new ProListener(), this);
    }

    private String setStyle(String message) {
        return setColor(setPlaceholder(message));
    }

    private String setColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private String setPlaceholder(String message) {
        for (Map.Entry<String, String> place : placeholder.entrySet()) {
            message = message.replace(place.getKey(), place.getValue());
        }

        return message;
    }

    public void sendMessage(String path, CommandSender sender) {
        String message = setStyle(language.get(path));

        sender.sendMessage(message);
    }

    public String getLanuage(String path) {
        return setStyle(language.get(path));
    }

    private void logo() {
        String logo1 = "&b     _       _           _     _____           _     ";
        String logo2 = "&b    / \\   __| |_ __ ___ (_)_ _|_   _|__   ___ | |___ ";
        String logo3 = "&b   / _ \\ / _` | '_ ` _ \\| | '_ \\| |/ _ \\ / _ \\| / __|";
        String logo4 = "&b  / ___ \\ (_| | | | | | | | | | | | (_) | (_) | \\__ \\";
        String logo5 = "&b /_/   \\_\\__,_|_| |_| |_|_|_| |_|_|\\___/ \\___/|_|___/";
        String logo6 = "&b                                                     ";
        CommandSender sender = Bukkit.getConsoleSender();

        sender.sendMessage(setColor(logo1));
        sender.sendMessage(setColor(logo2));
        sender.sendMessage(setColor(logo3));
        sender.sendMessage(setColor(logo4));
        sender.sendMessage(setColor(logo5));
        sender.sendMessage(setColor(logo6));
    }

    public void reload() {
        reloadConfig();

        languageManager.loadLang();
        language = languageManager.getLanuageMap();

        placeholder.clear();
    }

    @Override
    public void onLoad() {
        taskManager = new TaskManager(this);
        languageManager = new LanguageManager(this);
    }

    @Override
    public void onEnable() {
        logo();

        saveDefaultConfig();

        registerCommands();
        registerEvents();

        languageManager.loadLang();
        language = languageManager.getLanuageMap();

    }

    @Override
    public void onDisable() {
        taskManager.stopAllTask();
    }
}
