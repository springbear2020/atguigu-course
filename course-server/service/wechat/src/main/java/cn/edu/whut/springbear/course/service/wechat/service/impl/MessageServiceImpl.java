package cn.edu.whut.springbear.course.service.wechat.service.impl;


import cn.edu.whut.springbear.course.api.client.course.CourseFeignClient;
import cn.edu.whut.springbear.course.common.model.pojo.vod.Course;
import cn.edu.whut.springbear.course.service.wechat.service.MessageService;
import cn.edu.whut.springbear.course.service.wechat.util.PropertyUtils;
import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * TODO 修改一系列链接配置信息
 *
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
                    response = this.text(fromUser, toUser, "感谢您关注 Spring-_-Bear！");
                } else if ("CLICK".equals(event) && "aboutUs".equals(eventKey)) {
                    response = this.text(fromUser, toUser, "个人博客链接：https://springbear.blog.csdn.net");
                } else {
                    response = this.text(fromUser, toUser, "功能开发中······");
                }
                break;
            default:
                response = this.text(fromUser, toUser, "功能开发中······");
        }
        return response;
    }

    @Override
    public boolean pushPayMessage() {
        String uid = "oZZ8j5yWCbynuwRPfjGYvcpp0Bzc";
        String templateId = "4bTafg2x6tI-19bXgSLN9TDYePVFXvzBZCj7p5uToZE";
        String url = "https://springbear2020.cn";
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(uid)
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
        templateMessage.addData(new WxMpTemplateData("remark", "感谢你购买课程，如有疑问，随时咨询！", "#272727"));

        // 微信公众号管理核心配置
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(PropertyUtils.WECHAT_APP_ID);
        wxMpDefaultConfig.setSecret(PropertyUtils.WECHAT_APP_KEY);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpDefaultConfig);
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
     * @param keyword  课程关键字
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
