package com.plaidmrdeer.at.task;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

/**
 * @author PlaidMrdeer
 */
public class TaskManager {
    private final Plugin plugin;
    private final Map<String, BukkitTask> tasks = new HashMap<>();

    public TaskManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void startTask(String name,
                          BukkitRunnable runnable,
                          long delay,
                          long period) {

        BukkitTask task = runnable.runTaskTimerAsynchronously(plugin, delay, period);
        tasks.put(name, task);
    }

    public void stopTask(String name) {
        BukkitTask task = tasks.remove(name);
        if (task != null) {
            task.cancel();
        }
    }

    public void stopAllTask() {
        Bukkit.getScheduler().cancelTasks(plugin);
    }
}
