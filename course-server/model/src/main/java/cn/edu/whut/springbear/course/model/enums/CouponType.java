package cn.edu.whut.springbear.course.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Getter
public enum CouponType {
    /**
     * 注册
     */
    REGISTER(1, "注册"),
    /**
     * 推荐
     */
    RECOMMEND(2, "推荐购买");

    @EnumValue
    private final Integer code;
    private final String comment;

    CouponType(Integer code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}