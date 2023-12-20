package com.mcstaralliance.nohunger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoHunger extends JavaPlugin implements Listener {

    final String PERMISSION = "starcraft.nohunger";
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();
        if (!player.hasPermission(PERMISSION)) {
            return;
        }
        event.setCancelled(true);
        player.setFoodLevel(20);
    }
}
