package cn.edu.whut.springbear.course.common.model.vo.vod;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
public class CourseQueryVo {
    @ApiModelProperty(value = "课程讲师 ID")
    private Long teacherId;

    @ApiModelProperty(value = "课程专业 ID")
    private Long subjectId;

    @ApiModelProperty(value = "课程专业父级 ID")
    private Long subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;
}

