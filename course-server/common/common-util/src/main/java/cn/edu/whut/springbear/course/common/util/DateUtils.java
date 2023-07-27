package cn.edu.whut.springbear.course.common.util;

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

    /**
     * 将 java.util.Date 解析为 "2022-10-21" 格式
     */
    public static String parseDateWithHyphen() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    /**
     * 将 java.util.Date 解析为 "2022-09-20 08:57:03" 格式
     */
    public static String parseDatetime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 对比日期大小
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return true：endDate > beginDate
     */
    public static boolean dateCompare(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            return false;
        }
        return endDate.getTime() > beginDate.getTime();
    }
}
