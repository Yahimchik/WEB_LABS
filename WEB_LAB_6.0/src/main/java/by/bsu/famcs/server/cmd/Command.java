package by.bsu.famcs.server.cmd;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.nio.ByteBuffer;

/**
 * Interface for any server command
 */
public interface Command {
    byte[] execute(String[] args);
}
