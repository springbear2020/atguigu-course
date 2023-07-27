package cn.edu.whut.springbear.course.service.wechat.controller;

import cn.edu.whut.springbear.course.service.wechat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-26 10:08
 */
@RestController
@RequestMapping("admin/wechat/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public String verifyToken(HttpServletRequest request) {
        // 微信验证通过
        return request.getParameter("echostr");
    }

    @PostMapping
    public String receiveMessage(HttpServletRequest request) {
        // 解析微信后台的响应数据
        Map<String, Object> response = messageService.parseResponse(request);
        // 构建给公众号客户端的响应消息
        return messageService.buildMessage(response);
    }
}
