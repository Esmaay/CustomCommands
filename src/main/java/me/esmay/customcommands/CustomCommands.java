package me.esmay.customcommands;

import me.esmay.customcommands.commands.CustomCommandsCommand;
import me.esmay.customcommands.listeners.PreCommandListener;
import me.esmay.customcommands.managers.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomCommands extends JavaPlugin {

    public CommandManager commandManager;

    @Override
    public void onEnable() {
        commandManager = new CommandManager(this);
        loadConfig();
        loadListeners();
        loadCommands();
    }

    private void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new PreCommandListener(this), this);
    }

    private void loadCommands() {
        getCommand("customcommands").setExecutor(new CustomCommandsCommand(this));
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

}
