package cn.edu.whut.springbear.course.service.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-25 10:57
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cn.edu.whut.springbear.course")
@MapperScan("cn.edu.whut.springbear.course.service.user.mapper")
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
