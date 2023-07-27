package cn.edu.whut.springbear.course.service.vod.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-23 18:09
 */
public class FormatUtils {
    /**
     * 文件重命名
     */
    public static String uuidFileName(MultipartFile file, boolean hasDirectory) {
        String dateTodayStr = "";
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        if (hasDirectory) {
            dateTodayStr = DateUtils.parseDateWithSlash() + "/";
        }
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        return dateTodayStr + UUID.randomUUID().toString().replaceAll("-", "") + fileSuffix;
    }
}
