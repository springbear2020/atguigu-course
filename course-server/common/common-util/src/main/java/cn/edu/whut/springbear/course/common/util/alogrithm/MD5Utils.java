package cn.edu.whut.springbear.course.common.util.alogrithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-31 12:42
 */
public class MD5Utils {
    protected static MessageDigest messagedigest;
    protected static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对文件中的字节数据进行 MD5 编码
     *
     * @param file 文件对象
     * @return 文件字节数组 MD5 编码后的字符串
     */
    public static String fileMD5Encode(File file) throws IOException {
        if (file == null || !file.exists() || !file.isFile()) {
            throw new RuntimeException("file not exists or it's not a file");
        }
        FileChannel fileChannel = new FileInputStream(file).getChannel();
        MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }


    /**
     * 对字节数组中的元素进行 MD5 编码
     *
     * @param bytes 字节数组
     * @return MD5 编码后的字符串
     */
    private static String bufferToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            char c0 = hexDigits[(b & 0xf0) >> 4];
            char c1 = hexDigits[b & 0xf];
            sb.append(c0);
            sb.append(c1);
        }
        return sb.toString();
    }
}
