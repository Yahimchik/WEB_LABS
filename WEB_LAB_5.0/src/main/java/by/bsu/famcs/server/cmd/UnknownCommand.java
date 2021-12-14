package by.bsu.famcs.server.cmd;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * A specific handler that is called when the command is unknown
 */
public class UnknownCommand implements Command {
    private final static String message = "The command you entered is unknown. Use HELP for more information";

    @Override
    public byte[] execute(String[] args) {
        return message.getBytes(StandardCharsets.UTF_8);
    }
}
