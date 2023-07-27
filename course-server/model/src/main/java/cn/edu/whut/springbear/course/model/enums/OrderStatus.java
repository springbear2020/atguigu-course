package cn.edu.whut.springbear.course.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Getter
public enum OrderStatus {
    /**
     * 未支付
     */
    UNPAID(0, "待支付"),
    /**
     * 已支付
     */
    PAID(1, "已支付");

    /**
     * 订单状态
     * -1-已取消
     * 0-待付款
     * 1-待发货
     * 2-待团长收货
     * 3-待用户收货，已完成
     */
    @EnumValue
    private final Integer code;
    private final String comment;

    OrderStatus(Integer code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}