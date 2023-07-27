package cn.edu.whut.springbear.course.service.vod.controller;

import cn.edu.whut.springbear.course.service.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-19 20:23
 */
@CrossOrigin
@RestController
@Api(tags = "用户管理接口")
@RequestMapping("admin/vod/user")
public class UserController {
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
}
