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

    public boolean addNoHunger(String uuid) {
        List<String> uuidList = plugin.getConfig().getStringList(Constants.NO_HUNGER_PLAYER_CONFIG);
        Set<String> uuidSet = new HashSet<>(uuidList);
        final boolean added = uuidSet.add(uuid);
        uuidList = new ArrayList<>(uuidSet);
        plugin.getConfig().set(Constants.NO_HUNGER_PLAYER_CONFIG, uuidList);
        plugin.saveConfig();
        return added;
    }

    public boolean removeNoHunger(String uuid) {
        List<String> uuidList = plugin.getConfig().getStringList(Constants.NO_HUNGER_PLAYER_CONFIG);
        boolean removed = uuidList.remove(uuid);
        plugin.getConfig().set(Constants.NO_HUNGER_PLAYER_CONFIG, uuidList);
        plugin.saveConfig();
        return removed;
    }

    public List<String> getNoHungerList() {
        return plugin.getConfig().getStringList(Constants.NO_HUNGER_PLAYER_CONFIG);
    }
}
