package cn.edu.whut.springbear.course.service.wechat.controller.api;

import cn.edu.whut.springbear.course.common.util.Result;
import cn.edu.whut.springbear.course.service.wechat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-31 09:11
 */
@RestController
@RequestMapping("api/wechat/message")
public class WechatApiController {
    @Autowired
    private MessageService messageService;

    /**
     * 微信公众平台接口配置信息验证：需在公众号中配置 URL 和 Token
     *
     * @param request 微信后台的请求头
     * @return 验证通过：微信后台生成的随机字符串 echostr 的值
     */
    @GetMapping
    public String verifyToken(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");
        // SHA1 算法加密验证，将加密后的结果与微信后天所传输的 signature 进行比较
        String encryptedStr = messageService.checkWechatSignature(request.getParameter("timestamp"), request.getParameter("nonce"));
        // 验证通过返回 echostr，否则返回空串
        return signature.equals(encryptedStr) ? echostr : "";
    }

    /**
     * 响应微信公众号中的消息请求
     *
     * @param request 微信后台封装过的用户请求
     * @return 响应给微信后台的 xml 格式数据
     */
    @PostMapping
    public String sendMessage(HttpServletRequest request) {
        // 解析微信封装过的用户请求，并将参数值封装为 Map
        Map<String, Object> response = messageService.parseResponse(request);
        // 构建给公众号客户端的 xml 格式响应消息，响应用户的消息请求
        return messageService.buildMessage(response);
    }

    @GetMapping("push/{tradeNum}")
    public Result pushPayMessage(@PathVariable String tradeNum) {
        return messageService.pushPayMessage(tradeNum) ? Result.success("推送订单消息成功", null) : Result.fail("推送订单消息失败", null);
    }
}
