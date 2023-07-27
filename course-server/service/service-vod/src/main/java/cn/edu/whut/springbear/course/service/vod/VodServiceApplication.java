package cn.edu.whut.springbear.course.service.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("cn.edu.whut.springbear.course")
public class VodServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodServiceApplication.class, args);
    }
}
