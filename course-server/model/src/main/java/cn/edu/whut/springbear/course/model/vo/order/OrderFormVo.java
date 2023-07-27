package cn.edu.whut.springbear.course.model.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class OrderFormVo {
    @ApiModelProperty(value = "课程 id")
    private Long courseId;

    @ApiModelProperty(value = "优惠券 id")
    private Long couponId;

    @ApiModelProperty(value = "优惠券领取表 id")
    private Long couponUseId;
}

