package cn.edu.whut.springbear.course.service.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-23 16:55
 */
public interface VodService {
    /**
     * 上传视频到腾讯云
     *
     * @param file     视频文件对象
     * @param realPath 工程磁盘路径
     * @return 成功：视频 ID；失败：null
     */
    String videoUpload(MultipartFile file, String realPath);

    /**
     * 删除视频
     *
     * @param videoId 视频 ID
     * @return 成功：true；失败：false
     */
    boolean deleteVideo(String videoId);
}
