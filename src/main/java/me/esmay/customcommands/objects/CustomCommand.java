package me.esmay.customcommands.objects;

import java.util.List;

public class CustomCommand {

    private final String command;
    private final String permission;
    private final List<String> commandToExecute;

    public CustomCommand(String command, String permission, List<String> commandToExecute) {
        this.command = command;
        this.permission = permission;
        this.commandToExecute = commandToExecute;
    }

    public String getCommand() {
        return command;
    }

    public String getPermission() {
        return permission;
    }

    public List<String> getCommandToExecute() {
        return commandToExecute;
    }
}
