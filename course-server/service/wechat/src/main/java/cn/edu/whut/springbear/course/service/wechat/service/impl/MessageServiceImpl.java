package cn.edu.whut.springbear.course.service.wechat.service.impl;

import cn.edu.whut.springbear.course.api.client.course.CourseFeignClient;
import cn.edu.whut.springbear.course.common.model.pojo.vod.Course;
import cn.edu.whut.springbear.course.service.wechat.service.MessageService;
import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-26 10:23
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private CourseFeignClient courseFeignClient;

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> parseResponse(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 微信后台响应的 JSON 数据
            WxMpXmlMessage wxMpXmlMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            String response = JSONObject.toJSONString(wxMpXmlMessage);
            map = JSONObject.parseObject(response, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public String buildMessage(Map<String, Object> param) {
        String response;
        String fromUser = (String) param.get("fromUser");
        String toUser = (String) param.get("toUser");
        // 根据请求的类型返回不同的数据
        switch ((String) param.get("msgType")) {
            case "text":
                response = this.searchCourse(fromUser, toUser, (String) param.get("content"));
                break;
            case "event":
                String event = (String) param.get("event");
                String eventKey = (String) param.get("eventKey");
                // 关注公众号，致欢迎辞
                if ("subscribe".equals(event)) {
                    response = this.text(fromUser, toUser, "感谢您关注 “硅谷课堂”，可以根据关键字搜索您想看的视频教程，如：JAVA 基础、SpringBoot、大数据等");
                } else if ("CLICK".equals(event) && "aboutUs".equals(eventKey)) {
                    response = this.text(fromUser, toUser, "尚硅谷专注 IT 教育培训，在北京、上海、武汉、深圳、西安等地区均有分校，致力于培养中高级程序员,是业内口碑闻名的 IT 教育培训机构。");
                } else {
                    response = this.text(fromUser, toUser, "Hello World");
                }
                break;
            default:
                response = this.text(fromUser, toUser, "Hello World");
        }
        return response;
    }

    /**
     * 回复文本消息
     *
     * @param fromUser 来自用户名
     * @param toUser   目的用户名
     * @param msg          发送的消息
     * @return 格式化后的消息（xml 格式）
     */
    private String text(String fromUser, String toUser, String msg) {
        // 时间戳：单位为秒
        long createTime = new Date().getTime() / 1000;
        return "<xml>" +
                "<ToUserName><![CDATA[" + fromUser + "]]></ToUserName>" +
                "<FromUserName><![CDATA[" + toUser + "]]></FromUserName>" +
                "<CreateTime><![CDATA[" + createTime + "]]></CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[" + msg + "]]></Content>" +
                "</xml>";
    }

    /**
     * 根据用户输入的关键字搜索课程
     *
     * @param fromUser 来自用户名
     * @param toUser   目的用户名
     * @param keyword      课程关键字
     * @return 格式化后的响应消息
     */
    private String searchCourse(String fromUser, String toUser, String keyword) {
        // 根据课程关键字查询课程
        List<Course> courseList = courseFeignClient.listCoursesByTitle(keyword);
        if (courseList.isEmpty()) {
            return this.text(fromUser, toUser, "请重新输入关键字，没有匹配到相关视频课程");
        }

        // 时间戳：单位为秒
        long createTime = new Date().getTime() / 1000;
        // 微信后台要求每次只能返回一条图文消息
        Random random = new Random();
        int num = random.nextInt(courseList.size());
        Course course = courseList.get(num);

        // 按照微信要求对返回的消息进行格式化（xml 格式）
        StringBuilder response = new StringBuilder();
        response.append("<xml>");
        response.append("<ToUserName><![CDATA[").append(fromUser).append("]]></ToUserName>");
        response.append("<FromUserName><![CDATA[").append(toUser).append("]]></FromUserName>");
        response.append("<CreateTime><![CDATA[").append(createTime).append("]]></CreateTime>");
        response.append("<MsgType><![CDATA[news]]></MsgType>");
        response.append("<ArticleCount><![CDATA[1]]></ArticleCount>");
        response.append("<Articles>");
        String articles = "<item>" +
                "<Title><![CDATA[" + course.getTitle() + "]]></Title>" +
                "<Description><![CDATA[" + course.getTitle() + "]]></Description>" +
                "<PicUrl><![CDATA[" + course.getCover() + "]]></PicUrl>" +
                "<Url><![CDATA[https://glkt.atguigu.cn/#/live/" + course.getId() + "]]></Url>" +
                "</item>";
        response.append(articles);
        response.append("</Articles>");
        response.append("</xml>");
        return response.toString();
    }
}
