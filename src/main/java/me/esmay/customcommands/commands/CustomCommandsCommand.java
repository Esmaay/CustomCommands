package me.esmay.customcommands.commands;

import me.esmay.customcommands.CustomCommands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CustomCommandsCommand implements CommandExecutor {

    private final CustomCommands customCommands;

    public CustomCommandsCommand(CustomCommands customCommands) {
        this.customCommands = customCommands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("customcommands.command")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cJe kunt dit niet doen!"));
            return true;
        }
        if (args.length != 0 && args[0].equalsIgnoreCase("reload")) {
            customCommands.getCommandManager().loadCommands();
            customCommands.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aConfig is gereload!"));
            return true;
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cGebruik: /customcommands reload"));
        return true;
    }
}
