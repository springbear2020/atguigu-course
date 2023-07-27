package cn.edu.whut.springbear.course.client.user;

import cn.edu.whut.springbear.course.common.model.pojo.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-25 11:13
 */
@FeignClient(value = "course-service-user")
public interface UserFeignClient {
    /**
     * 通过 uid 获取用户信息
     *
     * @param id 用户 ID
     * @return 用户信息
     */
    @GetMapping("feign/user/get/{id}")
    UserInfo getUserById(@PathVariable Long id);
}
