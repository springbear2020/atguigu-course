package cn.edu.whut.springbear.course.common.util.interceptor;

import cn.edu.whut.springbear.course.common.util.alogrithm.JwtHelper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-30 10:43
 */
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        /*
         * 从请求头中获取用户登录后根据用户 uid 和 nickname 生成的 token，
         * 详见：cn.edu.whut.springbear.course.service.user.service.impl.WeChatServiceImpl.wechatUserRegister
         */
        String token = request.getHeader("token");
        if (token == null) {
            throw new RuntimeException("[user login interceptor] null token");
        }
        // 从 token 字符串中读取用户 ID signKey
        String signKey = "course-backend";
        Object userId = JwtHelper.get(token, "uid", signKey);
        AuthContextHolder.setUserId(Long.parseLong(String.valueOf(userId)));
        return true;
    }
}
