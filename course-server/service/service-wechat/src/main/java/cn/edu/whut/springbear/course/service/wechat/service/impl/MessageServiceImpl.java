package cn.edu.whut.springbear.course.service.wechat.service.impl;


import cn.edu.whut.springbear.course.api.client.course.CourseFeignClient;
import cn.edu.whut.springbear.course.common.model.pojo.vod.Course;
import cn.edu.whut.springbear.course.service.wechat.service.MessageService;
import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private WxMpService wxMpService;
    @Value("${course.subscribeMsg}")
    private String subscribeMsg;
    @Value("${course.aboutMe}")
    private String aboutMe;
    @Value("${course.liveCourse}")
    private String liveCoursePath;
    @Value("${course.contactMe}")
    private String contactMe;

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> parseResponse(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 微信后台响应的 JSON 数据
            WxMpXmlMessage wxMpXmlMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            String response = JSONObject.toJSONString(wxMpXmlMessage);
            // 将请求参数转换为 key&val 形式
            map = JSONObject.parseObject(response, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public String buildMessage(Map<String, Object> param) {
        // 响应给用户的消息
        String responseMsg;

        // 根据请求的类型返回不同的数据
        String fromUser = (String) param.get("fromUser");
        String toUser = (String) param.get("toUser");
        String requestType = (String) param.get("msgType");
        switch (requestType) {
            case "text":
                // 用户在输入框中输入文本信息，默认处理为查询对应的关键词课程数据
                responseMsg = this.searchCourse(fromUser, toUser, (String) param.get("content"));
                break;
            case "event":
                // 用户触发事件，也即用户点击了微信公众号中的菜单或关注、取关公众号
                String event = (String) param.get("event");
                String eventKey = (String) param.get("eventKey");

                // 关注公众号，致欢迎辞
                if ("subscribe".equals(event)) {
                    responseMsg = this.textMessage(fromUser, toUser, subscribeMsg);
                } else if ("CLICK".equals(event)) {
                    if ("about".equals(eventKey)) {
                        // 关于我
                        responseMsg = this.textMessage(fromUser, toUser, aboutMe);
                    } else {
                        // 联系我
                        responseMsg = this.textMessage(fromUser, toUser, contactMe);
                    }
                } else {
                    responseMsg = this.textMessage(fromUser, toUser, "更多功能开发中······");
                }
                break;
            default:
                responseMsg = this.textMessage(fromUser, toUser, "更多功能开发中······");
        }
        return responseMsg;
    }

    /**
     * TODO 用户支付消息推送
     */
    @Override
    public boolean pushPayMessage() {
        String openId = "oZZ8j5yWCbynuwRPfjGYvcpp0Bzc";
        String templateId = "4bTafg2x6tI-19bXgSLN9TDYePVFXvzBZCj7p5uToZE";
        String url = "https://springbear2020.cn";
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                .url(url)
                .build();

        // 配置模板消息
        templateMessage.addData(new WxMpTemplateData("first", "亲爱的用户：您有一笔订单支付成功。", "#272727"));
        // 商品名称
        templateMessage.addData(new WxMpTemplateData("keyword1", "java 基础课程", "#272727"));
        // 订单编号
        templateMessage.addData(new WxMpTemplateData("keyword2", "2022-10-26", "#272727"));
        // 支付事件
        templateMessage.addData(new WxMpTemplateData("keyword3", "20221026171818", "#272727"));
        // 支付金额
        templateMessage.addData(new WxMpTemplateData("keyword4", "100", "#272727"));
        // 备注消息
        templateMessage.addData(new WxMpTemplateData("remark", "感谢你购买课程，如有疑问，请联系客服！", "#272727"));

        try {
            // 发送模板消息
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            return true;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 回复文本消息
     *
     * @param fromUser 来自用户名
     * @param toUser   目的用户名
     * @param msg      发送的消息
     * @return 格式化后的消息（xml 格式）
     */
    private String textMessage(String fromUser, String toUser, String msg) {
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
     * @param keyword  课程关键字
     * @return 格式化后的响应消息
     */
    private String searchCourse(String fromUser, String toUser, String keyword) {
        // TODO 远程调用：根据课程关键字查询课程
        List<Course> courseList = courseFeignClient.listCoursesByTitle(keyword);
        // 用户查询的课程信息不存在，返回普通文本消息
        if (courseList.isEmpty()) {
            return this.textMessage(fromUser, toUser, "未查询到相关课程，请重新输入关键字");
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
        response.append("<ToUserName><![CDATA[").append(toUser).append("]]></ToUserName>");
        response.append("<FromUserName><![CDATA[").append(fromUser).append("]]></FromUserName>");
        response.append("<CreateTime><![CDATA[").append(createTime).append("]]></CreateTime>");
        response.append("<MsgType><![CDATA[news]]></MsgType>");
        response.append("<ArticleCount><![CDATA[1]]></ArticleCount>");
        response.append("<Articles>");
        String articles = "<item>" +
                "<Title><![CDATA[" + course.getTitle() + "]]></Title>" +
                "<Description><![CDATA[" + course.getDescription() + "]]></Description>" +
                "<PicUrl><![CDATA[" + course.getCover() + "]]></PicUrl>" +
                "<Url><![CDATA[" + liveCoursePath + course.getId() + "]]></Url>" +
                "</item>";
        response.append(articles);
        response.append("</Articles>");
        response.append("</xml>");
        return response.toString();
    }
}
