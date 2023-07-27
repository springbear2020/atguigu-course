package cn.edu.whut.springbear.course.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Getter
public enum PaymentType {
    /**
     * 支付宝
     */
    ALIPAY(1, "支付宝"),
    /**
     * 微信
     */
    WECHAT(2, "微信");

    @EnumValue
    private final Integer code;
    private final String comment;

    PaymentType(Integer code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}
