package cn.edu.whut.springbear.course.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 10:10
 */
public class NumberUtils {
    /**
     * 随机生成一个指定上限的整数 [0, bound)
     */
    public static int randomNumber(int bound) {
        return new Random().nextInt(bound);
    }

    /**
     * 生成订单号
     *
     * @return 订单号字符串
     */
    public static String orderNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 1; i <= 6; i++) {
            result.append(random.nextInt(10));
        }
        return newDate + result;
    }
}
