package cn.edu.whut.springbear.course.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Getter
public enum CouponStatus {
    /**
     * 未使用
     */
    NOT_USED(0, "未使用"),
    /**
     * 已使用
     */
    USED(1, "已使用");

    @EnumValue
    private final Integer code;
    private final String comment;

    CouponStatus(Integer code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}