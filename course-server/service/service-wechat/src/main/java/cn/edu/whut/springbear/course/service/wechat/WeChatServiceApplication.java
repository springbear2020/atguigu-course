package cn.edu.whut.springbear.course.service.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-25 15:17
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cn.edu.whut.springbear.course")
@MapperScan("cn.edu.whut.springbear.course.service.wechat.mapper")
@EnableFeignClients(basePackages = "cn.edu.whut.springbear.course.api.client.course")
public class WeChatServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeChatServiceApplication.class);
    }
}
