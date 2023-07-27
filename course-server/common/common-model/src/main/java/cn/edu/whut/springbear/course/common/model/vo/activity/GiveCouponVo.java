package cn.edu.whut.springbear.course.common.model.vo.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel(description = "领取优惠券 vo")
public class GiveCouponVo {
    @ApiModelProperty(value = "购物券类型")
    private Integer couponType;

    @ApiModelProperty(value = "优惠卷名字")
    private Long userId;
}