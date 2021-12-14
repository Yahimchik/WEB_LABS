package by.bsu.famcs.server;

import by.bsu.famcs.server.cmd.CommandsUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.net.*;
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
    private static final int port = 8081;

    public static void main(String[] args) {
        System.out.println("Starting Server...");
        final var serverHandler = new ServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {
                        @Override
                        public void initChannel(io.netty.channel.socket.SocketChannel ch) {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });

            ChannelFuture f = b.bind().sync();
            System.out.println("Started and listening for connections on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
