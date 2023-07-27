package cn.edu.whut.springbear.course.service.vod.service.impl;


import cn.edu.whut.springbear.course.service.vod.service.TransferService;
import cn.edu.whut.springbear.course.service.vod.util.ConstantPropertiesUtils;
import cn.edu.whut.springbear.course.service.vod.util.DateUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-21 09:31
 */
@Service
public class TransferServiceImpl implements TransferService {
    @Override
    public String fileUpload(MultipartFile file) {
        String region = ConstantPropertiesUtils.REGION;
        String bucket = ConstantPropertiesUtils.BUCKET;

        // 文件重命名：eg: 2022/10/24/uuid.png
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String key = DateUtils.parseDateWithSlash() + "/" + UUID.randomUUID().toString().replaceAll("-", "") + fileSuffix;

        try {
            // 根据 id 和 key 生成验证对象
            COSCredentials credential = new BasicCOSCredentials(ConstantPropertiesUtils.SECRET_ID, ConstantPropertiesUtils.SECRET_KEY);
            // 客户端配置上传地域和上传所用协议
            ClientConfig clientConfig = new ClientConfig(new Region(region));
            clientConfig.setHttpProtocol(HttpProtocol.https);
            // 根据验证对象和客户端配置上传客户端对象
            COSClient cosClient = new COSClient(credential, clientConfig);

            // 文件对象上传请求
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, file.getInputStream(), objectMetadata);
            cosClient.putObject(putObjectRequest);
            // 返回上传成功的文件 url
            return "https://" + bucket + "." + "cos" + "." + region + ".myqcloud.com" + "/" + key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
