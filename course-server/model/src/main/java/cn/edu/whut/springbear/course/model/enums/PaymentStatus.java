package cn.edu.whut.springbear.course.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Getter
public enum PaymentStatus {
    /**
     * 支付中
     */
    PAYING(1, "支付中"),
    /**
     * 已支付
     */
    PAID(2, "已支付"),
    /**
     * 已退款
     */
    REFUND(-1, "已退款");

    @EnumValue
    private final Integer code;
    private final String comment;

    PaymentStatus(Integer code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}
