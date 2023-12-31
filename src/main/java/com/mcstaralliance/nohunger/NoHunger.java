package com.mcstaralliance.nohunger;

import com.mcstaralliance.nohunger.config.NoHungerConfig;
import com.mcstaralliance.nohunger.listener.FoodLevelChangeListener;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandAPIConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoHunger extends JavaPlugin {

    private NoHungerConfig config;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIConfig());
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable(this);
        saveDefaultConfig();
        config = new NoHungerConfig(this);
        registerCommand();
        Bukkit.getPluginManager().registerEvents(new FoodLevelChangeListener(config), this);
    }

    private void registerCommand() {
        new CommandAPICommand(Constants.NO_HUNGER_COMMAND_NAME)
                .withPermission(Constants.NO_HUNGER_COMMAND_PERMISSION)
                .withSubcommand(registerSwitchCommand())
                .withSubcommand(registerShowCommand())
                .withSubcommand(registerReloadCommand())
                .register();
    }

    private CommandAPICommand registerSwitchCommand() {
        return new CommandAPICommand(Constants.NO_HUNGER_SWITCH_COMMAND_NAME)
                .withPermission(Constants.NO_HUNGER_SWITCH_COMMAND_PERMISSION)
                .executesPlayer((player, commandArguments) -> {
                    String name = player.getName();
                    if (config.getNoHungerList().contains(name)) {
                        config.removeNoHunger(name);
                        player.sendMessage(config.getNoHungerOffMessage());
                    } else {
                        config.addNoHunger(name);
                        player.sendMessage(config.getNoHungerOnMessage());
                        player.setFoodLevel(20);
                    }
                });
    }

    private CommandAPICommand registerShowCommand() {
        return new CommandAPICommand(Constants.NO_HUNGER_SHOW_COMMAND_NAME)
                .withPermission(Constants.NO_HUNGER_SHOW_COMMAND_PERMISSION)
                .executes((sender, arguments) -> {
                    sender.sendMessage(config.getNoHungerList().toArray(new String[0]));
                });
    }

    private CommandAPICommand registerReloadCommand() {
        return new CommandAPICommand(Constants.NO_HUNGER_RELOAD_COMMAND_NAME)
                .withPermission(Constants.NO_HUNGER_RELOAD_COMMAND_PERMISSION)
                .executes((sender, arguments) -> {
                    config.reload();
                    sender.sendMessage(config.getReloadMessage());
                });
    }


    @Override
    public void onDisable() {
        CommandAPI.unregister(Constants.NO_HUNGER_COMMAND_NAME);
    }
}
