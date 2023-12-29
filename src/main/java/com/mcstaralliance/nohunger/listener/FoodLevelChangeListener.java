package com.mcstaralliance.nohunger.listener;

import com.mcstaralliance.nohunger.Constants;
import com.mcstaralliance.nohunger.NoHunger;
import com.mcstaralliance.nohunger.config.NoHungerConfig;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.util.List;

public class FoodLevelChangeListener implements Listener {

    private final NoHungerConfig config;

    public FoodLevelChangeListener(NoHungerConfig config) {
        this.config = config;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        HumanEntity entity = event.getEntity();
        String uuid = entity.getUniqueId().toString();
        if (!config.getNoHungerList().contains(uuid)) {
            return;
        }
        event.setCancelled(true);
    }
}
