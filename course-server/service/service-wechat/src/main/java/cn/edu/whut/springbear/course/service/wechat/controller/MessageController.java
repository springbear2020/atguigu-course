package cn.edu.whut.springbear.course.service.wechat.controller;

import cn.edu.whut.springbear.course.common.util.Result;
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

    /**
     * 微信服务号绑定服务器 token 信息验证
     *
     * @param request 微信后台的请求头
     * @return 验证通过：微信后台生成的随机字符串 echostr 的值
     */
    @GetMapping
    public String verifyToken(HttpServletRequest request) {
        /*
         * TODO 进行微信 token 验证，验证通过则返回微信后台传入的 echostr 参数值，
         *  需在微信公众号测试号中输入接口配置信息：URL 和 Token
         */
        return request.getParameter("echostr");
    }

    /**
     * 公众号中发出请求，请求提交至微信后台，此接口用于处理微信后台转发的用户请求
     *
     * @param request 微信后台封装过的用户请求
     * @return 响应给微信后台的数据，也即响应用户请求
     */
    @PostMapping
    public String sendMessage(HttpServletRequest request) {
        // 解析微信微信封装过的用户请求
        Map<String, Object> response = messageService.parseResponse(request);
        // 构建给公众号客户端的响应消息，响应用户的消息请求
        return messageService.buildMessage(response);
    }

    @GetMapping("push")
    public Result pushPayMessage() {
        return messageService.pushPayMessage() ? Result.success("推送订单消息成功", null) : Result.fail("推送订单消息失败", null);
    }
}
