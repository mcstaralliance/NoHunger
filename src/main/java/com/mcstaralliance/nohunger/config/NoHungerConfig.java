package com.mcstaralliance.nohunger.config;

import com.mcstaralliance.nohunger.Constants;
import com.mcstaralliance.nohunger.NoHunger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoHungerConfig {
    private final NoHunger plugin;

    public NoHungerConfig(NoHunger plugin) {
        this.plugin = plugin;
    }

    public boolean addNoHunger(String name) {
        List<String> nameList = plugin.getConfig().getStringList(Constants.NO_HUNGER_PLAYER_CONFIG);
        Set<String> nameSet = new HashSet<>(nameList);
        final boolean added = nameSet.add(name);
        nameList = new ArrayList<>(nameSet);
        plugin.getConfig().set(Constants.NO_HUNGER_PLAYER_CONFIG, nameList);
        plugin.saveConfig();
        return added;
    }

    public boolean removeNoHunger(String name) {
        List<String> nameList = plugin.getConfig().getStringList(Constants.NO_HUNGER_PLAYER_CONFIG);
        boolean removed = nameList.remove(name);
        plugin.getConfig().set(Constants.NO_HUNGER_PLAYER_CONFIG, nameList);
        plugin.saveConfig();
        return removed;
    }

    public List<String> getNoHungerList() {
        return plugin.getConfig().getStringList(Constants.NO_HUNGER_PLAYER_CONFIG);
    }

    private String getMessage(String key) {
        return plugin.getConfig().getString(key);
    }

    public String getNoHungerOnMessage() {
        return getMessage(Constants.NO_HUNGER_ON_MESSAGE_KEY);
    }

    public String getNoHungerOffMessage() {
        return getMessage(Constants.NO_HUNGER_OFF_MESSAGE_KEY);
    }

    public String getReloadMessage() {
        return getMessage(Constants.NO_HUNGER_RELOAD_MESSAGE_KEY);
    }

    public void reload() {
        plugin.reloadConfig();
    }
}
