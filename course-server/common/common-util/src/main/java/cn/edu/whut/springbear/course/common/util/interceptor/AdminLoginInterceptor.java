package cn.edu.whut.springbear.course.common.util.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 10:44
 */
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // TODO admin login interceptor
//        String token = request.getHeader("token");
//        if (token == null) {
//            throw new RuntimeException("[admin login interceptor] null token");
//        }
//        AuthContextHolder.setAdminId(0L);
        return true;
    }
}
