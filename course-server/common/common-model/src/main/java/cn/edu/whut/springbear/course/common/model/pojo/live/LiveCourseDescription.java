package cn.edu.whut.springbear.course.common.model.pojo.live;

import cn.edu.whut.springbear.course.common.model.pojo.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Spring-_-Bear
 * @datetime 2022-10-18 19:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "CourseDescription")
@TableName("live_course_description")
public class LiveCourseDescription extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程 ID")
    @TableField("live_course_id")
    private Long liveCourseId;

    @ApiModelProperty(value = "课程简介")
    @TableField("description")
    private String description;
}