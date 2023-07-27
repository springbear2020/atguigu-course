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
     * @return 数据
     */
    Map<String, Object> parseResponse(HttpServletRequest request);

    /**
     * 构建给公众号的响应消息
     *
     * @param map 客户端请求信息 key-val
     * @return 响应给微信公众号的字符串消息
     */
    String buildMessage(Map<String, Object> map);
}
