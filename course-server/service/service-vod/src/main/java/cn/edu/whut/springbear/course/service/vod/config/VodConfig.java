package cn.edu.whut.springbear.course.service.vod.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 21:26:26
 */
@Configuration
@MapperScan("cn.edu.whut.springbear.course.service.vod.mapper")
public class VodConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
