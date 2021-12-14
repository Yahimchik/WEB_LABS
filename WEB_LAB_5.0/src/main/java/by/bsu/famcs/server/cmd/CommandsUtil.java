package by.bsu.famcs.server.cmd;

import java.util.Arrays;
import java.util.Map;

/**
 * Provides util for processing commands by server
 */
public class CommandsUtil {
    /**
     * @return list of all possible commands
     */
    public static String[] getCommands() {
        return Arrays.stream(Commands.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    private static final Map<String, Command> commandsByName = Map.of(
            Commands.LIST.name(), new ListCommand(),
            Commands.SEND.name(), new SendCommand(),
            Commands.HELP.name(), new HelpCommand()
    );

    /**
     * @param cmdName name of the command
     * @return an executable command. Returns specific Unknown processor if the command not found.
     */
    public static Command getCommandByName(String cmdName) {
        return commandsByName.getOrDefault(cmdName.toUpperCase(), new UnknownCommand());
    }

    private static final Map<String, String> commandHelp = Map.of(
            Commands.LIST.name(), "Prints a list of available images to send",
            Commands.SEND.name(), "Format: SEND <IMAGE_NAME> <CLIENT_IP>. Sends an image to a client",
            Commands.HELP.name(), "Returns help"
    );

    /**
     * @param cmdName the name of the command
     * @return the string describing what the command does
     */
    public static String getCommandHelp(String cmdName) {
        return commandHelp.getOrDefault(cmdName.toUpperCase(), "Unknown command");
    }
}
