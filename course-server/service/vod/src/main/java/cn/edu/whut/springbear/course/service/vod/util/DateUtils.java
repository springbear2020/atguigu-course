package cn.edu.whut.springbear.course.service.vod.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-21 19:56
 */
public class DateUtils {
    /**
     * 将 java.util.Date 解析为 "2022/10/21" 格式
     */
    public static String parseDateWithSlash() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(new Date());
    }
}
