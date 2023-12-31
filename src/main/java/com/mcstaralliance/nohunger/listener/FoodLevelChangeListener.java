package com.mcstaralliance.nohunger.listener;

import com.mcstaralliance.nohunger.config.NoHungerConfig;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {

    private final NoHungerConfig config;

    public FoodLevelChangeListener(NoHungerConfig config) {
        this.config = config;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        // if nohunger is on, player can eat foods.
        if (event.getItem() != null) {
            return;
        }
        HumanEntity entity = event.getEntity();
        String name = entity.getName();
        if (!config.getNoHungerList().contains(name)) {
            return;
        }
        entity.setFoodLevel(20);
        event.setCancelled(true);
    }
}
