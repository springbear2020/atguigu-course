package cn.edu.whut.springbear.course.service.wechat.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-26 10:22
 */
public interface MessageService {
    /**
     * 解析微信发送的 xml 格式数据
     *
     * @param request 请求头对象
     * @return key&val 参数数据
     */
    Map<String, Object> parseResponse(HttpServletRequest request);

    /**
     * 构建给公众号的响应消息
     *
     * @param map 请求参数 key-val
     * @return 响应给微信后台的 xml 格式数据
     */
    String buildMessage(Map<String, Object> map);

    /**
     * 推送用户课程订单购买成功消息
     *
     * @param tradeNum 订单流水号
     * @return true：推送成功
     */
    boolean pushPayMessage(String tradeNum);

    /**
     * 微信公众号签名验证（SHA1 算法加密验证）
     *
     * @param timestamp 微信后台传输的时间戳
     * @param nonce     微信后台传输的随机字符串参数
     * @return 加密后签名信息
     */
    String checkWechatSignature(String timestamp, String nonce);
}
