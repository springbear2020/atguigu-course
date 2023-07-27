package cn.edu.whut.springbear.course.service.user.service;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-27 18:14
 */
public interface WeChatService {
    /**
     * 微信授权，通过微信开发者 SDK 对用户请求进行授权
     *
     * @param from 从哪儿来
     * @return 授权后的重定向地址
     */
    String wechatAuthorization(String from);

    /**
     * 通过读取微信用户信息注册用户
     *
     * @param code  微信验证通过后生成的 code，用于获取微信用户信息
     * @param state 起始请求地址，即发起访问请求的页面 url -> 请求授权 -> 用户注册 -> 返回源页面
     * @return 源起始请求地址
     */
    String wechatUserRegister(String code, String state);
}
