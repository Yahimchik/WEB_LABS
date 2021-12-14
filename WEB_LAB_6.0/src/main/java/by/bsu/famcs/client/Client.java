package by.bsu.famcs.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

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
    static private SocketChannel client;
    static private final String serverIp = "127.0.0.1";
    static private final int serverPort = 8081;

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(serverIp, serverPort))
                    .handler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {
                        @Override
                        public void initChannel(io.netty.channel.socket.SocketChannel ch) {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });

            ChannelFuture f = b.connect().sync();
            processInput(f);
            f.channel().closeFuture().sync();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void processInput(ChannelFuture f) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            var cmd = scanner.nextLine();
            f.channel().writeAndFlush(Unpooled.copiedBuffer(cmd + '\n', CharsetUtil.UTF_8));
        }
        System.out.println("Closing connection...");
    }
}
