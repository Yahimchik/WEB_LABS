package by.bsu.famcs.server;

import by.bsu.famcs.server.cmd.CommandsUtil;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Server that stores images
 */
public class Server {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private static final List<SocketChannel> connectedChannels = new ArrayList<>();
    private static Selector selector;
    private static final int port = 8081;
    private static InetAddress hostIP;
    private static ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {
        System.out.println("Starting Server...");
        try {
            hostIP = InetAddress.getLocalHost();
            System.out.printf("Trying to accept connections on %s:%d...%n", hostIP.getHostAddress(), port);
            selector = Selector.open();
            registerSocketChannel();

            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> i = selectedKeys.iterator();

                while (i.hasNext()) {
                    SelectionKey key = i.next();

                    if (key.isAcceptable()) {
                        processAcceptEvent(serverSocketChannel);
                    } else if (key.isReadable()) {
                        processReadEvent(key);
                    }
                    i.remove();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void registerSocketChannel() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(hostIP, port);
        serverSocket.bind(address);
        serverSocketChannel.configureBlocking(false);
        int ops = serverSocketChannel.validOps();
        serverSocketChannel.register(selector, ops, null);
    }

    private static void processAcceptEvent(ServerSocketChannel socketChannel) throws IOException {
        SocketChannel acceptedChannel = socketChannel.accept();
        logger.info(String.format("Connection accepted: %s", acceptedChannel.getRemoteAddress().toString()));
        acceptedChannel.configureBlocking(false);
        acceptedChannel.register(selector, SelectionKey.OP_READ);
        connectedChannels.add(acceptedChannel);
    }

    private static void processReadEvent(SelectionKey key)
            throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        client.read(buffer);
        String data = new String(buffer.array()).trim();
        if (data.length() > 0) {
            logger.info(String.format("Message Received.....: %s\n", data));
        }
        var cmdArgs = data.split(" ");
        var cmd = CommandsUtil.getCommandByName(cmdArgs[0]);
        byte[] bytes = cmd.execute(cmdArgs);
        var writeBuffer = ByteBuffer.wrap(bytes);
        int bytesWritten = client.write(writeBuffer);
        writeBuffer.clear();
        System.out.printf("Client: %s\nSending Message...: %s\nbytesWritten...: %d\n",
                client.getRemoteAddress().toString(), cmd, bytesWritten);
    }

    public static List<SocketChannel> getConnectedChannels() {
        return connectedChannels;
    }
}
