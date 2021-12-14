package by.bsu.famcs.server.cmd;

import by.bsu.famcs.server.Server;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * SendCommand is used to process Send server command
 */
public class SendCommand implements Command {
    String imgResPath = "resources/images";
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    /**
     * Retrieves an image from args and sends it to a client
     *
     * @param args must contain 3 items: command name (send), image name, and address of receiver
     */
    @Override
    public byte[] execute(String[] args) {
        var resDir = new File(imgResPath);
        var images = resDir.listFiles();
        if (images == null) {
            return "Server error, directory not found\n".getBytes(StandardCharsets.UTF_8);
        }
        if (args.length < 3) {
            return "Wrong format. Write 'HELP' for more information\n".getBytes(StandardCharsets.UTF_8);
        }
        var imageName = args[1];
        var img = findImage(images, imageName);
        if (img.isEmpty()) {
            logger.error("Image not found " + imageName);
            return String.format("Image with name %s not found on server\n", imageName).getBytes(StandardCharsets.UTF_8);
        }
        var addressWithPort = parseAddress(args[2]);
        var chan = findSocket(addressWithPort);
        if (chan.isEmpty()) {
            logger.error("Client not found " + args[2]);
            return String.format("Client with address %s not found\n", args[2]).getBytes(StandardCharsets.UTF_8);
        }
        try {
            sendImage(img.get(), chan.get());
        } catch (IOException e) {
            logger.error("Connection error");
            return "Something went wrong\n".getBytes(StandardCharsets.UTF_8);
        }
        logger.info("Sent successfully");
        return "Sent successfully".getBytes(StandardCharsets.UTF_8);
    }

    private void sendImage(File img, SocketChannel chan) throws IOException {
        logger.info("Trying to send an image");
        var imgBytes = Files.readAllBytes(img.toPath());
        var prefixBytes = "imgbytes::".getBytes(StandardCharsets.UTF_8);

        var prefixBuffer = ByteBuffer.wrap(prefixBytes);
        int bytesWritten = chan.write(prefixBuffer);
        System.out.printf("Client: %s\nbytesWritten (prefix)...: %d\n",
                chan.getRemoteAddress().toString(), bytesWritten);
        prefixBuffer.clear();

        var lenBuffer = ByteBuffer.allocate(4).putInt(imgBytes.length);
        bytesWritten = chan.write(ByteBuffer.wrap( lenBuffer.array()));
        System.out.printf("Client: %s\nLength:%d\nbytesWritten (length)...: %d\n",
                chan.getRemoteAddress().toString(), imgBytes.length, bytesWritten);

        lenBuffer.clear();
        var imgBuffer = ByteBuffer.wrap(imgBytes);
        bytesWritten = chan.write(imgBuffer);
        System.out.printf("Client: %s\nbytesWritten (image)...: %d\n",
                chan.getRemoteAddress().toString(), bytesWritten);
        imgBuffer.clear();
    }

    private Optional<SocketChannel> findSocket(String[] addressWithPort) {
        var address = addressWithPort[0];
        var port = addressWithPort[1];
        synchronized (Server.getConnectedChannels()) {
            for (var chan : Server.getConnectedChannels()) {
                var socket = chan.socket();
                if (socket.getInetAddress().getHostAddress().equals(address)) {
                    if (port.isEmpty() || Integer.toString(socket.getPort()).equals(port)) {
                        return Optional.of(chan);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private String[] parseAddress(String address) {
        var addressWithPort = address.split(":");
        if (addressWithPort.length < 2) {
            addressWithPort = new String[]{addressWithPort[0], ""};
        }
        return addressWithPort;
    }

    private Optional<File> findImage(File[] images, String name) {
        for (var img : images) {
            if (img.getName().equals(name)) {
                return Optional.of(img);
            }
        }
        return Optional.empty();
    }
}
