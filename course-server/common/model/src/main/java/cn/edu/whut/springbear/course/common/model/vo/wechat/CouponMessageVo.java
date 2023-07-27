package cn.edu.whut.springbear.course.common.model.vo.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel(description = "优惠券消息")
public class CouponMessageVo {
    @ApiModelProperty(value = "优惠券 id")
    private Long couponId;

    @ApiModelProperty(value = "用户 id")
    private Long userId;
}