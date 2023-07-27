package cn.edu.whut.springbear.course.service.user.controller;


import cn.edu.whut.springbear.course.common.model.pojo.user.UserInfo;
import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Spring-_-Bear
 * @since 2022-10-25
 */
@RestController
@Api(tags = "用户管理接口")
@RequestMapping("admin/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin");
        return Result.success("用户登录成功", map);
    }

    @ApiOperation("查询用户信息")
    @GetMapping("info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", "Spring-_-Bear");
        map.put("avatar", "https://whut.springbear2020.cn/static/img/WHUT.png");
        return Result.success("查询用户信息成功", map);
    }

    @ApiOperation("注销登录")
    @PostMapping("logout")
    public Result logout() {
        return Result.success("注销登录成功", null);
    }

    @ApiOperation("查询用户信息")
    @GetMapping("get/{id}")
    public UserInfo getUserInfo(
            @ApiParam(name = "uid", value = "用户 ID") @PathVariable Long id) {
        return userInfoService.getById(id);
    }
}

