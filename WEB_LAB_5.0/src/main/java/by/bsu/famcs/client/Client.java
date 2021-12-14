package by.bsu.famcs.client;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Client for receiving images
 */
public class Client {
    static private final String downloadPath = "download";
    static private final Random random = new Random();
    static private SocketChannel client;
    static private final int serverPort = 8081;
    static private final byte[] imgResponsePrefix = "imgbytes::".getBytes(StandardCharsets.UTF_8);

    public static void main(String[] args) {
        System.out.println("Starting Client...");
        try {
            InetAddress hostIP = InetAddress.getLocalHost();
            InetSocketAddress serverAddress = new InetSocketAddress(hostIP, serverPort);
            System.out.printf("Trying to connect to %s:%d...%n", serverAddress.getHostName(), serverAddress.getPort());
            client = SocketChannel.open(serverAddress);
            System.out.println("Connected! Write your commands");
            var readingThread = new Thread(Client::read);
            readingThread.start();
            processInput();
            readingThread.join();
            System.out.println("Closing client connection...");
            client.close();
            System.out.println("Goodbye!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void processInput() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        var scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            var cmd = scanner.nextLine();
            buffer.put(cmd.getBytes());
            buffer.flip();
            int bytesWritten = client.write(buffer);
            buffer.clear();
            System.out.printf("Sending Message...: %s\nbytesWritten...: %d%n", cmd, bytesWritten);
        }
        client.close();
        System.out.println("Closing input reading");
    }

    private static void read() {
        while (client.isConnected()) {
            var buffer = ByteBuffer.allocate(1024);
            try {
                client.read(buffer);
            } catch (AsynchronousCloseException e) {
                System.out.println("Listening to server stopped");
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            var bytes = buffer.array();
            if (startsWith(bytes, imgResponsePrefix)) {
                System.out.println("Retrieving image...");
                try {
                    var fileName = processImageResponse(readImageBytes());
                    System.out.printf("Saved to %s\n", fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                continue;
            }
            System.out.println(new String(bytes, StandardCharsets.UTF_8).trim());
        }
    }

    private static byte[] readImageBytes() throws IOException {
        var lenBuffer = ByteBuffer.allocate(4);
        client.read(lenBuffer);
        var len = lenBuffer.getInt(0);
        System.out.printf("Image length: %d\n", len);
        var imgBuffer = ByteBuffer.allocate(len);
        var read = 0;
        while (read != len) {
            read += client.read(imgBuffer);
        }
        return imgBuffer.array();
    }

    private static boolean startsWith(byte[] array, byte[] prefix) {
        if (prefix.length > array.length) {
            return false;
        }
        for (int i = 0; i < prefix.length; i++) {
            if (prefix[i] != array[i]) {
                return false;
            }
        }
        return true;
    }

    private static String processImageResponse(byte[] buffer) throws IOException {
        return saveAndOpenImage(buffer);
    }

    private static String saveAndOpenImage(byte[] imgBytes) throws IOException {
        var is = new ByteArrayInputStream(imgBytes);
        var img = ImageIO.read(is);
        var filePath = downloadPath + "\\" + Math.abs(random.nextInt()) + ".jpg";
        File file = new File(filePath);
        ImageIO.write(img, "jpg", file);
        return filePath;
    }
}
