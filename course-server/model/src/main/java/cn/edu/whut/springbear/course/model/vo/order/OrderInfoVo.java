package cn.edu.whut.springbear.course.model.vo.order;

import cn.edu.whut.springbear.course.model.pojo.order.OrderInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderInfoVo extends OrderInfo {
    @ApiModelProperty(value = "课程 id")
    private Long courseId;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "总时长：分钟")
    private Integer durationSum;

    @ApiModelProperty(value = "观看进度总时长：分钟")
    private Integer progressSum;

    @ApiModelProperty(value = "观看进度")
    private Integer progress;
}

