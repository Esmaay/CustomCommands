package me.esmay.customcommands.listeners;

import me.esmay.customcommands.CustomCommands;
import me.esmay.customcommands.objects.CustomCommand;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PreCommandListener implements Listener {

    private final CustomCommands customCommands;
    public PreCommandListener(CustomCommands customCommands) {
        this.customCommands = customCommands;
    }

   @EventHandler
    public void onPreProcessCommand(PlayerCommandPreprocessEvent event) {
       for (CustomCommand commands : customCommands.getCommandManager().getCommands().values()) {
           if (event.getMessage().equalsIgnoreCase("/" + commands.getCommand())) {
               event.setCancelled(true);
               if (event.getPlayer().hasPermission(commands.getPermission())) {
                   for (String command : commands.getCommandToExecute()) {
                       if (command.startsWith("MSG: ")) {
                           event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', command.replaceAll("MSG: ", "").replaceAll("%player%", event.getPlayer().getName())));
                       } else {
                            event.getPlayer().performCommand(command.replaceAll("%player%", event.getPlayer().getName()));
                       }
                   }
               } else {
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', customCommands.getConfig().getString("messages.nopermission")));
               }
           }
       }
   }
}
