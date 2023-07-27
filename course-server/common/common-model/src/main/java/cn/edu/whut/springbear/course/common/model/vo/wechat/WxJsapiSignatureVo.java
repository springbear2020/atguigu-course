package cn.edu.whut.springbear.course.common.model.vo.wechat;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel("分享签名对象")
public class WxJsapiSignatureVo {
    private String appId;
    private String nonceStr;
    private long timestamp;
    private String url;
    private String signature;
    /**
     * 加密用户 id
     */
    private String userEedId;
}
