package cn.edu.whut.springbear.course.service.user.controller;

import cn.edu.whut.springbear.course.service.user.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-27 17:47
 */
@Controller
@RequestMapping("/api/user/wechat")
public class WeChatController {
    @Autowired
    private WeChatService weChatService;

    @GetMapping("auth")
    public String authorize(@RequestParam("from") String from) {
        // 进行微信授权，授权成功后重定向
        return "redirect:" + weChatService.wechatAuthorization(from);
    }

    @GetMapping("info")
    public String wechatUserInfo(@RequestParam("code") String code, @RequestParam("state") String state) {
        // 读取授权后的用户信息，重定向到源起始请求页面
        return "redirect:" + weChatService.wechatUserRegister(code, state);
    }
}
