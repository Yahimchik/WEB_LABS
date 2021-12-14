package by.bsu.famcs.server;

import by.bsu.famcs.server.cmd.CommandsUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static List<Channel> channels = new ArrayList<>();
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Channel client joined: " + ctx.channel().remoteAddress());
        logger.info("Channel client joined: " + ctx.channel().remoteAddress());
        channels.add(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error(cause);
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        logger.info("Read ended : " + ctx.channel().remoteAddress().toString());
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        var msg = in.toString(CharsetUtil.UTF_8);
        System.out.println("Server received: " + msg);

        logger.info("Received message of length " + msg.length() + " by " + ctx.channel().remoteAddress().toString());
        var cmdArgs = msg.split(" ");
        var cmd = CommandsUtil.getCommandByName(cmdArgs[0].trim());
        byte[] bytes = cmd.execute(cmdArgs);
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(bytes));
        System.out.printf("Client: %s\nSending Message...: %s\n", ctx.channel().remoteAddress().toString(), cmd);
        logger.info(String.format("Client: %s\nSending Message...: %s\n", ctx.channel().remoteAddress().toString(), cmd));
    }

    public static List<Channel> getChannels() {
        return channels;
    }
}
