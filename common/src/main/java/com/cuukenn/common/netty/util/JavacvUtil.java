package com.cuukenn.common.netty.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/**
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JavacvUtil {
    /**
     * pixels[] to byte[]
     *
     * @param pixels 像素
     * @return 字节数组
     */
    public static byte[] intToByte(int[] pixels) {
        if (pixels == null) {
            return null;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(pixels.length * 4);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(pixels);
        return byteBuffer.array();
    }

    /**
     * byte[] to pixels[]
     *
     * @param data 像素
     * @return 像素
     */
    public static int[] byteToInt(byte[] data) {
        if (data == null) {
            return null;
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        int[] pixels = new int[data.length / 4];
        byteBuffer.asIntBuffer().get(pixels);
        return pixels;
    }

    /**
     * @param data   数据
     * @param width  宽度
     * @param height 高度
     * @return png数据
     */
    public static byte[] imgPixelByteToPng(byte[] data, int width, int height) {
        Mat screen = new Mat(height, width, opencv_core.CV_8UC4);
        screen.data().put(data);
        BytePointer pointer = new BytePointer();
        opencv_imgcodecs.imencode(".jpeg", screen, pointer);
        return pointer.getStringBytes();
    }
}
