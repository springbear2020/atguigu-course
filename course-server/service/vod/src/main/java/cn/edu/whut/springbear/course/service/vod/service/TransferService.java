package cn.edu.whut.springbear.course.service.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-21 09:22
 */
public interface TransferService {
    /**
     * 文件上传
     *
     * @param file 文件对象
     * @return 成功：文件访问 url；失败：null
     */
    String fileUpload(MultipartFile file);
}
