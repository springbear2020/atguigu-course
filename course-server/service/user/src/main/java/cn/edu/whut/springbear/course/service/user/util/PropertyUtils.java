package cn.edu.whut.springbear.course.service.user.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-25 17:58
 */
@Component
public class PropertyUtils implements InitializingBean {
    @Value("${wechat.mpAppId}")
    private String appId;

    @Value("${wechat.mpAppSecret}")
    private String appSecret;

    public static String WECHAT_APP_ID;
    public static String WECHAT_APP_KEY;

    @Override
    public void afterPropertiesSet() {
        WECHAT_APP_ID = appId;
        WECHAT_APP_KEY = appSecret;
    }
}
