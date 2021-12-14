package by.bsu.famcs.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    static private final String downloadPath = "download";
    static private final Random random = new Random();
    static private final byte[] imgResponsePrefix = "imgbytes::".getBytes(StandardCharsets.UTF_8);

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

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Client is connected to the server!");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        var readBytesCount = in.readableBytes();
        var bytes = new byte[readBytesCount];
        in.readBytes(bytes);
        if (ImageReading.running(ctx.channel())) {
            ImageReading.addReadBytes(ctx.channel(), bytes);
            if (ImageReading.isFinished(ctx.channel())) {
                var fileName = saveAndOpenImage(ImageReading.getImageBytesAndFinish(ctx.channel()));
                System.out.printf("Saved to %s\n", fileName);
            }
            return;
        }
        if (startsWith(bytes, imgResponsePrefix)) {
            System.out.println("Retrieving image...");
            startReadingImage(ctx.channel(), bytes, in);
            return;
        }
        System.out.printf("%d - %s\n", readBytesCount, new String(bytes, StandardCharsets.UTF_8));

    }

    private static int fromByteArray(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8 ) |
                ((bytes[3] & 0xFF) << 0 );
    }

    private static void startReadingImage(Channel channel, byte[] prefix, ByteBuf in) throws IOException {
        int len = fromByteArray(Arrays.copyOfRange(prefix, 10, 14));
        System.out.println(len);
        System.out.printf("Image length: %d\n", len);
        ByteBuffer imgBuffer = null;
        if (prefix.length > 14) {
            imgBuffer = ByteBuffer.wrap(prefix, 14, prefix.length - 14);
        }
        ImageReading.start(channel, len, imgBuffer);
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
