package cn.edu.whut.springbear.course.common.model.vo.order;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class OrderInfoQueryVo {
    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单交易编号（第三方支付用)")
    private String outTradeNo;

    @ApiModelProperty(value = "地区 id")
    private String province;

    @ApiModelProperty(value = "创建时间")
    private String createTimeBegin;

    @ApiModelProperty(value = "创建时间")
    private String createTimeEnd;
}

