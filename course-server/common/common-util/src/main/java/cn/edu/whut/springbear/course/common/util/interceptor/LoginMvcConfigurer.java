package cn.edu.whut.springbear.course.common.util.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 10:44
 */
@Configuration
public class LoginMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 用户登录拦截器拦截以 api 开头的请求，也即移动端 course-mobile 的所有请求，排除微信消息请求、微信用户验证请求
        registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/user/**", "/api/wechat/message");
        // 管理员登录拦截器拦截以 admin 开头的请求，也即后台管理系统 course-frontend 的所有请求
        // registry.addInterceptor(new AdminLoginInterceptor()).addPathPatterns("/admin/**");
    }
}
