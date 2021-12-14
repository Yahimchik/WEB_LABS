package by.bsu.famcs.client;

import io.netty.channel.Channel;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ImageReading {
    static private class State {
        ByteBuffer buffer;
        int read;
        int cap;

        public boolean running() {
            return buffer != null;
        }

        public void start(int cap, ByteBuffer initial) {
            this.buffer = ByteBuffer.allocate(cap);
            this.read = 0;
            if (initial != null) {
                this.buffer.put(initial);
                this.read += initial.array().length;
            }
            this.cap = cap;
        }

        public boolean isFinished() {
            return cap == read;
        }
    }

    static private final Map<Channel, State> imageReading = new HashMap<>();

    static public boolean running(Channel channel) {
        return imageReading.containsKey(channel) && imageReading.get(channel).running();
    }

    static public void start(Channel channel, int cap, ByteBuffer initial) {
        if (!imageReading.containsKey(channel)) {
            imageReading.put(channel, new State());
        }
        imageReading.get(channel).start(cap, initial);
    }

    static public boolean isFinished(Channel channel) {
        return imageReading.containsKey(channel) && imageReading.get(channel).isFinished();
    }

    public static void addReadBytes(Channel channel, byte[] bytes) {
        if (!imageReading.containsKey(channel)) {
            return;
        }
        if (imageReading.get(channel).cap == 0) {
            imageReading.get(channel).start(fromByteArray(Arrays.copyOfRange(bytes, 0, 4)),
                    ByteBuffer.wrap(Arrays.copyOfRange(bytes, 4, bytes.length)));
            return;
        }
        var left = Math.min(bytes.length, imageReading.get(channel).cap - imageReading.get(channel).read);
        imageReading.get(channel).read += left;
        imageReading.get(channel).buffer.put(bytes, 0, left);
        System.out.println(left + ": " + imageReading.get(channel).read + " from " + imageReading.get(channel).cap);
    }

    public static byte[] getImageBytesAndFinish(Channel channel) {
        var res = imageReading.get(channel).buffer;
        imageReading.get(channel).buffer = null;
        return res.array();
    }

    private static int fromByteArray(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                ((bytes[3] & 0xFF) << 0);
    }
}
