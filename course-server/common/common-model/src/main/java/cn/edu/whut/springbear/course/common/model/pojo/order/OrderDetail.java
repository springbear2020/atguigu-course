package cn.edu.whut.springbear.course.common.model.pojo.order;

import cn.edu.whut.springbear.course.common.model.pojo.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "OrderDetail")
@TableName("order_detail")
public class OrderDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程 id")
    @TableField("course_id")
    private Long courseId;

    @ApiModelProperty(value = "课程名称")
    @TableField("course_name")
    private String courseName;

    @ApiModelProperty(value = "课程封面图片路径")
    @TableField("cover")
    private String cover;

    @ApiModelProperty(value = "订单编号")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty(value = "用户 id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "原始金额")
    @TableField("origin_amount")
    private BigDecimal originAmount;

    @ApiModelProperty(value = "优惠劵减免金额")
    @TableField("coupon_reduce")
    private BigDecimal couponReduce;

    @ApiModelProperty(value = "最终金额")
    @TableField("final_amount")
    private BigDecimal finalAmount;

    @ApiModelProperty(value = "会话 id；当前会话 id；继承购物车中会话 id")
    @TableField("session_id")
    private String sessionId;
}