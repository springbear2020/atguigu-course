package cn.edu.whut.springbear.course.service.live.config;

import cn.edu.whut.springbear.course.service.live.util.MTCloud;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-31 18:05
 */
@Configuration
public class MTCloudConfig {
    @Value("${mtcloud.openId}")
    private String openId;
    @Value("${mtcloud.openToken}")
    private String openToken;

    @Bean
    public MTCloud mtCloudClient() {
        return new MTCloud(openId, openToken);
    }
}
