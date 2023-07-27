package cn.edu.whut.springbear.course.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Getter
public enum CouponRangeType {
    /**
     * 所有
     */
    ALL(1, "通用");

    @EnumValue
    private final Integer code;
    private final String comment;

    CouponRangeType(Integer code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}