package cn.edu.whut.springbear.course.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-24 16:32
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cn.edu.whut.springbear.course")
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
