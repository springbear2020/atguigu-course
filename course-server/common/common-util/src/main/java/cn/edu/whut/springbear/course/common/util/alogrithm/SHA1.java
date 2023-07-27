package cn.edu.whut.springbear.course.common.util.alogrithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-29 07:27
 */
public class SHA1 {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (byte aByte : bytes) {
            buf.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[aByte & 0x0f]);
        }
        return buf.toString();
    }

    /**
     * 使用 SHA1 算法对 str 进行加密
     *
     * @param str 需要加密的算法
     * @return 加密成功：加密后的字符串；加密失败：null
     */
    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
