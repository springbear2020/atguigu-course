package cn.edu.whut.springbear.course.service.user.controller.feign;

import cn.edu.whut.springbear.course.common.model.pojo.user.UserInfo;
import cn.edu.whut.springbear.course.service.user.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-29 07:07
 */
@RestController
@RequestMapping("feign/user")
public class UserFeignController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("查询用户信息")
    @GetMapping("get/{id}")
    public UserInfo getUserInfo(@ApiParam(name = "uid", value = "用户 ID") @PathVariable Long id) {
        return userInfoService.getById(id);
    }
}
