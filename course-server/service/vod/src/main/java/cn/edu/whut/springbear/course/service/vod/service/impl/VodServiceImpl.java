package cn.edu.whut.springbear.course.service.vod.service.impl;

import cn.edu.whut.springbear.course.common.util.exception.CourseException;
import cn.edu.whut.springbear.course.service.vod.service.VodService;
import cn.edu.whut.springbear.course.service.vod.util.PropertiesUtils;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-23 16:55
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String videoUpload(MultipartFile file, String realPath) {
        // 保存上传文件到本地
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + fileSuffix;

        File video = new File(realPath + '/' + fileName);
        try {
            file.transferTo(video);
        } catch (IOException e) {
            throw new CourseException(30000, e.getMessage());
        }

        // 请求对象配置
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(video.toString());
        request.setProcedure("LongVideoPreset");
        request.setMediaName(fileName);
        try {
            // 视频文件上传
            VodUploadClient client = new VodUploadClient(PropertiesUtils.SECRET_ID, PropertiesUtils.SECRET_KEY);
            VodUploadResponse response = client.upload(PropertiesUtils.REGION, request);
            // 返回文件 id 保存到业务表，用于控制视频播放
            return response.getFileId();
        } catch (Exception e) {
            throw new CourseException(30000, e.getMessage());
        }
    }

    @Override
    public boolean deleteVideo(String videoId) {
        // 实例化一个认证对象
        Credential credential = new Credential(PropertiesUtils.SECRET_ID, PropertiesUtils.SECRET_KEY);
        // 实例化要请求产品的 client 对象,clientProfile 是可选的
        VodClient client = new VodClient(credential, PropertiesUtils.REGION);
        // 实例化一个请求对象，每个接口都会对应一个 request 对象
        DeleteMediaRequest req = new DeleteMediaRequest();
        req.setFileId(videoId);
        try {
            client.DeleteMedia(req);
            return true;
        } catch (TencentCloudSDKException e) {
            throw new CourseException(30000, e.getMessage());
        }
    }
}
