package cn.edu.whut.springbear.course.service.activity.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-24 22:09
 */
@Configuration
@MapperScan("cn.edu.whut.springbear.course.service.activity.mapper")
public class ActivityConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
