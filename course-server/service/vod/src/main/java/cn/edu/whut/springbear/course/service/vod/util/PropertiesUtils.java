package cn.edu.whut.springbear.course.service.vod.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-21 09:19
 */
@Component
public class PropertiesUtils implements InitializingBean {
    @Value("${tencent.cloud.region}")
    private String region;
    @Value("${tencent.cloud.secretId}")
    private String secretId;
    @Value("${tencent.cloud.secretKey}")
    private String secretKey;
    @Value("${tencent.cloud.bucket}")
    private String bucket;

    public static String REGION;
    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String BUCKET;

    @Override
    public void afterPropertiesSet() {
        REGION = this.region;
        SECRET_ID = this.secretId;
        SECRET_KEY = this.secretKey;
        BUCKET = this.bucket;
    }
}
