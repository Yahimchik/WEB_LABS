package by.bsu.famcs.server.cmd;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * HelpCommands sends help information for all commands
 */
public class HelpCommand implements Command {
    @Override
    public byte[] execute(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("List of possible commands: \n");
        for (var cmd : CommandsUtil.getCommands()) {
            sb.append(cmd).append(" - ").append(CommandsUtil.getCommandHelp(cmd)).append('\n');
        }
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }
}
