package cn.edu.whut.springbear.course.service.live.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-31 16:53
 */
@Configuration
@MapperScan("cn.edu.whut.springbear.course.service.live.mapper")
public class LiveConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
