package cn.edu.whut.springbear.course.common.model.vo.vod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@ApiModel("课程基本信息")
public class CourseFormVo {
    @ApiModelProperty(value = "课程 ID")
    private Long id;

    @ApiModelProperty(value = "课程讲师 ID")
    private Long teacherId;

    @ApiModelProperty(value = "课程专业 ID")
    private Long subjectId;

    @ApiModelProperty(value = "课程专业父级 ID")
    private Long subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为 0 则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    private String description;
}
