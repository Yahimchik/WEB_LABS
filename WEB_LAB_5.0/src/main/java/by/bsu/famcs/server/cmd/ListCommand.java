package by.bsu.famcs.server.cmd;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * ListCommand sends the list of all available images
 */
public class ListCommand implements Command {
    String imgResPath = "resources/images";

    @Override
    public byte[] execute(String[] args) {
        var resDir = new File(imgResPath);
        var images = resDir.listFiles();
        if (images == null) {
            return "Server error, directory not found".getBytes(StandardCharsets.UTF_8);
        }
        StringBuilder sb = new StringBuilder();
        for (var img : images) {
            sb.append(img.getName()).append('\n');
        }
        return  sb.toString().getBytes(StandardCharsets.UTF_8);
    }
}
