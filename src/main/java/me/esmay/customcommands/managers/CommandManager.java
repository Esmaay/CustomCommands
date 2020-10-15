package me.esmay.customcommands.managers;

import me.esmay.customcommands.CustomCommands;
import me.esmay.customcommands.objects.CustomCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CommandManager {

    public HashMap<String, CustomCommand> commands = new HashMap<>();

    private final CustomCommands customCommands;
    private File file;
    private YamlConfiguration yamlConfiguration;

    public CommandManager(CustomCommands customCommands) {
        this.customCommands = customCommands;

        file = new File(Bukkit.getPluginManager().getPlugin("CustomCommands").getDataFolder(), "commands.yml");
        if (!Bukkit.getPluginManager().getPlugin("CustomCommands").getDataFolder().exists()) {
            Bukkit.getPluginManager().getPlugin("CustomCommands").getDataFolder().mkdirs();
        }
        if (!file.exists()) {
            customCommands.saveResource("commands.yml", false);
        }
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadCommands();
    }

    public void loadCommands() {
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        commands.clear();
        Set<String> commands = yamlConfiguration.getConfigurationSection("commands").getKeys(false);
        for (String command : commands) {
            this.commands.put(command, getCommandFromFile(command));
        }
    }

    public HashMap<String, CustomCommand> getCommands() {
        return commands;
    }

    public CustomCommand getCommandFromFile(String command) {
        String permission = yamlConfiguration.getString("commands." + command + ".permission");
        List<String> commandsToExecute = yamlConfiguration.getStringList("commands." + command + ".commands");
        return new CustomCommand(command, permission, commandsToExecute);
    }


}
