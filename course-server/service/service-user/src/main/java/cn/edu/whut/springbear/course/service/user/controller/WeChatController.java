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

    /**
     * 微信授权登录流程：
     * 1. 用户在公众号中发起请求，所请求的页面中将进行用户登录状态检查
     * 2. 若用户未登录，则将用户的请求定向至此 controller 中
     * 3. 在此 controller 中读取用户的请求，并按照微信后台的要求组织重定向地址发给微信后台，微信后天转发至用户
     * 4. 用户收到第三步中组织的地址，用户同意后微信后台会将用户信息封装后进行重定向，重定向地址为第三步中指定的成功回调地址，也即 @GetMapping("info") 控制器
     */
    @GetMapping("auth")
    public String authorize(@RequestParam("from") String from) {
        /*
         * 进行微信授权，引导用户打开如下格式地址以获取 code，后用 code 作为换取 access_token 的票据
         * https://open.weixin.qq.com/connect/oauth2/authorize?
         * appid=wx520c15f417810387&
         * redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60
         * &response_type=code&scope=snsapi_base&state=123#wechat_redirect
         * 其中 appid 是公众号的唯一标识、redirect_uri 是授权后重定向的回调链接地址、response_type 为响应码、state 为重定向时带上的参数
         * 如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE
         *
         */
        return "redirect:" + weChatService.wechatAuthorization(from);
    }

    /**
     * 如 @GetMapping("auth") 控制器中的步骤描述，当用户同意授权获取个人信息后，此 controller 中会收到微信后台组织的微信用户信息，
     * 读取微信用户信息，若未注册则进行用户注册，成功后返回至用户最初请求的地址
     *
     * @param code  用户同意授权后微信后台生成的字符串，用于校验获取用户信息
     * @param state 参数
     * @return 用户请求的地址
     */
    @GetMapping("info")
    public String wechatUserInfo(@RequestParam("code") String code, @RequestParam("state") String state) {
        /*
         * 解析用户授权后的 code 值以获取微信用户信息如 openId、头像地址等，
         * 后重定向回 state 指向的地址即用于用户最初请求的页面地址
         */
        return "redirect:" + weChatService.wechatUserRegister(code, state);
    }
}
