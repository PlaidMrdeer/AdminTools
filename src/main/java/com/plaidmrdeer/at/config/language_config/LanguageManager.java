package com.plaidmrdeer.at.config.language_config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author PlaidMrdeer
 */
public class LanguageManager {
    private final Plugin plugin;

    public FileConfiguration language;

    public LanguageManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void loadLang() {
        File langDir = new File(plugin.getDataFolder(), "language");
        if (!langDir.exists()) {
            langDir.mkdir();
        }
        List<String> languageList = Arrays.asList(
                "english.yml", "chinese.yml", "german.yml"
        );

        for (String language : languageList) {
            File file = new File(langDir, language);
            if (!file.exists()) {
                plugin.saveResource("language" + File.separator + language, false);
            }
        }

        File file = new File(langDir, plugin.getConfig().getString("language") + ".yml");
        if (!file.exists()) {
            plugin.getLogger().warning("Unknown language file! The plugin will load the default language \"English\"");
            file = new File(langDir, "english.yml");
            language = YamlConfiguration.loadConfiguration(file);
            return;
        }

        language = YamlConfiguration.loadConfiguration(file);
    }

    public Map<String, String> getLanuageMap() {
         return language.getKeys(false).stream().collect(
                Collectors.toMap(key -> key, language::getString)
         );
    }
}
