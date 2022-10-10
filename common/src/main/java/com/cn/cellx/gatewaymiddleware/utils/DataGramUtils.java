package com.cn.cellx.gatewaymiddleware.utils;

import com.cn.cellx.gatewaymiddleware.exception.DataGramCheckException;
import io.netty.buffer.ByteBufUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * 通信电报解析工具类
 *
 * @author wade
 */
public class DataGramUtils {

    public static long transLong(byte[] bytes) {
        long result = 0;
        for (int i = bytes.length - 1; i >= 0; i--) {
            result = (result << 8) + Byte.toUnsignedInt(bytes[i]);
        }
        return result;
    }

    public static long transLong(byte[] bytes, int start, int length) throws DataGramCheckException {
        long result = 0;
        if (bytes.length < start + length) throw new DataGramCheckException("diagram length check failed");
        for (int i = length - 1; i >= 0; i--) {
            result = (result << 8) + Byte.toUnsignedInt(bytes[start + i]);
        }
        return result;
    }

    public static int transInt(byte[] bytes) {
        int result = 0;
        for (int i = bytes.length - 1; i >= 0; i--) {
            result = (result << 8) + Byte.toUnsignedInt(bytes[i]);
        }
        return result;
    }

    public static int transInt(byte[] bytes, int start, int length) throws DataGramCheckException {
        int result = 0;
        if (bytes.length < start + length) throw new DataGramCheckException("diagram length check failed");
        for (int i = length - 1; i >= 0; i--) {
            result = (result << 8) + Byte.toUnsignedInt(bytes[start + i]);
        }
        return result;
    }

    public static String transString(byte[] bytes, int start, int length) throws DataGramCheckException {
        if (bytes.length < start + length) throw new DataGramCheckException("diagram length check failed");
        StringBuilder result = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            result.append((char) bytes[start + i]);
        }
        return result.toString();
    }

    public static byte[] transHexStringToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static byte[] transLongToBytes(long number, int length) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(number);
        byte[] originResult = buffer.array();
        byte[] result;
        int orignLength = originResult.length;
        if (length <= orignLength) {
            result = Arrays.copyOfRange(originResult, orignLength - length, orignLength);
        } else {
            byte[] preResult = new byte[length - orignLength];
            result = ArrayUtils.addAll(preResult, originResult);
        }
        byte[] target = new byte[result.length];
        for (int i = 0; i < result.length; i++) {
            target[i] = result[result.length - i - 1];
        }
        return target;
    }

    public static byte[] transIntToBytes(int number, int length) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(number);
        byte[] originResult = buffer.array();
        byte[] result;
        int orignLength = originResult.length;
        if (length <= orignLength) {
            result = Arrays.copyOfRange(originResult, orignLength - length, orignLength);
        } else {
            byte[] preResult = new byte[length - orignLength];
            result = ArrayUtils.addAll(preResult, originResult);
        }
        byte[] target = new byte[result.length];
        for (int i = 0; i < result.length; i++) {
            target[i] = result[result.length - i - 1];
        }
        return target;
    }

    public static byte[] transBytes(byte[] bytes, int start, int length) throws DataGramCheckException {
        if (bytes.length < start + length) throw new DataGramCheckException("diagram length check failed");
        return Arrays.copyOfRange(bytes, start, start + length);
    }

    public static String transHexString(byte[] bytes) {
        return ByteBufUtil.hexDump(bytes).toUpperCase();
    }

    public static byte[] fillBytesToTargetLength(byte[] bytes, int length) {
        if (bytes.length >= length) return bytes;
        return ArrayUtils.addAll(bytes, new byte[length - bytes.length]);
    }

}